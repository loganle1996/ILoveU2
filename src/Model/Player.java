/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.GameView;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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
    public void render(GraphicsContext g, Image player) {
         if(this.facing == 0 ){
             g.drawImage(GameView.playerSprite[frame+ 3].getImage(), x, y, width, height);
         }
         else if(this.facing == 1){
             g.drawImage(GameView.playerSprite[frame].getImage(), x, y, width, height);
         }
           // g.drawImage(player, x, y, width, height);
    }

    @Override
    public void tick() {
        x+= velX;
        y += velY;
        if(velX != 0){
            this.animate = true;
        }
        else{
            this.animate = false;
        }
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
        for(Tile t: gameModel.getTileList()){
            if(t.solid == false){
                break;
            }
            else{
                if(t.getId() == Id.wall){
                    if(this.intersectsTopObject(t)){
                        y = t.getY() - height;
                    }    
                    if(this.intersectsBottomobject(t)){                       
                        setVelY(0);
                        y = t.getY() + height;
                    }
                    if(this.intersectsRightObject(t)){
                        setVelX(0);
                        x = t.getX() + t.width;
                    }
                    if(this.intersectsLeftObject(t)){
                        setVelX(0);
                        x = t.getX() - t.width;
                    }
                }
            }
            if(this.isJumping() == false) {
                    this.setVelY(10.0);

            }

            if  (this.isJumping() == true){
                    this.setVelY(-10.0);
            }

           if (this.isMovingLeft) {
               this.setVelX(-5.0);
           } else if (this.isMovingRight) {
               this.setVelX(5);
               this.facing = 1;
           } else {
               this.setVelX(0);
           }



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
}
