/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import GraphicsforAnimation.Sprite;
import javafx.scene.canvas.GraphicsContext;
import tile.Tile;

/**
 *
 * @author LoganLe
 */
public class FinalBoss extends AIenemy {
    private static FinalBoss finalBoss = new FinalBoss(50, 50, true, entityHandler);
    private FinalBoss(int width, int height, boolean solid, EntityHandler entityHandler) {
        super(width, height, solid, entityHandler);
        this.id = Id.GoombaBoss;
    }

    public static FinalBoss getInstance(){
        return finalBoss;
    }
    @Override
    public void render(GraphicsContext g,Sprite[] aiSprite) {
        if(this.facing == 0 ){
            //the number 0 in aiSprite[] will be replaced by frame variable
             g.drawImage(aiSprite[0].getImage(), x, y, width, height);
         }
         else if(this.facing == 1){
             g.drawImage(aiSprite[0].getImage(), x, y, width, height);
         }
    }
    @Override
    public void tileCollidingChecking(){
        for(Tile t: tileHandler.getTile()){
            if(t.getId() == Id.wall){
                if(this.intersectsTopTile(t)){
//                    this.setJumping(false);
                    y = t.getY() - height;
                }
                if(this.intersectsBottomTile(t)){
                    y = t.getY() + height;
                }
                if(this.intersectsRightTile(t)){
                    this.setIsMovingRight(true);
                    this.setIsMovingLeft(false);
                    x = t.getX()+t.width;
                        jump();
                }
                if(this.intersectsLeftTile(t)){
                    this.setIsMovingLeft(true);
                    this.setIsMovingRight(false);
                    x = t.getX()-t.width;
                        jump();
                }
            }
        }
    }
}
