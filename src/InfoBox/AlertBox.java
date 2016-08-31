package InfoBox;

import ArcaneArena.MainMenu;
import Map.GameMap;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by LoganLe on 8/30/16.
 */
public class AlertBox{
    public void display(String title, String message){
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

}
