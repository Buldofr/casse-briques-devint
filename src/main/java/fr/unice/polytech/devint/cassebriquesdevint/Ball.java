package fr.unice.polytech.devint.cassebriquesdevint;

import com.sun.org.omg.SendingContext._CodeBaseStub;
import fr.unice.polytech.devint.cassebriquesdevint.util.Direction;
import fr.unice.polytech.devint.cassebriquesdevint.util.Point2d;
import fr.unice.polytech.devint.cassebriquesdevint.util.Vector2d;

import java.awt.event.KeyEvent;

/**
 * Created by Loic GAILLARD on 26/03/14.
 */
public class Ball extends Entity {
    public Vector2d dir;
    public double radius;
    public double speed;

    public boolean linked;

    public double defaultSpeed = 2;

    public Ball() {
        setBitmap(ResourcesManager.getInstance().getBitmap("ball.png"));
        radius = width/2;
        Paddle paddle = Game.instance.paddle;
        coord.y = paddle.coord.y-radius;
        coord.x = paddle.coord.x+paddle.width/2+3;
        //coord.set(320/2-radius+50, 160);
        speed = defaultSpeed;
        dir = new Vector2d(0.1,-1);
        dir.normalize();

        linked = true;
    }

    private boolean diffup = false;
    private boolean diffdown = false;
    public void think() {
        InputsHandler ih = InputsHandler.getInstance();

        if(ih.keys[KeyEvent.VK_SPACE]) {
            linked = false;
        }

        if(ih.keys[KeyEvent.VK_PAGE_UP]) {
            if(!diffup) {
                speed += 0.5;
                diffup = true;
            }
        } else {
            diffup = false;
        }if(ih.keys[KeyEvent.VK_PAGE_DOWN]) {
            if(!diffdown) {
                speed -= 0.5;
                if(speed <= 0) {
                    speed = 0;
                }
                diffdown = true;
            }
        } else {
            diffdown = false;
        }

        if(linked) {
            Paddle paddle = Game.instance.paddle;
            coord.y = paddle.coord.y-radius;
            coord.x = paddle.coord.x+paddle.width/2+3;
        } else {
            move();
        }

        if(coord.y > 200) {
            linked = true;
            speed = defaultSpeed;
        }
    }

    public void move() {
        move(speed);
    }

    public void move(double speed) {
        Point2d p = new Point2d(coord.x + dir.x*speed, coord.y + dir.y*speed);

        double closestDist = -1;
        Collision.Collide closestCollide = null;
        Entity closestEntity = null;
        for(Entity entity : Game.entities) {
            if(entity instanceof Ball) continue;
            Collision.Collide c = Collision.interceptBall(this, entity.getBoundingBox(), speed);
            if(c != null) {
                double dist = Vector2d.norm(new Vector2d(coord, c.point));
                if(closestDist == -1 || dist < closestDist) {
                    closestDist = dist;
                    closestCollide = c;
                    closestEntity = entity;
                }
            }
        }

        if(closestCollide != null) {
            coord = closestCollide.point;

            if(closestEntity instanceof Paddle && closestCollide.dir == Direction.TOP) {
                dir.y = -dir.y;
                //double angle = dir.angle();
                double c = closestEntity.coord.x + closestEntity.width/2;
                double e = coord.x - c;
                double angle = -Math.PI/2 + e/closestEntity.width/2*Math.PI;
                dir = new Vector2d(angle);
            } else {
                switch (closestCollide.dir) {
                    case LEFT:
                    case RIGHT:
                        dir.x = -dir.x;
                        break;
                    case TOP:
                    case BOTTOM:
                        dir.y = -dir.y;
                        break;
                }
            }

            if(closestEntity instanceof Brick) {
                ((Brick) closestEntity).hit();
            }

            move(speed - closestDist);
            this.speed += 0.02;
            if(this.speed > 8) this.speed = 8;
        } else {
            coord = p;
        }
    }

    private void on_collision(Entity entity) {

    }

    public void drawOn(Bitmap parent) {
        parent.draw(bitmap, (int)(coord.x-radius), (int)(coord.y-radius));
    }
}
