/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Bullet.Bullet;
import Bullet.FireBall;
import Bullet.bulletType;
import Graphics.Sprite;
import MVCpattern.GameModel;
import MVCpattern.GameView;
import State.PlayerState;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import tile.Tile;
//import jdk.internal.org.objectweb.asm.Handle;

/**
 *
 * @author owne
 */
public class Player extends Entity {
    private PlayerState state;
    private int frame = 0;
    private int frameDelay = 0;
    private boolean animate = false;
    public Player(int x, int y, int width, int height, boolean solid,Id id,GameModel gameModel) {
        super(x, y, width, height, solid,id,gameModel);
        this.state = PlayerState.big;
    }

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    @Override
    public void render(GraphicsContext g ) {
         if(this.facing == 0 ){
             g.drawImage(GameModel.playerSprite[frame+ 3].getImage(), x, y, width, height);
         }
         else if(this.facing == 1){
             g.drawImage(GameModel.playerSprite[frame].getImage(), x, y, width, height);
         }
    }
    @Override
    public void shootFireBall(GraphicsContext gc) {
        if(facing == 0){
            gameModel.bullets.add(new FireBall(this.getX(), this.getY() + 5, gameModel,true));
            System.out.println("Added fireball to linkedlist");
        }
        else if(facing == 1){
            gameModel.bullets.add(new FireBall(this.getX() + this.getWidth(), this.getY() + 5, gameModel,false));
            System.out.println("Added fireball to linkedlist");
        }
        
        for(Bullet bullet : gameModel.bullets){
            System.out.println("111111111");
            if(bullet.getId() == bulletType.fireBall){
                System.out.println("222222222222");
                if(bullet.isFlyingLeft() == true){
                    bullet.setVelX(-6);
                    System.out.println("Set bullet VelX");
                }
                else if(bullet.isFlyingLeft() == false){
                    bullet.setVelX(6);
                    System.out.println("Set bullet VelX");
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
        System.out.println("HP: "+ hp);
        if(x<=0){
            x = 0;
        }
        if( y <= 0 ){
            y = 0;
        }
        if(x + width>= 800){
            x = 800 - width;
        }
        if(y +height>= 800){
            y = 800 - height;
        }
        if(this.getHp() <= 0){
            this.die();
        }
        if(this.getHp() <= 1000){
            this.setState(PlayerState.big);
        }
        else if(this.getHp() < 700){
            this.setState(PlayerState.medium);
        }
        else if(this.getHp() < 400){
            this.setState(PlayerState.small);
        }
        else if (this.getHp()<= 0){
            this.die();
        }
        if (this.jumping) {
              velY += this.getGravity() / 10;
              System.out.println("Count: ");
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
        //check if the player collides with enemies
        enemyCollidingChecking();
        //Check if the player collides with tiles
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
        for(Tile t: gameModel.getTileList()){
            if(t.solid == false){
                break;
            }
            else{
                if(t.getId() == Id.wall){
                    if(this.intersectsTopObject(t)){
                        y = t.getY() - height;
                        this.setVelY(10);
                        this.setJumping(false);
                    }    
                    if(this.intersectsBottomobject(t)){                       
                        setVelY(0);
                        y = t.getY() + height;
                    }
                    if(this.intersectsRightObject(t)){
                        setVelX(0);
                        x = t.getX()+t.width;
                    }
                    if(this.intersectsLeftObject(t)){
                        setVelX(0);
                        x = t.getX()-t.width;                       
                    }
                }
            }     
        }
    }
    public void enemyCollidingChecking(){
        for(Entity en : gameModel.getAiEntity()){
            if(en.getId() == Id.Goomba){
                    if(this.intersectsTopEntity(en)){
                        y = en.getY() - height;
                        this.setVelY(10);
                        this.setJumping(false);
//                        this.setCollidingTop(true);
                        this.setHp(this.getHp() - 10);
                    }    
                    else if(this.intersectsBottomEntity(en)){                       
                        setVelY(0);
//                        this.setCollidingBottom(true);
                        y = en.getY() + height;
                       this.setHp(this.getHp() - 10);
                    }
                    else if(this.intersectsRightEntity(en)){
//                        this.setIsCollidingRight(true);
//                        this.setIsMovingLeft(false);
                        setVelX(0);
                        x = en.getX()+en.width;
                        this.setHp(this.getHp() - 10); 
                    }
                    else if(this.intersectsLeftEntity(en)){
//                        this.setIsCollidingLeft(true);
//                        this.setIsMovingRight(false);
                        setVelX(0);
                        x = en.getX()-en.width;   
                        this.setHp(this.getHp() - 10);
                    }
//                    else{
//                        this.setIsCollidingLeft(false);
//                        this.setIsCollidingRight(false);
//                        this.setCollidingTop(false);
//                        this.setCollidingBottom(false);
//                    }
            }
        }
    }
}
