/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArcaneArena;

import MVCpattern.GameController;
import MVCpattern.GameModel;
import MVCpattern.GameView;
import Map.GameMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author owne
 */
public class MainMenu extends Application{
    public static Stage mainStage;
    static Scene mainScene;
    private GameController gameController;
    public static GameModel gameModel;
    private GameView gameView;
    private static MainMenu mainMenu = new MainMenu();
    @FXML
    public  void loadGame() throws Exception{
        gameModel = new GameModel();
        gameView = new GameView();
        gameController = new GameController(gameView, gameModel);
        gameController.renderGameScene();
//        mainStage.close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        mainStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MenuScene.fxml"));
        mainScene = new Scene(root);

        Platform.setImplicitExit(false);
        mainStage.setScene(mainScene);
        mainStage.setTitle("Arcane Arena");
        mainStage.show();

        mainStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }
    private void restartMap(){
        gameController.restartGame();
    }
    @FXML
    private void chooseMap(String map){
        gameController.chooseMap(map);
    }
    @FXML
    private void quitGame(ActionEvent event)
    {
        Platform.exit();
        System.exit(0);
    }
    public static void main(String[] args) {
        launch(args);
    }
    public static MainMenu getInstance(){
        return mainMenu;
    }
    public static GameModel getGameModel(){
        return gameModel;
    }
}
