package fr.unice.polytech.devint.cassebriquesdevint;

import javax.swing.*;

/**
 * Created by Loïc GAILLARD on 19/03/14.
 */
public class GameFrame extends JFrame {
    private final GameComponent gameComponent;

    public GameFrame() {
        super("Casse-Briques | DeViNT");

        gameComponent = new GameComponent();

        add(gameComponent);
        pack();

        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        gameComponent.start();
    }


    public static void main(String[] args) {
        new GameFrame();
    }
}
