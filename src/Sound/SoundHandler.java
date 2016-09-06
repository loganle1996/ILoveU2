package Sound;

import javafx.animation.Animation;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.HashMap;

/**
 * Created by NangTrongVuon on 17/8/16.
 */
public class SoundHandler
{

    private static SoundHandler soundHandler  = new SoundHandler();
    HashMap<String,AudioClip> soundEffectsMap = new HashMap<>();
    MediaPlayer backgroundPlayer;

    // Loads sound using an id - the filename
    public void loadSound(String id)
    {
        String audioClipPath = getClass().getResource("/" + id + ".wav").toExternalForm();
        System.out.println(audioClipPath);
        AudioClip sound = new AudioClip(audioClipPath);
        soundEffectsMap.put(id, sound);
    }

    public void loadAllSounds()
    {
      String[] soundArray = new String[] {"player_death", "eagle_death", "fireball", "iceball", "footstep", "jump", "monster_death", "monster_hurt", "player_hurt", "pickup", "swimming"};

      for (String sound : soundArray)
      {
        SoundHandler.getInstance().loadSound(sound);
      }
    }



    public void playSound(String id)
    {
        if (!soundEffectsMap.get(id).isPlaying())
        {
            soundEffectsMap.get(id).setVolume(1);
            soundEffectsMap.get(id).play();
        }

    }

    public void playBackgroundMusic(String id)
    {
      String backgroundPath = getClass().getResource("/" + id + ".mp3").toExternalForm();
      Media backgroundFile = new Media(backgroundPath);
      this.backgroundPlayer = new MediaPlayer(backgroundFile);
      MediaPlayer background = this.backgroundPlayer;
      background.setCycleCount(Animation.INDEFINITE);
      background.setVolume(0.5);
      background.setAutoPlay(true);
    }

    public void stopBackgroundMusic()
    {
     backgroundPlayer.stop();
    }




    public void editInstance(SoundHandler soundHandler){
        this.soundHandler = soundHandler;
    }

    public static SoundHandler getInstance()
    {
      return soundHandler;
    }

}
