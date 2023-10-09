package com.cst8334.solitaire.cardstacks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.cards.Card.SUITS;
import com.cst8334.solitaire.cards.Card.VALUES;

import com.cst8334.solitaire.utils.Position2D;

public class DeckCardStack extends CardStack {

  /**
   * Default constructor, initializes the deck of cards.
   */
  public DeckCardStack() {
    this(Position2D.Zero());
  }

  /**
   * Constructor to create a deck of cards at a specific position.
   *
   * @param position The position to initialize the deck.
   */
  public DeckCardStack(Position2D position) {
    this(Collections.emptyList(), position);
  }

  public DeckCardStack(List<Card> cards, Position2D position) {
    super(cards, position);
    if (cards.isEmpty()) {
      setCards(initializeDeckOfCards(position));
    }
  }

  /**
   * Initializes and returns a deck of cards at the specified position.
   *
   * @param position The position to initialize the deck.
   * @return A list of initialized cards.
   */
  private static List<Card> initializeDeckOfCards(Position2D position) {
    ArrayList<Card> cards = new ArrayList<>();

    for (SUITS suit : SUITS.values()) {
      for (VALUES rank : VALUES.values()) {
        Card card = new Card(suit, rank, position);
        cards.add(card);
      }
    }

    return cards;
  }

  /**
   * Remove and return the top card from the deck.
   *
   * @return The top card from the deck.
   */
  @Override
  public Card pop() {
    if (!isEmpty()) {
      Card topCard = getCards().remove(getCards().size() - 1);
      getLast().setFaceUp(true);
      return topCard;
    } else {
      return null; // Deck is empty
    }
  }
}
