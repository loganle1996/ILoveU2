/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bullet;

import Entity.Entity;
import Entity.EntityHandler;
import MVCpattern.GameModel;
import Target.AiHouse;
import Target.HouseHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tile.Tile;
import tile.TileHandler;

/**
 *
 * @author khoinguyen
 */
public abstract class Bullet {
    private double x,y,velX,velY;
    private int width = 20,height =20;
    private double damage;
    private int numberOfBullets = 10;
    private bulletType id;
    GameModel gameModel;
    TileHandler tileHandler = TileHandler.getInstance();
    EntityHandler entityHandler = EntityHandler.getInstance();
    BulletHandler bulletHandler = BulletHandler.getInstance();
    HouseHandler houseHandler = HouseHandler.getInstance();
    private boolean flyingLeft;

    public Bullet(double x, double y,BulletHandler bulletHandler,boolean flyingLeft) {
        this.x = x;
        this.y = y;
        this.bulletHandler = bulletHandler;
        this.flyingLeft = flyingLeft;
    }
    public abstract void renderBullet(GraphicsContext gc,Entity en,Image imageLeft, Image imageRight);
    public abstract void tick();

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
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

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public int getNumberOfBullets() {
        return numberOfBullets;
    }

    public void setNumberOfBullets(int numberOfBullets) {
        this.numberOfBullets = numberOfBullets;
    }

    public bulletType getId() {
        return id;
    }

    public void setId(bulletType id) {
        this.id = id;
    }

    public boolean isFlyingLeft() {
        return flyingLeft;
    }

    public void setFlyingLeft(boolean flyingLeft) {
        this.flyingLeft = flyingLeft;
    }
    
    //other methods
    public void disappear(){
        bulletHandler.removeBullets(this);
    }
    //BOUNDARY **************************************************************
    //***********************************************************************
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
    //is intersectedHouse
    public boolean intersectsHouse(AiHouse ah){
        return ah.getBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsTopHouse(AiHouse ah){
        return ah.getTopBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsLeftHouse(AiHouse ah){
        return ah.getLeftBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsRightHouse(AiHouse ah){
        return ah.getRightBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsBottomHouse(AiHouse ah){
        return ah.getBottomBoundary().intersects(this.getBoundary());
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
}
