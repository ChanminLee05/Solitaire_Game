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

public class VegasRulesState {
	 /**
	   * The player's current score in the game.
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

	  //vegas cumulative switch boolean (to check if it is enabled or not)
	  private boolean isVegasCumulative;

	  //draw three option switch boolean (to check if it is enabled or not)
	  private boolean drawThreeOption;


	  /**
	   * Private constructor to prevent direct instantiation of the class.
	   */
	  private VegasRulesState() { }

	  /**
	   * Creates and returns the initial state of a Solitaire game (vegas rule).
	   *
	   * @return The initial Solitaire state with a score of -52, a timer, and initial card stacks.
	   */
	  public static VegasRulesState initialState(boolean isVegasCumulative, int currentScore) {
	    VegasRulesState state = new VegasRulesState();
	    state.score = isVegasCumulative ? currentScore : -52;
	    state.timer = createGameTimer(() -> state.score -= 0);
	    state.stacks = createInitialCardStacks();
	    state.isVegasCumulative = isVegasCumulative;
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

	  public void setScore(int score) {
	        this.score = score;
	    }

	  /**
	   * Adds the given score to the player's current score.
	   *
	   * @param score The score to add to the player's current score.
	   */
	  /**
	     * Updates the score based on the game rules.
	     *
	     * @param scoreChange The change in score based on the current move.
	     * @param draw3Option Indicates whether the Draw 3 option is on.
	     */
	    public void updateScore(int scoreChange, boolean draw3Option) {

	        // Update the score
	        score += scoreChange;
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
	     * Sets the Vegas cumulative option.
	     *
	     * @param vegasCumulative true if Vegas cumulative is on, false otherwise.
	     */
	    public void setVegasCumulative(boolean vegasCumulative) {
	        this.isVegasCumulative = vegasCumulative;
	    }

	    /**
	     * Checks if Vegas cumulative is on.
	     *
	     * @return true if Vegas cumulative is on, false otherwise.
	     */
	    public boolean isVegasCumulative() {
	        return isVegasCumulative;
	    }

	    public boolean isDrawThreeOption() {
	        return drawThreeOption;
	    }

	    public void setDrawThreeOption(boolean drawThreeOption) {
	        this.drawThreeOption = drawThreeOption;
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
