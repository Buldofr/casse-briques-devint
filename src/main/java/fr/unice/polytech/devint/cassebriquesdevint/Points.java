package fr.unice.polytech.devint.cassebriquesdevint;

import fr.unice.polytech.devint.cassebriquesdevint.util.Point2d;

/**
 * Created by Loic GAILLARD on 07/04/14.
 */
public class Points extends Text {
    public Points(Point2d coord, int points) {
        super(coord, 0xCCCCCC);
        setPoints(points);
    }

    public void update() {
        super.update();
        setPoints(Game.instance.points);
    }

    public void setPoints(int points) {
        txt = "Points : "+points;
    }
}
