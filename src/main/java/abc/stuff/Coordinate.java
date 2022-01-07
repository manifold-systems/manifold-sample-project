package abc.stuff;

import manifold.ext.rt.api.IProxyFactory;
import manifold.ext.rt.api.Structural;

import java.awt.*;

/**
 * A structural interface defining an (x, y) Coordinate. Coordinate directly implements proxies for Point and Shape,
 * other structurally compatible types are dynamically proxied.
 */
@Structural(factoryClass = Coordinate.ProxyFactory.class)
public interface Coordinate {
  double getX();
  double getY();

  /**
   * This proxy factory directly proxies Point and Shape, other types structurally compatible with Coordinate are
   * _dynamically_ proxied.
   * <p/>
   * Note, this proxy factory is optional, it's purpose is to avoid the performance impact of dynamic proxies, which
   * require dynamic generation, compilation, and loading of Java classes. While dynamic proxies are powerful, the
   * power comes at the cost of increased runtime footprint to host dynamic Manifold, which includes tools.jar for
   * Java 8; and the cost of initialization time for each new proxy type to generate and compile. Here, since we know
   * ahead of time we are using Point and Rectangle in the RunMe class, we opt to implement the proxies statically right
   * here.
   * <p/>
   * Another option involves implementing Coordinate via Extension Classes. As such, abstract extension classes for
   * Point and Shape would each declare they implement Coordinate and provide static extension methods for getX() and
   * getY(). In this case proxy factory classes are generated automatically at compile-time.
   */
  class ProxyFactory implements IProxyFactory<Object, Coordinate> {
    @Override
    public Coordinate proxy(Object o, Class<Coordinate> aClass) {
      if(o instanceof Point) {
        return new Coordinate() {
          public double getX() {
            return ((Point)o).x;
          }
          public double getY() {
            return ((Point)o).y;
          }
        };
      }
      else if(o instanceof Shape) {
        return new Coordinate() {
          public double getX() {
            return ((Shape)o).getBounds().x;
          }
          public double getY() {
            return ((Shape)o).getBounds().y;
          }
        };
      }
      return null;
    }
  }
}
