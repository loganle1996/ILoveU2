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
public class WoodBridge extends Tile{
    private boolean isMovingLeft = true,isMovingRight = false;
    Image image = new Image("wood.png");
    
    //Constructor
    
    public WoodBridge(boolean isMovingLeft, int x, int y, int width, int height, boolean solid, Id id, TileHandler tileHandler, String type) {
        super(x, y, width,height,solid, id, tileHandler, type);
        this.isMovingLeft = isMovingLeft;
    }

    public boolean isIsMovingLeft() {
        return isMovingLeft;
    }

    public void setIsMovingLeft(boolean isMovingLeft) {
        this.isMovingLeft = isMovingLeft;
    }

    public boolean isIsMovingRight() {
        return isMovingRight;
    }

    public void setIsMovingRight(boolean isMovingRight) {
        this.isMovingRight = isMovingRight;
    }
    
    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(image, x, y,width,height);
    }

    @Override
    public void tick() {
        x+= velX;
        y+=velY;
        if(this.isMovingLeft == true){
            this.setVelX(-2);
        }
        else if(this.isMovingRight == true){
            this.setVelX(2);
        }
        tileCollidingChecking();
    }
    public void tileCollidingChecking(){
        for(Tile t: tileHandler.getTile()){
            if(t.getId() == Id.wall){
                if(!t.getType().equalsIgnoreCase("woodBridge")){
                    if(this.intersectsRightTile(t)){
                        this.setIsMovingRight(true);
                        this.setIsMovingLeft(false);
                    }
                    if(this.intersectsLeftTile(t)){
                        this.setIsMovingLeft(true);
                        this.setIsMovingRight(false);
                    }
                }
                
            }
        }
}
    
}
