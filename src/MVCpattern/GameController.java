/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVCpattern;

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
    public void tickModelGame(){
        gameModel.TickModelGame();
    }
    public LinkedList getTileList(){
        return gameModel.getTileList();
    }
    public void setTileList(LinkedList tileList){
        gameModel.setTile(tileList);
    }
    public LinkedList getEntityList(){
        return gameModel.getEntity();
    }
    public void setEntityList(LinkedList entityList){
        gameModel.setEntity(entityList);
    }
    public void renderGameScene(){
        //display all game graphics
        gameView.display(gameModel);
    }
}
