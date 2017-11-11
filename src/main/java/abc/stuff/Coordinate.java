package abc.stuff;

import manifold.ext.api.Structural;

/**
 * A structural interface defining for an (x, y) Coordinate
 */
@Structural
public interface Coordinate {
  double getX();
  double getY();
}
