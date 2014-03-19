package fr.unice.polytech.devint.cassebriquesdevint;

import java.util.Random;

/**
 * Created by Loïc GAILLARD on 12/03/14.
 */
public class Screen extends BitMap {
    public Screen(int width, int height) {
        super(width, height);
    }

    private static int tick;
    public void render(Game game) {
        ++tick;
        Random random = new Random();
        for(int y = 0; y < height; ++y) {
            for(int x = 0; x < width; ++x) {
                //pixels[x+y*width] = (random.nextInt() & (0xFF0000 >> 8*(((x+y+tick)/30)%3) ));
                int r = (int)((float)x/(float)width*255);
                int g = 0;//random.nextInt(128);
                int b = (int)((float)y/(float)height*255);
                pixels[x+y*width] = /*random.nextInt()%0x600060 &*/ (r<<16 | g<<8 | b);
            }
        }
    }
}
