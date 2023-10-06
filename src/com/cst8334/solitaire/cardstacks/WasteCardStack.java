package com.cst8334.solitaire.cardstacks;

import java.util.List;

import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.utils.Position2D;

/**
 * The {@code WasteCardStack} class represents the waste card stack in a Solitaire game.
 * It extends the {@code CardStack} class.
 * 
 * @see com.cst8334.solitaire.cardstacks.CardStack
 * @version 1.0
 * @since 17
 * @author Austin Kirby
 */
public class WasteCardStack extends CardStack {

  /**
   * Creates a new waste card stack with no initial cards and a default position.
   */
  public WasteCardStack() {
    super();
  }

  /**
   * Creates a new waste card stack with the provided list of initial cards and a default position.
   * @param cards The list of initial cards to populate the waste card stack.
   */
  public WasteCardStack(List<Card> cards) {
    super(cards);
  }

  /**
   * Creates a new waste card stack with no initial cards and the provided position.
   * @param cards The list of initial cards to populate the waste card stack.
   * @param position The position of the waste card stack.
   */
  public WasteCardStack(List<Card> cards, Position2D position) {
    super(cards, position);
  }
}
