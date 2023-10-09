package com.cst8334.solitaire;


	import java.awt.Graphics;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.Timer;

	import com.cst8334.solitaire.utils.Drawable;

	/**
	 * The {@code SolitaireGame} class represents the main game interface for a Solitaire game.
	 * It extends JPanel and implements ActionListener for rendering and updating the game state.
	 * 
	 * @see javax.swing.JPanel
	 * @see java.awt.event.ActionListener
	 * @version 1.0
	 * @since 17
	 * @author Austin Kirby
	 */
	public class SolitaireGame extends JPanel implements ActionListener {

	  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	/**
	   * The title of the game window.
	   */
	  private static final String WINDOW_TITLE = "Solitaire";

	  /**
	   * The width of the game window.
	   */
	  private static final int WINDOW_WIDTH = 800;

	  /**
	   * The height of the game window.
	   */
	  private static final int WINDOW_HEIGHT = 600;

	  /**
	   * The current state of the Solitaire game.
	   */
	  private SolitaireState state;

	  /**
	   * Constructs a new instance of the Solitaire game.
	   * Initializes the game state and sets up a timer for rendering.
	   */
	  public SolitaireGame() {
	    state = SolitaireState.initialState();
	    Timer renderTimer = new Timer(1000 / 60, this);
	    renderTimer.start();
	  }

	  /**
	   * The main entry point for the Solitaire game.
	   *
	   * @param args Command-line arguments (not used).
	   */
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			JFrame window = new JFrame(WINDOW_TITLE);
	    window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setLocationRelativeTo(null);

	    SolitaireGame game = new SolitaireGame();
	    window.add(game);

	    window.setVisible(true);
		}

	  /**
	   * Overrides the paintComponent method to render the game.
	   *
	   * @param gc The graphics context used for rendering.
	   */
	  @Override
	  protected void paintComponent(Graphics gc) {
	    super.paintComponents(gc);

	    // Iterate through the drawable objects in the game state and draw them
	    for (Drawable drawable : state.getStacks()) {
	      if (drawable == null) continue;
	      drawable.draw(gc);
	    }
	  }

	  /**
	   * Implements the actionPerformed method from the ActionListener interface.
	   * Repaints the game panel to update the rendering.
	   *
	   * @param e The ActionEvent representing the timer tick.
	   */
	  @Override
	  public void actionPerformed(ActionEvent e) {
	    repaint();
	  }
	}
