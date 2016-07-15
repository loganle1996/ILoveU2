/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Goomba;

import Entity.Entity;
import Entity.Id;
import MVCpattern.GameModel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import tile.Tile;

/**
 *
 * @author owne
 */
public class AIenemy extends Entity {

    public AIenemy(int x, int y, int width, int height, boolean solid, Id id, GameModel gameModel) {
        super(x, y, width, height, solid, id, gameModel);
        this.setIsMovingLeft(true);
        this.setIsMovingRight(false);
    }

    @Override
    public void render(GraphicsContext g) {
        g.setFill(Color.BLUE);
        g.fillRect(this.getX(), this.getY(), width, height);
    }

    @Override
    public void tick() {
        x+= getVelX();
        y+= getVelY();        
//        if(this.isMovingLeft){
//            this.setVelX(-2.0);
//            this.facing = 0;
//        }
//        else if(this.isMovingRight == true){
//            this.setVelX(2.0);
//            this.facing = 1;
//        }
            if (this.isMovingLeft == true && this.isCollidingRight() == false) {
                this.setVelX(-2.0);
                this.facing = 0;
            } else if (this.isMovingRight == true && this.isCollidingLeft() == false) {
                this.setVelX(2);
                this.facing = 1;
            }
            else {
                this.setVelX(0);
            }
        
        checkTileColliding();
        for(Entity e : gameModel.getEntity()){
            if(e.getId() == Id.player){
                if(this.intersectsTopEntity(e)){
                    //y = e.getY() - height;
                    this.setVelY(0);
                }    
                else if(this.intersectsBottomEntity(e)){                       
                    //y = e.getY() + height;
                    this.setVelY(0);
                }
                else if(this.intersectsRightEntity(e)){
                    //x = e.getX()+e.width;
                    //this.setVelX(0);
                    this.setCollidingRight(true);
                }
                else if(this.intersectsLeftEntity(e)){
                    //x = e.getX()-e.width;
                    //this.setVelX(0);
                    this.setCollidingLeft(true);
                }
                else{
                    this.setCollidingLeft(false);
                    this.setCollidingRight(false);
                    this.setCollidingTop(false);
                    this.setCollidingBottom(false);
                }
            }
        }
    }
    public void checkTileColliding(){
        for(Tile t: gameModel.getTileList()){
            if(t.solid == false){
                break;
            }
            else{
                if(t.getId() == Id.wall){
                    if(this.intersectsTopObject(t)){
                        y = t.getY() - height;
                    }    
                    else if(this.intersectsBottomobject(t)){ 
                        y = t.getY() + height;
                    }
                    else if(this.intersectsRightObject(t)){
                        setVelX(0);
                        x = t.getX()+t.width;
                        this.setIsMovingLeft(false);
                        this.setIsMovingRight(true);
                    }
                    else if(this.intersectsLeftObject(t)){
                        setVelX(0);
                        x = t.getX()-t.width;
                        this.setIsMovingRight(false);
                        this.setIsMovingLeft(true);
                    }
                }
            }          
        }
    }
    
}
