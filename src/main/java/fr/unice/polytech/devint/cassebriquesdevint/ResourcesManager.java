package fr.unice.polytech.devint.cassebriquesdevint;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Loic GAILLARD on 26/03/14.
 */
public class ResourcesManager {
    private static ResourcesManager instance;
    private Map<String, Bitmap> bitmaps;

    private ResourcesManager() {
        bitmaps = new HashMap<>();
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
}
