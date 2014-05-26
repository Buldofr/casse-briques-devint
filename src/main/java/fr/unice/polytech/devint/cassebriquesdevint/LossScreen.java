package fr.unice.polytech.devint.cassebriquesdevint;

/**
 * Created by Loic GAILLARD on 10/04/14.
 */
public class LossScreen extends HUDElement {

    public LossScreen() {
        super();
        setBitmap(ResourcesManager.getInstance().getBitmap("loss.png"));
        ResourcesManager.getInstance().getSound("loss.wav").play();
    }
}
