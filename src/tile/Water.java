package tile;

import Entity.Id;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.LinkedList;

/**
 * Created by LoganLe on 9/1/16.
 */
public class Water extends Tile {
    private boolean stickWater = false;
    private LinkedList<Tile> copiedTile;
    Image imageWater = new Image("water.jpeg");
    public Water(int x, int y, int width, int height, boolean solid, Id id, TileHandler tileHandler, String type) {
        super(x, y, width, height, solid, id, tileHandler, type);
    }
    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(imageWater, x, y, width, height);
    }

    @Override
    public void tick(long currentTime) {
        x += this.getVelX();
        y += this.getVelY();
        if (velY < 10) {
            velY += 1;
        }
        tileCollidingChecking();
    }
    public void tileCollidingChecking(){
        copiedTile = new LinkedList<>(tileHandler.getTile());
        for(Tile t: copiedTile){
            if(t.solid == false){
                if(t.getType().equalsIgnoreCase("water")&& t != this){
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
            else{
                if(t.getType().equalsIgnoreCase("fireTrap")){
                    if(this.intersectsTile(t)){
                        t.die();
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
