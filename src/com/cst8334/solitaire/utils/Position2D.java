package com.cst8334.solitaire.utils;


import java.awt.Point;

public class Position2D {
    private Point point;
    
    public Position2D(int x, int y) {
        point = new Point(x, y);
    }
    
    public int getXpos() {
        return point.x;
    }
    
    public int getYpos() {
        return point.y;
    }
    
    public void setX(int x) {
        point.x = x;
    }
    
    public void setY(int y) {
        point.y = y;
    }
    
    @Override
    public String toString() {
        return "(" + point.x + ", " + point.y + ")";
    }
}