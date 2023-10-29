package test.cst8334.solitaire.cardstacks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.cardstacks.TableauCardStack;

class TableauCardStackTest {

  @Test
  void testCanPush() {
    TableauCardStack stack = new TableauCardStack();
    Card card1 = new Card(Card.SUITS.CLUBS, Card.VALUES.KING);
    Card card2 = new Card(Card.SUITS.SPADES, Card.VALUES.QUEEN);
    Card card3 = new Card(Card.SUITS.HEARTS, Card.VALUES.QUEEN);

    assertTrue(stack.canPush(card1), "Should be able to push a King card to an empty stack");
    stack.push(card1);
    assertFalse(stack.canPush(card2), "Should not be able to push a Queen card with a same color to a King card");
    assertTrue(stack.canPush(card3), "Should be able to push an Queen card with a different color to a King card");
  }

  @Test
  void testPushAndPop() {
    TableauCardStack stack = new TableauCardStack();
    Card card1 = new Card(Card.SUITS.CLUBS, Card.VALUES.KING);
    Card card2 = new Card(Card.SUITS.HEARTS, Card.VALUES.QUEEN);

    stack.push(card1);
    assertEquals(card1, stack.pop(), "Popped card should be the same as the one pushed");
    assertNull(stack.pop(), "Popping from an empty stack should return null");

    stack.push(card1);
    stack.push(card2);
    assertEquals(card2, stack.pop(), "Popped card should be the most recently pushed card");
    assertEquals(card1, stack.pop(), "Popped card should be the only remaining card in the stack");
  }

  @Test
  void testSetSelected() {
    TableauCardStack stack = new TableauCardStack();
    Card card1 = new Card(Card.SUITS.CLUBS, Card.VALUES.KING);
    Card card2 = new Card(Card.SUITS.HEARTS, Card.VALUES.QUEEN);

    stack.push(card1);
    stack.push(card2);
    stack.setSelected(true);

    assertTrue(card1.isFaceUp(), "First card should be face up when the stack is selected");
    assertTrue(card2.isFaceUp(), "Second card should be face up when the stack is selected");

    // assertTrue(card1.isSelected(), "First card should be selected when the stack is selected");
    // assertTrue(card2.isSelected(), "Second card should be selected when the stack is selected");

    // stack.setSelected(false);
    // assertFalse(card1.isSelected(), "First card should be selected when the stack is not selected");
    // assertFalse(card2.isSelected(), "Second card should be selected when the stack is selected");

  }

}
