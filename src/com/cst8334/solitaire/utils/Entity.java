package com.cst8334.solitaire.utils;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * The {@code Entity} class represents objects that can be drawn on a graphics
 * context and selected.
 * It implements the {@code Drawable} and {@code Selectable} interfaces.
 * 
 * @see com.cst8334.solitaire.utils.Drawable
 * @see com.cst8334.solitaire.utils.Selectable
 * @version 1.1
 * @since 17
 * @author Melodie Langlois
 * @author Austin Kirby
 */
public class Entity implements Drawable, Selectable {
  private Position2D position;
  private int width;
  private int height;
  private boolean selected;
  private Point mouseOffset = new Point(0, 0);

  /**
   * Constructs a new instance of the {@code Entity} class with the specified
   * position, width, and height.
   * 
   * @param position The position of the entity.
   * @param width    The width of the entity.
   * @param height   The height of the entity.
   */
  public Entity(Position2D position, int width, int height) {
    this.position = position;
    this.width = width;
    this.height = height;
    this.selected = false;
  }

  /**
   * Constructs a new instance of the {@code Entity} class with the specified
   * position.
   * By default, the position, width and height are set to 0.
   */
  public Entity() {
    this(Position2D.Zero(), 0, 0);
  }

  /**
   * Gets the position of the entity.
   * 
   * @return The position of the entity.
   */
  public Position2D getPosition() {
    return position;
  }

  /**
   * Sets the position of the entity.
   * 
   * @param position The position of the entity.
   */
  public void setPosition(Position2D position) {
    this.position = position;
  }

  /**
   * Gets the width of the entity.
   * 
   * @return The width of the entity.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Sets the width of the entity.
   * 
   * @param width The width of the entity.
   */
  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * Gets the height of the entity.
   * 
   * @return The height of the entity.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Sets the height of the entity.
   * 
   * @param height The height of the entity.
   */
  public void setHeight(int height) {
    this.height = height;
  }

  @Override
  public void draw(Graphics2D gc) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'draw'");
  }

  @Override
  public boolean isSelected() {
    return selected;
  }

  @Override
  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  @Override
  public boolean contains(MouseEvent mouse) {

    /**
     * get position of mouse
     */
    Point pos = mouse.getLocationOnScreen();
    pos.x = mouse.getLocationOnScreen().x - pos.x - mouseOffset.x;
    pos.y = mouse.getLocationOnScreen().y - pos.y - mouseOffset.y;

    /**
     * get position of entity
     */
    int cardX = position.getX();
    int cardY = position.getY();
    int cardWidth = getWidth();
    int cardHeight = getHeight();

    boolean isInsideCard = pos.x >= cardX && pos.x <= cardX + cardWidth && pos.y >= cardY
        && pos.y <= cardY + cardHeight;

    return isInsideCard;

  }
}
