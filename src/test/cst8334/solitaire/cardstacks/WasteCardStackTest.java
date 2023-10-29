package test.cst8334.solitaire.cardstacks;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.cardstacks.WasteCardStack;

class WasteCardStackTest {

  @Test
  void testPop() {
    List<Card> cards = new ArrayList<>();
    Card card1 = new Card(Card.SUITS.CLUBS, Card.VALUES.ACE);
    Card card2 = new Card(Card.SUITS.DIAMONDS, Card.VALUES.TWO);
    Card card3 = new Card(Card.SUITS.HEARTS, Card.VALUES.THREE);
    cards.add(card1);
    cards.add(card2);
    cards.add(card3);
    WasteCardStack stack = new WasteCardStack(cards);

    assertEquals(card3, stack.pop(), "Popped card should be the last card in the list");
    assertTrue(card2.isFaceUp(), "Second last card should be face up after popping the last card");
  }

  @Test
  void testPush() {
    WasteCardStack stack = new WasteCardStack();
    Card card1 = new Card(Card.SUITS.CLUBS, Card.VALUES.ACE);
    Card card2 = new Card(Card.SUITS.DIAMONDS, Card.VALUES.TWO);
    stack.push(card1);
    stack.push(card2);

    assertTrue(card1.isFaceUp(), "Second last card should be face up after pushing a new card");
    assertTrue(card2.isFaceUp(), "Last card should be face up after pushing a new card");
  }

}
