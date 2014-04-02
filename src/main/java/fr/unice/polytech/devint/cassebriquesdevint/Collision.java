package fr.unice.polytech.devint.cassebriquesdevint;

import fr.unice.polytech.devint.cassebriquesdevint.util.Direction;
import fr.unice.polytech.devint.cassebriquesdevint.util.Point2d;
import fr.unice.polytech.devint.cassebriquesdevint.util.Rect2d;
import fr.unice.polytech.devint.cassebriquesdevint.util.Vector2d;

/**
 * Created by Loic GAILLARD on 01/04/14.
 */
public class Collision {

    public static Point2d intercept(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        double denom = ((y4-y3) * (x2-x1)) - ((x4-x3) * (y2-y1));
        if (denom != 0) {
            double ua = (((x4-x3) * (y1-y3)) - ((y4-y3) * (x1-x3))) / denom;
            if ((ua >= 0) && (ua <= 1)) {
                double ub = (((x2-x1) * (y1-y3)) - ((y2-y1) * (x1-x3))) / denom;
                if ((ub >= 0) && (ub <= 1)) {
                    double x = x1 + (ua * (x2-x1));
                    double y = y1 + (ua * (y2-y1));
                    return new Point2d(x, y);
                }
            }
        }
        return null;
    }

    public static Point2d intercept(Point2d p1, Point2d p2, Point2d p3, Point2d p4) {
        /*double denom = ((p4.y-p3.y) * (p2.x-p1.x)) - ((p4.x-p3.x) * (p2.y-p1.y));
        if (denom != 0) {
            double ua = (((p4.x-p3.x) * (p1.y-p3.y)) - ((p4.y-p3.y) * (p1.x-p3.x))) / denom;
            if ((ua >= 0) && (ua <= 1)) {
                double ub = (((p2.x-p1.x) * (p1.y-p3.y)) - ((p2.y-p1.y) * (p1.x-p3.x))) / denom;
                if ((ub >= 0) && (ub <= 1)) {
                    double x = p1.x + (ua * (p2.x-p1.x));
                    double y = p1.y + (ua * (p2.y-p1.y));
                    return new Point2d(x, y);
                }
            }
        }
        return null;*/
        return intercept(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y, p4.x, p4.y);
    }

    public static Collide interceptBall(Ball ball, Rect2d rect, double speed) {
        Point2d pt = null;
        Direction dir = null;
        double dx = ball.dir.x * speed;
        double dy = ball.dir.y * speed;

        if (dx < 0) {
            pt = intercept(ball.coord.x, ball.coord.y, ball.coord.x + dx, ball.coord.y + dy,
                    rect.p2.x + ball.radius,
                    rect.p1.y - ball.radius,
                    rect.p2.x + ball.radius,
                    rect.p2.y + ball.radius);
            dir = Direction.RIGHT;
        }
        else if (dx > 0) {
            pt = intercept(ball.coord.x, ball.coord.y, ball.coord.x + dx, ball.coord.y + dy,
                    rect.p1.x - ball.radius,
                    rect.p1.y - ball.radius,
                    rect.p1.x - ball.radius,
                    rect.p2.y + ball.radius);
            dir = Direction.LEFT;
        }
        if (pt == null) {
            if (dy < 0) {
                pt = intercept(ball.coord.x, ball.coord.y, ball.coord.x + dx, ball.coord.y + dy,
                        rect.p1.x - ball.radius,
                        rect.p2.y + ball.radius,
                        rect.p2.x + ball.radius,
                        rect.p2.y + ball.radius);
                dir = Direction.BOTTOM;
            }
            else if (dy > 0) {
                pt = intercept(ball.coord.x, ball.coord.y, ball.coord.x + dx, ball.coord.y + dy,
                        rect.p1.x - ball.radius,
                        rect.p1.y - ball.radius,
                        rect.p2.x + ball.radius,
                        rect.p1.y - ball.radius);
                dir = Direction.TOP;
            }
        }
        if(pt == null) {
            return null;
        }

        return new Collide(pt, dir);
    }

    public static class Collide {
        public final Point2d point;
        public final Direction dir;

        public Collide(Point2d point, Direction dir) {
            this.point = point;
            this.dir = dir;
        }
    }
}
