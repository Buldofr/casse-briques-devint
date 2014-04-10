package fr.unice.polytech.devint.cassebriquesdevint;

import fr.unice.polytech.devint.cassebriquesdevint.util.Point2d;

/**
 * Created by Loic GAILLARD on 09/04/14.
 */
public class Speed extends Text {

    public Speed(Point2d coord) {
        super(coord, 0xcccccc);
        think();
    }

    public void think() {
        super.think();
        txt = String.format("%.1f", Game.instance.ball.defaultSpeed);
    }
}
