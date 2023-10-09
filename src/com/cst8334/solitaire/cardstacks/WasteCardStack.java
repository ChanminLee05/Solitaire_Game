package com.cst8334.solitaire.cardstacks;


import java.util.List;
import java.awt.Graphics;
import java.util.List;

import com.cst8334.solitaire.cards.Card;

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
   * Overrides the pop method to flip the second last card face up before popping the last card.
   *
   * @return The last card in the waste pile.
   */
  @Override
  public Card pop() {
    // Flip the second last card face up
    getCards().get(getCards().size() - 2).setFaceUp(true);
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
    // Flip the second last card face down
    getCards().get(getCards().size() - 2).setFaceUp(false);
    // Add the card and flip it face up
    getCards().add(card);
    card.setFaceUp(true);
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

  /**
   * Overrides the draw method to provide custom drawing logic for the waste pile.
   *
   * @param gc The graphics context on which to draw the waste pile.
   */
  @Override
  public void draw(Graphics gc) {
    // TODO - Flush this out once Entity and Position2D are implemented
    gc.drawRect(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
  }
}
