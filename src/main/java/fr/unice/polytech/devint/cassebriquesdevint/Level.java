package fr.unice.polytech.devint.cassebriquesdevint;

import fr.unice.polytech.devint.cassebriquesdevint.util.Point2d;

/**
 * Created by Loic GAILLARD on 21/05/14.
 */
public class Level extends Text {
    int previousLevel;

    public Level(Point2d coord, int level) {
        super(coord, 0xCCCCCC);
        previousLevel = level;
        setLevel(level);
    }

    public void update() {
        super.update();
        setLevel(Game.instance.level);
    }

    public void setLevel(int level) {
        txt = "Niveau : "+level;
        if(level > 9 && previousLevel < 10) {
            coord.x -= 6;
        }
        previousLevel = level;
    }
}
