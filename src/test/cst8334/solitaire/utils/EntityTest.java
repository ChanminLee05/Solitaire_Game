package test.cst8334.solitaire.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.awt.Component;
import java.awt.event.MouseEvent;

import com.cst8334.solitaire.utils.Entity;
import com.cst8334.solitaire.utils.Position2D;

class EntityTest {

  @Test
  void testSetSelected() {
    Entity entity = new Entity();
    assertFalse(entity.isSelected(), "Newly created entity should not be selected");

    entity.setSelected(true);
    assertTrue(entity.isSelected(), "Entity should be selected after calling setSelected with true");

    entity.setSelected(false);
    assertFalse(entity.isSelected(), "Entity should not be selected after calling setSelected with false");
  }

  @Test
  void testContains() {
    Entity entity = new Entity(new Position2D(10, 10), 50, 50);
    Component source = new Component() {};
    MouseEvent mouseEventInside = new MouseEvent(source, 0, 0, 0, 15, 15, 0, false);
    MouseEvent mouseEventOutside = new MouseEvent(source, 0, 0, 0, 70, 70, 0, false);

    assertTrue(entity.contains(mouseEventInside),
        "Contains method should return true for a mouse event inside the entity");
    assertFalse(entity.contains(mouseEventOutside),
        "Contains method should return false for a mouse event outside the entity");
  }

  @Test
  void testPosition() {
    Entity entity = new Entity();
    Position2D newPosition = new Position2D(20, 20);

    assertEquals(Position2D.Zero(), entity.getPosition(), "Newly created entity should have a position of (0,0)");

    entity.setPosition(newPosition);
    assertEquals(newPosition, entity.getPosition(), "Entity should have the new position after calling setPosition");
  }

}
