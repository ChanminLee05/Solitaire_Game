package com.cst8334.solitaire.cardstacks;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.utils.Drawable;
import com.cst8334.solitaire.utils.Entity;

/**
 * The {@code CardStack} class represents a stack of playing cards.
 * It implements the {@code Drawable} interface and extends the {@code Entity} class.
 * 
 * @see com.cst8334.solitaire.utils.Drawable
 * @see com.cst8334.solitaire.utils.Entity
 * @version 1.0
 * @since 17
 * @author Austin Kirby
 */
public class CardStack extends Entity implements Drawable {

  /**
   * The list of cards in the stack.
   */
  private List<Card> cards;

  /**
   * Constructs an empty card stack.
   */
  public CardStack() {
    this(new ArrayList<Card>());
  }

  /**
   * Constructs a card stack with the specified list of cards.
   *
   * @param cards The list of cards to initialize the stack with.
   */
  public CardStack(List<Card> cards) {
    this.cards = cards;
  }

  /**
   * Gets the list of cards in the stack.
   *
   * @return The list of cards in the stack.
   */
  public List<Card> getCards() {
    return cards;
  }

  /**
   * Checks if the card stack is empty.
   *
   * @return {@code true} if the stack is empty, {@code false} otherwise.
   */
  public boolean isEmpty() {
    return cards.isEmpty();
  }

  /**
   * Adds a card to the top of the stack.
   *
   * @param card The card to be added.
   */
  public void putFirst(Card card) {
    cards.add(0, card);
  }

  /**
   * Gets the card at the top of the stack without removing it.
   *
   * @return The card at the top of the stack.
   */
  public Card getFirst() {
    return cards.get(0);
  }

  /**
   * Gets the card at the bottom of the stack without removing it.
   *
   * @return The card at the bottom of the stack.
   */
  public Card getLast() {
    return cards.get(cards.size() - 1);
  }

  /**
   * Pushes a card onto the top of the stack.
   *
   * @param card The card to be pushed.
   */
  public void push(Card card) {
    cards.add(card);
  }

  /**
   * Pops and removes the card from the top of the stack.
   *
   * @return The card that was removed from the top of the stack.
   */
  public Card pop() {
    return cards.remove(cards.size() - 1);
  }

  /**
   * Shuffles the cards in the stack.
   */
  public void shuffle() {
    Collections.shuffle(cards);
  }

  /**
   * Creates and returns a new card stack with the cards in the reverse order.
   *
   * @return A new card stack with the cards in the reverse order.
   */
  public CardStack reverse() {
    List<Card> reversedCards = new ArrayList<Card>(cards);
    Collections.reverse(reversedCards);
    return new CardStack(reversedCards);
  }

  /**
   * Draws the card stack on the specified graphics context.
   *
   * @param gc The graphics context on which to draw the card stack.
   */
  @Override
  public void draw(Graphics gc) {
    // TODO - Flush this out once Entity and Position2D are implemented
    gc.drawRect(getPosition().getXpos(), getPosition().getYpos(), getWidth(), getHeight());
  }
}
