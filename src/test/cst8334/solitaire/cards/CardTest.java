package test.cst8334.solitaire.cards;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.cards.Card.SUITS;
import com.cst8334.solitaire.cards.Card.VALUES;
import com.cst8334.solitaire.utils.Position2D;

class CardTest {

  @Test
  void testCardCreation() {
    Position2D position2d = new Position2D(10, 10);
    Card card = new Card(SUITS.HEARTS, VALUES.ACE, true, position2d);
    assertNotNull(card, "Card should not be null");
    assertEquals(SUITS.HEARTS, card.getSuit(), "Card suit should be HEARTS");
    assertEquals(VALUES.ACE, card.getValue(), "Card value should be ACE");
    assertTrue(card.isFaceUp(), "Card should be face up");
    assertEquals(position2d, card.getPosition(), "Card position should be (10, 10)");
  }

  @Test
  void testSetFaceUp() {
    Card card = new Card(SUITS.DIAMONDS, VALUES.JACK, false);
    assertFalse(card.isFaceUp(), "Card should be face down initially");
    card.setFaceUp(true);
    assertTrue(card.isFaceUp(), "Card should be face up after calling setFaceUp(true)");
  }

  @Test
  void testToString() {
    Card card = new Card(SUITS.SPADES, VALUES.TEN);
    String expectedString = "10♠";
    assertEquals(expectedString, card.toString(), "Card string representation should be 10♠");
  }

}
