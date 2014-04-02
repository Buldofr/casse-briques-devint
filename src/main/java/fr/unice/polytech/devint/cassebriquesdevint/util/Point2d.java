package fr.unice.polytech.devint.cassebriquesdevint.util;

/**
 * Created by Loic GAILLARD on 01/04/14.
 */
public class Point2d {
    public double x;
    public double y;

    public Point2d() {}

    public Point2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2d add(Point2d p) {
        return add(this, p);
    }

    public static Point2d add(Point2d p1, Point2d p2) {
        p1.x += p2.x;
        p1.y += p2.y;

        return p1;
    }
}
