package Rope;

import Entity.Id;
import Sound.SoundHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import tile.Tile;
import tile.TileHandler;

/**
 * Created by LoganLe on 8/31/16.
 */
public class Point {
    public double x,y,velX,velY;
    public double width = 20,height = 20;
    public Image imagePoint;
    public PointHandler pointHandler = PointHandler.getInstance();
    public TileHandler tileHandler = TileHandler.getInstance();
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Point(Point pointStart, Point pointEnd){

    }

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

    public Image getImagePoint() {
        return imagePoint;
    }

    public void setImagePoint(Image imagePoint) {
        this.imagePoint = imagePoint;
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

    public void tick(){
        x += velX;
        y += velY;
        if(this.getVelY() < 10){
            this.setVelY(this.getVelY()+1);
        }
        tileCollidingChecking();
    }
    public void tileCollidingChecking(){
        for(Tile t: tileHandler.getTile()){
            if(t.solid == false){
            }
            else{
                if(t.getId() == Id.wall || t.getId() == Id.fireTrap){
                    if(this.intersectsTopTile(t)){
                        y = t.getY() - height;
                    }

                    if(this.intersectsBottomTile(t)){
                        y = t.getY() + height;
                        if(t.getId() == Id.fireTrap){
                            SoundHandler.getInstance().playSound("player_hurt");

                        }
                    }
                    if(this.intersectsRightTile(t)){
                        x = t.getX()+t.width;
                    }
                    if(this.intersectsLeftTile(t)){
                        x = t.getX()-t.width;
                    }
                }
            }
        }
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

    public boolean intersectsPoint(Point p){
        return p.getBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsTopPoint(Point p){
        return p.getTopBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsLeftPoint(Point p){
        return p.getLeftBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsRightPoint(Point p){
        return p.getRightBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsBottomPoint(Point p){
        return p.getBottomBoundary().intersects(this.getBoundary());
    }
    public void render(GraphicsContext gc){
        gc.setFill(Color.BLACK);
        gc.fillOval(this.getX(),this.getY(),this.getWidth(),this.getHeight());
    }
}
