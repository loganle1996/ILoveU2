/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVCpattern;

import Map.GameMap;
import java.util.LinkedList;

/**
 *
 * @author owne
 */
public class GameController {
    private GameView gameView;
    private GameModel gameModel;
    
    public GameController(GameView view,GameModel model){
        this.gameView = view;
        this.gameModel = model;
    }
    public void tickModelGame(long currentime){
        gameModel.TickModelGame(currentime);
    }
    public LinkedList getTileList(){
        return gameModel.getTileHandler().getTile();
    }
    public void setTileList(LinkedList tileList){
        gameModel.getTileHandler().getTile();
    }
    public LinkedList getEntityList(){
        return gameModel.getEntityHandler().getEntity();
    }
    public void setEntityList(LinkedList entityList){
        gameModel.getEntityHandler().setEntity(entityList);
    }
    public void chooseMap(GameMap gameMap){
        gameModel.setGameMap(gameMap);
    }
    public void restartGame(){
        gameModel.gameMap.resetMap();
    }
    public void renderGameScene(){
        //display all game graphics
        gameView.display(gameModel);
    }
}
