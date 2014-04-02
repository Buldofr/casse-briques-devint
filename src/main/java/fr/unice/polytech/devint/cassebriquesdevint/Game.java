package fr.unice.polytech.devint.cassebriquesdevint;

import fr.unice.polytech.devint.cassebriquesdevint.util.Point2d;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loïc GAILLARD on 12/03/14.
 */
public class Game {
    public static Game instance;
    public static List<Entity> entities;
    public Paddle paddle;

    public int nbrBricks;

    public Game() {
        instance = this;
        entities = new ArrayList<>();

        newGame();
    }

    public void newGame() {
        entities.clear();

        paddle = new Paddle();
        entities.add(paddle);
        entities.add(new Wall(new Point2d(-1,-1), new Point2d(0, 181)));
        entities.add(new Wall(new Point2d(-1,-1), new Point2d(321, 0)));
        entities.add(new Wall(new Point2d(320,-1), new Point2d(321, 181)));

        nbrBricks = 0;
        for(int i = 1; i < 5; ++i) {
            for(int j = 0; j < 10; ++j) {
                if((j+j+3*i)%7 != 5) {
                    entities.add(new Brick(new Point2d(j*32, i*16)));
                    ++nbrBricks;
                }
            }
        }

        entities.add(new Ball());
    }

    public void tick() {
        InputsHandler ih = InputsHandler.getInstance();

        if(ih.keys[KeyEvent.VK_R]) {
            newGame();
            return;
        }

        if(nbrBricks <= 0) {
            return;
        }

        for(Entity entity : entities) {
            entity.think();
        }

        for(int i = 0; i < entities.size(); ++i) {
            Entity entity = entities.get(i);
            if(entity instanceof Brick) {
                if(!((Brick) entity).alive) {
                    entities.remove(i--);
                    --nbrBricks;
                }
            }

        }
    }

}
