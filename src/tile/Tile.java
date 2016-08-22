/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile;



import MVCpattern.GameModel;
import Entity.Id;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;


/**
 *
 * @author owne
 */
public abstract class Tile implements Cloneable{
    public int x, y;
    public int width = 40, height = 40;
    public GameModel gameModel;
    TileHandler tileHandler = TileHandler.getInstance();
    public String type;
    private double hp = 1000;
    public boolean solid;
    public int velX,velY;
    public Id id;
    public boolean isAddedGameModel = true;
//    public Image image; //= new Image(imagePath);
    
    public Tile(int x, int y, int width, int height, boolean solid,Id id,TileHandler tileHandler,String type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.solid = solid;
        this.id = id;
        this.tileHandler = tileHandler;
        this.type = type;
    }
    public Tile(boolean solid,Id id){
        this.solid = solid;
        this.id = id;
    }
    public Tile(){
        
    }
    public abstract void render(GraphicsContext gc);
        
    public abstract void tick();
    public void die(){
        tileHandler.removeTile(this);
    }
    public void setId(Id id){
        this.id = id;
    }
    public Id getId(){
        return id;
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
    public Rectangle2D getBoundary(){
        return new Rectangle2D(getX(), getY(), width, height);
    }

    public Rectangle2D getTopBoundary(){
        return new Rectangle2D(this.getX() + 10,this.getY(),width-20,5);
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
    //is intersected
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Override
    public Object clone() {
      Object clone = null;
      
      try {
         clone = super.clone();
         
      } catch (CloneNotSupportedException e) {
         e.printStackTrace();
      }
      
      return clone;
      
   }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isIsAddedGameModel() {
        return isAddedGameModel;
    }

    public void setIsAddedGameModel(boolean isAddedGameModel) {
        this.isAddedGameModel = isAddedGameModel;
    }
    


}
