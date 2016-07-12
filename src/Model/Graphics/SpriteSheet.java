/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

/**
 *
 * @author owne
 */
public class SpriteSheet {
    private Image sheet;
    WritableImage cropedImage;
    PixelReader reader;
    
    //Constructor
    public SpriteSheet(String path){
        try {
            sheet = new Image(path);
            reader = sheet.getPixelReader();
        } catch (Exception e) {
            System.out.println("Cannot get the page");
        }
    }
    public Image getSprite(int x, int y){
        cropedImage = new WritableImage(reader,x*32-32,y*32-32,32,32);
        return cropedImage;
    }
    
}
