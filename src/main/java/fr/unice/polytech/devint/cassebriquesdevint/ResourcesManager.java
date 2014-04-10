package fr.unice.polytech.devint.cassebriquesdevint;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Loic GAILLARD on 26/03/14.
 */
public class ResourcesManager {
    private static ResourcesManager instance;
    private Map<String, Bitmap> bitmaps;
    private Map<String, Sound> sounds;

    private ResourcesManager() {
        bitmaps = new HashMap<>();
        sounds = new HashMap<>();
    }

    public static ResourcesManager getInstance() {
        if(instance == null) {
            instance = new ResourcesManager();
        }

        return instance;
    }

    public void loadBitmaps() {
        //loadBitmap("screen_grid_test.png");
    }

    public void cacheSounds() {
        loadSound("brick.wav");
        loadSound("wall.wav");
        loadSound("paddle.wav");
        loadSound("miss.wav");
        loadSound("win.wav");
        loadSound("loss.wav");
    }

    public Bitmap getBitmap(String filename) {
        Bitmap bitmap = bitmaps.get(filename);

        if(bitmap == null) {
            bitmap = loadBitmap(filename);
        }

        return bitmap;
    }

    private Bitmap loadBitmap(String filename) {
        try {
            File file = new File("textures/"+filename);
            BufferedImage img = ImageIO.read(file);

            int w = img.getWidth();
            int h = img.getHeight();

            Bitmap bitmap = new Bitmap(w, h);
            img.getRGB(0, 0, w, h, bitmap.pixels, 0, w);
            for (int i = 0; i < bitmap.pixels.length; i++) {
                int in = bitmap.pixels[i];
                int col = (in & 0xffffff);
                if (in == 0xffff00ff) col = -1;
                bitmap.pixels[i] = col;
            }
            bitmaps.put(filename, bitmap);
            return bitmap;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Sound getSound(String filename) {
        Sound sound = sounds.get(filename);

        if(sound == null) {
            sound = loadSound(filename);
        }

        return sound;
    }

    private Sound loadSound(String filename) {
        Sound sound = new Sound();
        try {
            File file = new File("sounds/"+filename);
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            sound.clip = clip;
            sounds.put("filename", sound);
            return sound;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
