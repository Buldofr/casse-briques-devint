package fr.unice.polytech.devint.cassebriquesdevint;

import fr.unice.polytech.devint.cassebriquesdevint.util.Point2d;

/**
 * Created by Loic GAILLARD on 07/04/14.
 */
public class Text extends HUDElement {
    public Point2d coord;
    public int col;
    public String txt;

    private static final String chars = "" + //
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ.,!?\"'/\\<>()[]{}" + //
            "abcdefghijklmnopqrstuvwxyz_               " + //
            "0123456789+-=*:;����                      " + //
            "";

    public Text(Point2d coord) {
        this("", coord, 0xffffff);
    }

    public Text(Point2d coord, int col) {
        this("", coord, col);
    }

    public Text(String txt, Point2d coord) {
        this(txt, coord, 0xffffff);
    }

    public Text(String txt, Point2d coord, int col) {
        super();
        setBitmap(ResourcesManager.getInstance().getBitmap("font.png"));
        this.coord = coord;
        this.col = col;
        this.txt = txt;
    }

    public void drawOn(Bitmap parent) {
        for (int i = 0; i < txt.length(); i++) {
            int ch = chars.indexOf(txt.charAt(i));
            if (ch < 0) continue;

            int xx = ch % 42;
            int yy = ch / 42;
            parent.draw(bitmap, (int)(coord.x) + i * 6, (int)(coord.y), xx * 6, yy * 8, 5, 8, col);
        }
    }

    public void update() {
        super.update();
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Point2d getCoord() {
        return coord;
    }

    public void setCoord(Point2d coord) {
        this.coord = coord;
    }
}
