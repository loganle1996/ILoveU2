/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Bullet.*;
import GraphicsforAnimation.Sprite;
import InfoBox.AlertBox;
import ScoreData.PlayerScoreData;
import ScoreData.ScoreData;
import Sound.SoundHandler;
import Target.HouseHandler;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import tile.Tile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Optional;

/**
 *
 * @author owne
 */
public class Player extends Entity {
    private boolean hurtable = true;
    private long lastTimeHurt = 0;
    HouseHandler houseHandler = HouseHandler.getInstance();
    private static Player player = new Player(40, 40, true, Id.player, entityHandler);
    private BulletCache bulletCache = BulletCache.getInstance();
    int deltaTime = 0;
    private int score = 0;
    private AlertBox alertBox = new AlertBox();
    public double frameDelay2 =0;
    private LinkedList<Tile> copiedTile;

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
                if(isMovingLeft){
                    if(this.isSwimming == false){
                        g.drawImage(playerSprite[frame+5].getImage(), x, y, width, height);
                    }
                    else if(this.isSwimming == true){
                        g.drawImage(playerSprite[frame+8].getImage(), x, y, width, height);
                    }

                }
                else{
                    if(this.isSwimming == false){
                        if(this.isShooting == true){
                            g.drawImage(playerSprite[21].getImage(), x, y, width, height);
                        }
                        else{
                            g.drawImage(playerSprite[4].getImage(), x, y, width, height);
                        }
                    }
                    else{
                        g.drawImage(playerSprite[frame+8].getImage(), x, y, width, height);
                    }
                }
            }
            else if(isJumping() == true){
                g.drawImage(playerSprite[5].getImage(), x, y, width, height);

            }
        }
        else if(this.facing == 1){
            if(this.isJumping()== false){
                if(isMovingRight){
                    if(isSwimming == false){
                        g.drawImage(playerSprite[frame+1].getImage(), x, y, width, height);
                    }
                    else if(this.isSwimming == true){
                        g.drawImage(playerSprite[frame+15].getImage(), x, y, width, height);
                    }

                }
                else{
                    if(this.isSwimming == false){
                        if(this.isShooting == true){
                            g.drawImage(playerSprite[20].getImage(), x, y, width, height);
                        }
                        else{
                            g.drawImage(playerSprite[0].getImage(), x, y, width, height);
                        }
                    }
                    else {
                        g.drawImage(playerSprite[frame+15].getImage(), x, y, width, height);
                    }
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

    public boolean isHurtable() {
        return hurtable;
    }

    public void setHurtable(boolean hurtable) {
        this.hurtable = hurtable;
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
        if(this.getHp() > 1000){
            this.setHp(1000);
        }
        deltaTime = (int)  (currentime - lasttime) / 1000000000;
        if(hurtable == false){
            if(lastTimeHurt == 0){
                lastTimeHurt = currentime;
            }
            else {
                if(((currentime-lastTimeHurt)/1000000000.0) > 1 ){
                    this.hurtable = true;
                    lastTimeHurt = currentime;
                }
            }
        }

        if (deltaTime > this.getShootDelay()){
            lasttime = currentime;
            this.setShootable(true);
        }
        if(this.getHp() <= 0){
            this.die();
            SoundHandler.getInstance().stopBackgroundMusic();
            SoundHandler.getInstance().playSound("player_death");
            alertBox.display("Restart to Menu","Would you like to restart the game?", this.getScore());
        }

        if (this.getVelY() <= 10) {
            if(this.isSwimming== false){
                velY += this.getGravity() / 10;
            }
        }
        if(this.isSwimming == false) {
            if (this.isMovingLeft) {
                this.setVelX(-5);
                this.facing = 0;
                if (!this.jumping) {
                    SoundHandler.getInstance().playSound("footstep");
                }
            } else if (this.isMovingRight) {
                this.setVelX(5);
                this.facing = 1;
                if (!this.jumping) {
                    SoundHandler.getInstance().playSound("footstep");
                }
                this.moveRight();
            }
        }
        else if(this.isSwimming == true){

            SoundHandler.getInstance().playSound("swimming");

            if(this.isIsMovingLeft()){
                this.setVelX(-3);
                this.facing = 0;
            }
            else if(this.isMovingRight){
                this.setVelX(3);
                this.facing = 1;
            }
        }

        if(freeze == true ||(isMovingLeft == false && isMovingRight == false)) {
            this.setVelX(0);
        }
        enemyCollidingChecking(currentime);
        tileCollidingChecking();

        if(this.isMovingRight == true || this.isMovingLeft == true){
            this.animate = true;
        }
        else{
            this.animate = false;
        }
        if(this.isSwimming){
            this.animate = true;
        }
        if(animate == true){
            if (this.isSwimming == false){
                frameDelay++;
                if(frameDelay >= 3){
                    frame++;
                    if(frame >=3){
                        frame = 0;
                    }
                    frameDelay = 0;
                }
            }
            else if(this.isSwimming == true){
                frameDelay2++;
                if(frameDelay2 >= 10){
                    frame++;
                    if(frame >=5){
                        frame = 0;
                    }
                    frameDelay2 = 0;
                }
            }
        }

    }
    public void tileCollidingChecking(){
        isSwimming = false;
        copiedTile = new LinkedList<>(tileHandler.getTile());
        for(Tile t: copiedTile){
            if(t.solid == false){
                if(t.getType().equalsIgnoreCase("water")){
                    if(this.intersectsTile(t)){
                        this.isSwimming = true;
                    }
                }
            }
            else{
                if(t.getId() == Id.wall){
                    if(this.intersectsTopTile(t)){
                        y = t.getY() - height;
                        this.setVelY(0.1);
                        this.setJumping(false);
                        if(t.getType().equalsIgnoreCase("trap1")){
                            if((t.getDirection().equalsIgnoreCase("Down"))){
                                this.setHp(this.getHp()-200);
                                t.die();
                            }
                            else{
                                this.setHp(this.getHp() - 10);
                            }
                            SoundHandler.getInstance().playSound("player_hurt");
                        }
                        else if(t.getType().equalsIgnoreCase("fireTrap")){
                            this.setHp(this.getHp() - 30);
                        }
                        if(t.getType().equalsIgnoreCase("woodBridge") && this.getVelX() == 0){
                            this.setVelX(this.getVelX() + t.getVelX());
                        }
                    }

                    if(this.intersectsBottomTile(t)){
                        y = t.getY() + height;
                        if(t.getType().equalsIgnoreCase("trap1")){
                            if((t.getDirection().equalsIgnoreCase("Down"))){
                                this.setHp(this.getHp()-200);
                                t.die();
                            }
                            else{
                                this.setHp(this.getHp() - 10);
                            }
                            SoundHandler.getInstance().playSound("player_hurt");
                        }
                        else if(t.getType().equalsIgnoreCase("fireTrap")){
                            this.setHp(this.getHp() - 30);
                        }
                    }
                    if(this.intersectsRightTile(t)){
                        x = t.getX()+t.width;
                        this.setVelX(0);
                        if(t.getType().equalsIgnoreCase("trap1")){
                            if((t.getDirection().equalsIgnoreCase("Down"))){
                                this.setHp(this.getHp()-200);
                                t.die();
                            }
                            else{
                                this.setHp(this.getHp() - 10);
                            }
                            SoundHandler.getInstance().playSound("player_hurt");
                        }
                        else if(t.getType().equalsIgnoreCase("fireTrap")){
                            this.setHp(this.getHp() - 30);
                        }
                    }
                    if(this.intersectsLeftTile(t)){
                        x = t.getX()-t.width;
                        this.setVelX(0);
                        if(t.getType().equalsIgnoreCase("trap1")){
                            if((t.getDirection().equalsIgnoreCase("Down"))){
                                this.setHp(this.getHp()-200);
                                t.die();
                            }
                            else{
                                this.setHp(this.getHp() - 10);
                            }
                            SoundHandler.getInstance().playSound("player_hurt");
                        }
                        else if(t.getType().equalsIgnoreCase("fireTrap")){
                            this.setHp(this.getHp() - 30);
                        }
                    }
                }
            }
        }
    }

    public void enemyCollidingChecking(long currentime){
        for(Entity en : entityHandler.getEntity()){
            if((en.getId() == Id.Goomba ||en.getId() == Id.GoombaBoss ||en.getId()== Id.Eagle) && en.isFreeze() == false){
                if(this.intersectsEntity(en)){
                    if(this.isHurtable()){
                        this.setHp(this.getHp() - 200);
                        this.hurtable = false;
                    }
                }
                if(this.intersectsTopEntity(en)){
                    this.setVelY(10);

                    SoundHandler.getInstance().playSound("player_hurt");

                }
                if (this.intersectsBottomEntity(en)){

                }
                if (this.intersectsBottomEntity(en)){
                    SoundHandler.getInstance().playSound("player_hurt");

                }
                if(this.intersectsRightEntity(en)){
                    SoundHandler.getInstance().playSound("player_hurt");

                }
                if(this.intersectsLeftEntity(en)){
                    SoundHandler.getInstance().playSound("player_hurt");

                }
            }
        }
    }

    @Override
    public void moveLeft() {
        this.isMovingLeft = true;
        this.isMovingRight = false;
    }

    @Override
    public void moveRight() {
        this.isMovingRight = true;
        this.isMovingLeft = false;
    }
    @Override
    public void swimUp(){
        if(this.isSwimming){
            this.isSwimmingUp = true;
            this.isSwimmingDown = false;
        }


    }
    @Override
    public void swimDown(){
        if(this.isSwimming){
            this.isSwimmingUp = false;
            this.isSwimmingDown = true;
        }

    }
    @Override
    public void notSwimUpDown(){
        if(this.isSwimming){
            this.isSwimmingUp = false;
            this.isSwimmingDown = false;
        }
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
