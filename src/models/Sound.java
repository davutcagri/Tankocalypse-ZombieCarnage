package models;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    private Clip clip;
    private final URL[] soundURL = new URL[5];

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/zombieAttack.wav");
        soundURL[1] = getClass().getResource("/sound/zombieDeath.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.start();
        }
    }
}
