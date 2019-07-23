package abc;

import abc.res.*;
import abc.stuff.Car;
import abc.stuff.CarBuilder;
import abc.stuff.Coordinate;
import abc.stuff.SampleClass;
import manifold.ext.api.Jailbreak;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static abc.res.movies.Genre.Action;
import static java.lang.System.out;

/**
 * Utilize a small sampling of core Manifold features to demonstrate the
 * structure of a basic project using Manifold. Use the pom.xml file as a
 * template for your own project.
 * <p/>
 * Play with this in IntelliJ IDEA using the <b>Manifold</b> plugin:
 * <pre>
 * Settings | Plugins | Marketplace | search: 'Manifold'
 * </pre>
 * Use IntelliJ features such as: <ul>
 * <li/> Navigation
 * <li/> Code completion
 * <li/> Find Usages
 * <li/> Rename/Move Refactor
 * <li/> Fragment support
 * <li/> Professional Template Authoring
 * <li/> Incremental compilation
 * <li/> Hotswap debugging
 * <li/> etc.
 * </ul>
 */
public class RunMe {
  public static void main(String[] args) {
    useImage();
    useProperties();
    useJsonSample();
    useJsonSchema();
    useJsonFragment();
    useYamlUsingJsonSchema();
    useGraphQL();
    useGraphQLFragment();
    useCustomExtension();
    useProvidedExtension();
    useStructuralInterface();
    useSelfType();
    useJailbreak();
    useCheckedExceptionSuppression();
    useStringLiteralTemplates();
    useTemplateManifold();
    useJavascript();
    useJavascriptFragment();
  }

  private static void useImage() {
    out.println("\n\n### Use Image Manifold ###\n");
    logo_png logoImage = logo_png.get();
    out.println(logoImage.getIconWidth());
  }

  private static void useProperties() {
    out.println("\n\n### Use Properties Manifold ###\n");
    out.println(MyProperties.Chocolate);
    out.println(MyProperties.Chocolate.dark);
    out.println(MyProperties.Chocolate.milk);
  }

  private static void useJsonSample() {
    out.println("\n\n### Use JSON Manifold With JSON Sample ###\n");
    // Create instances of the *type* inferred from the sample data in Person.json
    Person person = Person.create();
    person.setName("Scott");
    person.setAddress(Person.Address.create());
    Person.Address address = person.getAddress();
    address.setCity("Cupertino");
    address.setState("CA");
    out.println(person.write().toJson());

    // Use the JSON sample *data* from Person.json itself types-safely
    Person p = Person.fromSource();
    out.println(p.getName());
    out.println(p.getAge());
  }

  private static void useJsonSchema() {
    out.println("\n\n### Use JSON Manifold With JSON Schema ###\n");
    Contact contact = Contact.builder()
      .withName("Scott McKinney")
      .withDateOfBirth(LocalDate.of(1986, 8, 9))
      .withPrimaryAddress(Contact.Address.create("111 Main St.", "Cupertino", "CA"))
      .withContactAddresses(Arrays.asList(Contact.Address.create("a", "b", "c")))
      .build();
    out.println(contact.write().toJson());
  }

  private static void useJsonFragment() {
    out.println("\n\n### Use Json Fragment ###\n");
    /*[>Dude.json<] {
      "Name": "Scott",
      "Age": 100,
      "Address": {
        "Street": "345 Syracuse Way",
        "City": "Atlantis"
      }
    }
    */

    Dude dude = Dude.fromSource();
    out.println(dude.getName());
    out.println(dude.getAge());
    out.println(dude.getAddress().getCity());
  }

  private static void useYamlUsingJsonSchema() {
    out.println("\n\n### Use YAML Manifold With JSON Schema ###\n");
    Contact2 contact = Contact2.builder()
      .withName("Scott McKinney")
      .withDateOfBirth(LocalDate.of(1986, 8, 9))
      .withPrimaryAddress(Contact2.Address.create("111 Main St.", "Cupertino", "CA"))
      .withContactAddresses(Arrays.asList(Contact2.Address.create("a", "b", "c")))
      .build();
    out.println(contact.write().toJson());
  }

  private static void useGraphQL() {
    out.println("\n\n### Use GraphQL Schemas ###\n");
    // Create new data
    movies.Person steveMcQueen = movies.Person.builder("anId", "Steve McQueen", LocalDate.of(1930, 3, 24))
      .withHeight(1.77)
      .withNationality("American")
      .build();
    out.println(steveMcQueen.write().toJson());

    // Build and execute queries
    queries.MovieQuery query = queries.MovieQuery.builder()
      .withGenre(Action)
      .build();
    out.println(query.write().toJson());

    // Executing the query requires a GraphQL server or test infrastructure,
    // see GraphQL sample app at https://github.com/manifold-systems/manifold-sample-graphql-app)
    //
    // query execution is simple and looks like this:
    //
    // var result = query.request("http://example.com/graphql").post();
    // var actionMovies = result.getMovies();
  }

  private static void useGraphQLFragment() {
    out.println("\n\n### Use GraphQL Fragment ###\n");

    // Note get the JS GraphQL IntelliJ plugin for rich editing of embedded GraphQL fragments

    /*[>MyQuery.graphql<]
    query Movies($title: String, $genre: Genre, $releaseDate: Date) {
        movies(title: $title, genre: $genre, releaseDate: $releaseDate) {
            id
            title
            genre
            releaseDate
        }
    }
    */

    var query = MyQuery.Movies.builder().withGenre(Action).build();
    out.println(query.toString());
    // experimental
    //    var q = "[>.graphql<] query MovieQuery($genre: Genre){ movies(genre: $genre){ genre } }";
    //    var result = q.builder().build().request("").post();
    //    result.getMovies().forEach( e -> e.getGenre() );
  }

