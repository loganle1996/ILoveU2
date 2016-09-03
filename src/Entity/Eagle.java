/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Entity.Folow.FlyFollow;
import Entity.Folow.WalkingFollow;
import GraphicsforAnimation.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import tile.Tile;

/**
 *
 * @author LoganLe
 */
public class Eagle extends Entity{

    public Eagle(int width, int height, boolean solid, Id id, EntityHandler entityHandler) {
        super(width, height, solid, id, entityHandler);
//        this.isMovingLeft = true;
        this.setFollowSkill(new FlyFollow());
        this.setVelY(0);
        this.animate = true;
    }

    public Eagle(int x, int y, int width, int height, boolean solid, Id id, EntityHandler entityHandler) {
        super(x, y, width, height, solid, id, entityHandler);
        this.setFollowSkill(new FlyFollow());
        this.setVelY(0);
        this.animate = true;
    }

    @Override
    public void render(GraphicsContext g, Sprite[] eagleSprite) {
        if(this.facing == 0){
//            if(velX != 0){
//               g.drawImage(eagleSprite[frame+4].getImage(), x, y, width, height); 
//            }
//            else{
//                g.drawImage(eagleSprite[4].getImage(), x, y, width, height); 
//            }
            g.drawImage(eagleSprite[frame+4].getImage(), x, y, width, height); 
        }
        else if(this.facing == 1){              
//            if(velX != 0){
//                   g.drawImage(eagleSprite[frame+1].getImage(), x, y, width, height); 
//                }
//                else{
//                    g.drawImage(eagleSprite[0].getImage(), x, y, width, height);
//                } 
            g.drawImage(eagleSprite[frame].getImage(), x, y, width, height); 
        }    
    }

    @Override
    public void tick(long currentTime) {
        x += velX;
        y += velY;
        if(hp<= 0){
            this.die();
        }
        watchingAround();
        searchingEntities();
        tileCollidingChecking();
        if(this.isMovingLeft == true && this.isFreeze() == false)
        {
            this.moveLeft();
        }
        else if(this.isMovingRight == true && this.isFreeze() == false)
        {
            this.moveRight();
        }
        else if(freeze == true || (isMovingLeft == false && isMovingRight == false)) 
        {
            this.setVelX(0);
        }
//        if(velX != 0){
//            this.animate = true;
//        }
//        else{
//            this.animate = false;
//        }
        if(animate == true){
                frameDelay++;
                if(frameDelay >=7){
                    frame++;
                if(frame >=4){
                    frame = 0;
                }
                frameDelay = 0;
            }
        }
    }

    @Override
    public void shootFireBall(GraphicsContext gc) {
        
    }

    @Override
    public void shootIceBall(GraphicsContext gc) {
        
    }

    @Override
    public void placeSlowSpirit(GraphicsContext gc) {
        
    }
    
    public void searchingEntities(){
        for(Entity en: entityHandler.getEntity()){
            if(en.getId() == Id.player){
                if(this.foundObject(en)){
                    this.follow(en);
                }
            }
        }
    }
    public void tileCollidingChecking(){
        for(Tile t: tileHandler.getTile()){
            if((t.getId() == Id.wall ) && t.getType().equalsIgnoreCase("wall1")){
                if(this.intersectsTopTile(t)){
                    y = t.getY() - height;
                }
                if(this.intersectsBottomTile(t)){
                    y = t.getY() + height;
                }
                if(this.intersectsRightTile(t)){
                    this.setIsMovingRight(true);
                    this.setIsMovingLeft(false);
                    x = t.getX()+t.width;
                }
                if(this.intersectsLeftTile(t)){
                    this.setIsMovingLeft(true);
                    this.setIsMovingRight(false);
                    x = t.getX()-t.width;
                }
            }
        }
}
    @Override
    public void moveLeft(){
        this.facing = 0;
        if(this.isOnSlow() == true)
        {
            this.setVelX(-0.5);
        }
        else
        {
            this.setVelX(-3);
            this.setIsOnSlow(false);
        }
    }
    @Override
    public void moveRight(){
        this.facing = 1;
        if(this.isOnSlow() == true)
        {
            this.setVelX(0.5);
        }
        else
        {
            this.setVelX(3);
            this.setIsOnSlow(false);
        }
    }

    @Override
    public void swimUp() {

    }

    @Override
    public void swimDown() {

    }

    @Override
    public void notSwimUpDown() {

    }
}
