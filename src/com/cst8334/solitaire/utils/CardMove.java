package com.cst8334.solitaire.utils;

import com.cst8334.solitaire.cardstacks.CardStack;

public class CardMove {
	private CardStack sourceStack;
    private CardStack destinationStack;

    public CardMove(CardStack sourceStack, CardStack destinationStack) {
        this.sourceStack = sourceStack;
        this.destinationStack = destinationStack;
    }

    public CardStack getSourceStack() {
        return sourceStack;
    }

    public CardStack getDestinationStack() {
        return destinationStack;
    }

    /**
     * Swaps the source and destination stacks.
     * This method is used for undoing a move.
     */
    public void swap() {
        // Swap the source and destination stacks
        CardStack temp = sourceStack;
        sourceStack = destinationStack;
        destinationStack = temp;
    }
}
