/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Bullet.Bullet;
import Bullet.bulletType;
import Entity.Folow.WalkingFollow;
import GraphicsforAnimation.Sprite;
import Sound.SoundHandler;
import javafx.scene.canvas.GraphicsContext;
import tile.Tile;

/**
 *
 * @author owne
 */
public class AIenemy extends Entity
{
    public AIenemy(int x, int y, int width, int height, boolean solid, Id id, EntityHandler entityHandler) {
        super(x, y, width, height, solid, id, entityHandler);
        this.setIsMovingLeft(true);
        this.setFollowSkill(new WalkingFollow());
    }

    @Override
    public void render(GraphicsContext g,Sprite[] aiSprite) {
        if(this.facing == 0 ){
             g.drawImage(aiSprite[frame+ 3].getImage(), x, y, width, height);
         }
         else if(this.facing == 1){
             g.drawImage(aiSprite[frame].getImage(), x, y, width, height);
         }


    }
    @Override
    public void shootFireBall(GraphicsContext gc)
    {

    }
    @Override
    public void shootIceBall(GraphicsContext gc){

    }

    @Override
    public void tick(long currentime) {
        x += velX;
        y += velY;
        this.watchingAround();
        for(Entity en: entityHandler.getEntity()){
            if(en.getId() == Id.player){
                if(this.foundObject(en)){
                    this.follow(en);
                }
            }
        }
        for(Bullet bullet: bulletHandler.getBullets()){
            if(bullet.getId() == bulletType.fireBall){
                if(this.foundDanger(bullet)){
                    this.jump();
                }
            }
        }
        if(this.isJumping() == true){
            if (lasttime == 0){
                lasttime = currentime;
            }
            else {
                if (((currentime - lasttime) / 1000000000.0) > 0.2){
                    lasttime = currentime;

                }
                if(velY <10){
                    velY += 1;
                }
                else{
                    jumping = false;
                }
            }
        }
        if(this.isMovingLeft == true && this.isFreeze() == false)
        {
            this.facing = 0;
            if(this.isOnSlow() == true)
            {
                this.setVelX(-0.5);
            }
            else
            {
                this.setVelX(-3);
                this.setIsOnSlow(false);
            }
        }
        else if(this.isMovingRight == true && this.isFreeze() == false)
        {
            this.facing = 1;
            if(this.isOnSlow() == true)
            {
                this.setVelX(0.5);
            }
            else
            {
                this.setVelX(3);
                this.setIsOnSlow(false);
            }
        }
        else if(freeze == true || (isMovingLeft == false && isMovingRight == false))
        {
            this.setVelX(0);
        }

        if(this.getHp() <= 0){
            this.die();
            SoundHandler.getInstance().playSound("monster_death");
        }

        //check if this collides tiles
        tileCollidingChecking();
        //animation
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
            if(t.getId() == Id.wall || t.getId() == Id.fireTrap){
                if(this.intersectsTopTile(t)){
//                    this.setJumping(false);
                    y = t.getY() - height;
                    if(t.getId() == Id.fireTrap){
                        hp -= 200;
                        this.jump();
                    }
                }
                if(this.intersectsBottomTile(t)){
                    y = t.getY() + height;
                    if(t.getId() == Id.fireTrap){
                        hp -= 200;
                        this.jump();
                    }
                }
                if(this.intersectsRightTile(t)){
                    this.setIsMovingRight(true);
                    this.setIsMovingLeft(false);
                    x = t.getX()+t.width;
                    if(t.getId() == Id.fireTrap){
                        hp -= 200;
                        jump();
                    }
                }
                if(this.intersectsLeftTile(t)){
                    this.setIsMovingLeft(true);
                    this.setIsMovingRight(false);
                    x = t.getX()-t.width;
                    if(t.getId() == Id.fireTrap){
                        hp -= 200;
                        jump();
                    }
                }
            }
        }
}
    public void jump(){
        if((this.jumping == false || this.jumping == true) && this.isFreeze() == false && this.isOnSlow() == true)
        {
            this.setVelY(-3);
            this.setJumping(true);
        }
        else
        {
            this.setIsOnSlow(false);
            this.setVelY(-15);
            this.setJumping(true);
        }

        if (isFreeze() == true)
        {
            this.setJumping(true);
            this.setVelY(0);
        }
    }

    @Override
    public void placeSlowSpirit(GraphicsContext gc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
