/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import GraphicsforAnimation.Sprite;
import java.util.LinkedList;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author khoinguyen
 */
public class EntityHandler implements Cloneable{
    public LinkedList<Entity> entity = new LinkedList<Entity>();
    public LinkedList<Entity> copiedEntity;
    private static EntityHandler entityHandler = new EntityHandler();
    private EntityHandler(){
    }
    //add and remove objects
    public void addEntity(Entity en){
        entity.add(en);
    }
    public void removeEntity(Entity en){
        entity.remove(en);
    }
    //clone
    @Override
    public Object clone(){
      Object clone = null;
      
      try {
         clone = super.clone();
         
      } catch (CloneNotSupportedException e) {
         e.printStackTrace();
      }
      
      return clone;
    }
    //Acessors and mutators
    public LinkedList<Entity> getEntity() {
        return entity;
    }

    public void setEntity(LinkedList<Entity> entity) {
        this.entity = entity;
    }
    //Tick entities
    public void tickEntities(long currentime){
        copiedEntity = new LinkedList<Entity>(entity);
        for(Entity en: copiedEntity){
            en.tick(currentime);
        }
    }
    //render entities
    public void renderEntities(GraphicsContext g, Sprite[] playerSprite,Sprite[] crocodileSprite){
        for(Entity en: entity){
            if(en.getId()== Id.player){
                en.render(g,playerSprite );
            }
            else if(en.getId() == Id.Goomba){
                en.render(g, crocodileSprite);
            }
        }
    }
    //empty handler without the player
    public void emptyHandler(){
        copiedEntity = new LinkedList<Entity>(entity);
        for(Entity en: copiedEntity){
                if(en.getId() == Id.player){
                    en.healAndRefill();
                }
                entityHandler.removeEntity(en);
        }
    }
    public void editInstance(EntityHandler entityHandler){
        this.entityHandler = entityHandler;
    }
    //get instance method
    public static EntityHandler getInstance(){
        return entityHandler;
    }
}
