/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Target;

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
    private static HouseHandler houseHandler = new HouseHandler();
    
    private HouseHandler(){
        
    }
    //Mutators and accessors
    public LinkedList<AiHouse> getAiHouses() {
        return aiHouses;
    }

    public void emptyHandler()
    {
        copiedHouse = new LinkedList<>(aiHouses);
        for(AiHouse ai: copiedHouse)
        {
            copiedHouse.remove(ai);
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
