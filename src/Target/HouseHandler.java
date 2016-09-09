/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Target;

import Entity.Entity;
import Entity.EntityHandler;
import Entity.Id;
import Map.GameMap;
import java.util.LinkedList;
import javafx.scene.canvas.GraphicsContext;
import tile.Tile;

/**
 *
 * @author khoinguyen
 */
public class HouseHandler {
    LinkedList<AiHouse> aiHouses = new LinkedList<>();
    LinkedList<AiHouse> copiedHouse;
    LinkedList<AiHouse> copiedHouse2;
    private static HouseHandler houseHandler = new HouseHandler();
    
    private HouseHandler(){
        
    }
    //Mutators and accessors
    public LinkedList<AiHouse> getAiHouses() {
        return aiHouses;
    }

    public void emptyHandler()
    {
        copiedHouse2 = new LinkedList<>(aiHouses);
        for(AiHouse ai: copiedHouse2)
        {
            aiHouses.remove(ai);
        }
    }
    
    public void setAiHouses(LinkedList<AiHouse> aiHouses) {
        this.aiHouses = aiHouses;
    }
    //other methods
    public void addHouse(AiHouse aiHouse){
        aiHouses.add(aiHouse);
    }
    public void removeHouse(AiHouse aiHouse){
        aiHouses.remove(aiHouse);

        LinkedList<Entity> entity = EntityHandler.getInstance().getEntity();

        for (Entity eachentity: entity)
        {
            if (eachentity.getId() == Id.player)
            {
                eachentity.setScore(eachentity.getScore() + 10);
            }
        }
    }
    public void tickHouses(){
        copiedHouse = new LinkedList<AiHouse>(aiHouses);
        for(AiHouse aiHouse: copiedHouse){
            aiHouse.tick();
        }
    }
    public void renderHouses(GraphicsContext gc){
        copiedHouse = new LinkedList<>(aiHouses);
        for(AiHouse aiHouse: copiedHouse){
            aiHouse.render(gc);
        }
    }
    //get Instance
    public static HouseHandler getInstance(){
        return houseHandler;
    }

}
