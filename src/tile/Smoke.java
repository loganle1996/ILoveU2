package tile;

import Entity.Id;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.LinkedList;

/**
 * Created by LoganLe on 9/4/16.
 */
public class Smoke extends Tile {
    private long lastTime = 0;
    private LinkedList<Tile> copiedTile;
    Image smokeImage = new Image("smoke3.gif");

    public Smoke(int x, int y, int width, int height, boolean solid, Id id, TileHandler tileHandler, String type) {
        super(x, y, width, height, solid, id, tileHandler, type);
        this.setVelY(-1);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(smokeImage,x,y,width,height);
    }

    @Override
    public void tick(long currentTime) {
        y += velY;
//        tileCollidingChecking();
        if(lastTime == 0){
            lastTime = currentTime;
        }
        else{
            if(((currentTime - lastTime)/ 1000000000.0)> 1.5){
                this.die();
                lastTime = currentTime;
            }
        }
    }
//    public void tileCollidingChecking(){
//        copiedTile = new LinkedList<>(tileHandler.getTile());
//        for(Tile t: copiedTile){
//            if(t.solid == false){
//            }
//            else{
//                if(t.getId() == Id.wall){
//                    if(!t.getType().equalsIgnoreCase("fireTrap")){
//                        if(this.intersectsTile(t)){
//                            this.die();
//                        }
//                    }
//                }
//            }
//        }
//    }
}
