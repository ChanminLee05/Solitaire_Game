package com.cst8334.solitaire.cardstacks;

import java.util.List;

import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.utils.Position2D;

/**
 * The {@code FoundationCardStack} class represents a stack of playing cards.
 * It extends the {@code CardStack} class and includes additional methods for
 * managing foundation cards.
 * 
 * @see com.cst8334.solitaire.cardstacks.CardStack
 * @version 1.1
 * @since 17
 * @author Austin Kirby
 * @author Melodie Langlois
 */
public class FoundationCardStack extends CardStack {

  private Card.SUITS suit;

  /**
   * Constructs an empty foundation pile.
   */
  public FoundationCardStack() {
    super();
  }

  /**
   * Constructs a foundation pile with the specified list of cards.
   *
   * @param cards The list of cards to initialize the foundation pile with.
   */
  public FoundationCardStack(List<Card> cards) {
    super(cards);
  }

  /**
   * Constructs a foundation pile with the specified list of cards and position.
   *
   * @param cards    The list of cards to initialize the foundation pile with.
   * @param position The position of the foundation pile.
   */
  public FoundationCardStack(List<Card> cards, Position2D position) {
    super(cards, position);
  }

  public Card.SUITS getSuit() {
    return suit;
  }

  @Override
  public void push(Card card) throws IllegalArgumentException {
    if (card == null)
      return;
    if (isEmpty()) {
      // If the stack is empty, only an ACE can be pushed as the first card
      if (card.getValue() == Card.VALUES.ACE) {
        suit = card.getSuit();
        super.push(card);
      } else {
        throw new IllegalArgumentException("Only an ACE can be pushed as the first card.");
      }
    } else {
      // If the stack is not empty, the card must be of the same suit and one rank
      // higher
      if (suit != card.getSuit()) {
        throw new IllegalArgumentException("Card must be of the same suit as the foundation pile.");
      }
      if (getLast().getValue().ordinal() + 1 != card.getValue().ordinal()) {
        throw new IllegalArgumentException("Card must be one rank higher than the last card in the foundation pile.");
      }
      super.push(card);
    }
  }
}
