package test.cst8334.solitaire.cardstacks;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.cardstacks.CardStack;
import com.cst8334.solitaire.cardstacks.CardStackFactory;
import com.cst8334.solitaire.cardstacks.DeckCardStack;
import com.cst8334.solitaire.cardstacks.FoundationCardStack;
import com.cst8334.solitaire.cardstacks.TableauCardStack;
import com.cst8334.solitaire.cardstacks.WasteCardStack;
import com.cst8334.solitaire.cardstacks.CardStackFactory.TYPES;
import com.cst8334.solitaire.utils.Position2D;

class CardStackFactoryTest {

  private List<Card> cards;
  private Position2D position;

  @BeforeEach
  void setUp() {
    cards = new ArrayList<>();
    cards.add(new Card(Card.SUITS.CLUBS, Card.VALUES.ACE));
    cards.add(new Card(Card.SUITS.DIAMONDS, Card.VALUES.TWO));

    position = new Position2D(10, 10);
  }

  @Test
  void testCreateDeckCardStack() {
    CardStack deckCardStack = CardStackFactory.createCardStack(TYPES.DECK, cards, position);

    assertNotNull(deckCardStack, "Deck card stack should not be null");
    assertEquals(cards, deckCardStack.getCards(), "Deck card stack should have the same cards as the provided list");
    assertEquals(position, deckCardStack.getPosition(),
        "Deck card stack should have the same position as the provided position");
    assertTrue(deckCardStack instanceof DeckCardStack, "Deck card stack should be an instance of DeckCardStack");
  }

  @Test
  void testCreateWasteCardStack() {
    CardStack wasteCardStack = CardStackFactory.createCardStack(TYPES.WASTE, cards, position);

    assertNotNull(wasteCardStack, "Waste card stack should not be null");
    assertEquals(cards, wasteCardStack.getCards(), "Waste card stack should have the same cards as the provided list");
    assertEquals(position, wasteCardStack.getPosition(),
        "Waste card stack should have the same position as the provided position");
    assertTrue(wasteCardStack instanceof WasteCardStack, "Deck card stack should be an instance of WasteCardStack");
  }

  @Test
  void testCreateFoundationCardStack() {
    CardStack foundationCardStack = CardStackFactory.createCardStack(TYPES.FOUNDATION, cards, position);

    assertNotNull(foundationCardStack, "Foundation card stack should not be null");
    assertEquals(cards, foundationCardStack.getCards(), "Foundation card stack should have the same cards as the provided list");
    assertEquals(position, foundationCardStack.getPosition(),
        "Foundation card stack should have the same position as the provided position");
    assertTrue(foundationCardStack instanceof FoundationCardStack, "Foundation card stack should be an instance of FoundationCardStack");
  }

  @Test
  void testCreateTableauCardStack() {
    CardStack tableauCardStack = CardStackFactory.createCardStack(TYPES.TABLEAU, cards, position);

    assertNotNull(tableauCardStack, "Tableau card stack should not be null");
    assertEquals(cards, tableauCardStack.getCards(), "Tableau card stack should have the same cards as the provided list");
    assertEquals(position, tableauCardStack.getPosition(),
        "Tableau card stack should have the same position as the provided position");
    assertTrue(tableauCardStack instanceof TableauCardStack, "Tableau card stack should be an instance of TableauCardStack");
  }

  @Test
  void testNullCardStack() {
    CardStack nullCardStack = CardStackFactory.createCardStack(null);
    assertNull(nullCardStack, "Null card stack should be null");
  }

}
