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
    handleDefaultMovement(state, prevStack, nextStack);
  }

  private void handleDeckMovement(SolitaireState state, DeckCardStack prevStack, CardStack nextStack) {
    if (nextStack instanceof WasteCardStack) {
      handleDeckToWasteMovement(state, prevStack, (WasteCardStack) nextStack);
    } else if (nextStack instanceof DeckCardStack) {
      handleDeckToDeckMovement(state, prevStack, (DeckCardStack) nextStack);
    } else {
      handleDefaultMovement(state, prevStack, nextStack);
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
  }

  private void handleDeckToWasteMovement(SolitaireState state, DeckCardStack prevStack, WasteCardStack nextStack) {
    System.out.println("Deck to waste movement");
    int toMove = Math.min(2, prevStack.getCards().size());
    for (; toMove > 0; toMove--) {
      nextStack.push(prevStack.pop());
    }
  }

}
