package com.cst8334.solitaire.utils;

public class Entity {
    private Position2D position;
    private int width;
    private int height;

    public Entity(Position2D position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }
    
    public Entity() {
        super(); // Explicitly call the superclass constructor
    }

    public Position2D getPosition() {
        return position;
    }

    public void setPosition(Position2D position) {
        this.position = position;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
