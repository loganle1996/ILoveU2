/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile;

import Entity.Id;
import MVCpattern.GameModel;
import MVCpattern.GameView;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author owne
 */
public class Wall3 extends Tile{
    Image image = new Image("bricks_3.png");
    public Wall3(int x, int y, int width, int height, boolean solid, Id id, TileHandler tileHandler, String type) {
        super(x, y, width, height, solid, id, tileHandler, type);
    }
    public Wall3(){
        //GameView.gameModel.addTile(this);
    }

    public Wall3(boolean solid, Id id) {
        super(solid, id);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(image, x, y, width, height);
    }

    @Override
    public void tick() {
        
    }
    
}
