/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArcaneArenaModel;



import ArcaneArenaModel.GameModel;
import ArcaneArenaModel.Id;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author owne
 */
public abstract class Tile {
    public int x, y;
    public int width = 40, height = 40;
    public GameModel gameModel;
    
    public boolean solid;
    public int velX,velY;
    public Id id;
    public Tile(int x, int y, int width, int height, boolean solid,Id id,GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.solid = solid;
        this.id = id;
        this.gameModel = gameModel;
    }
   public abstract void render(GraphicsContext g,Image image);
        
    public abstract void tick();
    public void die(){
        gameModel.removeTile(this);
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
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

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
    public Id getId(){
        return id;
    }
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
}
