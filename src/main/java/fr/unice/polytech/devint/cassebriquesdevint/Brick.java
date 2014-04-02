package fr.unice.polytech.devint.cassebriquesdevint;

import fr.unice.polytech.devint.cassebriquesdevint.util.Point2d;

/**
 * Created by Loic GAILLARD on 02/04/14.
 */
public class Brick extends Entity {
    public boolean alive;

    public Brick(Point2d coord) {
        super();
        setBitmap(ResourcesManager.getInstance().getBitmap("brick.png"));
        this.coord = coord;
        alive = true;
    }

    public void hit() {
        alive = false;
    }
}
