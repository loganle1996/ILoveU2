/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author owne
 */
public class Wall3 extends Tile{
    Image image = new Image("bricks_3.png");
    public Wall3(int x, int y, int width, int height, boolean solid, Id id, GameModel gameModel, String type) {
        super(x, y, width, height, solid, id, gameModel, type);
    }
    public Wall3(){
        //GameView.gameModel.addTile(this);
    }

    @Override
    public void render(GraphicsContext gc,int x , int y) {
         this.setX(x);
          this.setY(y);
        gc.drawImage(image, x, y, width, height);
    }

    @Override
    public void tick() {
        
    }
    
}
