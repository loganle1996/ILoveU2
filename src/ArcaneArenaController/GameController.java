/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArcaneArenaController;


import ArcaneArenaView.GameView;
import ArcaneArenaModel.GameModel;
import javafx.animation.AnimationTimer;

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
    public void renderGameScene(){
        //display all game graphics
        gameView.display(gameModel);
    }
    public void updateGame(){
        gameModel.updateModelGame();
    }  
}
