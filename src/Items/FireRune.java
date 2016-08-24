/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import Entity.Entity;
import Entity.Id;
import Sound.SoundHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tile.Tile;

/**
 *
 * @author LoganLe
 */
public class FireRune extends Item{

    public FireRune(ItemHandler itemHandler) {
        super(itemHandler);
        this.image = new Image("fireScroll.png");
        this.itemType = "fireRune";
    }
    public FireRune(ItemHandler itemHandler, double x, double y) {
        super(itemHandler);
        this.image = new Image("fireScroll.png");
        this.itemType = "bulletBox";
        this.x = x;
        this.y = y;
        this.setVelY(this.getVelY() + this.gravity);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(image, getX(), getY(),getWidth(),getHeight());
    }

    @Override
    public void tick(long currentime) {
        this.setX(this.getX() + this.getVelX());
        this.setY(this.getY() + this.getVelY());
        tileCollidingCheck();
        entitiesCollidingCheck();
    }
    public void tileCollidingCheck(){
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
                    }
                    if(this.intersectsRightTile(t)){
                        x = t.getX()+t.width;
                    }
                    if(this.intersectsLeftTile(t)){
                        x = t.getX()-t.width;
                    }
                }
            }
        }
    }
    public void entitiesCollidingCheck(){
        for(Entity en : entityHandler.getEntity()){
            if(this.intersectsEntity(en)){
                if(en.getId() == Id.player){
                    en.setNumberFireball(en.getNumberFireball() + 10);
                    SoundHandler.getInstance().playSound("pickup");
                    this.disappear();
                }
            }

        }
    }
    public void bulletCollidingCheck(){

    }
}
