/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Target;

import Entity.EntityHandler;
import Entity.Id;
import Map.GameMap;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tile.Tile;
import tile.TileHandler;

/**
 *
 * @author khoinguyen
 */
public class AiHouse {
    private final int width = 40,height = 40;
    private double x,y;
    private Image houseImage;
    private int id;
    public HouseHandler houseHandler = HouseHandler.getInstance();
    public TileHandler tileHandler = TileHandler.getInstance();
    public EntityHandler entityHandler = EntityHandler.getInstance();
    public double hp = 4000;
    public GameMap gameMap = GameMap.getInstance();
    public AiHouse(double x, double y, Image houseImage) {
        this.x = x;
        this.y = y;
        this.houseImage = houseImage;
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

    public Image getHouseImage() {
        return houseImage;
    }

    public void setHouseImage(Image houseImage) {
        this.houseImage = houseImage;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }
    
    //other methods
    public void die(){
        houseHandler.removeHouse(this);
    }
    public void tick(){
        if(this.getHp()<=0){
            this.die();
            gameMap.changeMap();
        }
        tileCollidingCheck();
    }
    public void render(GraphicsContext gc){
        gc.drawImage(houseImage, x, y, width, height);
    }
    public void tileCollidingCheck(){
        for(Tile t: tileHandler.getTile()){
            if(t.solid == false){
            }
            else{
                if(t.getId() == Id.wall){
                    if(this.intersectsTopTile(t)){
                        y = t.getY() - height;
                    }    
                    if(this.intersectsBottomTile(t)){                       
                        y = t.getY() + height;
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
    //BOUNDARY
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
    
}
