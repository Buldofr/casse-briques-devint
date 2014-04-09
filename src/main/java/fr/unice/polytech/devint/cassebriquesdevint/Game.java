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
    public Ball ball;
    public Paddle paddle;

    public int nbrBricks;


    public int points;
    public int multiplier;

    public int lifes;

    public boolean bigBall;

    public boolean resetHolding;

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
                    entities.add(new Brick(new Point2d(j*32, i*16), (i+j)));
                    ++nbrBricks;
                }
            }
        }

        ball = new Ball(bigBall);
        entities.add(ball);

        entities.add(new Points(new Point2d(2,2)));
        entities.add(new Lifes(new Point2d(2, 172)));
        entities.add(new Speed(new Point2d(300, 172)));

        lifes = 3;
        points = 0;
        multiplier = 1;
    }

    public void tick() {
        InputsHandler ih = InputsHandler.getInstance();

        if(ih.keys[KeyEvent.VK_R]) {
            if(!resetHolding) {
                resetHolding = true;
                newGame();
            }
            return;
        }

        if(ih.keys[KeyEvent.VK_DOLLAR]) {
            if(!resetHolding) {
                resetHolding = true;
                bigBall = !bigBall;
                newGame();
            }
            return;
        }
        resetHolding = false;

        if(nbrBricks <= 0) {
            return;
        }

        if(lifes <= 0) {
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
                    points += 10*multiplier;
                    ++multiplier;
                }
            }

        }
    }
}
