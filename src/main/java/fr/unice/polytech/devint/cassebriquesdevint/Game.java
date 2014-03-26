package fr.unice.polytech.devint.cassebriquesdevint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loïc GAILLARD on 12/03/14.
 */
public class Game {
    public static List<Entity> entities;

    public Game() {
        entities = new ArrayList<>();
        entities.add(new Paddle());
    }

    public void tick() {
        for(Entity entity : entities) {
            entity.think();
        }
    }
}