  private static void useCustomExtension() {
    out.println("\n\n### Use Custom Extension Method ###\n");
    String hello = "hello";
    hello.echo();
  }

  private static void useProvidedExtension() {
    out.println("\n\n### Use Collections Extension Library ###\n");
    Iterable<Integer> list = Arrays.asList(1, 2, 3);
    out.println(list.first());
    out.println(list.joinToString(", "));
  }

  private static void useStructuralInterface() {
    out.println("\n\n### Use Structural Interfaces ###\n");
    // No casting necessary, Point indirectly implements Coordinate via extension (see MyPointExt)
    Coordinate coord = new Point(4, 5);
    out.println("x: " + coord.getX() + ", y: " + coord.getY());

    // Casting necessary, Rectangle does not nominally implement Coordinate
    Coordinate loc = (Coordinate) new Rectangle(3, 4, 5, 6);
    out.println("x: " + loc.getX() + ", y: " + loc.getY());

    // Use a registered IProxyFactory interface to make old Date structurally compatible with new ChronoLocalDateTime
    // See Date_To_ChronoLocalDateTime and MyChronoLocalDateTimeExt
    Date date = new Date(82, Calendar.JULY, 4);
    out.println(((ChronoLocalDateTime) date).plus(1, ChronoUnit.MONTHS));
  }

  private static void useSelfType() {
    out.println("\n\n### Use Self Type ###\n");
    CarBuilder carBuilder = new CarBuilder();
    Car car = carBuilder
      .withName("Mach Five") // returns CarBuilder
      .withColor(255, 255, 255)
      .build();
    out.println(car.getName());
  }

  private static void useJailbreak() {
    out.println("\n\n### Use Jailbreak (Type-safe Reflection) ###\n");
    @Jailbreak SampleClass sample = new SampleClass();
    out.println(sample.privateMethod());
    sample._privateField = "assign to private field";
    out.println(sample._privateField);
    sample = new abc.stuff.@Jailbreak SampleClass("use hidden constructor");
    out.println(sample._privateField);

    String data = new SampleClass().jailbreak().privateMethod();
    out.println(data);

    @Jailbreak LocalTime time = LocalTime.now();
    time.hour = 10; // private field
    out.println(time);
  }

  private static void useStringLiteralTemplates() {
    out.println("\n\n### Use String Literal Templates ###\n");
    int hour = 5;
    int minute = 30;
    String nice = "The time is $hour:$minute";
    out.println(nice);

    LocalTime time = LocalTime.now();
    String cool = "The time is ${time.getHour()}:${String.format(\"%02d\", time.getMinute())}";
    out.println(cool);
  }

  private static void useTemplateManifold() {
    out.println("\n\n### Use ManTL (Type-safe Java Templates) ###\n");
    String html = SampleTemplate.render("ZOMG");
    out.println(html);
  }

  private static void useCheckedExceptionSuppression() {
    out.println("\n\n### Use Checked Exception Suppression ###\n");
    java.util.List<String> list = Arrays.asList("http://manifold.systems", "https://github.com/manifold-systems/manifold");
    List<URL> urls = list.map(URL::new).collect(Collectors.toList()); // w/o suppression, this would have compile errors
    urls.forEach(out::println);
    boom(); // w/o suppression, this would have a compile error
  }

  private static void boom() throws IOException {
    if (false)
      throw new IOException();
  }

  private static void freedom() {
    if (false)
      throw new IOException(); // w/o suppression this would have a compile error
  }

  private static void useJavascript() {
    out.println("\n\n### Use Javascript Manifold ###\n");

    // Use a js *program*
    out.println(BasicJavascriptProgram.bar());
    out.println(BasicJavascriptProgram.incrementAndGet());
    out.println(BasicJavascriptProgram.incrementAndGet());
    out.println(BasicJavascriptProgram.identity("Foo"));
    out.println(BasicJavascriptProgram.identity(1));

    // Use a *class*
    MyClass myClass = new MyClass("foo", 5);
    out.println(myClass.getFoo());
    out.println(myClass.getBar());
    myClass.setFoo("foo2");
    myClass.setBar(6);
    out.println(myClass.getFoo());
    out.println(myClass.getBar());
    out.println(MyClass.somethingStatic());
  }

  private static void useJavascriptFragment() {
    out.println("\n\n### Use Javascript Fragment ###\n");

    // Embedded fragment (requires IJ Ultimate Edition for JS code injection editing)

    /*[>Sample.js<]
    function total(list) {
      return reduce(list, (sum, entry) => {
        return sum + entry.intValue();
      })
    }

    function max(list) {
      return reduce(list, (max, entry) => {
        return max < entry.intValue() ? entry.intValue() : max;
      })
    }

    function reduce(list, f) {
      var acc = null;
      for (var i = 0; i < list.size(); i++) {
        acc = f(acc, list.get(i));
      }
      return acc;
    }
    */
    out.println("Sum: " + Sample.total(Arrays.asList(1, 2, 3)));
    out.println("Max: " + Sample.max(Arrays.asList(1, 2, 3)));

    // Fragment *evaluation* from String literal (experimental)
    //var value = (int) "[>.js<] 3 + 4 + 5";
    //out.println(value);
  }
}
