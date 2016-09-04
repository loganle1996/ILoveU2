/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile;

import Entity.Entity;
import static Entity.Entity.entityHandler;
import Entity.Id;
import Map.GameMap;
import java.util.LinkedList;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author apple
 */
public class Portal extends Tile
{
    private GameMap gameMap = GameMap.getInstance();
    Image image = new Image("door.png");
    LinkedList<Entity> copEntities;

    Portal(int x, int y, int width, int height, boolean solid, Id id,TileHandler tileHandler,String type) {
        super(x, y, width, height, solid, id,tileHandler,type);
    }

    public Portal() {
        //GameView.gameModel.addTile(this);
    }

    public Portal(boolean solid, Id id) {
        super(solid, id);
    }
    

    @Override
    public void render(GraphicsContext gc) {
          gc.drawImage(image,x, y, width, height);
    }
    public Rectangle2D getBoundary(){
        return new Rectangle2D(getX(), getY(), width, height);
    }
    public boolean intersectsEntity(Entity en){
        return en.getBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsTopEntity(Entity en){
        return en.getTopBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsLeftEntity(Entity en){
        return en.getLeftBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsRightEntity(Entity en){
        return en.getRightBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsBottomEntity(Entity en){
        return en.getBottomBoundary().intersects(this.getBoundary());
    }
    @Override
    public void tick(long currentTime)
    {
        playerCollidingCheck();
    }
    public void playerCollidingCheck(){
        copEntities = new LinkedList<Entity>(entityHandler.getEntity());
        for(Entity entity : copEntities){
            if(entity.getId() == Id.player){
                if(this.intersectsEntity(entity)){
                    gameMap.changeMap();
                }
            }
        }
    }
}
