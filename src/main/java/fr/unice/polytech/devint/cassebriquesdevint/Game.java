package fr.unice.polytech.devint.cassebriquesdevint;

import fr.unice.polytech.devint.cassebriquesdevint.util.Point2d;

import java.awt.event.KeyEvent;
import java.util.*;

/**
 * Created by Loïc GAILLARD on 12/03/14.
 */
public class Game {
    public static Game instance;
    public static List<Entity> entities;
    public static Map<String, HUDElement> hud;
    public Ball ball;
    public Paddle paddle;

    public int level;
    public boolean levelDone;

    public int nbrBricks;


    public int points;
    public int multiplier;

    public int lifes;

    public boolean bigBall;

    public boolean resetHolding;

    public boolean end;

    public Game() {
        instance = this;
        entities = new ArrayList<>();
        hud = new HashMap<>();

        ResourcesManager.getInstance().cacheSounds();

        newGame();
    }

    public void newGame() {
        level = 1;
        levelDone = false;

        generateLevel(level);

        lifes = 3;
        points = 0;
        multiplier = level;

        hud.put("points", new Points(new Point2d(2, 2), points));
        hud.put("lifes", new Lifes(new Point2d(2, 172), lifes));
        //hud.put("speed", new Speed(new Point2d(300, 172), ball.defaultSpeed));
        hud.put("level", new Level(new Point2d(258, 2), level));

        end = false;
    }

    public void generateLevel(int level) {
        entities.clear();
        paddle = new Paddle();
        entities.add(paddle);
        entities.add(new Wall(new Point2d(-1,-1), new Point2d(0, 181)));
        entities.add(new Wall(new Point2d(-1,-1), new Point2d(321, 0)));
        entities.add(new Wall(new Point2d(320,-1), new Point2d(321, 181)));

        nbrBricks = 0;
        Random random = new Random(3 + level);
        double proba = 0.5-Math.log(level)*0.08;
        for(int i = 1; i < 8; ++i) {
            for(int j = 0; j < 10; ++j) {
                if(random.nextDouble() > proba) {
                    entities.add(new Brick(new Point2d(j*32, i*16), (i+j)));
                    ++nbrBricks;
                }
            }
        }

        ball = new Ball(bigBall, level);
        entities.add(ball);
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

        if(levelDone) {
            nextLevel();
            return;
        }

        if(nbrBricks <= 0) {
            levelDone = true;
            return;
        }

        if(lifes <= 0) {
            endGame(false);
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
                    addPoints(10*multiplier);
                    ++multiplier;
                }
            }
        }
    }

    public void nextLevel() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ++level;
        levelDone = false;
        if(level > 30) {
            endGame(true);
        }
        generateLevel(level);
        ++lifes;
        multiplier = level;
        updateHud();
    }

    private void updateHud() {
        for(HUDElement hudElement: hud.values()) {
            hudElement.update();
        }
    }

    public void endGame(boolean victory) {
        if(end) return;

        end = true;
        if(victory) {
            hud.put("winscreen", new WinScreen()); 
        } else {
            hud.put("lossScreen", new LossScreen());
        }
        hud.put("endPoints", new Points(new Point2d(320/2-30, 100), points));
    }

    public void resetMultiplier() {
        multiplier = level;
    }

    public void addPoints(int points) {
        this.points += points;
        hud.get("points").update();
    }

    public void lossLife() {
        resetMultiplier();
        --lifes;
        hud.get("lifes").update();
    }
}
