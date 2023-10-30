package test.cst8334.solitaire.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cst8334.solitaire.utils.Position2D;

class Position2DTest {

  @Test
  void testEquals() {
    Position2D position1 = new Position2D(3, 4);
    Position2D position2 = new Position2D(3, 4);
    Position2D position3 = new Position2D(5, 6);

    assertEquals(position1, position2, "Position1 should be equal to Position2");
    assertNotEquals(position1, position3, "Position1 should not be equal to Position3");
  }

  @Test
  void testGetX() {
    Position2D position = new Position2D(3, 4);
    assertEquals(3, position.getX(), "X-coordinate should be 3");
  }

  @Test
  void testGetY() {
    Position2D position = new Position2D(3, 4);
    assertEquals(4, position.getY(), "Y-coordinate should be 4");
  }

  @Test
  void testSetX() {
    Position2D position = new Position2D(3, 4);
    position.setX(5);
    assertEquals(5, position.getX(), "X-coordinate should be 5");
  }

  @Test
  void testSetY() {
    Position2D position = new Position2D(3, 4);
    position.setY(6);
    assertEquals(6, position.getY(), "Y-coordinate should be 6");
  }

}
