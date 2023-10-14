package com.cst8334.solitaire;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.cst8334.solitaire.utils.Drawable;
import com.cst8334.solitaire.utils.Selectable;
import com.cst8334.solitaire.utils.SolitaireGameMouseListener;

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
    addMouseListener(new SolitaireGameMouseListener(this::handleMouseClick));
    renderTimer.start();
  }

  /**
   * The main entry point for the Solitaire game.
   *
   * @param args Command-line arguments (not used).
   */
	public static void main(String[] args) {
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
      drawable.draw((Graphics2D) gc);
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

  private void handleMouseClick(MouseEvent ev) {
    for (Selectable selectable : state.getStacks()) {
      if (selectable == null) continue;
      if (!selectable.contains(ev)) continue;
      if (state.getSelectedStack() == null) {
        state.setSelectedStack((CardStack) selectable);
        selectable.setSelected(true);
      } else {
        state.getSelectedStack().setSelected(false);
        state.setSelectedStack(null);
      }
    }
  }

}
