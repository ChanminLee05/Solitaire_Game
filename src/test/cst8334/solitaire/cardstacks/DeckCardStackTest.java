package test.cst8334.solitaire.cardstacks;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cst8334.solitaire.cards.Card;
import com.cst8334.solitaire.cardstacks.DeckCardStack;
import com.cst8334.solitaire.utils.Position2D;

class DeckCardStackTest {

  private DeckCardStack deckCardStack;

  @BeforeEach
  void setUp() {
    deckCardStack = new DeckCardStack(new Position2D(0, 0));
  }

  @Test
  void testInitializeDeckOfCards() {
    List<Card> cards = deckCardStack.getCards();
    assertNotNull(cards, "Deck of cards should not be null");
    assertEquals(52, cards.size(), "Deck should contain 52 cards");
  }

  @Test
  void testPop() {
    Card topCard = deckCardStack.pop();
    assertNotNull(topCard, "Top card should not be null");
    assertFalse(deckCardStack.getLast().isFaceUp(), "Last card should be face down after popping the top card");
  }

  @Test
  void testShuffle() {
    List<Card> originalOrder = new ArrayList<>(deckCardStack.getCards());
    deckCardStack.shuffle();
    assertNotEquals(originalOrder, deckCardStack.getCards(),
        "Shuffled card stack should not have the same order as the original card stack");
  }
}
