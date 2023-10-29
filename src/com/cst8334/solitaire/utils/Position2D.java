package com.cst8334.solitaire.utils;

import java.awt.Point;

public class Position2D {
  private Point point;

  public Position2D(int x, int y) {
    point = new Point(x, y);
  }

  public static Position2D Zero() {
    return new Position2D(0, 0);
  }

  public int getX() {
    return point.x;
  }

  public int getY() {
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

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Position2D other = (Position2D) obj;
    return point.equals(other.point);
  }
}
