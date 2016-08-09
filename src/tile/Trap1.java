/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile;

import Entity.Id;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author khoinguyen
 */
public class Trap1 extends Tile {
    private String trapDirection;
    Image imageLeft = new Image("fireTrapLeft.png");
    Image imageRight = new Image("fireTrapRight.png");
    Image imageUp = new Image("fireTrapUp.png");
    Image imageDown = new Image("fireTrapDown.png");
    
    //Constructor
    public Trap1( int x, int y, int width, int height, boolean solid, Id id, TileHandler tileHandler, String type) {
        super(x, y, width, height, solid, id, tileHandler, type);
    }
    
    
    @Override
    public void render(GraphicsContext gc) {
        if(trapDirection.equalsIgnoreCase("left"))
           gc.drawImage(imageLeft, x, y,width,height);
        else if(trapDirection.equalsIgnoreCase("right"))
            gc.drawImage(imageRight, x, y,width,height);
        else if(trapDirection.equalsIgnoreCase("Up"))
            gc.drawImage(imageUp, x, y,width,height);
        else if(trapDirection.equalsIgnoreCase("Down"))
            gc.drawImage(imageDown, x, y,width,height);
    }

    @Override
    public void tick() {
        
    }

    public String getTrapDirection() {
        return trapDirection;
    }

    public void setTrapDirection(String trapDirection) {
        this.trapDirection = trapDirection;
    }
    
}
