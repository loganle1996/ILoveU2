/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArcaneArenaModel;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author owne
 */
public abstract class Entity {
    public double x, y;
    public int width = 60 , height = 60;
    
    public boolean solid;
    public final double gravity= 10;
    public double velX,velY;
    public Id id;
    public boolean jumping = false;
    GameModel gameModel;
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

    public abstract void render(GraphicsContext g,Image image);
        
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
    //is intersected ?
    public boolean intersectsEntity(Entity en){
        return en.getBoundary().intersects(this.getBoundary());
    }
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
}
