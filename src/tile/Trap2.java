package tile;

import Entity.Id;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.LinkedList;

/**
 * Created by LoganLe on 9/2/16.
 */
public class Trap2 extends Tile {
    private long lastTime = 0;
    private LinkedList<Tile> copiedTile;
    private TileCache tileCache = TileCache.getInstance();
    private Smoke smoke;
    Image fireTrap = new Image("fire.gif");

    public Trap2(int x, int y, int width, int height, boolean solid, Id id, TileHandler tileHandler, String type) {
        super(x, y, width, height, solid, id, tileHandler, type);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(fireTrap, x, y,width,height);
    }

    @Override
    public void tick(long currentTime) {
        if(lastTime == 0){
            lastTime = currentTime;
        }
        else{
            if(((currentTime - lastTime) / 1000000000.0) > 3){
                smoke = (Smoke) tileCache.getTile("smoke");
                smoke.setX(this.getX());
                smoke.setY(this.getY());
                tileHandler.addTile(smoke);
                lastTime = currentTime;
            }
        }
        tileCollidingChecking();

    }
    public void tileCollidingChecking(){
        copiedTile = new LinkedList<>(tileHandler.getTile());
        for(Tile t: copiedTile){
            if(t.solid == false){
            }
            else{
                if(t.getId() == Id.wall){
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
}
