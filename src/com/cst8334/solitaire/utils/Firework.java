package com.cst8334.solitaire.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Firework {
	private int x;
    private int y;
    private Color color;
    private int size;
    private int speed;
    
    private Random random = new Random();

    public Firework(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        this.size = random.nextInt(5) + 10; // Random size between 3 and 7
        this.speed = random.nextInt(5) + 5; // Random speed between 1 and 5
    }

    public void draw(Graphics gc) {
        gc.setColor(color);
        gc.fillRect(x, y, size, size);
        move();
    }

    private void move() {
        y -= speed;
    }

    public boolean isBurntOut() {
        return y < 0;
    }
}
