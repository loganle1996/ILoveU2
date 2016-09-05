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
import ScoreData.PlayerScoreData;
import ScoreData.ScoreData;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private GameMap gameMap;

    private static MainMenu mainMenu = new MainMenu();
    @FXML
    public void loadGame() throws Exception{

        gameModel = new GameModel();
        gameView = new GameView();
        gameController = new GameController(gameView, gameModel);

        mapSelection();

        gameController.renderGameScene();
//        mainStage.close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        mainStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MenuScene.fxml"));
        mainScene = new Scene(root);

        // Tries to read score data from a file.
        try {
           ScoreData.getInstance().readHighScoreData("highScoreData.tmp");
        } catch (IOException ex) {
            System.out.println("No high score data found.");
//            Logger.getLogger(HighScoreScene.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HighScoreScene.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    private void mapSelection()
    {
        String mapName = "";

        List<String> choices = new ArrayList<>();

        choices.add("Map 1");
        choices.add("Map 2");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Map 1", choices);
        dialog.setTitle("Map Selection");
        dialog.setHeaderText("Please select what map you want?");
        dialog.setContentText("Choose your map: ");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent())
        {
            mapName = result.get();
        }
        switch (mapName)
        {
            case "Map 1":
                gameController.chooseMap("map1");
                break;
            case "Map 2":
                gameController.chooseMap("map2");
                break;
            default:
                gameController.chooseMap("map1");
                break;
        }
    }

    @FXML
    private void viewHighScores(ActionEvent event) throws IOException
    {

          Stage stage = mainStage;
          Parent root = null;

              try {
                  // Get the reference to the button's stage
                  root = FXMLLoader.load(getClass().getResource("HighScoreScene.fxml"));
              } catch (IOException ex) {
                  Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
              }


          // Create a new scene with root.
          Scene scene = new Scene(root);
          stage.setScene(scene);
          stage.show();

    }

    @FXML
    private void showHowToPlay(ActionEvent event) throws IOException
    {

        Stage stage = mainStage;
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("HowToPlayScene.fxml"));


        // Create a new scene with root.
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

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
