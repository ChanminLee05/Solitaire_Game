package com.cst8334.solitaire.cardstacks;

import java.util.List;

import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.utils.Position2D;

/**
 * The {@code FoundationCardStack} class represents a stack of playing cards.
 * It extends the {@code CardStack} class and includes additional methods for managing foundation cards.
 * 
 * @see com.cst8334.solitaire.cardstacks.CardStack
 * @version 1.0
 * @since 17
 * @author Austin Kirby
 */
public class FoundationCardStack extends CardStack {

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
   * @param cards The list of cards to initialize the foundation pile with.
   * @param position The position of the foundation pile.
   */
  public FoundationCardStack(List<Card> cards, Position2D position) {
    super(cards, position);
  }

}
