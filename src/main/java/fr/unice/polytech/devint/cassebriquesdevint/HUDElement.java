package fr.unice.polytech.devint.cassebriquesdevint;

import fr.unice.polytech.devint.cassebriquesdevint.util.Point2d;

/**
 * Created by Loic GAILLARD on 21/05/14.
 */
public class HUDElement {
    public Point2d coord;
    public Bitmap bitmap;
    public double width;
    public double height;

    public HUDElement() {
        coord = new Point2d();
    }

    public void drawOn(Bitmap parent) {
        parent.draw(bitmap, (int)coord.x, (int)coord.y);
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        width = bitmap.width;
        height = bitmap.height;
    }

    public void update() {

    }
}
