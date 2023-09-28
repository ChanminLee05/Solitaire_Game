package com.cst8334.solitaire.cards;

import java.awt.Graphics;

import com.cst8334.solitaire.utils.Drawable;
import com.cst8334.solitaire.utils.Entity;

/**
 * The {@code Card} class represents a playing card with a suit, value, and face orientation.
 * It implements the {@code Drawable} interface to allow for graphical rendering.
 * 
 * @see com.cst8334.solitaire.utils.Drawable
 * @see com.cst8334.solitaire.utils.Entity
 * @version 1.0
 * @since 17
 * @author Austin Kirby
 */
public class Card extends Entity implements Drawable {

  /**
   * An enumeration representing the suits of a playing card.
   */
  public static enum SUITS {
    CLUBS, DIAMONDS, HEARTS, SPADES
  }

  /**
   * An enumeration representing the values of a playing card.
   */
  public static enum VALUES {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
  }

  /**
   * The suit of the card (e.g., Clubs, Diamonds, Hearts, or Spades).
   */
  private final SUITS suit;

  /**
   * The value of the card (e.g., Ace, Two, Three, ..., King).
   */
  private final VALUES value;

  /**
   * A flag indicating whether the card is face up (visible) or face down (hidden).
   */
  private boolean faceUP;

  /**
   * Constructs a new playing card with the specified suit, value, and face orientation.
   *
   * @param suit   The suit of the card (e.g., Clubs, Diamonds, Hearts, or Spades).
   * @param value  The value of the card (e.g., Ace, Two, Three, ..., King).
   * @param faceUP A boolean indicating whether the card is face up (true) or face down (false).
   */
  public Card(SUITS suit, VALUES value, boolean faceUP) {
    this.suit = suit;
    this.value = value;
    this.faceUP = false;
  }

  /**
   * Gets the suit of the card.
   *
   * @return The suit of the card (e.g., Clubs, Diamonds, Hearts, or Spades).
   */
  public SUITS getSuit() {
    return suit;
  }

  /**
   * Gets the value of the card.
   *
   * @return The value of the card (e.g., Ace, Two, Three, ..., King).
   */
  public VALUES getValue() {
    return value;
  }

  /**
   * Checks if the card is face up (visible).
   *
   * @return {@code true} if the card is face up, {@code false} if it is face down.
   */
  public boolean isFaceUP() {
    return faceUP;
  }

  /**
   * Sets the face orientation of the card.
   *
   * @param faceUP {@code true} to set the card as face up (visible), {@code false} to set it as face down (hidden).
   */
  public void setFaceUP(boolean faceUP) {
    this.faceUP = faceUP;
  }

  /**
   * Draws a representation of the card using the provided graphics context.
   *
   * @param gc The graphics context on which to draw the card.
   */
  @Override
  public void draw(Graphics gc) {
    // TODO - Flush this out once Entity and Position2D are implemented
    gc.drawRect(getPosition().getXPos(), getPosition().getYPos(), getWidth(), getHeight());
  }

}
