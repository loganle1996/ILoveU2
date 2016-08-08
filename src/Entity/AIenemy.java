/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Bullet.Bullet;
import Bullet.bulletType;
import GraphicsforAnimation.Sprite;
import MVCpattern.GameModel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import tile.Tile;

/**
 *
 * @author owne
 */
public class AIenemy extends Entity {
    private int frame = 0;
    private int frameDelay = 0;
    private boolean animate = false;
    public AIenemy(int x, int y, int width, int height, boolean solid, Id id, EntityHandler entityHandler) {
        super(x, y, width, height, solid, id, entityHandler);
        this.setIsMovingLeft(true);
    }

    @Override
    public void render(GraphicsContext g,Sprite[] aiSprite) {
//        g.setFill(Color.BLUE);
//        g.fillRect(this.getX(), this.getY(), width, height);
        if(this.facing == 0 ){
             g.drawImage(aiSprite[frame+ 3].getImage(), x, y, width, height);
         }
         else if(this.facing == 1){
             g.drawImage(aiSprite[frame].getImage(), x, y, width, height);
         }
        
    }
    @Override
    public void shootFireBall(GraphicsContext gc) {
        
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        if(this.isMovingLeft == true){
            this.facing = 0;
            this.setVelX(-3);
        }
        else if(this.isMovingRight == true){
            this.facing = 1;
            this.setVelX(3);
        }
        else{
            this.setVelX(0);
        }
        if(this.getHp() <= 0){
            this.die();
        }

        //check if this collides tiles
        tileCollidingChecking();
        //animation
        if(velX != 0){
            this.animate = true;
        }
        else{
            this.animate = false;
        }
        if(animate == true){
            frameDelay++;
            if(frameDelay >=3){
                frame++;
            if(frame >=3){
                frame = 0;
            }
            frameDelay = 0;
        }
        }
        
    }
public void tileCollidingChecking(){
        for(Tile t: tileHandler.getTile()){
            if(t.solid == false){
                break;
            }
            else{
                if(t.getId() == Id.wall){
                    if(this.intersectsTopObject(t)){
                        y = t.getY() - height;
                        this.setVelY(10);
                        this.setJumping(false);
                    }    
                    if(this.intersectsBottomobject(t)){                       
                        y = t.getY() + height;
                    }
                    if(this.intersectsRightObject(t)){
                        this.setIsMovingRight(true);
                        this.setIsMovingLeft(false);
                    }
                    if(this.intersectsLeftObject(t)){
                        this.setIsMovingLeft(true);
                        this.setIsMovingRight(false);
                    }
                }
            }
        }
}
    
}

