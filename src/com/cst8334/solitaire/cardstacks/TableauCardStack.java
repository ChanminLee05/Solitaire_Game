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

  /**
   * Returns a list of face-up cards in the stack.
   * @return A list of face-up cards in the stack.
   */
  public List<Card> getFaceUpCards() {
    return getCards().stream().filter(Card::isFaceUp).toList();
  }

  public boolean canPush(Card card) {
    if (isEmpty()) {
      return card.getValue() == VALUES.KING;
    }
    Card topCard = getLast();
    if (topCard.isFaceUp() && topCard.getValue().ordinal() - 1 != card.getValue().ordinal()) {
      return false;
    }
    if ((topCard.getSuit() == SUITS.HEARTS || topCard.getSuit() == SUITS.DIAMONDS) &&
        !(card.getSuit() == SUITS.CLUBS || card.getSuit() == SUITS.SPADES)) {
      return false;
    }
    if ((topCard.getSuit() == SUITS.CLUBS || topCard.getSuit() == SUITS.SPADES) &&
        !(card.getSuit() == SUITS.HEARTS || card.getSuit() == SUITS.DIAMONDS)) {
      return false;
    }
    return true;
  }

  @Override
  public void push(Card card) {
    
    if(card == null) return;

	/**
	 * if try to put other than King card on empty stack, it throws exception
	 * if not empty, add card on top if the second card's color is different 
	 * and if the second card's value is one lower than the first card
	 */
	if (isEmpty()) {
		if (card.getValue() == VALUES.KING) {
			super.push(card);
			card.setFaceUp(true);
		} else {
			throw new IllegalArgumentException("Only King card can be placed when there is no card on stack");
		}
	} else {
		Card topCard = getLast();

	      if (topCard.isFaceUp() && topCard.getValue().ordinal() - 1 == card.getValue().ordinal()) {
	    	  if ((topCard.getSuit() == SUITS.HEARTS || topCard.getSuit() == SUITS.DIAMONDS) &&
	    			  (card.getSuit() == SUITS.CLUBS || card.getSuit() == SUITS.SPADES)) {
	    		  		super.push(card);
	    		  		card.setFaceUp(true);
	    	  } else if ((topCard.getSuit() == SUITS.CLUBS || topCard.getSuit() == SUITS.SPADES) &&
	    			  (card.getSuit() == SUITS.HEARTS || card.getSuit() == SUITS.DIAMONDS)) {
	    		  		super.push(card);
	    		  		card.setFaceUp(true);
	    	  }
	      } else {
	    	  throw new IllegalArgumentException("Different color and suit, or 1 lower value card should be placed");
	      }
    }
	    int yPos = getPosition().getY() + (getCards().size() - 1) * 25;
	    card.setPosition(new Position2D(getPosition().getX(), yPos));
  }

	
  @Override
  public Card pop() {
      // Check if there are cards in the stack
      if (!isEmpty()) {
          Card topCard = getLast();
          
          // Check if the top card is face-down
          if (!topCard.isFaceUp()) {
              topCard.setFaceUp(true); // Change the face-down card to face-up
          }
	      // Check if there is another card beneath the top card and change it to face-up automatically
          if (getCards().size() > 1) {
              Card nextCard = getCards().get(getCards().size() - 2);
              if (!nextCard.isFaceUp()) {
                  nextCard.setFaceUp(true);
              }
          }
          
          return super.pop();
      }
      return null; // Return null if the stack is empty
  }
  
  @Override
  public void setSelected(boolean selected) {
	  super.setSelected(selected);
	  
	  if (!isEmpty()) {
		  Card lastCard = getLast();
		  
		  if (!lastCard.isFaceUp()) {
			  lastCard.setFaceUp(true);
	      }
	  }
  }
}


