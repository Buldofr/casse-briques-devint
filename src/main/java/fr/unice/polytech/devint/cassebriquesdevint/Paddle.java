package fr.unice.polytech.devint.cassebriquesdevint;

import java.awt.event.KeyEvent;

/**
 * Created by Loic GAILLARD on 26/03/14.
 */
public class Paddle extends Entity {
    private int width;
    private int height;

    public Paddle() {
        bitmap = ResourcesManager.getInstance().getBitmap("paddle.png");
        width = bitmap.width;
        height = bitmap.height;

        x = (320-width)/2;
        y = 170-height/2;
    }

    public void think() {
        InputsHandler ih = InputsHandler.getInstance();

        int dir = 0;
        if(ih.keys[KeyEvent.VK_LEFT]) dir -= 1;
        if(ih.keys[KeyEvent.VK_RIGHT]) dir += 1;
        x += 2 * dir;

        if(x < 0) x = 0;
        else if(x > 320-width) x = 320-width;
    }
}
