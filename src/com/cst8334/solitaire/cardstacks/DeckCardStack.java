package com.cst8334.solitaire.cardstacks;

import java.util.ArrayList;
import java.util.List;

import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.cards.Card.SUITS;
import com.cst8334.solitaire.cards.Card.VALUES;

public class DeckCardStack extends CardStack {
	
	/** call generateDeckOfCards
	 * **/
	
    
    public DeckCardStack() {
        super(generateDeckOfCards());
    }
    
    /** 
     * create a deck of cards 
     * by ititrating throught every combination of suits x values
     * and adding a new card to the deck
     * @return
     */
    private static List<Card> generateDeckOfCards() {
        List<Card> deck = new ArrayList<>();

        for (SUITS suit : SUITS.values()) {
            for (VALUES rank : VALUES.values()) {
            	 Card card = new Card(suit, rank, false); 
                 deck.add(card);
            }
        }

        return deck;
    }
}
