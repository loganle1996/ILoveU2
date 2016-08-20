/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Folow;

import Entity.Entity;
import Entity.EntityHandler;
import Entity.Id;

/**
 *
 * @author LoganLe
 */
public class WalkingFollow implements Follow{
    EntityHandler entityHandler = EntityHandler.getInstance();
    @Override
    public void following(Entity follower, Entity followedEntity) {
                if(followedEntity.intersectsEntity(follower)){
                    follower.setIsMovingLeft(false);
                    follower.setIsMovingRight(false);
                }
                else if(followedEntity.getX() < follower.getX()){
                    follower.isMovingLeft = true;
                    follower.isMovingRight = false;
                    System.out.println("moving left");
                }
                else if(followedEntity.getX() > follower.getX()){
                    follower.isMovingRight = true;
                    follower.isMovingLeft = false;
                    System.out.println("moving right");
                }
                else if(followedEntity.getX() == follower.getX()){
                    follower.setIsMovingLeft(false);
                    follower.setIsMovingRight(false);
                }
    }
    
}
