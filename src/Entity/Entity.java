/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Bullet.Bullet;
import Bullet.BulletHandler;
import Entity.Folow.Follow;
import GraphicsforAnimation.Sprite;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import tile.Tile;
import tile.TileHandler;

/**
 *
 * @author owne
 */
public abstract class Entity{
    public double x, y;
    public int width = 40 , height = 40;

    public boolean solid;
    public final double gravity= 10;
    public double velX,velY;
    public Id id;
    public static EntityHandler entityHandler = EntityHandler.getInstance();
    public TileHandler tileHandler = TileHandler.getInstance();
    public BulletHandler bulletHandler = BulletHandler.getInstance();
    public boolean isMovingLeft = false, isMovingRight = false, jumping = false;
    public int facing = 0; //0 is left; 1 is right
    public double hp = 1000; //1000
    public boolean isCollidingLeft = false, isCollidingRight = false,collidingTop = false, collidingBottom = false,shootable = true;
    public int frame = 0;
    public int frameDelay = 0;
    public boolean animate = false;
    public boolean freeze = false;
    public boolean OnSlow = false;
    public boolean flyUp = false,flyDown = false;
    public boolean isSwimming = false,isSwimmingLeft = false,isSwimmingRight = false,isSwimmingUp = false, isSwimmingDown = false;
    public boolean isShooting = false;
    private double shootDelay = 0.5;


    public boolean isOnSlow() {
        return OnSlow;
    }

    public void setIsOnSlow(boolean isOnSlow) {
        this.OnSlow = isOnSlow;
    }
    public int numberFireball = 50,numberIceBall = 10,numberSlowSpirit = 20;
    public Rectangle2D bigRectangle2D,smallRectangle2D;
    private Follow followskill;
    public long lasttime = 0;
    //public boolean falling = true;

