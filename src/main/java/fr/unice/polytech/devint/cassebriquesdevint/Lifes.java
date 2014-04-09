package fr.unice.polytech.devint.cassebriquesdevint;

import fr.unice.polytech.devint.cassebriquesdevint.util.Point2d;

/**
 * Created by Loic GAILLARD on 07/04/14.
 */
public class Lifes extends Text {
    public Lifes(Point2d coord) {
        super(coord, 0xCCCCCC);

    }

    public void think() {
        txt = "Vies : "+Game.instance.lifes;
    }
}
