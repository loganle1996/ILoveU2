package ArcaneArena;

/**
 * Created by NTV on 05/9/2016.
 */

 import java.io.IOException;
 import java.net.URL;
 import java.util.ResourceBundle;
 import java.util.logging.Level;
 import java.util.logging.Logger;

 import ScoreData.ScoreData;
 import ScoreData.PlayerScoreData;
 import javafx.animation.FadeTransition;
 import javafx.collections.ObservableList;
 import javafx.event.ActionEvent;
 import javafx.fxml.FXML;
 import javafx.fxml.FXMLLoader;
 import javafx.fxml.Initializable;
 import javafx.scene.Parent;
 import javafx.scene.Scene;
 import javafx.scene.control.Button;
 import javafx.scene.control.TableColumn;
 import javafx.scene.control.TableView;
 import javafx.scene.layout.Pane;
 import javafx.stage.Stage;
 import javafx.util.Duration;

 /**
  *
  * @author NangTrongVuon
  */
 public class HighScoreScene implements Initializable{

     @FXML
     private Button backToMainMenuButton;

     @FXML
     private Pane highScorePane;

     @FXML
     private TableView<PlayerScoreData> highScoreTable;

     @FXML
     private TableColumn<PlayerScoreData, String> nameColumn;

     @FXML
     private TableColumn<PlayerScoreData, Integer> scoreColumn;

     @Override
     public void initialize(URL location, ResourceBundle resources) {

         ObservableList<PlayerScoreData> highScoreData = ScoreData.getInstance().getPlayerHighScoreData();
         highScoreTable.getItems().removeAll(highScoreData);
         nameColumn.setCellValueFactory(cellData -> cellData.getValue().playerNameProperty());
         scoreColumn.setCellValueFactory(cellData -> cellData.getValue().scoreProperty().asObject());

         highScoreTable.setItems(highScoreData);

     }

      @FXML
     private void handleBackToMenu(ActionEvent event) throws IOException {

             Stage stage = (Stage) highScorePane.getScene().getWindow();
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
                     root = FXMLLoader.load(getClass().getResource("HighScoreScene.fxml"));
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
