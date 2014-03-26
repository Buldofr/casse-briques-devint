package fr.unice.polytech.devint.cassebriquesdevint;

import java.awt.event.*;

/**
 * Created by Loïc GAILLARD on 15/03/14.
 */
public class InputsHandler implements KeyListener, FocusListener, MouseListener, MouseMotionListener {
    private static InputsHandler instance;

    public final boolean[] keys;

    
    private InputsHandler() {
        keys = new boolean[0xFFFF];
    }

    public static InputsHandler getInstance() {
        if(instance == null) {
            instance = new InputsHandler();
        }
        return instance;
    }
    

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        for(int i = 0; i < 0xFFFF; ++i) {
            keys[i] = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
