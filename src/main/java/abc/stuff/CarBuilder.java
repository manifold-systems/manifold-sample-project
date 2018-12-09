package abc.stuff;

import manifold.ext.api.Self;

import java.awt.*;

public class CarBuilder extends AbstractBuilder {
  private Color _color;

  public @Self CarBuilder withColor(int red, int green, int blue) {
    _color = new Color(red, green, blue);
    return this;
  }

  protected Color getColor() {
    return _color;
  }

  public Car build() {
    return new Car(getName(), getColor());
  }
}
