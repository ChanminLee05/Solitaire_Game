package com.cst8334.solitaire.cardstacks;

import java.util.ArrayList;
import java.util.List;

import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.cards.Card.SUITS;
import com.cst8334.solitaire.cards.Card.VALUES;

import com.cst8334.solitaire.utils.Position2D;



public class DeckCardStack extends CardStack {
	

    private List<Card> cards;

    /**
     * Default constructor, initializes the deck of cards.
     */
    public DeckCardStack() {
        initializeDeckOfCards(new Position2D(0, 0)); // Default position
    }
    
    /**
     * Constructor to create a deck of cards at a specific position.
     *
     * @param position The position to initialize the deck.
     */
    public DeckCardStack(Position2D position) {
        initializeDeckOfCards(position);
    }

    /**
     * Initializes and returns a deck of cards at the specified position.
     *
     * @param position The position to initialize the deck.
     * @return A list of initialized cards.
     */
    private List<Card> initializeDeckOfCards(Position2D position) {
        cards = new ArrayList<>();

        for (SUITS suit : SUITS.values()) {
            for (VALUES rank : VALUES.values()) {
                Card card = new Card(suit, rank, false);
                cards.add(card);
            }
        }

        // Set the position for the entire deck
        setPosition(position);

        return cards;
    }

    /**
     * Remove and return the top card from the deck.
     *
     * @return The top card from the deck.
     */
    public Card pop() {
        if (!isEmpty()) {
            Card topCard = cards.remove(cards.size() - 1);
            return topCard;
        } else {
            return null; // Deck is empty
        }
    }
}
    
