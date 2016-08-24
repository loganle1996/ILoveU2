/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Folow;

import Entity.Entity;

/**
 *
 * @author LoganLe
 */
public class FlyFollow implements Follow{

    @Override
    public void following(Entity follower, Entity followedEn) {
        if(followedEn.getX() < follower.getX()){
            follower.isMovingLeft = true;
            follower.isMovingRight = false;
        }
        else if(followedEn.getX() > follower.getX()){
            follower.isMovingRight = true;
            follower.isMovingLeft = false;
        }
        if(followedEn.getY() + 15 < follower.getY()){
            follower.setFlyUp(true);
            follower.setFlyDown(false);
            follower.flyUp();
            
        }
        else if(followedEn.getY() + 15 > follower.getY()+follower.getHeight()){
            follower.setFlyUp(false);
            follower.setFlyDown(true);
            follower.flyDown();
        }
        else if(followedEn.getY() + 15 < follower.getY()+follower.getHeight() && followedEn.getY() + 15 > follower.getY()){
            follower.setFlyUp(false);
            follower.setFlyDown(false);
            follower.stopFlyUpAndDown();
        }
    }


    
}
