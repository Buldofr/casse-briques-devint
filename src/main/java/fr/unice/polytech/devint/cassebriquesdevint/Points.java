package fr.unice.polytech.devint.cassebriquesdevint;

import fr.unice.polytech.devint.cassebriquesdevint.util.Point2d;

/**
 * Created by Loic GAILLARD on 07/04/14.
 */
public class Points extends Text {
    public Points(Point2d coord) {
        super(coord, 0xCCCCCC);
        think();
    }

    public void think() {
        txt = "Points : "+Game.instance.points;
    }
}
