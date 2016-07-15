/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Graphics.Sprite;
import MVCpattern.GameModel;
import MVCpattern.GameView;
import javafx.scene.canvas.GraphicsContext;
import tile.Tile;
//import jdk.internal.org.objectweb.asm.Handle;

/**
 *
 * @author owne
 */
public class Player extends Entity {
    private int frame = 0;
    private int frameDelay = 0;
    private boolean animate = false;
    public Player(int x, int y, int width, int height, boolean solid,Id id,GameModel gameModel) {
        super(x, y, width, height, solid,id,gameModel);
    }

    @Override
    public void render(GraphicsContext g ) {
         if(this.facing == 0 ){
             g.drawImage(GameModel.playerSprite[frame+ 3].getImage(), x, y, width, height);
         }
         else if(this.facing == 1){
             g.drawImage(GameModel.playerSprite[frame].getImage(), x, y, width, height);
         }
    }
    
    public void jump() {
        this.setVelY(-15);
    }

    @Override
    public void tick() {
        x+= velX;
        y += velY;
        System.out.println("hp: "+this.getHp());
        if(x<=0){
            x = 0;
        }
        if( y <= 0 ){
            y = 0;
        }
        if(x + width>= 800){
            x = 800 - width;
        }
        if(y +height>= 800){
            y = 800 - height;
        }
          if(hp == 0){
              this.die();
          }
          if (this.isMovingLeft == true && this.isCollidingRight() == false) {
                this.setVelX(-5.0);
                this.facing = 0;
            } 
           else if (this.isMovingRight == true && this.isCollidingLeft() == false) {
                this.setVelX(5);
                this.facing = 1;
            }
            else if (this.isJumping() == true && this.isCollidingBottom() == false && this.isCollidingTop() == true && this.isFalling()== false) {
                 //this.setY(this.getY() + 5);
            }
            else if(this.isFalling() == true && this.isCollidingTop() == false&& this.isJumping() == false){
                setVelY(this.getVelY() - this.getGravity());
            }
            else {
                this.setVelY(0);
                this.setVelX(0);
            }
        
        if(velX != 0){
            this.animate = true;
        }
        else{
            this.animate = false;
        }
        //Check if the player collides with tiles
        tileCollidingChecking();
        for(Entity e : gameModel.getEntity()){
            if(e.getId() == Id.Goomba){
                if(this.intersectsTopEntity(e)){
                    //y = e.getY() - height;
                    //y = e.getY() - height;
                    this.setCollidingTop(true);
                    this.setFalling(false);
                    System.out.println("player collides top object");
                }    
                else if(this.intersectsBottomEntity(e)){                       
                    //y = e.getY() + height;
                   // y = e.getY() + height;
                    this.setCollidingBottom(true);
                    this.setJumping(false);
                    System.out.println("player collides bottom object");
                }
                else if(this.intersectsRightEntity(e)){
                   // x = e.getX()+e.width;
                   this.setHp(this.getHp() - 10);
                   this.setCollidingRight(true);
                   System.out.println("player collides right object");
                }
                else if(this.intersectsLeftEntity(e)){
                    //x = e.getX()-e.width;
                    this.setHp(this.getHp() - 10);
                    this.setCollidingLeft(true);
                    System.out.println("player collides left object");
                }
                else{
                    this.setCollidingTop(false);
                    this.setCollidingBottom(false);
                    this.setCollidingRight(false);
                    this.setCollidingLeft(false);
                }
            }
        }
        //check if the player collides with enemies
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
        for(Tile t: gameModel.getTileList()){
            if(t.solid == false){
                break;
            }
            else{
                if(t.getId() == Id.wall){
                    if(this.intersectsTopObject(t)){
//                        y = t.getY() - height;
//                        this.setVelY(10);
//                        this.setJumping(false);
                          this.setCollidingTop(true);
                    }    
                    if(this.intersectsBottomobject(t)){                       
//                        setVelY(0);
//                        y = t.getY() + height;
                          this.setCollidingBottom(true);
                    }
                    if(this.intersectsRightObject(t)){
//                        setVelX(0);
//                        x = t.getX()+t.width;
                          this.setCollidingRight(true);
                    }
                    if(this.intersectsLeftObject(t)){
//                        setVelX(0);
//                        x = t.getX()-t.width; 
                          this.setCollidingLeft(true);
                          
                    }
                }
            }          
        }
    }
    public void enemyCollidingChecking(){
        
    }
}
