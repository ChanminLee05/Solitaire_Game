package com.cst8334.solitaire.cardstacks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.cards.CardBuilder;
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
        shuffle();
    }
  }

  /**
   * creates a deck of cards using the CardBuilder class
   * 
   * @param position from position2D (sets the position of the deck)
   * @return the list of cards created as the deck of cards
   */
  private static List<Card> initializeDeckOfCards(Position2D position) {
    ArrayList<Card> cards = new ArrayList<>();

    for (SUITS suit : SUITS.values()) {
      for (VALUES rank : VALUES.values()) {
        Card card = new CardBuilder()
            .setSuit(suit)
            .setValue(rank)
            .setPosition(position)
            .setFaceUP(false) // Set the default face orientation (down)
            .createCard();

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
    Card topCard = super.pop();
    if (!isEmpty())
      getLast().setFaceUp(false);
    return topCard;
  }
//shuffle the deck
   public void shuffleDeck() {
	    Collections.shuffle(getCards());
	  }
}
