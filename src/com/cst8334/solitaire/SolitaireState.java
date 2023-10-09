package com.cst8334.solitaire;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import com.cst8334.solitaire.cardstacks.CardStack;
import com.cst8334.solitaire.cardstacks.CardStackFactory;
import com.cst8334.solitaire.cardstacks.CardStackFactory.TYPES;

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
    stacks.add(CardStackFactory.createCardStack(TYPES.DECK));
    stacks.add(CardStackFactory.createCardStack(TYPES.WASTE));
    for (int i = 0; i < 4; i++) {
      stacks.add(CardStackFactory.createCardStack(TYPES.FOUNDATION));
    }
    for (int i = 0; i < 7; i++) {
      stacks.add(CardStackFactory.createCardStack(TYPES.TABLEAU));
    }
    return stacks;
  }
}
