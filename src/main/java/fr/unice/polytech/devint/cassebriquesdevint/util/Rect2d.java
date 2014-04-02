package fr.unice.polytech.devint.cassebriquesdevint.util;

/**
 * Created by Loic GAILLARD on 01/04/14.
 */
public class Rect2d {
    public Point2d p1;
    public Point2d p2;

    /*public Rect2d(double x, double y, double w, double h) {
        p1 = new Point2d(x, y);
        p2 = new Point2d(x+w, y+h);
    }*/
    public Rect2d(double top, double bottom, double left, double right) {
        p1 = new Point2d(left, top);
        p2 = new Point2d(right, bottom);
    }

    public Rect2d(Point2d p1, Point2d p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
}
