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
public class FireBall extends Bullet{
    public FireBall(double x, double y,BulletHandler bulletHandler,Boolean flyingLeft) {
        super(x, y,bulletHandler,flyingLeft);
        this.setId(bulletType.fireBall);
    }

    @Override
    public void renderBullet(GraphicsContext gc, Entity en,Image imageLeft, Image imageRight) {
        if(en.getFacing() == 0){
            gc.drawImage(imageLeft, this.getX(),this.getY(),this.getWidth() , this.getHeight());
        }
        else if(en.facing == 1){
            gc.drawImage(imageRight, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        }
    }

    @Override
    public void tick() {
        this.setX(this.getX() + this.getVelX());
        this.setY(this.getY() + this.getVelY());
        //check if fireball collides tiles
        tileCollidingChecking();
        //check if this collides entities
        entityCollidingChecking();
        
    }
    public void tileCollidingChecking(){
        for(Tile t: tileHandler.getTile()){
                if(t.getId() == Id.wall){
                    if(this.intersectsObject(t) && t.getType().equals("wall3")){
                        if(t.getType().equalsIgnoreCase("wall3")){
                            t.setHp(t.getHp()-200);
                        }
                        this.disappear();
                    }    
                }  
        }
    }
    public void entityCollidingChecking(){
        for(Entity en : entityHandler.getEntity()){
            if(en.getId() != Id.player){
                if(this.intersectsEntity(en)){
                    en.setHp(en.getHp()-200);
                    this.disappear();
                }
            }
        }
    }
    
}
