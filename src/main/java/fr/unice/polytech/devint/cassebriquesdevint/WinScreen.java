package fr.unice.polytech.devint.cassebriquesdevint;

/**
 * Created by Loic GAILLARD on 10/04/14.
 */
public class WinScreen extends HUDElement {

    public WinScreen() {
        super();
        setBitmap(ResourcesManager.getInstance().getBitmap("win.png"));
        ResourcesManager.getInstance().getSound("win.wav").play();
    }
}
