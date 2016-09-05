package ScoreData;

/**
 * Created by NTV on 05/9/2016.
 */

 import java.io.Externalizable;
 import java.io.IOException;
 import java.io.ObjectInput;
 import java.io.ObjectOutput;
 import java.io.Serializable;
 import javafx.beans.property.IntegerProperty;
 import javafx.beans.property.SimpleIntegerProperty;
 import javafx.beans.property.SimpleStringProperty;
 import javafx.beans.property.StringProperty;

 /**
  *
  * @author NangTrongVuon
  */
 public class PlayerScoreData implements Externalizable {
     private StringProperty playerName;
     private IntegerProperty score;

     public PlayerScoreData() {
         this.playerName = new SimpleStringProperty("lol");
         this.score = new SimpleIntegerProperty(0);
     }

     public PlayerScoreData(String playerName, String difficultyName, Integer score) {
         this.playerName = new SimpleStringProperty(playerName);
         this.score = new SimpleIntegerProperty(score);
     }

     public String getPlayerScoreDataName() {
         return playerName.get();
     }

     public void setPlayerScoreDataName(String playerName) {
         this.playerName.set(playerName);
     }

     public StringProperty playerNameProperty() {
         return playerName;
     }

     public int getScore() {
         return score.get();
     }

     public void setScore(int score) {
         this.score.set(score);
     }

     public IntegerProperty scoreProperty() {
         return score;
     }

     @Override
     public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(playerName.get());
        out.writeObject(score.get());
     }

     @Override
     public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
         setPlayerScoreDataName((String) in.readObject());
         setScore((int) in.readObject());
     }
 }
