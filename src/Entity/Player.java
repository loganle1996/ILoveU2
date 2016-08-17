/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Bullet.Bullet;
import Bullet.FireBall;
import Bullet.bulletType;
import GraphicsforAnimation.Sprite;
import javafx.scene.canvas.GraphicsContext;
import tile.Tile;

/**
 *
 * @author owne
 */
public class Player extends Entity {
    public int numberFireball = 50;
    private static Player player = new Player(40, 40, true, Id.player, entityHandler);

    private Player(int width, int height, boolean solid, Id id, EntityHandler entityHandler) {
        super(width, height, solid, id, entityHandler);
    }
    
    public static Player getInstance(){
        return player;
    }

    public int getNumberFireball() {
        return numberFireball;
    }

    public void setNumberFireball(int numberFireball) {
        this.numberFireball = numberFireball;
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
                bulletHandler.getBullets().add(new FireBall(this.getX(), this.getY() + 5, bulletHandler,true));
                numberFireball -= 1;
            }
        }
        else if(facing == 1){
            if(numberFireball > 0){
                bulletHandler.getBullets().add(new FireBall(this.getX() + this.getWidth()/4, this.getY() + 5, bulletHandler,false));
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
    
    public void jump() {
        this.setVelY(-15);
    }

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
        else {
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
                        setVelY(0);
                        y = t.getY() + height;
                        if(t.getId() == Id.fireTrap){
                            hp -= 10;
                        }
                    }
                    if(this.intersectsRightTile(t)){
                        setVelX(0);
                        x = t.getX()+t.width;
                        if(t.getId() == Id.fireTrap){
                            hp -= 10;
                        }
                    }
                    if(this.intersectsLeftTile(t)){
                        setVelX(0);
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
