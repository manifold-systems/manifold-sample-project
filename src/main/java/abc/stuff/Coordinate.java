package abc.stuff;

import manifold.ext.rt.api.IProxyFactory;
import manifold.ext.rt.api.Structural;

import java.awt.*;

/**
 * A structural interface defining an (x, y) Coordinate.
 */
@Structural
public interface Coordinate {
  double getX();
  double getY();
}
