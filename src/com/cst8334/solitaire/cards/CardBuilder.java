package com.cst8334.solitaire.cards;

import com.cst8334.solitaire.cards.Card.SUITS;
import com.cst8334.solitaire.cards.Card.VALUES;

/**
 * The {@code CardBuilder} class is a builder pattern implementation for creating instances of the {@code Card} class.
 * It allows for convenient construction of cards with specific suit, value, and face orientation settings.
 * 
 * @see com.cst8334.solitaire.cards.Card
 * @version 1.0
 * @since 17
 * @author Austin Kirby
 */
public class CardBuilder {
  private SUITS suit;
  private VALUES value;
  private boolean faceUP = false;

  /**
   * Sets the suit of the card.
   *
   * @param suit The suit of the card (e.g., Clubs, Diamonds, Hearts, or Spades).
   * @return This {@code CardBuilder} instance for method chaining.
   */
  public CardBuilder setSuit(SUITS suit) {
    this.suit = suit;
    return this;
  }

  /**
   * Sets the value of the card.
   *
   * @param value The value of the card (e.g., Ace, Two, Three, ..., King).
   * @return This {@code CardBuilder} instance for method chaining.
   */
  public CardBuilder setValue(VALUES value) {
    this.value = value;
    return this;
  }

  /**
   * Sets the face orientation of the card (face up or face down).
   *
   * @param faceUP {@code true} to set the card as face up (visible), {@code false} to set it as face down (hidden).
   * @return This {@code CardBuilder} instance for method chaining.
   */
  public CardBuilder setFaceUP(boolean faceUP) {
    this.faceUP = faceUP;
    return this;
  }

  /**
   * Creates a new card instance based on the provided suit, value, and face orientation settings.
   *
   * @return A new {@code Card} instance with the specified suit, value, and face orientation.
   * @throws IllegalStateException If the suit and/or value have not been set before creating the card.
   */
  public Card createCard() throws IllegalStateException {
    if (suit == null || value == null) throw new IllegalStateException("Suit and value must be set");
    return new Card(suit, value, faceUP);
  }
}
