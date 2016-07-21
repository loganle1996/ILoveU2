/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArcaneArena;

import MVCpattern.GameController;
import MVCpattern.GameModel;
import MVCpattern.GameView;

/**
 *
 * @author owne
 */
public class Main {

    public static void main(String[] args) {
        GameModel model = new GameModel();
        GameView view = new GameView();
        GameController controller = new GameController(view, model);
        controller.renderGameScene();
    }

}
