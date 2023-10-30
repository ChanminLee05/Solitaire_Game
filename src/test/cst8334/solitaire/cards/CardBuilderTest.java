package test.cst8334.solitaire.cards;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cst8334.solitaire.cards.Card.SUITS;
import com.cst8334.solitaire.cards.Card.VALUES;
import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.cards.CardBuilder;
import com.cst8334.solitaire.utils.Position2D;

class CardBuilderTest {

  @Test
  void testCardCreation() {
    CardBuilder cardBuilder = new CardBuilder();
    SUITS suit = SUITS.CLUBS;
    VALUES value = VALUES.ACE;
    boolean faceUp = true;
    Position2D position = new Position2D(10, 10);

    Card card = cardBuilder.setSuit(suit)
        .setValue(value)
        .setFaceUP(faceUp)
        .setPosition(position)
        .createCard();

    assertNotNull(card, "Card should not be null");
    assertEquals(suit, card.getSuit(), "Card suit should match the provided suit");
    assertEquals(value, card.getValue(), "Card value should match the provided value");
    assertEquals(faceUp, card.isFaceUp(), "Card face orientation should match the provided value");
    assertEquals(position, card.getPosition(), "Card position should match the provided position");
  }

  @Test
  void testIncompleteCardCreation() {
    CardBuilder cardBuilder = new CardBuilder();
    cardBuilder.setSuit(SUITS.DIAMONDS);
    assertThrows(IllegalStateException.class, cardBuilder::createCard,
        "Creating card without setting value should throw IllegalStateException");
  }

}
