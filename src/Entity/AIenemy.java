/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

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
    }

    @Override
    public void render(GraphicsContext g) {
        g.setFill(Color.BLUE);
        g.fillRect(this.getX(), this.getY(), width, height);
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

        //check if this collides tiles
        tileCollidingChecking();
        //check if this collides player
   //     playerCollidingChecking();
    }
public void tileCollidingChecking(){
        for(Tile t: gameModel.getTileList()){
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
                        //x = t.getX()+t.width;
                        this.setIsMovingRight(true);
                        this.setIsMovingLeft(false);
                        System.out.println("intersected right object");
                    }
                    if(this.intersectsLeftObject(t)){
                        //x = t.getX()-t.width;
                        this.setIsMovingLeft(true);
                        this.setIsMovingRight(false);
                        System.out.println(" intersected left object");
                    }
                }
            }
        }
}
    public void playerCollidingChecking(){
        for(Entity en : gameModel.getEntity()){
            if(en.getId() == Id.player){
                    if(this.intersectsTopEntity(en)){
                        y = en.getY() - height;
                        this.setVelY(10);
                        this.setJumping(false);
                        this.setHp(this.getHp() - 10);
                    }    
                    else if(this.intersectsBottomEntity(en)){                       
                        setVelY(0);
                        y = en.getY() + height;
                       this.setHp(this.getHp() - 10);
                    }
                    else if(this.intersectsRightEntity(en)){
//                        this.setIsCollidingRight(true);
//                        this.setIsMovingLeft(false);
                        System.out.println("X: "+this.getVelX());
//                        x = en.getX()+en.width;
                        this.setIsMovingLeft(false);
                        this.setHp(this.getHp() - 10); 
                    }
                    else if(this.intersectsLeftEntity(en)){
//                        this.setIsCollidingLeft(true);
//                        this.setIsMovingRight(false);
                        this.setIsMovingRight(false);
                        System.out.println("X: "+this.getVelX());
//                        x = en.getX()-en.width;   
                        this.setHp(this.getHp() - 10);
                    }
//                    else{
//                      this.setIsMovingRight(true);
//                    }
            }
        }
    }
    
}

