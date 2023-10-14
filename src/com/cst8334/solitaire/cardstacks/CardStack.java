package com.cst8334.solitaire.cardstacks;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.utils.Drawable;
import com.cst8334.solitaire.utils.Entity;
import com.cst8334.solitaire.utils.Position2D;

/**
 * The {@code CardStack} class represents a stack of playing cards.
 * It implements the {@code Drawable} interface and extends the {@code Entity} class.
 * 
 * @see com.cst8334.solitaire.utils.Drawable
 * @see com.cst8334.solitaire.utils.Entity
 * @version 1.1
 * @since 17
 * @author Austin Kirby
 */
public class CardStack extends Entity {

  private static final int WIDTH = 70;

  private static final int HEIGHT = 90;

  /**
   * The list of cards in the stack.
   */
  private ArrayList<Card> cards;

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
    this(cards, new Position2D(0, 0));
  }

  /**
   * Constructs a card stack with the specified list of cards and position.
   * 
   * @param cards The list of cards to initialize the stack with.
   * @param position The position of the card stack.
   */
  public CardStack(List<Card> cards, Position2D position) {
    super(position, WIDTH, HEIGHT);
    this.cards = new ArrayList<>(cards);
  }

  /**
   * Sets the list of cards in the stack.
   * @param cards The list of cards to set.
   */
  protected void setCards(List<Card> cards) {
    this.cards = new ArrayList<>(cards);
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
    if (cards == null) return true; // Null check
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
    if (cards.isEmpty()) return null; // Null check (empty stack)
    return cards.get(cards.size() - 1);
  }

  /**
   * Pushes a card onto the top of the stack.
   *
   * @param card The card to be pushed.
   */
  public void push(Card card) {
    if (card == null) return;
    card.setPosition(getPosition());
    cards.add(card);
  }

  /**
   * Pops and removes the card from the top of the stack.
   *
   * @return The card that was removed from the top of the stack.
   */
  public Card pop() {
    try {
      return cards.remove(cards.size() - 1);
    } catch (IndexOutOfBoundsException e) {
      return null;
    }
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
  public void draw(Graphics2D gc) {
    if (isSelected()) {
      gc.setColor(Color.GREEN);
      gc.setStroke(new BasicStroke(2));
      gc.drawRect(getPosition().getX() - 2, getPosition().getY() - 2, getWidth() + 4, getHeight() + 4);
    } else {
      gc.setColor(Color.BLACK);
      gc.setStroke(new BasicStroke(1));
      gc.drawRect(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
    }
    for (Drawable drawable : getCards()) {
      if (drawable == null) continue;
      drawable.draw(gc);
    }
  }
}
