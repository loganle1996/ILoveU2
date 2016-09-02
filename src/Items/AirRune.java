/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import Entity.Entity;
import Entity.Id;
import Sound.SoundHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;
import tile.Tile;

/**
 *
 * @author LoganLe
 */
public class AirRune extends Item {

    final int quickcastDuration = 10;
    int duration = quickcastDuration;


    public AirRune(ItemHandler itemHandler) {
        super(itemHandler);
        this.image = new Image("airScroll.png");
        this.itemType = "airRune";
    }
    public AirRune(ItemHandler itemHandler,double x,double y) {
        super(itemHandler);
        this.image = new Image("airScroll.png");
        this.itemType = "airRune";
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
                if(t.getId() == Id.wall){
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

        duration = quickcastDuration;

        for(Entity en : entityHandler.getEntity()){
            if(this.intersectsEntity(en)){
                if(en.getId() == Id.player){

                    en.setShootDelay(0.25);

                    Timeline timer = new Timeline();
                    timer.setCycleCount(Timeline.INDEFINITE);
                    duration = quickcastDuration;
                    timer.getKeyFrames().add(
                            new KeyFrame(Duration.seconds(1), event -> {
                                duration--;
                                if (duration == 0)
                                {
                                    en.setShootDelay(1);
                                }
                            })
                    );
                    timer.play();


                    SoundHandler.getInstance().playSound("pickup");
                    this.disappear();
                }
            }

        }
    }
    public void bulletCollidingCheck(){

    }
}
