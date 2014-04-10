package fr.unice.polytech.devint.cassebriquesdevint;

import javax.sound.sampled.Clip;

/**
 * Created by Loic GAILLARD on 10/04/14.
 */
public class Sound {
    public Clip clip;

    public void play() {
        try {
            if (clip != null) {
                new Thread() {
                    public void run() {
                        synchronized (clip) {
                            clip.stop();
                            clip.setFramePosition(0);
                            clip.start();
                        }
                    }
                }.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
