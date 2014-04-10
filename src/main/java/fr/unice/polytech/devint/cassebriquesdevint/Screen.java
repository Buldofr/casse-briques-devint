package fr.unice.polytech.devint.cassebriquesdevint;

import fr.unice.polytech.devint.cassebriquesdevint.util.Point2d;

import java.util.Random;

/**
 * Created by Loïc GAILLARD on 12/03/14.
 */
public class Screen extends Bitmap {
    public Screen(int width, int height) {
        super(width, height);
    }

    private static int tick;
    public void render(Game game) {
        ++tick;
        Random random = new Random();
        /*for(int y = 0; y < height; ++y) {
            for(int x = 0; x < width; ++x) {
                //pixels[x+y*width] = (random.nextInt() & (0xFF0000 >> 8*(((x+y+tick)/30)%3) ));
                int r = (int)((float)x/(float)width*255);
                int g = random.nextInt(128);
                int b = (int)((float)y/(float)height*255);
                pixels[x+y*width] = /*random.nextInt()%0x600060 &*/ /*(r<<16 | g<<8 | b);
            }
        }*/

        draw(ResourcesManager.getInstance().getBitmap("grid_background.png"), 0, 0);

        //if(Game.entities != null)
        for(Entity entity : Game.entities) {
            entity.drawOn(this);
        }

        /*fill(0,0,320,180, 0xff00ff);
        Text t = new Text(new Point2d(0,0));
        t.txt = "Perdu...";
        t.drawOn(this);*/
    }
}
