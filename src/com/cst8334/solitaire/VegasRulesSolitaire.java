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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.cst8334.solitaire.cardstacks.CardStack;
import com.cst8334.solitaire.utils.CardMovementHandler;
import com.cst8334.solitaire.utils.CardMovementHandlerVegasRules;
import com.cst8334.solitaire.utils.Drawable;
import com.cst8334.solitaire.utils.Selectable;
import com.cst8334.solitaire.utils.SolitaireGameMouseListener;

public class VegasRulesSolitaire extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	  private static final String WINDOW_TITLE = "Vegas Rules Solitaire";

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
	  private VegasRulesState state;
	  /**
	   * The card movement handler for the game.
	   */
	  private CardMovementHandlerVegasRules cardMovementHandlerVR;
  
	  /*
	   * New game button (restart)
	   */

	    private JButton newGameButton;

	    /**
	     *  Solitaire button (to go back)
	     */
	    private JButton OriginalSolitaireButton;

	    /*
	     * vegas Rules Solitaire button
	     */
	    private JButton FreeCellButton;

	    /*
	     * the score label
	     */
	    private JLabel scoreLabel;

	    /*
	     * label for the value of the score
	     */
	    private JLabel scoreValueLabel;

	    // Vegas cumulative switch
	    private JCheckBox drawThreeSwitch;

	    // Vegas cumulative switch
	    private JCheckBox vegasCumulativeSwitch;

	    /**
	     * Constructs a new instance of the Solitaire game.
	     * Initializes the game state and sets up a timer for rendering.
	     */
	    public VegasRulesSolitaire() {
	        state = VegasRulesState.initialState(false, 0);

	        cardMovementHandlerVR = new CardMovementHandlerVegasRules();
	        Timer renderTimer = new Timer(1000 / 60, this);
	        addMouseListener(new SolitaireGameMouseListener(this::handleMouseClick));
	        renderTimer.start();

	        // Right panel setup
	        JPanel rightPanel = new JPanel();
	        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
	        rightPanel.setPreferredSize(new Dimension(200, WINDOW_HEIGHT));
	        rightPanel.setBackground(Color.cyan);

	        // Score components
	        scoreLabel = new JLabel("Score:");
	        scoreValueLabel = new JLabel("0");
	        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	        scoreValueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

	        // Draw Three option switch
	        drawThreeSwitch = new JCheckBox("Draw 3 option");
	        drawThreeSwitch.setAlignmentX(Component.CENTER_ALIGNMENT);
	        drawThreeSwitch.addActionListener(this);

	        // Vegas cumulative switch
	        vegasCumulativeSwitch = new JCheckBox("Vegas Cumulative");
	        vegasCumulativeSwitch.setAlignmentX(Component.CENTER_ALIGNMENT);
	        vegasCumulativeSwitch.addActionListener(this);

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
	        OriginalSolitaireButton = new JButton("Klondike Solitaire");
	        OriginalSolitaireButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	        OriginalSolitaireButton.addActionListener(this);

	        /*
	         * vegas Rules solitaire button 
	         */
	        FreeCellButton = new JButton("Free Cell Solitaire");
	        FreeCellButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	        FreeCellButton.addActionListener(this);

	        // Add components to the left panel
	        rightPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add some spacing
	        rightPanel.add(scoreLabel);
	        rightPanel.add(scoreValueLabel);
	        rightPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some spacing
	        rightPanel.add(drawThreeSwitch);
	        rightPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some spacing
	        rightPanel.add(vegasCumulativeSwitch);
	        rightPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some spacing
	        rightPanel.add(newGameButton);
	        rightPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some spacing
	        rightPanel.add(separator);
	        rightPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some spacing
	        rightPanel.add(OriginalSolitaireButton);
	        rightPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Add some spacing
	        rightPanel.add(FreeCellButton);


	        // Set BorderLayout for the main panel
	        setLayout(new BorderLayout());

	        // Add right panel to the right of the main panel
	        add(rightPanel, BorderLayout.EAST);
	    }


	public static void main(String[] args) {
		 JFrame window = new JFrame(WINDOW_TITLE);
	      window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	      window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      window.setLocationRelativeTo(null);

	      VegasRulesSolitaire game = new VegasRulesSolitaire();
	      window.add(game);

	      window.setVisible(true);
	  }

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
	    	  if (!state.isVegasCumulative()) {
	              state.setScore(0);
	          }
	          state = VegasRulesState.initialState(state.isVegasCumulative(), state.getScore());
	          
	      } else if (e.getSource() == OriginalSolitaireButton) {
	    	  //when user clicks on Klondlike Solitaire button, it closes Vegas Rules Solitaire and opens Klondlike solitaire
	          JFrame vegasRulesWindow = (JFrame) SwingUtilities.getWindowAncestor(this);
	          vegasRulesWindow.dispose();
	          // Open the Solitaire window

	    	  JFrame window = new JFrame("Solitaire");
	    	  window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	    	  window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	  window.setLocationRelativeTo(null);
	    	  SolitaireGame game = new SolitaireGame();
	    	  window.add(game);

	    	  window.setVisible(true);
	      } else if (e.getSource() == FreeCellButton) {
	    	  //when user clicks on FreeCellButton, another window pops open of free cell solitaire

	    	  JFrame FCwindow = new JFrame("Free Cell Solitaire");
	    	  FCwindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	    	  FCwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	  FCwindow.setLocationRelativeTo(null);
	    	  
	    	  //This needs to be changed to FreeCell game once FreeCell class is created
	    	  SolitaireGame game = new SolitaireGame();
	    	  FCwindow.add(game);

	    	  FCwindow.setVisible(true);

	      } else if (e.getSource() == vegasCumulativeSwitch) {
	            // Toggle the Vegas cumulative option based on the switch state
	            state.setVegasCumulative(vegasCumulativeSwitch.isSelected());
        } else if (e.getSource() == drawThreeSwitch) {
	            // Toggle the Vegas cumulative option based on the switch state
	            state.setDrawThreeOption(drawThreeSwitch.isSelected());
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
	        cardMovementHandlerVR.handleCardMovement(state, state.getSelectedStack(), (CardStack) selectable);
	      } catch (Exception e) {
	        System.out.println(e.getMessage());
	      }
	      state.getSelectedStack().setSelected(false);
	      state.setSelectedStack(null);
	    }
	  }
	}

