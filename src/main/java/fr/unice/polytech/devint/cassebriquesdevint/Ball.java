package fr.unice.polytech.devint.cassebriquesdevint;

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

    public static final double SPEEDSHIFT = 0.2;
    public static final double MAXSPEED = 5;

    public Ball(boolean big, int level) {
        if(big) {
            setBitmap(ResourcesManager.getInstance().getBitmap("ball_b.png"));
        } else {
            setBitmap(ResourcesManager.getInstance().getBitmap("ball_s.png"));
        }
        radius = width/2;
        Paddle paddle = Game.instance.paddle;
        coord.y = paddle.coord.y-radius;
        coord.x = paddle.coord.x+paddle.width/2+3;
        //coord.set(320/2-radius+50, 160);
        defaultSpeed = 2+(level-1)*0.1;
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
                speed += SPEEDSHIFT;
                defaultSpeed += SPEEDSHIFT;
                diffup = true;
            }
        } else {
            diffup = false;
        }if(ih.keys[KeyEvent.VK_PAGE_DOWN]) {
            if(!diffdown) {
                speed -= SPEEDSHIFT;
                defaultSpeed -= SPEEDSHIFT;
                if(speed <= 0) {
                    speed = 0;
                }
                if(defaultSpeed <= 0) {
                    defaultSpeed = 0;
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
            ResourcesManager.getInstance().getSound("miss.wav").play();
            linked = true;
            speed = defaultSpeed;

            Game.instance.lossLife();
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
            if(!entity.collidable) continue;
            if(entity instanceof Brick && !((Brick)(entity)).alive) continue;
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

                Game.instance.resetMultiplier();
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

            closestEntity.hit();

            if(speed > 0)
                move(speed - closestDist);
            else
                move(closestDist - speed);
            this.speed += 0.02;
            if(this.speed > MAXSPEED) this.speed = MAXSPEED;
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
