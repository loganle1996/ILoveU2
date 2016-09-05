/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WouldYouKindly;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import ArcaneArena.MainMenu;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author NangTrongVuon
 */
public class HowToPlayScene implements Initializable{

    @FXML
    private Button backToMainMenuButton;
    
    @FXML
    private Pane howToPlayPane;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
     @FXML
    private void handleBackToMenu(ActionEvent event) throws IOException {

            Stage stage = (Stage) howToPlayPane.getScene().getWindow();
            Parent root = null;

            if (event.getSource() == backToMainMenuButton) {
                try {
                    // Get the reference to the button's stage
                    root = FXMLLoader.load(getClass().getResource("MenuScene.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                try {
                    root = FXMLLoader.load(getClass().getResource("HowToPlayScene.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

           
            // Create a new scene with root.
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

    }
    
    
}
