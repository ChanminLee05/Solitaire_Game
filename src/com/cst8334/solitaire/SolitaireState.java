package com.cst8334.solitaire;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.cardstacks.CardStack;
import com.cst8334.solitaire.cardstacks.CardStackFactory;
import com.cst8334.solitaire.cardstacks.CardStackFactory.TYPES;
import com.cst8334.solitaire.utils.Callback;
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
   * The selected card stack.
   */
  private CardStack selectedStack;

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
    state.timer = createGameTimer(() -> state.score -= 2);
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
   * Sets the selected card stack
   * @param cardStack The card stack to set as selected.
   */
  public void setSelectedStack(CardStack cardStack) {
    selectedStack = cardStack;
  }

  /**
   * Gets the selected card stack.
   * @return The selected card stack.
   */
  public CardStack getSelectedStack() {
    return selectedStack;
  }

  /**
   * Creates and returns the initial card stacks for a Solitaire game.
   *
   * @return A list of card stacks representing the initial game board setup.
   */
  private static List<CardStack> createInitialCardStacks() {
    List<CardStack> stacks = new ArrayList<CardStack>();
    stacks.add(CardStackFactory.createCardStack(TYPES.DECK, new Position2D(700, 50)));
    stacks.add(CardStackFactory.createCardStack(TYPES.WASTE, new Position2D(560, 50)));
    for (int i = 0; i < 4; i++) {
      stacks.add(CardStackFactory.createCardStack(TYPES.FOUNDATION, new Position2D(10 + (i * 100), 50)));
    }
    // Deal initial cards for the tableau stacks
    List<List<Card>> cards = dealInitialCards(stacks.get(0));
    for (int i = 0; i < 7; i++) {
      stacks.add(CardStackFactory.createCardStack(TYPES.TABLEAU, cards.get(i), new Position2D(10 + (i * 85), 200)));
    }
    return stacks;
  }

  /**
   * Creates and returns a timer for the Solitaire game.
   * 
   * @param callback The callback to call when the timer fires.
   * @return The timer for the Solitaire game.
   */
  private static Timer createGameTimer(Callback callback) {
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        callback.call();
      }
    }, 10000, 10000);
    return timer;
  }

  /**
   * Deals the initial cards to the card stacks from the DeckCardStack.
   * @param deck The deck of cards to deal from.
   * @return The list of card stacks with the initial cards dealt.
   */
  private static List<List<Card>> dealInitialCards(CardStack deck) {
    List<List<Card>> stacks = new ArrayList<List<Card>>();
    for (int i = 0; i < 7; i++) {
      ArrayList<Card> stack = new ArrayList<Card>();
      for (int j = 0; j <= i; j++) {
        Card card = deck.pop();
        card.setFaceUp(i == j);
        stack.add(card);
      }
      stacks.add(stack);
    }
    return stacks;
  }
}
