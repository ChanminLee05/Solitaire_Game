package com.cst8334.solitaire.cardstacks;

import java.util.List;

import com.cst8334.solitaire.cards.Card;
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
  }
	
 @Override
  public Card pop() {
      // Check if there are cards in the stack
      if (!isEmpty()) {
          // Find the first face-down card and change it to face-up
          for (Card card : getCards()) {
              if (!card.isFaceUp()) {
                  card.setFaceUp(true);
                  break; // Stop after changing the first face-down card
              }
          }
          return super.pop();
      }
      return null; // Return null if the stack is empty
  }
}

