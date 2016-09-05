package InfoBox;

import ArcaneArena.MainMenu;
import Map.GameMap;
import ScoreData.PlayerScoreData;
import ScoreData.ScoreData;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by LoganLe on 8/30/16.
 */
public class AlertBox{

    public void display(String title, String message, int score){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        Button yesButton = new Button("Yep");
        Button noButton = new Button("Nope");
        yesButton.setOnAction(e -> {
            GameMap gameMap = GameMap.getInstance();
            gameMap.resetMap();
            window.close();
        });
        noButton.setOnAction(e -> {

            ask(score);

            Platform.exit();
            System.exit(0);
            window.close();
        });
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,yesButton,noButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

    public void ask(int score)
    {
        // Asks for the player's name.
        TextInputDialog nameDialog = new TextInputDialog("Jack");
        nameDialog.setTitle("Thanks for playing!");
        nameDialog.setHeaderText("What is your name?");
        nameDialog.setContentText("Please enter your name:");

        Optional<String> nameResult = nameDialog.showAndWait();

        if (nameResult.isPresent() == false) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("What a shame..");
            alert.setHeaderText("I hope you'll come back and do better next time...");
            alert.setContentText("I'll be waiting..");

            alert.showAndWait();

            Platform.exit();
            System.exit(0);
        }

        String playerName = nameResult.get();
        int playerScore = score;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thanks for playing!");
        alert.setHeaderText("Thanks for playing Arcane Arena!");
        alert.setContentText("Thank you for giving us a chance, " + playerName + "! We hope to see you next time!");

        alert.showAndWait();

        // Add player's score and name to the data file.
        PlayerScoreData newScoreData = new PlayerScoreData();

        newScoreData.setPlayerScoreDataName(playerName);
        newScoreData.setScore(playerScore);

        ScoreData.getInstance().getScoreList().add(newScoreData);

        try {
            ScoreData.getInstance().writeHighScoreData("highScoreData.tmp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
