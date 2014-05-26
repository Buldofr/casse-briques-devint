package fr.unice.polytech.devint.cassebriquesdevint;

import fr.unice.polytech.devint.cassebriquesdevint.util.Point2d;

/**
 * Created by Loic GAILLARD on 09/04/14.
 */
public class Speed extends Text {

    public Speed(Point2d coord, double speed) {
        super(coord, 0xcccccc);
        setSpeed(speed);
    }

    public void update() {
        super.update();
        setSpeed(Game.instance.ball.defaultSpeed);
    }

    public void setSpeed(double speed) {
        txt = String.format("%.1f", speed);
    }
}
