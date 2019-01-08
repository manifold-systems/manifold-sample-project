package abc;

import abc.res.*;
import abc.stuff.Car;
import abc.stuff.CarBuilder;
import abc.stuff.Coordinate;
import abc.stuff.SampleClass;
import manifold.ext.api.Jailbreak;

import java.awt.*;
import java.time.LocalTime;
import java.util.Arrays;

import static java.lang.System.out;

/**
 * Utilize a small sampling of core Manifold features to demonstrate the
 * structure of a basic project using Manifold. Use the pom.xml file as a
 * template for your own project.
 * <p/>
 * Play with this in IntelliJ IDEA using the <b>Manifold</b> plugin:
 * <pre>
 * File | Settings | Plugins | Browse Repositories | Manifold
 * </pre>
 * Use IntelliJ features such as: <ul>
 * <li/> Navigation
 * <li/> Code completion
 * <li/> Find Usages
 * <li/> Rename/Move Refactor
 * <li/> Incremental compilation
 * <li/> Hotswap debugging
 * </ul>
 */
public class RunMe {
  public static void main(String[] args) {
    useImage();
    useProperties();
    useJsonSample();
    useJsonSchema();
    useCustomExtension();
    useProvidedExtension();
    useStructuralInterface();
    useSelfType();
    useJailbreak();
    useStringLiteralTemplates();
    useTemplateManifold();
  }

  private static void useImage() {
    System.out.println("\n\n### Use Image Type Manifold ###\n");
    logo_png logoImage = logo_png.get();
    out.println(logoImage.getIconWidth());
  }

  private static void useProperties() {
    System.out.println("\n\n### Use Properties Type Manifold ###\n");
    out.println(MyProperties.Chocolate);
    out.println(MyProperties.Chocolate.dark);
    out.println(MyProperties.Chocolate.milk);
  }

  private static void useJsonSample() {
    System.out.println("\n\n### Use JSON Sample Type Manifold ###\n");
    Person person = Person.create();
    person.setName("Bob");
    person.setAddress(Person.Address.create());
    Person.Address address = person.getAddress();
    address.setCity("Cupertino");
    address.setState("CA");
    out.println(person.toJson());
  }

  private static void useJsonSchema() {
    System.out.println("\n\n### Use JSON Schema Type Manifold ###\n");
    Contact contact = Contact.create();
    contact.setName("Bob");
    contact.setPrimaryAddress(Contact.Address.create("111 Main St.", "Cupertino", "CA"));
    Contact.Address primaryAddress = contact.getPrimaryAddress();
    primaryAddress.setCity("Cupertino");
    primaryAddress.setState("CA");
    out.println(contact.toJson());
  }

  private static void useCustomExtension() {
    System.out.println("\n\n### Use Custom Extension Method ###\n");
    String hello = "hello";
    hello.echo();
  }

  private static void useProvidedExtension() {
    System.out.println("\n\n### Use Collections Extension Library ###\n");
    Iterable<Integer> list = Arrays.asList(1, 2, 3);
    out.println(list.first());
    out.println(list.joinToString(", "));
  }

  private static void useStructuralInterface() {
    System.out.println("\n\n### Use Structural Interfaces ###\n");
    // No casting necessary, Point indirectly implements Coordinate via extension (see MyPointExt)
    Coordinate coord = new Point(4, 5);
    out.println("x: " + coord.getX() + ", y: " + coord.getY());

    // Casting necessary, Rectangle does not nominally implement Coordinate
    Coordinate loc = (Coordinate) new Rectangle(3, 4, 5, 6);
    out.println("x: " + loc.getX() + ", y: " + loc.getY());
  }

  private static void useSelfType()
  {
    System.out.println("\n\n### Use Self Type ###\n");
    CarBuilder carBuilder = new CarBuilder();
    Car car = carBuilder
      .withName("Mach Five") // returns CarBuilder
      .withColor(255, 255, 255)
      .build();
    System.out.println(car.getName());
  }

  private static void useJailbreak() {
    System.out.println("\n\n### Use Jailbreak (Type-safe Reflection) ###\n");
    @Jailbreak SampleClass sample = new SampleClass();
    out.println(sample.privateMethod());
    sample._privateField = "assign to private field";
    out.println(sample._privateField);
    sample = new abc.stuff. @Jailbreak SampleClass("use hidden constructor");
    out.println(sample._privateField);

    String data = new SampleClass().jailbreak().privateMethod();
    System.out.println(data);

    @Jailbreak LocalTime time = LocalTime.now();
    time.hour = 10; // private field
    System.out.println(time);
  }

  private static void useStringLiteralTemplates() {
    System.out.println("\n\n### Use String Literal Templates ###\n");
    int hour = 5;
    int minute = 30;
    String nice = "The time is $hour:$minute";
    out.println(nice);

    LocalTime time = LocalTime.now();
    String cool = "The time is ${time.getHour()}:${String.format(\"%02d\", time.getMinute())}";
    out.println(cool);
  }
  
  private static void useTemplateManifold() {
    System.out.println("\n\n### Use ManTL (Type-safe Templates) ###\n");
    String html = SampleTemplate.render("ZOMG");
    System.out.println(html);
  }
}
