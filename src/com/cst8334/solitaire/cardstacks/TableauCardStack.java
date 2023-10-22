package com.cst8334.solitaire.cardstacks;

import java.util.List;

import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.cards.Card.SUITS;
import com.cst8334.solitaire.cards.Card.VALUES;
import com.cst8334.solitaire.utils.Position2D;

public class TableauCardStack extends CardStack {
	public TableauCardStack() {
		
	}
	
	public TableauCardStack(List<Card> card) {
		super();
	}

  public TableauCardStack(List<Card> cards, Position2D position) {
    super(cards, position);
    for (int i = 0; i < getCards().size(); i++) {
      int yPos = getPosition().getY() + i * 25;
      getCards().get(i).setPosition(new Position2D(getPosition().getX(), yPos));
    }
  }

  @Override
  public void push(Card card) {
    super.push(card);
    int yPos = getPosition().getY() + (getCards().size() - 1) * 25;
    card.setPosition(new Position2D(getPosition().getX(), yPos));
    
    if(card == null) return;

	/**
	 * if try to put other than King card on empty stack, it throws exception
	 * if not empty, add card on top if the second card's color is different 
	 * and if the second card's value is one lower than the first card
	 */
	if (isEmpty()) {
		if (card.getValue() == VALUES.KING) {
			super.push(card);
		} else {
			throw new IllegalArgumentException("Only King card can be placed when there is no card on stack");
		}
	} else {
		Card bottomCard = getFirst();

		int bottomCardValue = bottomCard.getValue().ordinal();
        int newCardValue = card.getValue().ordinal();

        if ((bottomCard.getSuit() == SUITS.HEARTS || bottomCard.getSuit() == SUITS.DIAMONDS)) {
			if (card.getSuit() == SUITS.CLUBS || card.getSuit() == SUITS.SPADES) {
				if (newCardValue - 1 == bottomCardValue) {
					super.push(card);
				}
			}
        }
	}
  }
  
  @Override
  public void setSelected(boolean selected) {
	  super.setSelected(selected);
	  
	// Update the selected state of all face-up cards in the stack
      for (Card card : getCards()) {
          if (card.isFaceUp()) {
              card.setSelected(selected);
          }
      }
  }
  
}
