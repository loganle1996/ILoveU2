/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile;

import Entity.Id;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author khoinguyen
 */
public class InvisibleWall extends Tile {

    //Contructor
    
    public InvisibleWall(int x, int y, int width, int height, boolean solid, Id id, TileHandler tileHandler, String type) {
        super(x, y, width, height, solid, id, tileHandler, type);
        
    }

    @Override
    public void render(GraphicsContext gc) {
    }

    @Override
    public void tick(long currentTime) {
        
    }
    
}