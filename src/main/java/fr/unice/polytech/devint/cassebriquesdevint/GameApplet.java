package fr.unice.polytech.devint.cassebriquesdevint;

import java.applet.Applet;
import java.awt.*;

/**
 * Created by Loïc GAILLARD on 19/03/14.
 */
public class GameApplet extends Applet {
    private static final long serialVersionUID = 1L;

    private GameComponent gameComponent = new GameComponent();

    public void init() {
        setLayout(new BorderLayout());
        add(gameComponent, BorderLayout.CENTER);
    }

    public void start() {
        gameComponent.start();
    }

    public void stop() {
        gameComponent.stop();
    }
}
