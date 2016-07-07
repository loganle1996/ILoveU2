/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArcaneArenaModel;

import ArcaneArenaModel.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
//import jdk.internal.org.objectweb.asm.Handle;

/**
 *
 * @author owne
 */
public class Player extends Entity {
    public Player(int x, int y, int width, int height, boolean solid,Id id,GameModel gameModel) {
        super(x, y, width, height, solid,id,gameModel);
    }

    @Override
    public void render(GraphicsContext g, Image player) {
        g.drawImage(player, x, y, width, height);
    }

    @Override
    public void tick() {
        x+= velX;
        y += velY;
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
            if(t.solid == false) break;
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
                        x = t.getX()+t.width;
                    }
                    if(this.intersectsLeftObject(t)){
                        setVelX(0);
                        x = t.getX()-t.width;
                        
                    }
                }
            }
            if(this.isJumping() == false){
                  this.setVelY(10.0);
            }
            if(this.isJumping() == true){
                this.setVelY(-10.0);
                
            }
        }
    }
}
