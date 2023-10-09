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
  }

  @Override
  public void push(Card card) {
    super.push(card);
    int yPos = getPosition().getY() + (getCards().size() - 1) * 25;
    card.setPosition(new Position2D(getPosition().getX(), yPos));
  }
  
}
