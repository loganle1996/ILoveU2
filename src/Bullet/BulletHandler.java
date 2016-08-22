/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bullet;

import Entity.Entity;
import Entity.EntityHandler;
import Entity.Id;
import java.util.LinkedList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author khoinguyen
 */
public class BulletHandler implements Cloneable{
    public LinkedList<Bullet> bullets = new LinkedList<Bullet>();
    public LinkedList<Bullet> copiedBullets;
    public EntityHandler entityHandler = EntityHandler.getInstance();
    private static BulletHandler bulletHandler = new BulletHandler();
    Image imageLeft = new Image("fireball.png");
    Image imageRight = new Image("fireball.png");
    Image iceBallLeft = new Image("leftIceBall.png");
    Image iceBallRight = new Image("rightIceBall.png");
    Image slowSpiriteLeft = new Image("Crystall.png");
    Image slowSpiriteRight = new Image("Crystall.png");
    private BulletHandler(){
        
    }
    @Override
    public Object clone(){
    Object clone = null;
      
      try {
         clone = super.clone();
         
      } catch (CloneNotSupportedException e) {
         e.printStackTrace();
      }
      
      return clone;
    }
    //Add and remove bullets
    public void addBullets(Bullet bullet){
        bullets.add(bullet);
    }
    public void removeBullets(Bullet bullet){
        bullets.remove(bullet);
    }
    //Accessors and mutators

    public LinkedList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(LinkedList<Bullet> bullets) {
        this.bullets = bullets;
    }
    
    //tick
    public void tickBullets(long currenttime){
        copiedBullets = new LinkedList<Bullet>(bullets);    
            for(Bullet bullet : copiedBullets){
                bullet.tick(currenttime);
            }
    }
    //render
    public void renderBullets(GraphicsContext gc){
        copiedBullets = new LinkedList<Bullet>(bullets);
        for(Bullet bullet: copiedBullets){
            for(Entity en : entityHandler.getEntity()){
                if(en.getId()== Id.player){
                    if(bullet.getId() == bulletType.fireBall){                        
                        bullet.renderBullet(gc, en,imageLeft,imageRight);
                    }
                    else if(bullet.getId() == bulletType.snowBall){
                        bullet.renderBullet(gc, en, iceBallLeft, iceBallRight);
                    }
                    else if(bullet.getId() == bulletType.SlowSpirit)
                    {
                        bullet.renderBullet(gc, en, slowSpiriteLeft, slowSpiriteRight);
                    }
                }
            }
        }
    }
    //edit instance
    public void editInstance(BulletHandler bulletHandler){
        this.bulletHandler = bulletHandler;
    }
    public static BulletHandler getInstance(){
        return bulletHandler;
    }
    
}
