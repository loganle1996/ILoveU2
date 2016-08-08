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
public class BulletHandler {
    public LinkedList<Bullet> bullets = new LinkedList<Bullet>();
    public LinkedList<Bullet> copiedBullets;
    public EntityHandler entityHandler = EntityHandler.getInstance();
    private static BulletHandler bulletHandler = new BulletHandler();
    
    private BulletHandler(){
        
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
    public void tickBullets(){
        copiedBullets = new LinkedList<Bullet>(bullets);
        for(Bullet bullet : copiedBullets){
            bullet.tick();
        }
    }
    //render
    public void renderBullets(GraphicsContext gc, Image imageLeft, Image imageRight){
        copiedBullets = new LinkedList<Bullet>(bullets);
        for(Bullet bullet: copiedBullets){
            for(Entity en : entityHandler.getEntity()){
                if(en.getId()== Id.player){
                    bullet.renderBullet(gc, en,imageLeft,imageRight);
                }
            }
        }
    }
    public static BulletHandler getInstance(){
        return bulletHandler;
    }
}
