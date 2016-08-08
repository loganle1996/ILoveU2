/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArcaneArena;

import MVCpattern.GameController;
import MVCpattern.GameModel;
import MVCpattern.GameView;
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
public class MainMenu implements Initializable {

//    public static void main(String[] args) {
//        GameModel model = new GameModel();
//        GameView view = new GameView();
//        GameController controller = new GameController(view, model);
//        controller.renderGameScene();
//    }

    @FXML
    private Button startButton;

    @FXML
    private Button howToPlayButton;

    @FXML
    private Button highScoresButton;

    @FXML
    private Button quitButton;

    @FXML
    private Label wouldYouKindlyTitle;

    @FXML
    private Label authorTitle;

    @FXML
    private Label versionLabel;

    @FXML
    private VBox menuVbox;

    @FXML
    private Pane menuScenePane;

    @FXML
    private Pane howToPlayPane;

    @FXML
    private Pane highScorePane;

    public static void launchGame() {
        GameModel model = new GameModel();
        GameView view = new GameView();
        GameController controller = new GameController(view, model);
        controller.renderGameScene();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleStartButtonAction(ActionEvent event) throws IOException {

            Stage stage = (Stage) startButton.getScene().getWindow();
            Parent root = null;

            if (event.getSource() == startButton) {
                try {
                    // Get the reference to the button's stage
                    root = FXMLLoader.load(getClass().getResource("GameScene.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            // Create a new scene with root.
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

    }

//    @Override
//    public void start(Stage primaryStage) {
//
//    }

}
