package com.cst8334.solitaire.utils;

import java.util.List;

import com.cst8334.solitaire.SolitaireState;
import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.cardstacks.CardStack;
import com.cst8334.solitaire.cardstacks.DeckCardStack;
import com.cst8334.solitaire.cardstacks.FoundationCardStack;
import com.cst8334.solitaire.cardstacks.TableauCardStack;
import com.cst8334.solitaire.cardstacks.WasteCardStack;

public class CardMovementHandler {
  
  public void handleCardMovement(SolitaireState state, CardStack prevStack, CardStack nextStack) {
    prevStack.getCards().forEach(card -> card.setSelected(false));
    if (prevStack instanceof DeckCardStack) {
      handleDeckMovement(state, (DeckCardStack) prevStack, nextStack);
    } else if (prevStack instanceof WasteCardStack) {
      handleWasteMovement(state, (WasteCardStack) prevStack, nextStack);
    } else if (prevStack instanceof FoundationCardStack) {
      handleFoundationMovement(state, (FoundationCardStack) prevStack, nextStack);
    } else if (prevStack instanceof TableauCardStack) {
      handleTableauMovement(state, (TableauCardStack) prevStack, nextStack);
    } else {
      handleDefaultMovement(state, prevStack, nextStack);
    }
  }

   private void handleTableauMovement(SolitaireState state, TableauCardStack prevStack, CardStack nextStack) {
	    if (nextStack instanceof WasteCardStack) {
	        throw new IllegalArgumentException("Cannot move cards from the tableau to the waste");
	    }

	    if (nextStack.getClass() == TableauCardStack.class) {
	        List<Card> faceUpCards = prevStack.getFaceUpCards();
	        int toRemove = 0;
	        for (Card card : faceUpCards) {
	            if (!((TableauCardStack) nextStack).canPush(card)) continue;
	            nextStack.push(card);
	            toRemove++;
	        }
	        for (int i = 0; i < toRemove; i++) {
	            prevStack.pop();
	        }
	        System.out.println(prevStack.getLast());
	        prevStack.getLast().setFaceUp(true);
	        return;
	    }
	    handleDefaultMovement(state, prevStack, nextStack);
	}


  private void handleFoundationMovement(SolitaireState state, FoundationCardStack prevStack, CardStack nextStack) {
    handleDefaultMovement(state, prevStack, nextStack);
  }

  private void handleWasteMovement(SolitaireState state, WasteCardStack prevStack, CardStack nextStack) {
    if (nextStack instanceof WasteCardStack) {
      throw new IllegalArgumentException("Cannot move cards from the waste to the waste");
    }
    handleDefaultMovement(state, prevStack, nextStack);
  }

  //can only move deck cards to waste stack
  private void handleDeckMovement(SolitaireState state, DeckCardStack prevStack, CardStack nextStack) {
	  if (nextStack instanceof WasteCardStack) {
	    handleDeckToWasteMovement(state, prevStack, (WasteCardStack) nextStack);
	  } else if (nextStack instanceof DeckCardStack) {
      handleDeckToDeckMovement(state, prevStack, (DeckCardStack) nextStack);
    } else {
	    throw new IllegalArgumentException("Cannot move cards from the deck to a non-waste deck stack");
	  }
	}


  private void handleDefaultMovement(SolitaireState state, CardStack prevStack, CardStack nextStack) {
    if (nextStack instanceof DeckCardStack) {
      throw new IllegalArgumentException("Cannot move cards to deck");
    }
    System.out.println("Default movement");
    Card card = prevStack.getLast();
    nextStack.push(card);
    prevStack.pop();
  }

  private void handleDeckToDeckMovement(SolitaireState state, DeckCardStack prevStack, DeckCardStack nextStack) {
    System.out.println("Deck to deck movement");
    if (!prevStack.isEmpty()) {
      throw new IllegalArgumentException("Cannot refil cards if the deck is not empty");
    }
    CardStack wasteStack = state.getStacks().get(1);
    while (!wasteStack.isEmpty()) {
      prevStack.push(wasteStack.pop());
    }
    prevStack.getLast().setFaceUp(false);
  }

  private void handleDeckToWasteMovement(SolitaireState state, DeckCardStack prevStack, WasteCardStack nextStack) {
    System.out.println("Deck to waste movement");
    // center all cards in the waste stack
    Position2D stackPosition = nextStack.getPosition();
    nextStack.getCards().forEach(card -> card.setPosition(stackPosition));
    // move the top ~3 cards from the deck to the waste stack
    int toMove = Math.min(2, prevStack.getCards().size());
    for (int i = 0; i <= toMove; i++) {
      Card card = prevStack.pop();
      card.setFaceUp(true);
      // move the card to the left a bit
      Position2D cardPosition = Position2D.Zero();
      cardPosition.setY(stackPosition.getY());
      cardPosition.setX(stackPosition.getX() + (35 * i));
      nextStack.push(card);
      card.setPosition(cardPosition);
    }
  }

}
