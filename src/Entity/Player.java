/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Bullet.Bullet;
import Bullet.BulletCache;
import Bullet.FireBall;
import Bullet.IceBall;
import Bullet.bulletType;
import GraphicsforAnimation.Sprite;
import javafx.scene.canvas.GraphicsContext;
import tile.Tile;

/**
 *
 * @author owne
 */
public class Player extends Entity {
    private static Player player = new Player(40, 40, true, Id.player, entityHandler);
    private BulletCache bulletCache = BulletCache.getInstance();
    private Player(int width, int height, boolean solid, Id id, EntityHandler entityHandler) {
        super(width, height, solid, id, entityHandler);
    }
    
    public static Player getInstance(){
        return player;
    }

    @Override
    public void render(GraphicsContext g,Sprite[] playerSprite ) {
         if(this.facing == 0 ){
             g.drawImage(playerSprite[frame+ 3].getImage(), x, y, width, height);
         }
         else if(this.facing == 1){
             g.drawImage(playerSprite[frame].getImage(), x, y, width, height);
         }
    }
    @Override
    public void shootFireBall(GraphicsContext gc) {
        if(facing == 0){
            if(numberFireball > 0){  
//              bulletHandler.getBullets().add(new FireBall(this.getX(), this.getY(), bulletHandler,true));
                FireBall fireBall = (FireBall) bulletCache.getBullet(bulletType.fireBall);
                fireBall.setX(this.getX());
                fireBall.setY(this.getY());
                fireBall.setFlyingLeft(true);
                bulletHandler.getBullets().add(fireBall);   
                numberFireball -= 1;
            }
        }
        else if(facing == 1){
            if(numberFireball > 0){
//                bulletHandler.getBullets().add(new FireBall(this.getX() + this.getWidth()/4, this.getY() + 5, bulletHandler,false));
                FireBall fireBall = (FireBall) bulletCache.getBullet(bulletType.fireBall);
                fireBall.setX(this.getX()+this.getWidth()/4);
                fireBall.setY(this.getY());
                fireBall.setFlyingLeft(false);
                bulletHandler.getBullets().add(fireBall);
                numberFireball -= 1;
            }
        }
        
        for(Bullet bullet : bulletHandler.getBullets()){
            if(bullet.getId() == bulletType.fireBall){
                if(bullet.isFlyingLeft() == true){
                    bullet.setVelX(-15);
                }
                else if(bullet.isFlyingLeft() == false){
                    bullet.setVelX(15);
                }
            }
        }

    }
    @Override
    public void shootIceBall(GraphicsContext gc){
        if(facing == 0){
            if(numberIceBall > 0){  
//              bulletHandler.getBullets().add(new FireBall(this.getX(), this.getY(), bulletHandler,true));
                IceBall iceBall = (IceBall) bulletCache.getBullet(bulletType.snowBall);
                iceBall.setX(this.getX());
                iceBall.setY(this.getY());
                iceBall.setFlyingLeft(true);
                bulletHandler.getBullets().add(iceBall);   
                numberIceBall -= 1;
            }
        }
        else if(facing == 1){
            if(numberIceBall > 0){
//                bulletHandler.getBullets().add(new FireBall(this.getX() + this.getWidth()/4, this.getY() + 5, bulletHandler,false));
                IceBall iceBall = (IceBall) bulletCache.getBullet(bulletType.snowBall);
                iceBall.setX(this.getX()+this.getWidth()/4);
                iceBall.setY(this.getY());
                iceBall.setFlyingLeft(false);
                bulletHandler.getBullets().add(iceBall);
                numberIceBall -= 1;
            }
        }
        for(Bullet bullet : bulletHandler.getBullets()){
            if(bullet.getId() == bulletType.snowBall){
                if(bullet.isFlyingLeft() == true){
                    bullet.setVelX(-15);
                }
                else if(bullet.isFlyingLeft() == false){
                    bullet.setVelX(15);
                }
            }
        }
    }
//    public void jump() {
//        this.setVelY(-15);
//    }
    @Override
    public void tick() {
        x+= velX;
        y += velY;
        if(this.getHp() <= 0){
            this.die();
        }
        if (this.jumping) {
              velY += this.getGravity() / 10;
        }
        if(this.isMovingLeft == true){
            this.setVelX(-5.0);
            this.facing = 0;
        }
        else if(this.isMovingRight){
            this.setVelX(5);
            this.facing = 1;
        }
        else if(freeze == true ||(isMovingLeft == false && isMovingRight == false)) {
            this.setVelX(0);
        }
        
        if(velX != 0){
            this.animate = true;
        }
        else{
            this.animate = false;
        }
        enemyCollidingChecking();
        tileCollidingChecking();
        if(animate == true){
            frameDelay++;
            if(frameDelay >=3){
                frame++;
            if(frame >=3){
                frame = 0;
            }
            frameDelay = 0;
        }
        }
 
    }
    public void tileCollidingChecking(){
        for(Tile t: tileHandler.getTile()){
            if(t.solid == false){
            }
            else{
                if(t.getId() == Id.wall || t.getId() == Id.fireTrap){
                    if(this.intersectsTopTile(t)){
                        y = t.getY() - height;
                        this.setVelY(10);
                        this.setJumping(false);
                        if(t.getId() == Id.fireTrap){
                            hp -= 10;
                        }
                    }    
                    if(this.intersectsBottomTile(t)){                       
                        y = t.getY() + height;
                        if(t.getId() == Id.fireTrap){
                            hp -= 10;
                        }
                    }
                    if(this.intersectsRightTile(t)){
                        x = t.getX()+t.width;
                        if(t.getId() == Id.fireTrap){
                            hp -= 10;
                        }
                    }
                    if(this.intersectsLeftTile(t)){
                        x = t.getX()-t.width;  
                        if(t.getId() == Id.fireTrap){
                            hp -= 10;
                        }
                    }
                }
            }     
        }
    }
    public void enemyCollidingChecking(){
        for(Entity en : entityHandler.getEntity()){
            if(en.getId() == Id.Goomba){
                    if(this.intersectsTopEntity(en)){
                        y = en.getY() - height;
                        this.setVelY(10);
                        this.setJumping(false);
                        this.setHp(this.getHp() - 10);
                    }    
                    else if(this.intersectsBottomEntity(en)){                       
                        setVelY(0);
                        y = en.getY() + height;
                       this.setHp(this.getHp() - 10);
                    }
                    else if(this.intersectsRightEntity(en)){
                        setVelX(0);
                        x = en.getX()+en.width;
                        this.setHp(this.getHp() - 10); 
                    }
                    else if(this.intersectsLeftEntity(en)){

                        setVelX(0);
                        x = en.getX()-en.width;   
                        this.setHp(this.getHp() - 10);
                    }
            }
        }
    }
    
}
