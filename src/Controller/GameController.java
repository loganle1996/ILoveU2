/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.GameModel;
import View.GameView;

/**
 *
 * @author owne
 */
public class GameController {
    private final GameView gameView;
    private final GameModel gameModel;
    
    public GameController(GameView view,GameModel model){
        this.gameView = view;
        this.gameModel = model;
    }
    public void renderGameScene(){
        //display all game graphics
        gameView.display(gameModel);
    }
}
