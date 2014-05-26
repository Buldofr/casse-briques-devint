package fr.unice.polytech.devint.cassebriquesdevint;

import fr.unice.polytech.devint.cassebriquesdevint.util.Point2d;

/**
 * Created by Loic GAILLARD on 07/04/14.
 */
public class Lifes extends Text {
    public Lifes(Point2d coord, int lifes) {
        super(coord, 0xCCCCCC);
        setLifes(lifes);
    }

    public void update() {
        super.update();
        setLifes(Game.instance.lifes);
    }

    public void setLifes(int lifes) {
        txt = "Vies : " + lifes;
    }
}
