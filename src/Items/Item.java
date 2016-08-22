/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import Entity.Entity;
import Entity.EntityHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tile.Tile;
import tile.TileHandler;

/**
 *
 * @author LoganLe
 */
public abstract class Item implements Cloneable{
    public double x = 0,y = 0;
    public double velX,VelY,gravity = 10;
    public ItemHandler itemHandler = ItemHandler.getInstance();
    public double width= 40, height = 40;
    public long lastime = 0;
    public Image image;
    public String itemType;
    public TileHandler tileHandler = TileHandler.getInstance();
    public EntityHandler entityHandler = EntityHandler.getInstance();
    public Item(ItemHandler itemHandler) {
        this.itemHandler = itemHandler;
        this.image = image;
        this.VelY += gravity;
    }
      
    
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
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
        return VelY;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setVelY(double VelY) {
        this.VelY = VelY;
    }
    public void disappear(){
        itemHandler.removeItem(this);
    }
    @Override
    public Object clone(){
        Object clone = null;

        try {
           clone = super.clone();

        } catch (CloneNotSupportedException e) {
           e.printStackTrace();
        }

        return clone;
    }
    public abstract void render(GraphicsContext gc);
    
    public abstract void tick(long currentime);
    
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
