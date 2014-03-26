package fr.unice.polytech.devint.cassebriquesdevint;

/**
 * Created by Loïc GAILLARD on 12/03/14.
 */
public class BitMap {
    public final int width;
    public final int height;
    public final int[] pixels;

    public BitMap(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width*height];
    }
}
