package com.cst8334.solitaire.cards;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cst8334.solitaire.utils.Entity;
import com.cst8334.solitaire.utils.ImageLoader;
import com.cst8334.solitaire.utils.Position2D;

/**
 * The {@code Card} class represents a playing card with a suit, value, and face orientation.
 * 
 * @see com.cst8334.solitaire.utils.Drawable
 * @see com.cst8334.solitaire.utils.Entity
 * @version 1.0
 * @since 17
 * @author Austin Kirby
 */
public class Card extends Entity {

  private static final int WIDTH = 72;
  private static final int HEIGHT = 105;

  private static final int ARC_WIDTH = 10;
  private static final int ARC_HEIGHT = 10;

  /**
   * An enumeration representing the suits of a playing card.
   */
  public static enum SUITS {
    CLUBS("♣"), DIAMONDS("♦"), HEARTS("♥"), SPADES("♠");

    public final String label;

    private SUITS(String label) {
      this.label = label;
    }
  }

  /**
   * An enumeration representing the values of a playing card.
   */
  public static enum VALUES {
    ACE("A"), TWO("2"), THREE("3"), 
    FOUR("4"), FIVE("5"), SIX("6"), 
    SEVEN("7"), EIGHT("8"), NINE("9"), 
    TEN("10"), JACK("J"), QUEEN("Q"), KING("K");

    public final String label;

    private VALUES(String label) {
      this.label = label;
    }
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
  private boolean faceUp;

  /**
   * Constructs a new playing card with the specified suit and value. By default, the card is face down.
   *
   * @param suit  The suit of the card (e.g., Clubs, Diamonds, Hearts, or Spades).
   * @param value The value of the card (e.g., Ace, Two, Three, ..., King).
   */
  public Card(SUITS suit, VALUES value) {
    this(suit, value, false);
  }

  /**
   * Constructs a new playing card with the specified suit, value, and face orientation.
   *
   * @param suit   The suit of the card (e.g., Clubs, Diamonds, Hearts, or Spades).
   * @param value  The value of the card (e.g., Ace, Two, Three, ..., King).
   * @param faceUp A boolean indicating whether the card is face up (true) or face down (false).
   */
  public Card(SUITS suit, VALUES value, boolean faceUp) {
    this(suit, value, faceUp, Position2D.Zero());
  }

  /**
   * Constructs a new playing card with the specified suit, value, and position. By default, the card is face down.
   * 
   * @param suit The suit of the card (e.g., Clubs, Diamonds, Hearts, or Spades).
   * @param value The value of the card (e.g., Ace, Two, Three, ..., King).
   * @param position The position of the card on the game board.
   */
  public Card(SUITS suit, VALUES value, Position2D position) {
    this(suit, value, false, position);
  }

  /**
   * Constructs a new playing card with the specified suit, value, face orientation, and position.
   * 
   * @param suit The suit of the card (e.g., Clubs, Diamonds, Hearts, or Spades).
   * @param value The value of the card (e.g., Ace, Two, Three, ..., King).
   * @param faceUp A boolean indicating whether the card is face up (true) or face down (false).
   * @param position The position of the card on the game board.
   */
  public Card(SUITS suit, VALUES value, boolean faceUp, Position2D position) {
    super(position, WIDTH, HEIGHT);
    this.suit = suit;
    this.value = value;
    this.faceUp = faceUp;
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
  public boolean isFaceUp() {
    return faceUp;
  }

  /**
   * Sets the face orientation of the card.
   *
   * @param faceUp {@code true} to set the card as face up (visible), {@code false} to set it as face down (hidden).
   */
  public void setFaceUp(boolean faceUp) {
    this.faceUp = faceUp;
  }

  /**
   * Draws a representation of the card using the provided graphics context.
   *
   * @param gc The graphics context on which to draw the card.
   */
  @Override
  public void draw(Graphics2D gc) {
    // Draw a border around the card
    if (isSelected()) {
      gc.setColor(Color.GREEN);
      gc.setStroke(new BasicStroke(2));
      gc.drawRoundRect(getPosition().getX()-1, getPosition().getY()-1, WIDTH+2, HEIGHT+2, ARC_WIDTH, ARC_HEIGHT);
    } else {
      gc.setColor(Color.BLACK);
      gc.setStroke(new BasicStroke(1));
      gc.drawRoundRect(getPosition().getX()-1, getPosition().getY()-1, WIDTH+1, HEIGHT+1, ARC_WIDTH, ARC_HEIGHT);
    }
    // Draw the card contents
    gc.setColor(Color.white);
    gc.fillRoundRect(getPosition().getX(), getPosition().getY(), WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);

    ImageLoader imageLoader = ImageLoader.getInstance();
    String path = (isFaceUp() ? toString() : "back") + ".png";
    BufferedImage image = imageLoader.loadImage(path);
    if (image != null) {
      BufferedImage scaledImage = imageLoader.scaleImage(image, WIDTH, HEIGHT);
      gc.drawImage(scaledImage, getPosition().getX(), getPosition().getY(), WIDTH, HEIGHT, null);
    } else {
      System.out.println("Image not found: " + toString() + ".png");
      // Set the color of the card based on its suit
      if (suit == SUITS.DIAMONDS || suit == SUITS.HEARTS) {
        gc.setColor(Color.red);
      } else {
        gc.setColor(Color.black);
      }
      // Draw the suit and value of the card
      gc.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
      gc.drawString(toString(), getPosition().getX() + 5, getPosition().getY() + 15);
    }
  }

  /**
   * Returns a string representation of the card, including its value and suit.
   *
   * @return The card's value and suit as a string (e.g., "A♠" for Ace of Spades).
   */
  @Override
  public String toString() {
    return String.format("%s%s", value.label, suit.label);
  }
}
