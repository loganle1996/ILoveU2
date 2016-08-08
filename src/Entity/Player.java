/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Bullet.Bullet;
import Bullet.FireBall;
import Bullet.bulletType;
import MVCpattern.GameModel;
import State.PlayerState;
import javafx.scene.canvas.GraphicsContext;
import tile.Tile;

/**
 *
 * @author owne
 */
public class Player extends Entity {
    private PlayerState state;
    private int frame = 0;
    private int frameDelay = 0;
    private boolean animate = false;
    private static Player player = new Player(40, 40, true, Id.player, entityHandler);
//    public Player(int x, int y, int width, int height, boolean solid,Id id,EntityHandler entityHandler) {
//        super(x, y, width, height, solid,id,entityHandler);
//        this.state = PlayerState.big;
//    }

    private Player(int width, int height, boolean solid, Id id, EntityHandler entityHandler) {
        super(width, height, solid, id, entityHandler);
        this.state = PlayerState.big;
    }
    
    PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }
    public static Player getInstance(){
        return player;
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
            bulletHandler.getBullets().add(new FireBall(this.getX(), this.getY() + 5, bulletHandler,true));
        }
        else if(facing == 1){
            bulletHandler.getBullets().add(new FireBall(this.getX() + this.getWidth(), this.getY() + 5, bulletHandler,false));
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