    public Entity(int x, int y, int width, int height, boolean solid,Id id,EntityHandler entityHandler) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.solid = solid;
        this.id = id;
        Entity.entityHandler = entityHandler;
        velY += gravity;
    }
    public Entity(int width,int height, boolean solid, Id id, EntityHandler entityHandler){
        this.width = width;
        this.height = height;
        this.solid = solid;
        this.id = id;
        Entity.entityHandler = entityHandler;
        velY += gravity;
    }
    public Entity(int width,int height, boolean solid, EntityHandler entityHandler){
        this.width = width;
        this.height = height;
        this.solid = solid;
        Entity.entityHandler = entityHandler;
        velY += gravity;
    }
    public abstract void render(GraphicsContext g, Sprite[] sprite);

    public abstract void tick(long currentTime);

    public abstract void shootFireBall(GraphicsContext gc);
    public abstract void shootIceBall(GraphicsContext gc);
    public abstract void placeSlowSpirit(GraphicsContext gc);

    public void healAndRefill(){
        this.numberFireball = 50;
        this.numberIceBall = 10;
        this.setHp(1000);
        System.out.println("healandrefill succesfully");
    }

    public void setFollowSkill(Follow followSkill){
        this.followskill = followSkill;
    }
    public void follow(Entity player){
        followskill.following(this,player);
    }
    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public boolean isFlyUp() {
        return flyUp;
    }

    public void setFlyUp(boolean flyUp) {
        this.flyUp = flyUp;
    }

    public boolean isFlyDown() {
        return flyDown;
    }

    public void setFlyDown(boolean flyDown) {
        this.flyDown = flyDown;
    }

    public double getY() {
        return y;
    }

    public int getNumberIceBall() {
        return numberIceBall;
    }

    public void setNumberIceBall(int numberIceBall) {
        this.numberIceBall = numberIceBall;
    }


    public void setY(int y) {
        this.y = y;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }
    public Id getId(){
        return id;
    }

    public boolean isFreeze() {
        return freeze;
    }

    public void setFreeze(boolean freeze) {
        this.freeze = freeze;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public double getGravity() {
        return gravity;
    }

    public Rectangle2D getBoundary(){
        return new Rectangle2D(getX(), getY(), width, height);
    }
    public Rectangle2D getTopBoundary(){
        return new Rectangle2D(getX(),getY(),width,5);
    }
    public Rectangle2D getBottomBoundary(){
        return new Rectangle2D(getX() + 10,getY()+ height - 5,width-20,5);
    }
    public Rectangle2D getLeftBoundary(){
        return new Rectangle2D(getX(), getY()+ 10,5,height-20);
    }
    public Rectangle2D getRightBoundary(){
        return new Rectangle2D(getX() + width - 5, getY() + 10,5, height - 20);
    }
    //is intersected ?
    public boolean intersectsTile(Tile ti){
        return ti.getBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsTopTile(Tile ti){
        return ti.getTopBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsLeftTile(Tile ti){
        return ti.getLeftBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsRightTile(Tile ti){
        return ti.getRightBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsBottomTile(Tile ti){
        return ti.getBottomBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsExtraBounds(Tile ti) {
        return ti.getExtraBoundary().intersects(this.getBoundary());
    }

    // is intersected Entity
    public boolean intersectsEntity(Entity en){
        return en.getBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsTopEntity(Entity en){
        return en.getTopBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsLeftEntity(Entity en){
        return en.getLeftBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsRightEntity(Entity en){
        return en.getRightBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsBottomEntity(Entity en){
        return en.getBottomBoundary().intersects(this.getBoundary());
    }

    public boolean isIsMovingLeft() {
        return isMovingLeft;
    }

    public void setIsMovingLeft(boolean isMovingLeft) {
        this.isMovingLeft = isMovingLeft;
    }

    public boolean isIsMovingRight() {
        return isMovingRight;
    }

    public void setIsMovingRight(boolean isMovingRight) {
        this.isMovingRight = isMovingRight;
    }
    public int getNumberFireball() {
        return numberFireball;
    }

    public void setNumberFireball(int numberFireball) {
        this.numberFireball = numberFireball;
    }
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public void die(){
        entityHandler.removeEntity(this);
    }
    public void recover(){
        entityHandler.addEntity(this);
    }
    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public boolean isIsCollidingLeft() {
        return isCollidingLeft;
    }

    public void setIsCollidingLeft(boolean isCollidingLeft) {
        this.isCollidingLeft = isCollidingLeft;
    }

    public boolean isIsCollidingRight() {
        return isCollidingRight;
    }

    public void setIsCollidingRight(boolean isCollidingRight) {
        this.isCollidingRight = isCollidingRight;
    }

    public boolean isCollidingTop() {
        return collidingTop;
    }

    public void setCollidingTop(boolean collidingTop) {
        this.collidingTop = collidingTop;
    }

    public boolean isCollidingBottom() {
        return collidingBottom;
    }

    public void setCollidingBottom(boolean collidingBottom) {
        this.collidingBottom = collidingBottom;
    }

    public int getFacing() {
        return facing;
    }

    public boolean isShootable() {
        return shootable;
    }

    public void setShootable(boolean shootable) {
        this.shootable = shootable;
    }

    public void setFacing(int facing) {
        this.facing = facing;
    }

    public EntityHandler getEntityHandler() {
        return entityHandler;
    }

    public void setEntityHandler(EntityHandler entityHandler) {
        this.entityHandler = entityHandler;
    }

    public void watchingAround(){

        if(facing == 0){
            bigRectangle2D =  new Rectangle2D(this.getX()-200, this.getY()-200, 210, 400);
            smallRectangle2D = new Rectangle2D(this.getX()-100,this.getY(), 100, 40);
        }
        else if(facing == 1){
            bigRectangle2D =  new Rectangle2D(this.getX()-10, this.getY()-200, 200, 400);
            smallRectangle2D = new Rectangle2D(this.getX(),this.getY(), 140, 40);
        }

    }
    public boolean foundDanger(Bullet bullet){
        return smallRectangle2D.intersects(bullet.getBoundary());
    }
    public boolean foundObject(Entity en){
        return bigRectangle2D.intersects(en.getBoundary());
    }
    public void flyUp(){
        if(flyDown == false && this.isFreeze() == false){
            flyUp = true;
            this.setVelY(-5);
        }
    }
    public void flyDown(){
        if(flyUp == false && this.isFreeze() == false){
            flyDown = true;
            this.setVelY(5);
        }
    }
    public void stopFlyUpAndDown(){
        if(flyDown == false && flyUp == false){
            this.setVelY(0);
        }
    }

    public double getShootDelay() {
        return shootDelay;
    }

    public void setShootDelay(double shootDelay) {
        this.shootDelay = shootDelay;
    }

    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract void swimUp();
    public abstract void swimDown();
    public abstract void notSwimUpDown();
}
