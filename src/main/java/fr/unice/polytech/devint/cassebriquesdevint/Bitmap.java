package fr.unice.polytech.devint.cassebriquesdevint;

/**
 * Created by Loïc GAILLARD on 12/03/14.
 */
public class Bitmap {
    public final int width;
    public final int height;
    public final int[] pixels;

    public Bitmap(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void draw(Bitmap bitmap, int xOffs, int yOffs) {
        for (int y = 0; y < bitmap.height; y++) {
            int yPix = y + yOffs;
            if (yPix < 0 || yPix >= height) continue;

            for (int x = 0; x < bitmap.width; x++) {
                int xPix = x + xOffs;
                if (xPix < 0 || xPix >= width) continue;

                int src = bitmap.pixels[x + y * bitmap.width];
                if(src >= 0) {
                    pixels[xPix + yPix * width] = src;
                }
            }
        }
    }

    public void draw(Bitmap bitmap, int xOffs, int yOffs, int xo, int yo, int w, int h, int col) {
        for (int y = 0; y < h; y++) {
            int yPix = y + yOffs;
            if (yPix < 0 || yPix >= height) continue;

            for (int x = 0; x < w; x++) {
                int xPix = x + xOffs;
                if (xPix < 0 || xPix >= width) continue;

                int src = bitmap.pixels[(x + xo) + (y + yo) * bitmap.width];
                if (src >= 0) {
                    pixels[xPix + yPix * width] = src * col;
                }
            }
        }
    }

    public void fill(int x0, int y0, int width, int height, int color) {
        for (int y = y0; y < height; y++) {
            for (int x = x0; x < width; x++) {
                pixels[x + y * this.width] = color;
            }
        }
    }
}
