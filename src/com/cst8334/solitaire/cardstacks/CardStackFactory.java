package com.cst8334.solitaire.cardstacks;

import java.util.Collections;
import java.util.List;

import com.cst8334.solitaire.cards.Card;

/**
 * The {@code CardStackFactory} class is responsible for creating different types of card stacks
 * used in a Solitaire game, such as the deck, waste, foundation, and tableau stacks.
 * @see com.cst8334.solitaire.cardstacks.CardStack
 * @version 1.0
 * @since 17
 * @author Austin Kirby
 */
public class CardStackFactory {

  /**
   * An enumeration representing the types of card stacks that can be created.
   */
  public static enum TYPES {
    DECK,
    WASTE,
    FOUNDATION,
    TABLEAU
  }

  /**
   * Creates a new card stack of the specified type with no initial cards.
   *
   * @param type The type of card stack to create (e.g., DECK, WASTE, FOUNDATION, TABLEAU).
   * @return A new card stack of the specified type with no initial cards.
   */
  public static CardStack createCardStack(TYPES type) {
    return createCardStack(type, Collections.emptyList());
  }

  /**
   * Creates a new card stack of the specified type with the provided list of initial cards.
   *
   * @param type  The type of card stack to create (e.g., DECK, WASTE, FOUNDATION, TABLEAU).
   * @param cards The list of initial cards to populate the card stack.
   * @return A new card stack of the specified type initialized with the provided cards.
   */
  public static CardStack createCardStack(TYPES type, List<Card> cards) {
    switch (type) {
      case DECK:
        return new DeckCardStack(cards);
      case WASTE:
        return new WasteCardStack(cards);
      case FOUNDATION:
        return new FoundationCardStack(cards);
      case TABLEAU:
        return new TableauCardStack(cards);
      default:
        return null;
    }
  }

}
