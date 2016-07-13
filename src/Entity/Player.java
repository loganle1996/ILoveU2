/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import MVCpattern.GameModel;
import javafx.scene.canvas.GraphicsContext;
import tile.Tile;

/**
 *
 * @author owne
 */
public class Player extends Entity {
    private int frame = 0;
    private int frameDelay = 0;
    private boolean animate = false;
    public boolean touchingGround;
    public Player(int x, int y, int width, int height, boolean solid,Id id,GameModel gameModel) {
        super(x, y, width, height, solid,id,gameModel);
    }

    @Override
    public void render(GraphicsContext g ) {
        if(this.facing == 0 ){
            g.drawImage(GameModel.playerSprite[frame+ 3].getImage(), x, y, 40, 40);
        }
        else if(this.facing == 1){
            g.drawImage(GameModel.playerSprite[frame].getImage(), x, y, 40, 40);
        }
    }

    public void jump() {

        if (this.touchingGround) {

            this.setVelY(-20);
            this.setFalling(true);
            this.setJumping(true);
        }
    }

    @Override
    public void tick() {

        x += velX;

//        if (this.isFalling()) {

        // Simulates gravity
        velY += this.getGravity() / 10;
//        }

        y += velY;

        if (this.velY > 0) {
            this.setJumping(false);
            this.setFalling(true);
            this.touchingGround = false;
        }


//        System.out.println(this.getVelY());
//        System.out.println("jumping: " + this.isJumping());
//        System.out.println("falling: " + this.isFalling());

        if (this.isMovingLeft()) {
            this.setVelX(-5.0);
            this.facing = 0;
        } else if (this.isMovingRight()) {
            this.setVelX(5);
            this.facing = 1;
        } else {
            this.setVelX(0);
        }

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

        //Check if the player collides with tiles
        tileCollidingChecking();

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
    public void tileCollidingChecking() {
        for (Tile t : gameModel.getTileList()) {
            if (t.solid == false) {
                break;
            } else {
                if (t.getId() == Id.wall) {

//                    double currentLocation = 0.0;
                    if (this.intersectsTopObject(t)) {

                        y = t.getY() - height;

                        this.setVelY(0);
                        this.setFalling(false);
                        this.setJumping(false);
                        this.touchingGround = true;

                    }

                }
                if (this.intersectsBottomObject(t)) {
                    setVelY(0);
                    y = t.getY() + height;
                    System.out.println("Touched bottom");
                }
                if (this.intersectsRightObject(t)) {
                    setVelX(0);
                    x = t.getX() + t.width;
                }
                if (this.intersectsLeftObject(t)) {
                    setVelX(0);
                    x = t.getX() - t.width;
                }
            }
        }

    }




//public void enemyCollidingChecking(){
//
//        }
//
}