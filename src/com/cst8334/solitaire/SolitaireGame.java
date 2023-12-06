package com.cst8334.solitaire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
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
	     * Undo button
	     */
	    private JButton undoButton;
	    
	    /*
	     * Vegas Rules Solitaire button
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
      leftPanel.setBackground(new Color(7, 92, 7));
      
      // Score components
      scoreLabel = new JLabel("Score:");
      scoreLabel.setForeground(Color.WHITE); // Set text color
      scoreLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font and style
      scoreValueLabel = new JLabel("0");
      scoreValueLabel.setForeground(Color.WHITE); // Set text color
      scoreValueLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font and style
      scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      scoreValueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      // Set the image as the button's icon
      ImageIcon restartIcon = new ImageIcon("..\\CST8334-Solitaire\\src\\main\\resources\\imgs\\restart.png");
      newGameButton = new JButton("Restart Game", restartIcon);
      newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      newGameButton.setBackground(new Color(202, 209, 63)); // Set background color
      newGameButton.setForeground(new Color(78, 168, 237)); // Set text color
      newGameButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set font and style
      newGameButton.setLayout(new BoxLayout(newGameButton, BoxLayout.Y_AXIS));
      newGameButton.setVerticalTextPosition(SwingConstants.BOTTOM); // Place text at the bottom
      newGameButton.setHorizontalTextPosition(SwingConstants.CENTER);
      newGameButton.setBorder(BorderFactory.createCompoundBorder(
    		  BorderFactory.createLineBorder(new Color(91, 189, 45), 3),
    		  BorderFactory.createEmptyBorder(5, 15, 5, 15)
    		  ));
      newGameButton.setFocusPainted(false);
      newGameButton.addActionListener(this);

      // Game modes separator
      JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
      separator.setPreferredSize(new Dimension(180, 2));
      separator.setAlignmentX(Component.CENTER_ALIGNMENT);

      /* 
       * undo button
       */
      // Set the image as the button's icon
      ImageIcon undoIcon = new ImageIcon("..\\CST8334-Solitaire\\src\\main\\resources\\imgs\\undo.png");
      undoButton = new JButton("Undo", undoIcon);
      undoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      undoButton.setBackground(new Color(232, 223, 56)); // Set background color
      undoButton.setForeground(new Color(78, 168, 237)); // Set text color
      undoButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set font and style
      undoButton.setFocusPainted(false);
      undoButton.setLayout(new BoxLayout(undoButton, BoxLayout.Y_AXIS));
      undoButton.setVerticalTextPosition(SwingConstants.BOTTOM); // Place text at the bottom
      undoButton.setHorizontalTextPosition(SwingConstants.CENTER);
      undoButton.setBorder(BorderFactory.createCompoundBorder(
    		  BorderFactory.createLineBorder(new Color(232, 164, 53), 3),
    		  BorderFactory.createEmptyBorder(5, 15, 5, 15)
    		  ));
      undoButton.addActionListener(this);

      /*
       * vegas Rules solitaire button 
       */
      ImageIcon vegasIcon = new ImageIcon("..\\CST8334-Solitaire\\src\\main\\resources\\imgs\\vegas.png");
      vegasSolitaireButton = new JButton("Vegas Rules Solitaire", vegasIcon);
      vegasSolitaireButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      vegasSolitaireButton.setBackground(new Color(51, 163, 232)); // Set background color
      vegasSolitaireButton.setForeground(new Color(230, 231, 232)); // Set text color
      vegasSolitaireButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set font and style
      vegasSolitaireButton.setFocusPainted(false);
      vegasSolitaireButton.setLayout(new BoxLayout(vegasSolitaireButton, BoxLayout.Y_AXIS));
      vegasSolitaireButton.setVerticalTextPosition(SwingConstants.BOTTOM); // Place text at the bottom
      vegasSolitaireButton.setHorizontalTextPosition(SwingConstants.CENTER);
      vegasSolitaireButton.setBorder(BorderFactory.createCompoundBorder(
    		  BorderFactory.createLineBorder(new Color(9, 114, 189), 3),
    		  BorderFactory.createEmptyBorder(5, 15, 5, 15)
    		  ));
      vegasSolitaireButton.addActionListener(this);

      // Add components to the left panel
      leftPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add some spacing
      leftPanel.add(scoreLabel);
      leftPanel.add(scoreValueLabel);
      leftPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some spacing
      leftPanel.add(newGameButton);
      leftPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some spacing
      leftPanel.add(separator);
      leftPanel.add(undoButton);
      leftPanel.add(Box.createRigidArea(new Dimension(0, 250))); // Add some spacing
      leftPanel.add(vegasSolitaireButton);
      leftPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Add some spacing


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
      
      // Check if the state is null
      if (state == null) {
          return;
      }
      
      // Draw the background
      Color color1 = new Color(7, 92, 7); // Start color
      Color color2 = new Color(9, 158, 9); // End color
      GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);

      Graphics2D g2d = (Graphics2D) gc;

      // Set the gradient paint
      g2d.setPaint(gradient);
      
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
      } else if (e.getSource() == undoButton) {
    	  return;	//Needs modification to make it work
      }
      	//when user clicks on Vegas Rules Solitaire button, another window pops open for vegas rules solitaire     
      	else if (e.getSource() == vegasSolitaireButton) {
    	  //when user clicks on Vegas Rules Solitaire button, it closes Klondlike game and opens Vegas rules solitaire
		    JFrame vegasRulesWindow = (JFrame) SwingUtilities.getWindowAncestor(this);
		    vegasRulesWindow.dispose();

    	  JFrame VRwindow = new JFrame("Vegas Solitaire");
    	  VRwindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    	  VRwindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	  VRwindow.setLocationRelativeTo(null);
    	  VegasRulesSolitaire game = new VegasRulesSolitaire();
    	  VRwindow.add(game);

    	  VRwindow.setVisible(true);
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
