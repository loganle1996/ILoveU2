/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Graphics.Sprite;
import MVCpattern.GameModel;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tile.Tile;

/**
 *
 * @author owne
 */
public abstract class Entity {
    public double x, y;
    public int width = 40 , height = 40;
    
    public boolean solid;
    public final double gravity= 10;
    public double velX,velY;
    public Id id;
    GameModel gameModel;
    public boolean isMovingLeft = false, isMovingRight = false, jumping = false;
    public int facing = 0; //0 is left; 1 is right
    public double hp = 1000;
    public boolean isCollidingLeft = false, isCollidingRight = false,collidingTop = false, collidingBottom = false;
    //public boolean falling = true;
    
    public Entity(int x, int y, int width, int height, boolean solid,Id id,GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.solid = solid;
        this.id = id;
        this.gameModel = gameModel;
        velY += gravity;
    }

    public abstract void render(GraphicsContext g);
        
    public abstract void tick();


    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
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

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public double getGravity() {
        return gravity;
    }
//    }
    public Rectangle2D getBoundary(){
        return new Rectangle2D(getX(), getY(), width, height);
    }
    public Rectangle2D getTopBoundary(){
        return new Rectangle2D(getX() + 10,getY(),width-20,5);
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
    public boolean intersectsObject(Tile ti){
        return ti.getBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsTopObject(Tile ti){
        return ti.getTopBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsLeftObject(Tile ti){
        return ti.getLeftBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsRightObject(Tile ti){
        return ti.getRightBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsBottomobject(Tile ti){
        return ti.getBottomBoundary().intersects(this.getBoundary());
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
        gameModel.removeEntity(this);
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

    public void setFacing(int facing) {
        this.facing = facing;
    }
    
}
