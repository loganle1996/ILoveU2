package tile;

import Entity.Id;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by LoganLe on 9/1/16.
 */
public class Water extends Tile {
    Image imageWater = new Image("water.jpeg");
    public Water(int x, int y, int width, int height, boolean solid, Id id, TileHandler tileHandler, String type) {
        super(x, y, width, height, solid, id, tileHandler, type);
    }
    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(imageWater, x, y, width, height);
    }

    @Override
    public void tick() {
        x += this.getVelX();
        y += this.getVelY();
        if (velY < 10) {
            velY += 1;
        }
        tileCollidingChecking();
    }
    public void tileCollidingChecking(){
        for(Tile t: tileHandler.getTile()){
            if(t.solid == false){
                if(t.getType().equalsIgnoreCase("water")){
                    if(this.intersectsTopTile(t)){
                        y = t.getY() - height;
                    }


                    if(this.intersectsBottomTile(t)){
                        y = t.getY() + height;
                        if(t.getId() == Id.fireTrap){

                        }
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
                if(this.intersectsTopTile(t)){
                    y = t.getY() - height;
                }


                if(this.intersectsBottomTile(t)){
                    y = t.getY() + height;
                    if(t.getId() == Id.fireTrap){

                    }
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
