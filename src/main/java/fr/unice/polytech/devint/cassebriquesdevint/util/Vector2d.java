package fr.unice.polytech.devint.cassebriquesdevint.util;

import static java.lang.Math.atan2;
import static java.lang.Math.sqrt;

/**
 * Created by Loic GAILLARD on 01/04/14.
 */
public class Vector2d {
    public double x;
    public double y;

    public Vector2d() {
    }

    public Vector2d(double ang) {
        this.x = Math.cos(ang);
        this.y = Math.sin(ang);
    }

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d(Point2d p1, Point2d p2) {
        this.x = p2.x - p1.x;
        this.y = p2.y - p1.y;
    }

    public double dot(Vector2d v) {
        return dot(this, v);
    }

    public static double dot(Vector2d v1, Vector2d v2) {
        return v1.x*v2.x + v1.y*v2.y;
    }

    public double cross(Vector2d v) {
        return cross(this, v);
    }

    public static double cross(Vector2d v1, Vector2d v2) {
        return v1.x * v2.y - v1.y * v2.x;
    }

    public double norm() {
        return norm(this);
    }

    public static double norm(Vector2d v) {
        return sqrt(v.x*v.x + v.y*v.y);
    }

    public void normalize() {
        normalize(this);
    }

    public static void normalize(Vector2d v) {
        double n = v.norm();
        v.x /= n;
        v.y /= n;
    }

    public double angle() {
        return angle(this);
    }

    public static double angle(Vector2d v) {
        return atan2(v.y, v.x);
    }
}
