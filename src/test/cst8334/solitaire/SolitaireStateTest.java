package test.cst8334.solitaire;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cst8334.solitaire.SolitaireState;

class SolitaireStateTest {

  private SolitaireState state;

  @BeforeEach
  void setUp() {
    state = SolitaireState.initialState();
  }

  @Test
  void testInitialState() {
    assertNotNull(state);
    assertEquals(0, state.getScore());
    assertNotNull(state.getTimer());
    assertNotNull(state.getStacks());
    assertEquals(13, state.getStacks().size());
    assertNull(state.getSelectedStack());
  }

  @Test
  void testAddScore() {
    state.addScore(100);
    assertEquals(100, state.getScore());
  }

  @Test
  void testSetSelectedStack() {
    assertNull(state.getSelectedStack());
    state.setSelectedStack(state.getStacks().get(0));
    assertNotNull(state.getSelectedStack());
  }

  @Test
  void testCreateInitialCardStacks() {
    assertEquals(13, state.getStacks().size());
    assertNotNull(state.getStacks().get(0));
    assertNotNull(state.getStacks().get(1));
    assertNotNull(state.getStacks().get(2));
    assertNotNull(state.getStacks().get(3));
    assertNotNull(state.getStacks().get(4));
    assertNotNull(state.getStacks().get(5));
    assertNotNull(state.getStacks().get(6));
    assertNotNull(state.getStacks().get(7));
    assertNotNull(state.getStacks().get(8));
    assertNotNull(state.getStacks().get(9));
    assertNotNull(state.getStacks().get(10));
    assertNotNull(state.getStacks().get(11));
    assertNotNull(state.getStacks().get(12));
  }

  @Test
  void testDealInitialCards() {
    assertEquals(2, state.getStacks().get(7).getCards().size());
    assertEquals(3, state.getStacks().get(8).getCards().size());
    assertEquals(4, state.getStacks().get(9).getCards().size());
    assertEquals(5, state.getStacks().get(10).getCards().size());
    assertEquals(6, state.getStacks().get(11).getCards().size());
    assertEquals(7, state.getStacks().get(12).getCards().size());
  }

}
