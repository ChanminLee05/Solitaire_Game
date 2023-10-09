package com.cst8334.solitaire.utils;

import java.awt.Graphics2D;

/**
 * The {@code Drawable} interface represents objects that can be drawn on a graphics context.
 * Classes implementing this interface should provide an implementation for the {@code draw} method.
 * 
 * @version 1.0
 * @since 17
 * @author Austin Kirby
 */
public interface Drawable {

  /**
   * Draws the object on the specified graphics context.
   *
   * @param gc The graphics context on which to draw the object.
   */
  public void draw(Graphics2D gc);
}
