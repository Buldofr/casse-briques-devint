package fr.unice.polytech.devint.cassebriquesdevint;

/**
 * Created by Loic GAILLARD on 26/03/14.
 */
public class Entity {
    public double x;
    public double y;
    public Bitmap bitmap;

    public Entity() {

    }

    public void think() {

    }

    public void drawOn(Bitmap parent) {
        parent.draw(bitmap, (int)x, (int)y);
    }
}
