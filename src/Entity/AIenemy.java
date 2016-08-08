/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Bullet.Bullet;
import Bullet.bulletType;
import MVCpattern.GameModel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import tile.Tile;

/**
 *
 * @author owne
 */
public class AIenemy extends Entity {

    public AIenemy(int x, int y, int width, int height, boolean solid, Id id, EntityHandler entityHandler) {
        super(x, y, width, height, solid, id, entityHandler);
        this.setIsMovingLeft(true);
    }

    @Override
    public void render(GraphicsContext g) {
        g.setFill(Color.BLUE);
        g.fillRect(this.getX(), this.getY(), width, height);
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

