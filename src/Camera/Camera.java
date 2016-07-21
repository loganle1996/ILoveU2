/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Camera;

import Entity.Entity;

/**
 *
 * @author Loganle
 */
public class Camera {
    private double x=0.0,y=0.0;

    private Entity player;
    

    public Camera(Entity player){
        this.player = player;
    }
    
    public void tick(){
        System.out.println("camera tick");
        this.setX(-this.player.getX()+ MVCpattern.GameModel.WIDTH/4);
        this.setY(-this.player.getY() + MVCpattern.GameModel.HEIGHT/4);
        
//        for(Entity en : gameModel.getEntity()){
//            if(en.getId()== Id.player){
//                
//            }
//        }
    }
    //Accessors and mutators
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
}
