package abc;

import abc.res.Contact;
import abc.res.MyProperties;
import abc.res.Person;
import abc.res.logo_png;
import abc.stuff.Coordinate;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * Utilize a small sampling of core Manifold features to demonstrate the
 * structure of a basic project using Manifold. Use the pom.xml file as a
 * template for your own project.
 * <p/>
 * Play with this in IntelliJ IDEA using the <b>Manifold</b> plugin:
 * <pre>
 * File | Settings | Plugins | Browse Repositories | Manifold
 * </pre>
 * Use features such as: Navigation, Code completion, Find Usages, Rename Refactor, Move Refactor, Go To Declaration, etc.
 */
public class RunMe {
  public static void main(String[] args) {
    useJsonSample();
    useJsonSchema();
    useImage();
    useProperties();
    useCustomExtension();
    useProvidedExtension();
    useStructuralInterface();
  }

  private static void useJsonSample() {
    Person person = Person.create();
    person.setName("Bob");
    person.setAddress(Person.Address.create());
    Person.Address address = person.getAddress();
    address.setCity("Cupertino");
    address.setState("CA");
    System.out.println(person.toJson());
  }

  private static void useJsonSchema() {
    Contact contact = Contact.create();
    contact.setName("Bob");
    contact.setPrimaryAddress(Contact.Address.create());
    Contact.Address primaryAddress = contact.getPrimaryAddress();
    primaryAddress.setCity("Cupertino");
    primaryAddress.setState("CA");
    System.out.println(contact.toJson());
  }

  private static void useImage() {
    logo_png logoImage = logo_png.get();
    System.out.println(logoImage.getIconWidth());
  }

  private static void useProperties() {
    System.out.println(MyProperties.Chocolate);
    System.out.println(MyProperties.Chocolate.dark);
    System.out.println(MyProperties.Chocolate.milk);
  }

  private static void useCustomExtension() {
    String hello = "hello";
    hello.echo();
  }

  private static void useProvidedExtension() {
    List<Integer> list = Arrays.asList(1, 2, 3);
    System.out.println(list.first());
    System.out.println(list.join(", "));
  }

  private static void useStructuralInterface() {
    // No casting necessary, Point indirectly implements Coordinate via extension (see MyPointExt)
    Coordinate coord = new Point(4, 5);
    System.out.println("x: " + coord.getX() + ", y: " + coord.getY());

    // Casting necessary, Rectangle does not nominally implement Coordinate
    Coordinate loc = (Coordinate) new Rectangle(3, 4, 5, 6);
    System.out.println("x: " + loc.getX() + ", y: " + loc.getY());
  }
}
