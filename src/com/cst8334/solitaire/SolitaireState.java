package com.cst8334.solitaire;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.swing.text.Position;

import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.cardstacks.CardStack;
import com.cst8334.solitaire.cardstacks.CardStackFactory;
import com.cst8334.solitaire.cardstacks.CardStackFactory.TYPES;
import com.cst8334.solitaire.utils.Position2D;

/**
 * The {@code SolitaireState} class represents the state of a Solitaire game.
 * It includes information such as the player's score, a timer, and the card stacks on the game board.
 *
 * @see com.cst8334.solitaire.cardstacks.CardStack
 * @version 1.0
 * @since 17
 * @author Ausitn Kirby
 */
public class SolitaireState {

  /**
   * The player's current score in the Solitaire game.
   */
  private int score;

  /**
   * A timer used to track the duration of the game.
   */
  private Timer timer;

  /**
   * A list of card stacks on the game board.
   */
  private List<CardStack> stacks;

  /**
   * Private constructor to prevent direct instantiation of the class.
   */
  private SolitaireState() { }

  /**
   * Creates and returns the initial state of a Solitaire game.
   *
   * @return The initial Solitaire state with a score of 0, a timer, and initial card stacks.
   */
  public static SolitaireState initialState() {
    SolitaireState state = new SolitaireState();
    state.score = 0;
    state.timer = new Timer();
    state.stacks = createInitialCardStacks();
    return state;
  }

  /**
   * Gets the player's current score.
   *
   * @return The player's score.
   */
  public int getScore() {
    return score;
  }

  /**
   * Adds the given score to the player's current score.
   *
   * @param score The score to add to the player's current score.
   */
  public void addScore(int score) {
    this.score += score;
  }

  /**
   * Gets the timer used to track game duration.
   *
   * @return The game timer.
   */
  public Timer getTimer() {
    return timer;
  }

  /**
   * Gets the list of card stacks on the game board.
   *
   * @return A list of card stacks.
   */
  public List<CardStack> getStacks() {
    return stacks;
  }

  /**
   * Creates and returns the initial card stacks for a Solitaire game.
   *
   * @return A list of card stacks representing the initial game board setup.
   */
  private static List<CardStack> createInitialCardStacks() {
    List<CardStack> stacks = new ArrayList<CardStack>();
    stacks.add(CardStackFactory.createCardStack(TYPES.DECK, new Position2D(700, 10)));
    stacks.add(CardStackFactory.createCardStack(TYPES.WASTE, new Position2D(600, 10)));
    for (int i = 0; i < 4; i++) {
      stacks.add(CardStackFactory.createCardStack(TYPES.FOUNDATION, new Position2D(10 + (i * 100), 10)));
    }
    for (int i = 0; i < 7; i++) {
      stacks.add(CardStackFactory.createCardStack(TYPES.TABLEAU, new Position2D(10 + (i * 85), 150)));
    }
    return dealInitialCards(stacks);
  }

  /**
   * Deals the initial cards to the card stacks from the DeckCardStack.
   * @param stacks The list of card stacks from the initial game board setup.
   * @return The list of card stacks with the initial cards dealt.
   */
  private static List<CardStack> dealInitialCards(List<CardStack> stacks) {
    CardStack deck = stacks.get(0);
    for (int i = 0; i <= 7; i++) {
      CardStack tableau = stacks.get(5 + i);
      for (int j = 0; j < i; j++) {
        Card card = deck.pop();
        card.setFaceUp(i == j + 1);
        tableau.push(card);
      }
    }
    return stacks;
  }
}
