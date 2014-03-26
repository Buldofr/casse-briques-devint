package fr.unice.polytech.devint.cassebriquesdevint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Loïc GAILLARD on 19/03/14.
 */
public class GameFrame extends JFrame {
    private final GameComponent gameComponent;
    private Rectangle storedBounds;

    public GameFrame() {
        super("Casse-Briques | DeViNT");

        gameComponent = new GameComponent(this);

        add(gameComponent);
        pack();

        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //toggleFullscreen();

        gameComponent.start();
    }

    public void toggleFullscreen() {
        dispose();
        GraphicsDevice graphicsDevice = getGraphicsConfiguration().getDevice();

        if(isFullscreenMode()) {
            disableFullscreen(graphicsDevice);
        } else {
            enableFullscreen(graphicsDevice);
        }
        toFront();
        requestFocus();
    }

    private void enableFullscreen(GraphicsDevice graphicsDevice) {
        setUndecorated(true);
        storedBounds = getBounds();

        if(graphicsDevice.isFullScreenSupported()) {
            graphicsDevice.setFullScreenWindow(this);
        } else {
            setLocation(0, 0);
            setSize(Toolkit.getDefaultToolkit().getScreenSize());
        }
    }

    private void disableFullscreen(GraphicsDevice graphicsDevice) {
        graphicsDevice.setFullScreenWindow(null);
        setUndecorated(false);
        setBounds(storedBounds);
        setVisible(true);
    }

    public boolean isFullscreenMode() {
        return isUndecorated();
    }

    // Main

    public static void main(String[] args) {
        new GameFrame();
    }
}
