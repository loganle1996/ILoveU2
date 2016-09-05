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
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import tile.Tile;

/**
 *
 * @author owne
 */
public class AIenemy extends Entity
{
    private long lastimeFreeze = 0;
    public AIenemy(int x, int y, int width, int height, boolean solid, Id id, EntityHandler entityHandler) {
        super(x, y, width, height, solid, id, entityHandler);
        this.setIsMovingLeft(true);
        this.setFollowSkill(new WalkingFollow());
    }

    public AIenemy(int width, int height, boolean solid, EntityHandler entityHandler) {
        super(width, height, solid, entityHandler);
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
        if(this.getHp() <= 0){
            this.die();
        }
        if(isFreeze() == false){
            this.watchingAround();
            searchingEntities();
            searchingDanger();
        }
        if(this.isJumping() == true){
            
                if(this.getVelY() < 10){
                    velY += this.getGravity() / 10;
                }
                else{
                    jumping = false;
                }
//            if (lasttime == 0){
//                lasttime = currentime;
//            }
//            else {
//                if (((currentime - lasttime) / 1000000000.0) > 0.2){
//                    lasttime = currentime;
//                }
//            }
        }
        if(this.freeze == true){
            if(lastimeFreeze == 0){
                lastimeFreeze = currentime;
            }
            else {
                if(((currentime - lastimeFreeze) / 1000000000.0) > 3){
                    lastimeFreeze =0;
                    this.setFreeze(false);
                }
            }
        }

        if(this.isMovingLeft == true && this.isFreeze() == false)
        {
            this.moveLeft();
        }
        else if(this.isMovingRight == true && this.isFreeze() == false)
        {
            this.moveRight();
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
            if(t.solid == false){
                if(t.getType().equalsIgnoreCase("invisibleWall")){
                    if (this.intersectsRightTile(t)) {
                        this.setIsMovingRight(true);
                        this.setIsMovingLeft(false);
                        x = t.getX() + t.width;
                    }
                    if (this.intersectsLeftTile(t)) {
                        this.setIsMovingLeft(true);
                        this.setIsMovingRight(false);
                        x = t.getX() - t.width;
                    }
                }
            }
            else {
                if (t.getId() == Id.wall) {
                    if (this.intersectsTopTile(t)) {
                        y = t.getY() - height;
                    }
                    if (this.intersectsBottomTile(t)) {
                        y = t.getY() + height;
                    }
                    if (this.intersectsRightTile(t)) {
                        this.setIsMovingRight(true);
                        this.setIsMovingLeft(false);
                        x = t.getX() + t.width;
                    }
                    if (this.intersectsLeftTile(t)) {
                        this.setIsMovingLeft(true);
                        this.setIsMovingRight(false);
                        x = t.getX() - t.width;
                    }
                }
            }
        }
}
    public void jump(){
        if((this.jumping == false) && this.isFreeze() == false && this.isOnSlow() == true)
        {
            this.setVelY(-3);
            this.setJumping(true);
        }
        else
        {
            this.setIsOnSlow(false);
            this.setVelY(-10);
            this.setJumping(true);
        }

        if (isFreeze() == true)
        {
            this.setJumping(true);
//            this.setVelY(0);
        }
    }
    public void searchingEntities(){
        for(Entity en: entityHandler.getEntity()){
            if(en.getId() == Id.player){
                if(this.foundObject(en)){
                    this.follow(en);
                }
            }
        }
    }
    public void searchingDanger(){
        for(Bullet bullet: bulletHandler.getBullets()){
            if(bullet.getId() == bulletType.fireBall){
                if(this.foundDanger(bullet)){
                    if(this.isFreeze() == false){
                        this.jump();
                    }
                }
            }
        }
    }
    public void moveLeft(){
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
    public void moveRight(){
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

    @Override
    public void swimUp() {

    }

    @Override
    public void swimDown() {

    }

    @Override
    public void notSwimUpDown() {

    }

    @Override
    public void placeSlowSpirit(GraphicsContext gc) {
        
    }
    @Override
        public void watchingAround(){
        if(facing == 0){
            bigRectangle2D =  new Rectangle2D(this.getX()-100, this.getY()-200, 110, 400);
            smallRectangle2D = new Rectangle2D(this.getX()-100,this.getY(), 100, 40);
        }
        else if(facing == 1){
            bigRectangle2D =  new Rectangle2D(this.getX()-10, this.getY()-200, 200, 400);
            smallRectangle2D = new Rectangle2D(this.getX(),this.getY(), 140, 40);
        }
    }
}
