package abc.stuff;

import java.awt.*;

public class Car {
  private final String _name;
  private final Color _color;

  public Car(String name, Color color) {
    _name = name;
    _color = color;
  }

  public String getName() {
    return _name;
  }

  public Color getColor() {
    return _color;
  }
}
