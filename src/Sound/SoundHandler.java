package Sound;

import javafx.scene.media.AudioClip;
import java.io.File;
import java.util.HashMap;

/**
 * Created by NangTrongVuon on 17/8/16.
 */
public class SoundHandler
{

    HashMap<String,AudioClip> soundEffectsMap = new HashMap<>();
    public void loadSound(String id)
    {
        AudioClip sound = new AudioClip(new File("src/" + id + ".wav").toURI().toString());
        soundEffectsMap.put(id, sound);

    }

    public void playSound(String id)
    {
        soundEffectsMap.get(id).play();
    }

}
