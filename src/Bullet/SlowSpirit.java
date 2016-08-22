/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bullet;

import Entity.Entity;
import Entity.Id;
import Target.AiHouse;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tile.Tile;

/**
 *
 * @author apple
 */
public class SlowSpirit extends Bullet
{
    boolean outOfEffect = false;
    public SlowSpirit(double x, double y,BulletHandler bulletHandler,Boolean flyingLeft) {
        super(x, y,bulletHandler,flyingLeft);
        this.setId(bulletType.SlowSpirit);
    }
    
    public SlowSpirit(BulletHandler bulletHandler, bulletType id) 
    {
        super(bulletHandler, id);
    }

    @Override
    public void renderBullet(GraphicsContext gc, Entity en, Image imageLeft, Image imageRight) 
    {
       if(en.getFacing() == 0){
            gc.drawImage(imageLeft, this.getX(),this.getY(),this.getWidth() , this.getHeight());
        }
        else if(en.facing == 1){
            gc.drawImage(imageRight, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        }
    }

    @Override
    public void tick(long currenttime) 
    {
        this.setX(this.getX() + this.getVelX());
        this.setY(this.getY() + this.getVelY());
        //check if fireball collides tiles
        tileCollidingChecking();
        //check if this collides entities
        entityCollidingChecking();
        
        if (lastTime == 0){
            lastTime = currenttime;
        }
        else {
            if (((currenttime - lastTime) / 1000000000.0) > 3){
                lastTime = currenttime;
                outOfEffect = true;
            }
        }
    }
    
    public void tileCollidingChecking(){
        for(Tile t: tileHandler.getTile())  {
            if(t.solid == false){
            }
            else if(t.getId() == Id.wall){
                if(this.intersectsTile(t)){
                    if(t.getType().equalsIgnoreCase("wall3")){
                        t.setHp(t.getHp()-200);
                    }
                }    
            }  
        }
    }
    public void entityCollidingChecking(){
        for(Entity en : entityHandler.getEntity()){
            if(en.getId() == Id.Goomba)
            {
                if(this.intersectsEntity(en))
                {
                    if((en.facing == 1 || en.facing == 0) && outOfEffect == false)
                    {
                        en.setHp(en.getHp()-1);
                        en.setIsOnSlow(true);
                        System.out.println("Intersect");
                    }
                }
                else
                {
                    en.setIsOnSlow(false);
                    System.out.println("Not interesect");
                }
                
                if (outOfEffect == true)
                {
                    en.setIsOnSlow(false);
                    this.disappear();
                }
            }
        }
    }
}
