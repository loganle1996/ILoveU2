/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicsforAnimation;

import javafx.scene.image.Image;

/**
 *
 * @author owne
 */
public class Sprite {
    private SpriteSheet sheet;
    
    private Image image;
    public  Sprite(SpriteSheet sheet, int x, int y){
        image = sheet.getSprite(x, y);
    }
    public Image getImage(){
        return image;
    }
    
}
