package com.cst8334.solitaire.utils;

import java.util.List;

import com.cst8334.solitaire.VegasRulesState;
import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.cardstacks.CardStack;
import com.cst8334.solitaire.cardstacks.DeckCardStack;
import com.cst8334.solitaire.cardstacks.FoundationCardStack;
import com.cst8334.solitaire.cardstacks.TableauCardStack;
import com.cst8334.solitaire.cardstacks.WasteCardStack;

public class CardMovementHandlerVegasRules {

	  public void handleCardMovement(VegasRulesState state, CardStack prevStack, CardStack nextStack) {
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
	    // Update the score based on the moved card
	    int scoreChange = calculateScoreChange(prevStack, nextStack);
	    state.updateScore(scoreChange, state.isVegasCumulative());
	  }

	   private void handleTableauMovement(VegasRulesState state, TableauCardStack prevStack, CardStack nextStack) {
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


	  private void handleFoundationMovement(VegasRulesState state, FoundationCardStack prevStack, CardStack nextStack) {
	    handleDefaultMovement(state, prevStack, nextStack);
	  }

	  private void handleWasteMovement(VegasRulesState state, WasteCardStack prevStack, CardStack nextStack) {
	    if (nextStack instanceof WasteCardStack) {
	      throw new IllegalArgumentException("Cannot move cards from the waste to the waste");
	    }
	    handleDefaultMovement(state, prevStack, nextStack);
	  }

	  //can only move deck cards to waste stack
	  private void handleDeckMovement(VegasRulesState state, DeckCardStack prevStack, CardStack nextStack) {
		  if (nextStack instanceof WasteCardStack) {
		    handleDeckToWasteMovement(state, prevStack, (WasteCardStack) nextStack);
		  } else if (nextStack instanceof DeckCardStack) {
	      handleDeckToDeckMovement(state, prevStack, (DeckCardStack) nextStack);
	    } else {
		    throw new IllegalArgumentException("Cannot move cards from the deck to a non-waste deck stack");
		  }
		}


	  private void handleDefaultMovement(VegasRulesState state, CardStack prevStack, CardStack nextStack) {
	    if (nextStack instanceof DeckCardStack) {
	      throw new IllegalArgumentException("Cannot move cards to deck");
	    }
	    System.out.println("Default movement");
	    Card card = prevStack.getLast();
	    nextStack.push(card);
	    prevStack.pop();
	  }

	  private void handleDeckToDeckMovement(VegasRulesState state, DeckCardStack prevStack, DeckCardStack nextStack) {
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

	  private void handleDeckToWasteMovement(VegasRulesState state, DeckCardStack prevStack, WasteCardStack nextStack) {
	    System.out.println("Deck to waste movement");
	    // center all cards in the waste stack
	    Position2D stackPosition = nextStack.getPosition();
	    nextStack.getCards().forEach(card -> card.setPosition(stackPosition));

	    // Determine how many cards to move based on the drawThreeSwitch checkbox
	    int toMove = state.isDrawThreeOption() ? Math.min(3, prevStack.getCards().size()) : 1;
	    for (int i = 0; i < toMove; i++) {

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

  //calculate the score based on vegas rules
	  private int calculateScoreChange(CardStack prevStack, CardStack nextStack) {
		    int scoreChange = 0;
		    // Scoring based on moving cards to the foundation stacks
		    if (prevStack instanceof TableauCardStack && nextStack instanceof FoundationCardStack) {
		        // Each card moved to the foundation piles is worth +5 points
		        scoreChange += 5;
		    } else if (prevStack instanceof WasteCardStack && nextStack instanceof FoundationCardStack) {
		        // Each card moved from waste to foundation is also worth +5 points
		        scoreChange += 5;
		    } 
		    return scoreChange;
		}




	}

