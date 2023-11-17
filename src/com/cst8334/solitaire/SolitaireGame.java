package com.cst8334.solitaire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.Timer;

import com.cst8334.solitaire.cardstacks.CardStack;
import com.cst8334.solitaire.utils.CardMovementHandler;
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
	  private static final int WINDOW_WIDTH = 1000;

	  /**
	   * The height of the game window.
	   */
	  private static final int WINDOW_HEIGHT = 600;

	  /**
	   * The current state of the Solitaire game.
	   */
	  private SolitaireState state;
	  /**
	   * The card movement handler for the game.
	   */
	  private CardMovementHandler cardMovementHandler;
	  
	  /*
	   * New game button (restart)
	   */

	    private JButton newGameButton;
	    
	    /**
	     * spider Solitaire button
	     */
	    private JButton spiderSolitaireButton;
	    
	    /*
	     * vegas Rules Solitaire button
	     */
	    private JButton vegasSolitaireButton;
	    
	    /*
	     * the score label
	     */
	    private JLabel scoreLabel;
	    
	    /*
	     * label for the value of the score
	     */
	    private JLabel scoreValueLabel;

  /**
   * Constructs a new instance of the Solitaire game.
   * Initializes the game state and sets up a timer for rendering.
   */
  public SolitaireGame() {
      state = SolitaireState.initialState();
      cardMovementHandler = new CardMovementHandler();
      Timer renderTimer = new Timer(1000 / 60, this);
      addMouseListener(new SolitaireGameMouseListener(this::handleMouseClick));
      renderTimer.start();
      
      

      // Right panel setup
      JPanel leftPanel = new JPanel();
      leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
      leftPanel.setPreferredSize(new Dimension(200, WINDOW_HEIGHT));
      leftPanel.setBackground(Color.cyan);
      
      // Score components
      scoreLabel = new JLabel("Score:");
      scoreValueLabel = new JLabel("0");
      scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      scoreValueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

      // Restart button
      newGameButton = new JButton("Restart Game");
      newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      newGameButton.addActionListener(this);

      // Game modes separator
      JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
      separator.setPreferredSize(new Dimension(180, 2));
      separator.setAlignmentX(Component.CENTER_ALIGNMENT);

      // Game mode buttons
      /* 
       * spider solitaire button
       */
      spiderSolitaireButton = new JButton("Spider Solitaire");
      spiderSolitaireButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      spiderSolitaireButton.addActionListener(this);

      /*
       * vegas Rules solitaire button 
       */
      vegasSolitaireButton = new JButton("Vegas Rules Solitaire");
      vegasSolitaireButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      vegasSolitaireButton.addActionListener(this);

      // Add components to the left panel
      leftPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add some spacing
      leftPanel.add(scoreLabel);
      leftPanel.add(scoreValueLabel);
      leftPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some spacing
      leftPanel.add(newGameButton);
      leftPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some spacing
      leftPanel.add(separator);
      leftPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some spacing
      leftPanel.add(spiderSolitaireButton);
      leftPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Add some spacing
      leftPanel.add(vegasSolitaireButton);


      // Set BorderLayout for the main panel
      setLayout(new BorderLayout());

      // Add right panel to the right of the main panel
      add(leftPanel, BorderLayout.EAST);
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
      super.paintComponent(gc);
      // Draw the background
      gc.setColor(Color.WHITE);
      gc.fillRect(0, 0, getWidth(), getHeight());
      gc.setColor(Color.black);
      gc.setFont(new Font("Arial", Font.PLAIN, 16));
      // Do not draw the score in the bottom left corner here

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
      if (e.getSource() == newGameButton) {
          state = SolitaireState.initialState();
      } else if (e.getSource() == spiderSolitaireButton) {
          // Handle Spider Solitaire button click (change game settings)
      } else if (e.getSource() == vegasSolitaireButton) {
          // Handle Vegas Solitaire button click (change game settings)
      }

      // Update the score label
      scoreValueLabel.setText(Integer.toString(state.getScore()));

      // Update the UI, e.g., repaint or modify the labels
      repaint();
  }
  
  private void handleMouseClick(MouseEvent ev) {
    for (Selectable selectable : state.getStacks()) {
      if (selectable == null) continue;
      if (!selectable.contains(ev)) continue;
      if (state.getSelectedStack() == null) {
        state.setSelectedStack((CardStack) selectable);
        selectable.setSelected(true);
        return;
      }
      try {
        cardMovementHandler.handleCardMovement(state, state.getSelectedStack(), (CardStack) selectable);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
      state.getSelectedStack().setSelected(false);
      state.setSelectedStack(null);
    }
  }
}
