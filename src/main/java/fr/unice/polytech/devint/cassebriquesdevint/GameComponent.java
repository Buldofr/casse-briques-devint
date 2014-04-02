package fr.unice.polytech.devint.cassebriquesdevint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Created by Loïc GAILLARD on 12/03/14.
 */
public class GameComponent extends Canvas implements Runnable {
    public static final int WIDTH = 320;
    public static final int HEIGHT = 180;
    public static final int SCALE = 2;

    private boolean running;
    private final GameFrame frame;
    private Thread thread;

    private Game game;
    private Screen screen;
    private BufferedImage img;
    private int[] pixels;

    public GameComponent(final GameFrame frame) {
        this.frame = frame;

        Dimension dimension = new Dimension(WIDTH*SCALE, HEIGHT*SCALE);
        setSize(dimension);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);

        game = new Game();
        screen = new Screen(WIDTH, HEIGHT);

        InputsHandler inputsHandler = InputsHandler.getInstance();
        addFocusListener(inputsHandler);
        addMouseListener(inputsHandler);
        addMouseMotionListener(inputsHandler);
        addKeyListener(inputsHandler);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_F) {
                    frame.toggleFullscreen();
                    requestFocusInWindow();
                } else if(e.getKeyCode() == KeyEvent.VK_ESCAPE && frame.isFullscreenMode()) {
                    frame.toggleFullscreen();
                    requestFocusInWindow();
                } else if(e.getKeyCode() == KeyEvent.VK_Q) {
                    frame.dispose();
                    System.exit(0);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        int frames = 0;

        double unprocessedSeconds = 0;
        long lastTime = System.nanoTime();
        double secondsPerTick = 1 / 60.0;
        int tickCount = 0;

        requestFocus();

        while (running) {
            long now = System.nanoTime();
            long passedTime = now - lastTime;
            lastTime = now;
            if (passedTime < 0) passedTime = 0;
            if (passedTime > 100000000) passedTime = 100000000;

            unprocessedSeconds += passedTime / 1000000000.0;

            boolean ticked = false;
            while (unprocessedSeconds > secondsPerTick) {
                tick();
                unprocessedSeconds -= secondsPerTick;
                ticked = true;

                tickCount++;
                if (tickCount % 60 == 0) {
                    System.out.println(frames + " fps");
                    lastTime += 1000;
                    frames = 0;
                }
            }

            if (ticked) {
                render();
                ++frames;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void tick() {
        if(hasFocus()) {
            game.tick();
        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.render(game);

        for (int i = 0; i < WIDTH * HEIGHT; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        g.fillRect(0, 0, getWidth(), getHeight());
        //g.drawImage(img, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        float ratio = (float)WIDTH/(float)HEIGHT;
        if(getWidth() >= ratio*getHeight()) {
            g.drawImage(img, this.getWidth()/2-((int)(ratio*this.getHeight()/2)), 0, (int)(ratio*this.getHeight()), this.getHeight(), null);
        } else {
            ratio = 1/ratio;
            g.drawImage(img, 0, this.getHeight()/2-((int)(ratio*this.getWidth()/2)), this.getWidth(), (int)(ratio*this.getWidth()), null);
        }
        g.dispose();
        bs.show();
    }
}
