package test.cst8334.solitaire.cardstacks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.cardstacks.FoundationCardStack;

class FoundationCardStackTest {

  private FoundationCardStack foundationCardStack;

  @BeforeEach
  void setUp() {
    foundationCardStack = new FoundationCardStack();
  }

  @Test
  void testPushValidCard() {
    Card firstCard = new Card(Card.SUITS.SPADES, Card.VALUES.ACE);
    assertDoesNotThrow(() -> foundationCardStack.push(firstCard),
        "Pushing the first card (ACE) should not throw an exception");
    Card nextCard = new Card(Card.SUITS.SPADES, Card.VALUES.TWO);
    assertDoesNotThrow(() -> foundationCardStack.push(nextCard),
        "Pushing a card of the same suit and one rank higher should not throw an exception");
  }

  @Test
  void testPushInvalidCard() {
    Card firstCard = new Card(Card.SUITS.SPADES, Card.VALUES.ACE);
    assertDoesNotThrow(() -> foundationCardStack.push(firstCard),
        "Pushing the first card (ACE) should not throw an exception");
    Card invalidSuitCard = new Card(Card.SUITS.HEARTS, Card.VALUES.TWO);
    assertThrows(IllegalArgumentException.class, () -> foundationCardStack.push(invalidSuitCard),
        "Pushing a card of a different suit should throw an IllegalArgumentException");
    Card invalidRankCard = new Card(Card.SUITS.SPADES, Card.VALUES.FOUR);
    assertThrows(IllegalArgumentException.class, () -> foundationCardStack.push(invalidRankCard),
        "Pushing a card of the same suit but not one rank higher should throw an IllegalArgumentException");
  }

  @Test
  void testGetSuit() {
    Card firstCard = new Card(Card.SUITS.HEARTS, Card.VALUES.ACE);
    foundationCardStack.push(firstCard);
    assertEquals(Card.SUITS.HEARTS, foundationCardStack.getSuit(),
        "The suit returned should be the same as the suit of the first card pushed to the foundation pile");
  }

}
