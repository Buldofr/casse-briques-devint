package fr.unice.polytech.devint.cassebriquesdevint;

import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Created by Loic GAILLARD on 26/03/14.
 */
public class Paddle extends Entity {
    //private int width;
    //private int height;

    public Paddle() {
        super();
        setBitmap(ResourcesManager.getInstance().getBitmap("paddle.png"));
        width = bitmap.width;
        height = bitmap.height;

        coord.x = (320-width)/2;
        coord.y = 170-height/2;
    }

    public void think() {
        InputsHandler ih = InputsHandler.getInstance();

        int dir = 0;
        if(ih.keys[KeyEvent.VK_LEFT]) dir -= 1;
        if(ih.keys[KeyEvent.VK_RIGHT]) dir += 1;
        coord.x += 4 * dir;

        if(coord.x < 0) coord.x = 0;
        else if(coord.x > 320-width) coord.x = 320-width;

        Ball ball = Game.instance.ball;
        if(ball.coord.y+ball.radius > coord.y/* && ball.coord.y+ball.radius < coord.y+height*/) {
            if(ball.coord.x+ball.radius > coord.x && ball.coord.x-ball.radius < coord.x+width) {
                    ball.coord.x += 4 * dir;
            }
        }
    }

    public void hit() {
        super.hit();
        ResourcesManager.getInstance().getSound("paddle.wav").play();
    }
}
