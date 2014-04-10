package fr.unice.polytech.devint.cassebriquesdevint;

import fr.unice.polytech.devint.cassebriquesdevint.util.Point2d;

/**
 * Created by Loic GAILLARD on 02/04/14.
 */
public class Wall extends Entity {

    public Wall() {
        super();
    }

    public Wall(Point2d p1, Point2d p2) {
        super();
        coord = p1;
        width = p2.x - p1.x;
        height = p2.y - p1.y;
    }

    public void drawOn(Bitmap parent) {
        //parent.draw(bitmap, (int)coord.x, (int)coord.y);
    }

    public void hit() {
        super.hit();
        ResourcesManager.getInstance().getSound("wall.wav").play();
    }
}
