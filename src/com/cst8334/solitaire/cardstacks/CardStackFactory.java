package com.cst8334.solitaire.cardstacks;

import java.util.Collections;
import java.util.List;

import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.utils.Position2D;

/**
 * The {@code CardStackFactory} class is responsible for creating different types of card stacks
 * used in a Solitaire game, such as the deck, waste, foundation, and tableau stacks.
 * @see com.cst8334.solitaire.cardstacks.CardStack
 * @version 1.1
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
   * Creates a new card stack of the specified type with no initial cards.
   * 
   * @param type The type of card stack to create (e.g., DECK, WASTE, FOUNDATION, TABLEAU).
   * @param position The position of the card stack.
   * @return A new card stack of the specified type with no initial cards.
   */
  public static CardStack createCardStack(TYPES type, Position2D position) {
    return createCardStack(type, Collections.emptyList(), position);
  }

  /**
   * Creates a new card stack of the specified type with the provided list of initial cards.
   *
   * @param type  The type of card stack to create (e.g., DECK, WASTE, FOUNDATION, TABLEAU).
   * @param cards The list of initial cards to populate the card stack.
   * @return A new card stack of the specified type initialized with the provided cards.
   */
  public static CardStack createCardStack(TYPES type, List<Card> cards) {
    return createCardStack(type, cards, Position2D.ZERO());
  }

  /**
   * Creates a new card stack of the specified type with the provided list of initial cards.
   * 
   * @param type The type of card stack to create (e.g., DECK, WASTE, FOUNDATION, TABLEAU).
   * @param cards The list of initial cards to populate the card stack.
   * @param position The position of the card stack.
   * @return A new card stack of the specified type initialized with the provided cards.
   */
  public static CardStack createCardStack(TYPES type, List<Card> cards, Position2D position) {
    switch (type) {
      case DECK:
        return new DeckCardStack(cards, position);
      case WASTE:
        return new WasteCardStack(cards, position);
      case FOUNDATION:
        return new FoundationCardStack(cards, position);
      case TABLEAU:
        return new TableauCardStack(cards, position);
      default:
        return null;
    }
  }
}
