package test.cst8334.solitaire.cardstacks;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.cardstacks.CardStack;

class CardStackTest {

  @Test
  void testIsEmpty() {
    CardStack cardStack = new CardStack();
    assertTrue(cardStack.isEmpty(), "New card stack should be empty");
  }

  @Test
  void testPutFirst() {
    CardStack cardStack = new CardStack();
    Card card = new Card(Card.SUITS.CLUBS, Card.VALUES.ACE);
    cardStack.putFirst(card);
    assertFalse(cardStack.isEmpty(), "Card stack should not be empty after adding a card");
    assertEquals(card, cardStack.getFirst(), "First card should be the one added");
  }

  @Test
  void testGetFirst() {
    CardStack cardStack = new CardStack();
    Card card = new Card(Card.SUITS.CLUBS, Card.VALUES.ACE);
    cardStack.putFirst(card);
    assertEquals(card, cardStack.getFirst(), "First card should be the one added");
  }

  @Test
  void testGetLast() {
    CardStack cardStack = new CardStack();
    Card card = new Card(Card.SUITS.CLUBS, Card.VALUES.ACE);
    cardStack.putFirst(card);
    assertEquals(card, cardStack.getLast(), "Last card should be the one added");
  }

  @Test
  void testPush() {
    CardStack cardStack = new CardStack();
    Card card = new Card(Card.SUITS.CLUBS, Card.VALUES.ACE);
    cardStack.push(card);
    assertFalse(cardStack.isEmpty(), "Card stack should not be empty after pushing a card");
    assertEquals(card, cardStack.getFirst(), "First card should be the one pushed");
  }

  @Test
  void testPop() {
    CardStack cardStack = new CardStack();
    Card card = new Card(Card.SUITS.CLUBS, Card.VALUES.ACE);
    cardStack.push(card);
    Card poppedCard = cardStack.pop();
    assertEquals(card, poppedCard, "Popped card should be the one pushed");
  }

  @Test
  void testShuffle() {
    CardStack cardStack = new CardStack();
    cardStack.push(new Card(Card.SUITS.CLUBS, Card.VALUES.ACE));
    cardStack.push(new Card(Card.SUITS.DIAMONDS, Card.VALUES.TWO));
    cardStack.push(new Card(Card.SUITS.HEARTS, Card.VALUES.THREE));
    cardStack.push(new Card(Card.SUITS.SPADES, Card.VALUES.FOUR));
    cardStack.push(new Card(Card.SUITS.CLUBS, Card.VALUES.FIVE));
    cardStack.push(new Card(Card.SUITS.DIAMONDS, Card.VALUES.SIX));
    cardStack.push(new Card(Card.SUITS.HEARTS, Card.VALUES.SEVEN));
    cardStack.push(new Card(Card.SUITS.SPADES, Card.VALUES.EIGHT));
    List<Card> originalOrder = new ArrayList<>(cardStack.getCards());
    cardStack.shuffle();
    assertNotEquals(originalOrder, cardStack.getCards(),
        "Shuffled card stack should not have the same order as the original card stack");
  }

  @Test
  void testReverse() {
    CardStack cardStack = new CardStack();
    cardStack.push(new Card(Card.SUITS.CLUBS, Card.VALUES.ACE));
    cardStack.push(new Card(Card.SUITS.DIAMONDS, Card.VALUES.TWO));
    CardStack reversedCardStack = cardStack.reverse();
    List<Card> reversedCards = new ArrayList<>(cardStack.getCards());
    java.util.Collections.reverse(reversedCards);
    assertEquals(reversedCards, reversedCardStack.getCards(),
        "Reversed card stack should have the cards in reverse order");
  }

}
