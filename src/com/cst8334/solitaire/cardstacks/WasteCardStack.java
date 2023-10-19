package com.cst8334.solitaire.cardstacks;

import java.util.List;
import java.awt.Graphics2D;

import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.utils.Position2D;

/**
 * The {@code WasteCardStack} class represents the waste pile in a Solitaire card game.
 * It extends the {@code CardStack} class and includes additional methods for managing waste cards.
 * @see com.cst8334.solitaire.cardstacks.CardStack
 * @version 1.0
 * @since 17
 * @author Austin Kirby
 */
public class WasteCardStack extends CardStack {

  /**
   * Constructs an empty waste pile.
   */
  public WasteCardStack() {
    super();
  }

  /**
   * Constructs a waste pile with the specified list of cards.
   *
   * @param cards The list of cards to initialize the waste pile with.
   */
  public WasteCardStack(List<Card> cards) {
    super(cards);
  }

  /**
   * Constructs a waste pile with the specified list of cards and position.
   *
   * @param cards The list of cards to initialize the waste pile with.
   * @param position The position of the waste pile.
   */
  public WasteCardStack(List<Card> cards, Position2D position) {
    super(cards, position);
  }

 @Override
  public Card pop() {
    List<Card> cards = getCards();
    if (cards.size() >= 2) {
      cards.get(cards.size() - 2).setFaceUp(true);
    }

    // Return the last card
    return super.pop();
  }

  /**
   * Overrides the push method to flip the second last card face down before adding a new card
   * and flipping it face up.
   *
   * @param card The card to push onto the waste pile.
   */
  @Override
  public void push(Card card) {
    List<Card> cards = getCards();
    if (cards.size() >= 2) {
      cards.get(cards.size() - 2).setFaceUp(false);
    }
    
    // Add the card and flip it face up
    card.setFaceUp(true);
    super.push(card);
  }

  /**
   * Pushes exactly three cards onto the waste pile and flips them face down,
   * except for the last card, which is flipped face up.
   *
   * @param cards The list of three cards to push onto the waste pile.
   * @throws IllegalArgumentException If the number of cards to push is not exactly three.
   */
  public void pushThreeCards(List<Card> cards) throws IllegalArgumentException {
    if (cards.size() != 3) {
      throw new IllegalArgumentException("Must push exactly three cards");
    }
    // Add all cards and flip them face down
    getCards().addAll(cards);
    getCards().forEach((card) -> card.setFaceUp(false));
    // Flip the last card face up
    getLast().setFaceUp(true);
  }
}
