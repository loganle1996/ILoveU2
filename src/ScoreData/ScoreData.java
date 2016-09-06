package ScoreData;

/**
 * Created by NTV on 05/9/2016.
 */

 import java.io.EOFException;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.ObjectInputStream;
 import java.io.ObjectOutputStream;
 import java.util.ArrayList;
 import javafx.collections.FXCollections;
 import javafx.collections.ObservableList;

 /**
  *
  * @author NangTrongVuon
  */
 public class ScoreData {


   // Singleton implementation - Since only one instance of this class is needed at any point.

     private static ScoreData scoreData = new ScoreData();

     ObservableList<PlayerScoreData> playerHighScoreData = FXCollections.observableArrayList();
     ArrayList<PlayerScoreData> scoreList = new ArrayList<>();

     private ObservableList<PlayerScoreData> getHighScoreData() {

         return playerHighScoreData;
     }

     // Writes the high score data to a file.
     public void writeHighScoreData(String fileName) throws IOException {
         FileOutputStream fos = null;

         try {
             fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos);
             oos.writeObject(this.getScoreList());
         } finally {
             if (fos != null) {
                 fos.close();
             }
         }
     }

     // Reads the high score data from a file.
     public void readHighScoreData(String fileName) throws IOException, ClassNotFoundException {
          FileInputStream fis = null;

         try {
             fis = new FileInputStream(fileName);
             while (true) {
                 ObjectInputStream ois = new ObjectInputStream(fis);
                 this.setScoreList((ArrayList<PlayerScoreData>) ois.readObject());

             }

         } catch (EOFException ignored) {
             // It will eventually run to end of file.
         } finally {
             if (fis != null) {
                 fis.close();
             }
         }

         this.getPlayerHighScoreData().clear();

         for (PlayerScoreData player: this.getScoreList()) {
             this.getPlayerHighScoreData().add(player);
         }
     }

     public ArrayList<PlayerScoreData> getScoreList() {
         return scoreList;
     }

     public ScoreData getScoreData() {
         return scoreData;
     }

     public void setScoreData(ScoreData scoreData) {
         ScoreData.scoreData = scoreData;
     }

     public ObservableList<PlayerScoreData> getPlayerHighScoreData() {
         return playerHighScoreData;
     }

     public void setPlayerHighScoreData(ObservableList<PlayerScoreData> playerHighScoreData) {
         this.playerHighScoreData = playerHighScoreData;
     }

     public void setScoreList(ArrayList<PlayerScoreData> scoreList) {
         this.scoreList = scoreList;
     }

     public static ScoreData getInstance()
     {
         return scoreData;
     }

 }
