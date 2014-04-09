package fr.unice.polytech.devint.cassebriquesdevint;

import fr.unice.polytech.devint.cassebriquesdevint.util.Point2d;
import fr.unice.polytech.devint.cassebriquesdevint.util.Rect2d;

/**
 * Created by Loic GAILLARD on 26/03/14.
 */
public class Entity {
    public Point2d coord;
    public Bitmap bitmap;
    public double width;
    public double height;

    public boolean collidable;

    public Entity() {
        coord = new Point2d();
        collidable = true;
    }

    public void think() {

    }

    public void drawOn(Bitmap parent) {
        parent.draw(bitmap, (int)coord.x, (int)coord.y);
    }

    public Rect2d getBoundingBox() {
        return new Rect2d(coord.y, coord.y+height, coord.x, coord.x + width);
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        width = bitmap.width;
        height = bitmap.height;
    }
}
