/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bullet;

import Entity.Entity;
import Entity.Id;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tile.Tile;

/**
 *
 * @author khoinguyen
 */
public class IceBall extends Bullet{

    public IceBall(BulletHandler bulletHandler, bulletType id) {
        super(bulletHandler, id);
    }


    @Override
    public void renderBullet(GraphicsContext gc, Entity en, Image imageLeft, Image imageRight) {
        if(en.getFacing() == 0){
            gc.drawImage(imageLeft, this.getX(),this.getY(),this.getWidth() , this.getHeight());
        }
        else if(en.facing == 1){
            gc.drawImage(imageRight, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        }
    }

    @Override
    public void tick(long currenttime) {
        this.setX(this.getVelX()+this.getX());
        this.setY(this.getY()+ this.getVelY());
        //tileCollidingCheck
        tileCollidingChecking();
        //entitycollidingcheck
        entityCollidingChecking();
    }
    public void tileCollidingChecking(){
        for(Tile t: tileHandler.getTile())  {
            if(t.solid == false){
            }
            else if(t.getId() == Id.wall){
                if(this.intersectsTile(t)){
                    this.disappear();
                }    
            }  
        }  
    }
    public void entityCollidingChecking(){
        for(Entity en : entityHandler.getEntity()){
            if(en.getId() == Id.Goomba){
                if(this.intersectsEntity(en)){
                    en.setFreeze(true);
                    this.disappear();
                    
                }
            }
        }
    }
}

