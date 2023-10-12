package com.cst8334.solitaire.utils;

/**
 * The {@code Selectable} interface represents objects that can be selected.
 * Classes implementing this interface should provide implementations for the {@code setSelected} and {@code isSelected} methods.
 * 
 * @version 1.0
 * @since 17
 * @author Austin Kirby
 */
public interface Selectable {
  
  /**
   * Sets the selected state of the object.
   * @param selected
   */
  public void setSelected(boolean selected);

  /**
   * Gets the selected state of the object.
   * @return {@code true} if the object is selected, {@code false} otherwise.
   */
  public boolean isSelected();

    /** Gets if a user clicked on the position of an entity
   *
   * @param position returns true if the position selected is assigned to an entity
   * @return
   */
  public boolean contains (Position2D position);
  
  
  /** Gets if a user selects a cardinal point where an entity is situated
   * 
   * @param x
   * @param y
   * @return
   */
  
  public boolean contains (int x, int y);


}
