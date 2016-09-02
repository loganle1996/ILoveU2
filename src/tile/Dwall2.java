package tile;

import Entity.Id;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by LoganLe on 9/1/16.
 */
public class Dwall2 extends  Tile {
    Image image = new Image("ground.jpeg");
    private TileCache tileCache = TileCache.getInstance();
    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(image, x, y, width, height);
    }

    public Dwall2(int x, int y, int width, int height, boolean solid, Id id, TileHandler tileHandler, String type) {
        super(x, y, width, height, solid, id, tileHandler, type);
        this.setHp(200);
    }

    @Override
    public void tick() {
        if(this.getHp() <= 0.0){
            this.die();
            Water water = (Water) tileCache.getTile("water");
            water.setX(this.getX());
            water.setY(this.getY());
            tileHandler.addTile(water);
        }
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
            }
            else{
                if(t.getId() == Id.wall || t.getId() == Id.fireTrap){
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
}
