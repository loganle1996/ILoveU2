/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Bullet.*;
import GraphicsforAnimation.Sprite;
import Sound.SoundHandler;
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
        if(this.facing == 0){
            if(isJumping() == false){
                if(velX != 0){
                   g.drawImage(playerSprite[frame+5].getImage(), x, y, width, height);
                }
                else{
                    g.drawImage(playerSprite[4].getImage(), x, y, width, height);
                }
            }
            else if(isJumping() == true){
                g.drawImage(playerSprite[5].getImage(), x, y, width, height);
            }
        }
        else if(this.facing == 1){
            if(this.isJumping()== false){
                if(velX != 0){
                   g.drawImage(playerSprite[frame+1].getImage(), x, y, width, height);
                }
                else{
                    g.drawImage(playerSprite[0].getImage(), x, y, width, height);
                }
            }
            else if(isJumping() == true){
                g.drawImage(playerSprite[1].getImage(), x, y, width, height);
            }
        }
    }
    @Override
    public void shootFireBall(GraphicsContext gc) {

      SoundHandler.getInstance().playSound("fireball");


        if(facing == 0){
            if(numberFireball > 0){
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
    public void shootIceBall(GraphicsContext gc)
    {
      SoundHandler.getInstance().playSound("iceball");

        if(facing == 0){
            if(numberIceBall > 0){
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

    @Override
    public void placeSlowSpirit(GraphicsContext gc)
    {
        SoundHandler.getInstance().playSound("iceball");

        if(facing == 0)
        {
            if(numberSlowSpirit > 0){
                SlowSpirit slowSpirit = (SlowSpirit) bulletCache.getBullet(bulletType.SlowSpirit);
                slowSpirit.setX(this.getX());
                slowSpirit.setY(this.getY());
                slowSpirit.setFlyingLeft(true);
                bulletHandler.getBullets().add(slowSpirit);
                numberSlowSpirit -= 1;
            }
        }
        else if(facing == 1){
            if(numberIceBall > 0)
            {
                SlowSpirit slowSpirit = (SlowSpirit) bulletCache.getBullet(bulletType.SlowSpirit);
                slowSpirit.setX(this.getX() + getWidth() / 4);
                slowSpirit.setY(this.getY());
                slowSpirit.setFlyingLeft(false);
                bulletHandler.getBullets().add(slowSpirit);
                numberSlowSpirit -= 1;
            }
        }
        for(Bullet bullet : bulletHandler.getBullets()){
            if(bullet.getId() == bulletType.SlowSpirit){
                if(bullet.isFlyingLeft() == true)
                {
                    bullet.setVelX(0);
                }
                else if(bullet.isFlyingLeft() == false)
                {
                    bullet.setVelX(0);
                }
            }
        }
    }

    @Override
    public void tick(long currentime) {
        x += velX;
        y += velY;
        if (lasttime == 0){
            lasttime = currentime;
        }
        else {
            if (((currentime - lasttime) / 1000000000.0) > this.getShootDelay()){
                lasttime = currentime;
                this.setShootable(true);
            }
        }

        if(this.getHp() <= 0){
            this.die();
        }
        if (this.jumping == true) {
              velY += this.getGravity() / 10;
        }
        if(this.isMovingLeft){
            this.setVelX(-5.0);
            this.facing = 0;
            if (!this.jumping)
            {
            SoundHandler.getInstance().playSound("footstep");
            }
        }
        else if(this.isMovingRight){
            this.setVelX(5);
            this.facing = 1;
            if (!this.jumping)
            {
            SoundHandler.getInstance().playSound("footstep");
            }
            this.moveRight();
        }

        else if(freeze == true ||(isMovingLeft == false && isMovingRight == false)) {
            this.setVelX(0);
        }
        enemyCollidingChecking();
        tileCollidingChecking();

        if(velX != 0){
            this.animate = true;
        }
        else{
            this.animate = false;
        }
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
                            SoundHandler.getInstance().playSound("player_hurt");

                        }
                    }
                    if(this.intersectsBottomTile(t)){
                        y = t.getY() + height;
                        if(t.getId() == Id.fireTrap){
                            hp -= 10;
                            SoundHandler.getInstance().playSound("player_hurt");

                        }
                    }
                    if(this.intersectsRightTile(t)){
                        x = t.getX()+t.width;
                        if(t.getId() == Id.fireTrap){
                            hp -= 10;
                            SoundHandler.getInstance().playSound("player_hurt");

                        }
                    }
                    if(this.intersectsLeftTile(t)){
                        x = t.getX()-t.width;
                        if(t.getId() == Id.fireTrap){
                            hp -= 10;
                            SoundHandler.getInstance().playSound("player_hurt");

                        }
                    }
                }
            }
        }
    }
    public void enemyCollidingChecking(){
        for(Entity en : entityHandler.getEntity()){
            if((en.getId() == Id.Goomba ||en.getId() == Id.GoombaBoss ||en.getId()== Id.Eagle) && en.isFreeze() == false){
                    if(this.intersectsTopEntity(en)){
//                        y = en.getY() - height;
                        this.setVelY(10);
//                        this.setJumping(false);

                        this.setHp(this.getHp() - 10);
                        SoundHandler.getInstance().playSound("player_hurt");

                    }
                    if (this.intersectsBottomEntity(en)){

                        if(en.getId() == Id.Goomba){
                            this.setHp(this.getHp() - 10);
                        }
                        if(en.getId() == Id.GoombaBoss){
                            this.setHp(this.getHp() - 80);
                        }
                    }
                    if (this.intersectsBottomEntity(en)){

                        setVelY(0);
//                        y = en.getY() + height;
                       this.setHp(this.getHp() - 10);
                       SoundHandler.getInstance().playSound("player_hurt");

                    }
                    if(this.intersectsRightEntity(en)){
//                        x = en.getX()+en.width;
                        this.setHp(this.getHp() - 10);
                        SoundHandler.getInstance().playSound("player_hurt");

                    }
                    if(this.intersectsLeftEntity(en)){
//                        x = en.getX()-en.width;
                        this.setHp(this.getHp() - 10);
                        SoundHandler.getInstance().playSound("player_hurt");

                    }
            }
        }
    }

    @Override
    public void moveLeft() {
        this.setVelX(-5.0);
        this.facing = 0;
    }

    @Override
    public void moveRight() {
        this.setVelX(5);
        this.facing = 1;
    }

}
