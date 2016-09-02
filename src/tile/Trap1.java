/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile;

import Entity.Entity;
import Entity.Id;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.LinkedList;

/**
 *
 * @author khoinguyen
 */
public class Trap1 extends Tile {
    private LinkedList<Tile> copiedTile;
    private Rectangle2D bigRectangle2D;
    private boolean fallable = false;
    Image imageLeft = new Image("fireTrapLeft.png");
    Image imageRight = new Image("fireTrapRight.png");
    Image imageUp = new Image("spikeTrapUp.png");
    Image imageDown = new Image("spikeTrapDown.png");

    //Constructor
    public Trap1( int x, int y, int width, int height, boolean solid, Id id, TileHandler tileHandler, String type) {
        super(x, y, width, height, solid, id, tileHandler, type);
    }


    @Override
    public void render(GraphicsContext gc) {
        if(direction.equalsIgnoreCase("left"))
           gc.drawImage(imageLeft, x, y,width,height);
        else if(direction.equalsIgnoreCase("right"))
            gc.drawImage(imageRight, x, y,width,height);
        else if(direction.equalsIgnoreCase("Up"))
            gc.drawImage(imageUp, x, y,width,height);
        else if(direction.equalsIgnoreCase("Down"))
            gc.drawImage(imageDown, x, y,width,height);
    }

    @Override
    public void tick() {
        watchingAround();
        if(this.getDirection().equalsIgnoreCase("Down")){
            for(Entity entity: entityHandler.getEntity()){
                if(entity.getId() == Id.player){
                    if(this.foundPlayer(entity)){
                        this.fallable = true;
                    }
                }
            }
            if(this.fallable == true){
                y += this.getVelY();
                if (velY < 15) {
                    velY += 2;
                }
            }
            tileCollidingChecking();
        }

    }
    public void tileCollidingChecking(){
        copiedTile = new LinkedList<>(tileHandler.getTile());
        for(Tile t: copiedTile){
            if(t.solid == false){
            }
            else{
                if(t.getId() == Id.wall){
                    if(t.getType().equalsIgnoreCase("wall2")){
                        if(this.intersectsTile(t)){
                            this.die();
                        }
                    }
                    if(this.intersectsTopTile(t)){
                        y = t.getY() - height;
                    }

                    if(this.intersectsBottomTile(t)){
                        y = t.getY() + height;
                    }
                    if(this.intersectsRightTile(t)){
                        x = t.getX()+t.width;
                        this.setVelX(0);

                    }
                    if(this.intersectsLeftTile(t)){
                        x = t.getX()-t.width;
                    }
                }
            }
        }
    }
    public void watchingAround(){
        bigRectangle2D =  new Rectangle2D(this.getX()-20, this.getY()+140, 45, 140);
    }
    public boolean foundPlayer(Entity en){
        return bigRectangle2D.intersects(en.getBoundary());
    }
    public void setdirection(String direction) {
        this.direction = direction;
    }

}
