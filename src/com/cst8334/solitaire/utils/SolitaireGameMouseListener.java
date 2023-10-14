package com.cst8334.solitaire.utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The {@code SolitaireGameMouseListener} class is a custom implementation of the {@code MouseListener}
 * interface that allows handling mouse events in the Solitaire game.
 * 
 * @see java.awt.event.MouseListener
 * @version 1.0
 * @since 17
 * @author Austin Kirby
 */
public class SolitaireGameMouseListener implements MouseListener {

  /**
   * The callback interface for handling mouse click events.
   */
  public interface MouseEventCallback {

    /**
     * Invoked when the mouse is clicked.
     *
     * @param e The MouseEvent corresponding to the click event.
     */
    void mouseClicked(MouseEvent e);
  }

  private MouseEventCallback callback;

  /**
   * Constructs a new {@code SolitaireGameMouseListener} with the provided callback for mouse events.
   *
   * @param callback The callback interface for handling mouse click events.
   */
  public SolitaireGameMouseListener(MouseEventCallback callback) {
    this.callback = callback;
  }

  @Override
  public void mouseClicked(MouseEvent ev) {
    if (callback == null) return;
    callback.mouseClicked(ev);
  }

  @Override
  public void mouseEntered(MouseEvent ev) { }

  @Override
  public void mouseExited(MouseEvent ev) { }

  @Override
  public void mousePressed(MouseEvent ev) { }

  @Override
  public void mouseReleased(MouseEvent ev) { }
  
}
