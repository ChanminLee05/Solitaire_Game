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
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.cardstacks.CardStack;
import com.cst8334.solitaire.cardstacks.DeckCardStack;
import com.cst8334.solitaire.cardstacks.FoundationCardStack;
import com.cst8334.solitaire.cardstacks.TableauCardStack;
import com.cst8334.solitaire.cardstacks.WasteCardStack;
import com.cst8334.solitaire.utils.CardMove;
import com.cst8334.solitaire.utils.CardMovementHandlerVegasRules;
import com.cst8334.solitaire.utils.Drawable;
import com.cst8334.solitaire.utils.Firework;
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
	  private static final int WINDOW_HEIGHT = 800;

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
	     * the score label
	     */
	    private JLabel scoreLabel;

	    /*
	     * label for the value of the score
	     */
	    private JLabel scoreValueLabel;

	    // Vegas drawThree switch
	    private JCheckBox drawThreeSwitch;

	    // Vegas cumulative switch
	    private JCheckBox vegasCumulativeSwitch;
	    
	    /**
	     * Undo button
	     */
	    private JButton undoButton;

	    private Stack<CardMove> moveHistory;
	    
	    /**
	     * The original score before any moves are made.
	     */
	    private int originalScore;
	    
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

	        // Initialize original score
	        originalScore = state.getScore();
	        
	        // Right panel setup
	        JPanel rightPanel = new JPanel();
	        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
	        rightPanel.setPreferredSize(new Dimension(200, WINDOW_HEIGHT));
	        rightPanel.setBackground(new Color(7, 92, 7));

	     // Score components
	        scoreLabel = new JLabel("Score:");
	        scoreLabel.setForeground(Color.WHITE); // Set text color
	        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font and style
	        scoreValueLabel = new JLabel("0");
	        scoreValueLabel.setForeground(Color.WHITE); // Set text color
	        scoreValueLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font and style
	        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	        scoreValueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

	        // Draw Three option switch
	        drawThreeSwitch = new JCheckBox("Draw 3 option");
	        drawThreeSwitch.setAlignmentX(Component.CENTER_ALIGNMENT);
	        drawThreeSwitch.setBackground(new Color(7, 92, 7));
	        drawThreeSwitch.setFocusPainted(false);
	        drawThreeSwitch.addActionListener(this);

	        // Vegas cumulative switch
	        vegasCumulativeSwitch = new JCheckBox("Vegas Cumulative");
	        vegasCumulativeSwitch.setAlignmentX(Component.CENTER_ALIGNMENT);
	        vegasCumulativeSwitch.setBackground(new Color(7, 92, 7));
	        vegasCumulativeSwitch.setFocusPainted(false);
	        vegasCumulativeSwitch.addActionListener(this);

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
	         * klondike solitaire button
	         */
	        ImageIcon klondikeIcon = new ImageIcon("..\\CST8334-Solitaire\\src\\main\\resources\\imgs\\klondike.png");
	        OriginalSolitaireButton = new JButton("Klondike Solitaire", klondikeIcon);
	        OriginalSolitaireButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	        OriginalSolitaireButton.setBackground(new Color(51, 163, 232)); // Set background color
	        OriginalSolitaireButton.setForeground(new Color(230, 231, 232)); // Set text color
	        OriginalSolitaireButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set font and style
	        OriginalSolitaireButton.setFocusPainted(false);
	        OriginalSolitaireButton.setLayout(new BoxLayout(OriginalSolitaireButton, BoxLayout.Y_AXIS));
	        OriginalSolitaireButton.setVerticalTextPosition(SwingConstants.BOTTOM); // Place text at the bottom
	        OriginalSolitaireButton.setHorizontalTextPosition(SwingConstants.CENTER);
	        OriginalSolitaireButton.setBorder(BorderFactory.createCompoundBorder(
	      		  BorderFactory.createLineBorder(new Color(9, 114, 189), 3),
	      		  BorderFactory.createEmptyBorder(5, 15, 5, 15)
	      		  ));
	        OriginalSolitaireButton.addActionListener(this);

	        // Add components to the left panel
	        rightPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add some spacing
	        rightPanel.add(scoreLabel);
	        rightPanel.add(scoreValueLabel);
	        rightPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some spacing
	        rightPanel.add(drawThreeSwitch);
	        rightPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some spacing
	        rightPanel.add(vegasCumulativeSwitch);
	        rightPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Add some spacing
	        rightPanel.add(newGameButton);
	        rightPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some spacing
	        rightPanel.add(separator);
	        rightPanel.add(undoButton);
	        rightPanel.add(Box.createRigidArea(new Dimension(0, 300))); // Add some spacing
	        rightPanel.add(OriginalSolitaireButton);
	        rightPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Add some spacing


	        // Set BorderLayout for the main panel
	        setLayout(new BorderLayout());

	        // Add right panel to the right of the main panel
	        add(rightPanel, BorderLayout.EAST);
	        
	        moveHistory = new Stack<>();
	    }


	public static void main(String[] args) {
		ImageIcon favicon = new ImageIcon("..\\CST8334-Solitaire\\src\\main\\resources\\imgs\\cards.png");
		
		JFrame window = new JFrame(WINDOW_TITLE);
			window.setIconImage(favicon.getImage());
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
	      Color color1 = new Color(7, 92, 7); // Start color
	      Color color2 = new Color(9, 158, 9); // End color
	      GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);

	      Graphics2D g2d = (Graphics2D) gc;

	      // Set the gradient paint
	      g2d.setPaint(gradient);
	      gc.fillRect(0, 0, getWidth(), getHeight());
	      gc.setFont(new Font("Arial", Font.PLAIN, 16));
	      // Do not draw the score in the bottom left corner here

	      // Check if Tableau stacks are empty
	      boolean tableauEmpty = true;
	      for (CardStack stack : state.getStacks()) {
	          if (stack instanceof TableauCardStack) {
	              if (!stack.isEmpty()) {
	            	  //change to false later
	                  tableauEmpty = false;
	                  break;
	              }
	          }
	      }
	      
	   // Draw animation if both Deck and Tableau stacks are empty
	      if (tableauEmpty) {
	          drawAnimation(gc);
		  } else {
		      // Iterate through the drawable objects in the game state and draw them
		      for (Drawable drawable : state.getStacks()) {
		          if (drawable == null) continue;
		          drawable.draw((Graphics2D) gc);
		      }
	      }
	      
	      // Iterate through the drawable objects in the game state and draw them
	      for (Drawable drawable : state.getStacks()) {
	          if (drawable == null) continue;
	          drawable.draw((Graphics2D) gc);
	      }
	  }

	 private ArrayList<Firework> fireworks = new ArrayList<>();
	 private Random random = new Random();
	  
	 //Fireworks animation method
	 private void drawAnimation(Graphics gc) {
		   
	    for (Firework firework : fireworks) {
	        firework.draw(gc);
	    }
	    
		// Draw the animation here
	   gc.setColor(Color.red);
	   gc.setFont(new Font("Arial", Font.BOLD, 30));
	   String message = "Congratulations! You have completed the game!";
	   int messageWidth = gc.getFontMetrics().stringWidth(message);
	   int x = (getWidth() - messageWidth) / 5;
	   int y = getHeight() / 2;
	   gc.drawString(message, x, y);
	}
		
	private void updateFireworks() {
	    fireworks.removeIf(Firework::isBurntOut);
	    if (random.nextInt(100) < 10) {
	        fireworks.add(new Firework(random.nextInt(getWidth()), getHeight()));
	    }
	}
	
	/**
     * Reverts the last move made by the player.
     */
    private void undo() {
    	if (!moveHistory.isEmpty()) {
            CardMove lastMove = moveHistory.pop();
            CardStack sourceStack = lastMove.getDestinationStack();
            CardStack prevStack = lastMove.getSourceStack();
            
         // Debugging information
            System.out.println("Source Stack: " + sourceStack);
            System.out.println("Prev Stack: " + prevStack);
            
            if (sourceStack != null && prevStack != null && sourceStack instanceof FoundationCardStack && prevStack instanceof TableauCardStack) {
                // Subtract 5 from the score only if moving from FoundationCardStack to TableauCardStack
                int scoreChange = -5;
                state.updateScore(scoreChange, state.isDrawThreeOption());
            }
            
            // Check if the source and prev stacks are not null
            if (sourceStack != null && !sourceStack.equals(prevStack)) {
            	((TableauCardStack) prevStack).setUndoPush(true);
                // Remove the card from the prev stack
                Card movedCard = sourceStack.pop();
                
             // Debugging information
                System.out.println("Moved Card: " + movedCard);
                
                // Add the card back to the prev stack
                prevStack.push(movedCard);
                
                ((TableauCardStack) prevStack).setUndoPush(false);
                // Debugging information
                System.out.println("Card pushed to Prev Stack");

            }
            
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
		  ImageIcon favicon = new ImageIcon("..\\CST8334-Solitaire\\src\\main\\resources\\imgs\\cards.png");
	      if (e.getSource() == newGameButton) {
	    	  if (!state.isVegasCumulative()) {
	              state.setScore(0);
	          }
	          state = VegasRulesState.initialState(state.isVegasCumulative(), state.getScore());
	          
	          drawThreeSwitch.setSelected(false);
	          vegasCumulativeSwitch.setSelected(false);
	          
	      } else if (e.getSource() == OriginalSolitaireButton) {
	    	  //when user clicks on Klondlike Solitaire button, it closes Vegas Rules Solitaire and opens Klondlike solitaire
	          JFrame vegasRulesWindow = (JFrame) SwingUtilities.getWindowAncestor(this);
	          vegasRulesWindow.dispose();
	          // Open the Solitaire window

	    	  JFrame window = new JFrame("Solitaire");
	    	  window.setIconImage(favicon.getImage());
	    	  window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	    	  window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	  window.setLocationRelativeTo(null);
	    	  SolitaireGame game = new SolitaireGame();
	    	  window.add(game);

	    	  window.setVisible(true);

	      } else if (e.getSource() == vegasCumulativeSwitch) {
	    	  
	            // Toggle the Vegas cumulative option based on the switch state
	            state.setVegasCumulative(vegasCumulativeSwitch.isSelected());
	      } else if (e.getSource() == drawThreeSwitch) {
	    	  
	            // Toggle the Vegas cumulative option based on the switch state
	            state.setDrawThreeOption(drawThreeSwitch.isSelected());
	      }	else if (e.getSource() == undoButton) {
	    	  
	            // Handle undo button click
	            undo();
	      }

	      // Update the score label
	      scoreValueLabel.setText(Integer.toString(state.getScore()));
	      
	      // Update the original score if it has changed
	        if (state.getScore() != originalScore) {
	            originalScore = state.getScore();
	        }

	      updateFireworks();
	      
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
	    	  CardStack sourceStack = state.getSelectedStack();
	          CardStack prevStack = (CardStack) selectable;
	          
	          // Make the move using cardMovementHandlerVR
	          cardMovementHandlerVR.handleCardMovement(state, state.getSelectedStack(), (CardStack) selectable);
	          
	            System.out.println("Move made from " + sourceStack + " to " + prevStack);
	            
	            if (!sourceStack.equals(prevStack)) {
	            	if (!(sourceStack instanceof WasteCardStack || sourceStack instanceof DeckCardStack)) {
			          // Create a CardMove object and push it onto the move history stack
			          moveHistory.push(new CardMove(sourceStack, prevStack));
	            	}
	            }
	          
	            System.out.println("Move history size: " + moveHistory.size());
	      } catch (Exception e) {
	          System.out.println(e.getMessage());
	      }
	      state.getSelectedStack().setSelected(false);
	      state.setSelectedStack(null);
	    }
	  }
	  
}

