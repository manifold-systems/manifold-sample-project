package abc;

import abc.res.*;
import abc.res.Person;
import abc.stuff.Car;
import abc.stuff.CarBuilder;
import abc.stuff.Coordinate;
import abc.stuff.SampleClass;
import manifold.collections.api.range.IntegerRange;
import manifold.ext.rt.api.ComparableUsing;
import manifold.ext.rt.api.Jailbreak;
import manifold.science.measures.*;
import manifold.science.util.Rational;

import static manifold.collections.api.range.RangeFun.*;
import static manifold.science.measures.MetricScaleUnit.M;
import static manifold.science.util.UnitConstants.*;
import static manifold.science.util.CoercionConstants.*;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static abc.res.movies.Genre.Action;
import static java.lang.System.out;

// use #define/#undef to demonstrate use of the Preprocessor
#define EXPERIMENTAL
#undef EXPERIMENTAL // comment out this #undef to test experimental features

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
 * <li/> Unit Expressions
 * <li/> Template Authoring
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
    useYamlUsingJsonSchema();
    useGraphQL();
    useCustomExtension();
    useProvidedExtension();
    useStructuralInterface();
    useSelfType();
    useJailbreak();
    useCheckedExceptionSuppression();
    useStringLiteralTemplates();
    useTemplateManifold();
    useJavascript();
    useOperatorOverloading();
    useUnitExpressions();
    useRanges();

    #if EXPERIMENTAL
    useJsonFragment();
    useGraphQLFragment();
    useJavascriptFragment();
    #endif
  }

  // via manifold-image dependency
  private static void useImage() {
    out.println("\n\n### Use Image Manifold ###\n");
    logo_png logoImage = logo_png.get();
    out.println(logoImage.getIconWidth());
  }

  // via manifold-properties dependency
  private static void useProperties() {
    out.println("\n\n### Use Properties Manifold ###\n");
    out.println(MyProperties.Chocolate);
    out.println(MyProperties.Chocolate.dark);
    out.println(MyProperties.Chocolate.milk);
  }

  // via manifold-json dependency
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

    // Use the JSON sample *data* from Person.json itself type-safely
    Person p = Person.fromSource();
    out.println(p.getName());
    out.println(p.getAge());
  }

  // via manifold-json dependency
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

  #if EXPERIMENTAL
  // via manifold-json dependency
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
  #endif

  // via manifold-yaml dependency
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

  // via manifold-graphql dependency
  private static void useGraphQL() {
    out.println("\n\n### Use GraphQL Schemas ###\n");
    // Create new data
    movies.Person steveMcQueen = movies.Person.builder("anId", "Steve McQueen", LocalDate.of(1930, 3, 24))
      .withHeight(5 ft + 10 in) // extension method
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

#if EXPERIMENTAL
  // via manifold-graphql dependency
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
    out.println(query.getGenre());

  #if JAVA_14_OR_LATER
    var moviesQuery = """
    [>.graphql<]
      query Movies($title: String, $genre: Genre, $releaseDate: Date) {
          movies(title: $title, genre: $genre, releaseDate: $releaseDate) {
              id
              title
              genre
              releaseDate
          }
      }
    """;
    var actionMoviesQuery = moviesQuery.builder()
            .withGenre(Action).build();
    out.println(actionMoviesQuery.getGenre());
    //actionMoviesQuery.request("https://com.example/movies/graphql").post();
  #endif
  }
#endif

  // via manifold-ext dependency
  private static void useCustomExtension() {
    // echo() extension on String
    out.println("\n\n### Use Custom Extension Method ###\n");
    String hello = "hello";
    hello.echo();

    // containsKeys() extension on Map
    HashMap<String, Integer> map = new HashMap<String, Integer>() {{
      put("one", 1);
      put("two", 2);
      put("three", 3);
    }};
    List<String> keys = Arrays.asList("one", "two");
    out.println(map.containsKeys(keys));

    for( Map.Entry<String, Integer> entry: map.entrySet()) {
      // hiFromEntry extension on inner type Map.Entry
      out.println(entry.hiFromEntry());
    }
  }

  // via manifold-collections dependency
  private static void useProvidedExtension() {
    out.println("\n\n### Use Collections Extension Library ###\n");
    Iterable<Integer> list = Arrays.asList(1, 2, 3);
    out.println(list.first());
    out.println(list.joinToString(", "));
  }

  // via manifold-ext dependency
  private static void useStructuralInterface() {
    out.println("\n\n### Use Structural Interfaces ###\n");
    // No casting necessary, Point indirectly implements Coordinate via extension (see MyPointExt).
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

  // via manifold-ext dependency
  private static void useSelfType() {
    out.println("\n\n### Use Self Type ###\n");
    CarBuilder carBuilder = new CarBuilder();
    Car car = carBuilder
      .withName("Mach Five") // returns CarBuilder
      .withColor(255, 255, 255)
      .build();
    out.println(car.getName());
  }

  // via manifold-ext dependency
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

  // via manifold-strings dependency
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

  // via manifold-templates dependency
  private static void useTemplateManifold() {
    out.println("\n\n### Use ManTL (Type-safe Java Templates) ###\n");
    String html = SampleTemplate.render("ZOMG");
    out.println(html);
  }

  // via manifold-exceptions dependency
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
      throw new IOException(); // w/o the manifold-exceptions dependency this would have a compile error
  }

  // via manifold-js dependency
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

#if EXPERIMENTAL
  // via manifold-js dependency
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
#endif

  // manifold-ext dependency
  private static void useOperatorOverloading() {
    out.println("\n\n### Use Operator Overloading ###\n");

    Foo a = new Foo(2);
    Foo b = new Foo(3);

    // use '+' operator directly on Foo
    Foo sum = a + b; // Foo(5)
    out.println(sum.x);

    // use relational operators
    if (a < b) {
      out.println("a < b");
    }
    // map '==' to compareTo()
    if (a == new Foo(2)) {
      out.println(":)");
    }
    // Use operators on BigDecimal
    BigDecimal result = 2.1bd * 3.2bd;
    out.println(2.1bd < 3.2bd);
    out.println(2.1bd == 2.1bd);
  }

  // manifold-ext dependency
  private static void useUnitExpressions() {
    out.println("\n\n### Use Unit Expressions ###\n");

    // Conveniently use Rational, BigDecimal, etc. (CoercionConstants)
    Rational rational = 2.1r + 2.2r;
    BigDecimal result = 2.1bd + 2.2bd;

    // Type-safely work with physical measurements (UnitConstants)
    Length distance = 75mph * 2.3hr;
    Force force = 5kg * 9.8m/s/s;
    Energy e1 = force * 12m;
    Energy e2 = 200kg m/s/s m;

    // SI formatted measurements (UnitConstants)
    HeatCapacity kBoltzmann = 1.380649e-23 J/dK;
    out.println(kBoltzmann);

    // Convenient metric units (MetricScaleUnits)
    Length longDistance = 5.2M mi; // 5.2 million miles

    // Use your own unit names
    LengthUnit bigYard = LengthUnit.Meter;
    Length twoMeters = 2 bigYard;

    // Easily mix units
    Length measurement = 2 m + 5 in;
    out.println(measurement);
    // Display with any unit
    out.println(measurement.to(ft));
    // Display as mixed fraction
    out.println(measurement.to(ft).toMixedString());
  }

  // manifold-collections dependency
  private static void useRanges() {
    out.println("\n\n### Use Ranges with `RangeFun` ###\n");

    // Work with ranges using binder constants from manifold.collections.api.range.RangeFun

    // Easily make ranges on sequential endpoints using 'to'
    for (int i : 1 to 10) {
      out.println(i);
    }

    // Use with lambdas
    (1 to 10).forEach(out::println);

    // Strong typing
    IntegerRange range = 1 to 10;

    // Reverse the endpoints
    for (int i : 10 to 1) {
      out.println(i);
    }

    // Use 'step' to define an increment
    for (Rational i : 1r to 10r step 2r) {
      out.println(i);
    }

    // Use variations of 'to' ('_to', '_to_', 'to_') to exclude range endpoints
    for (Rational i : 1r _to 10r) {
      out.println(i);
    }

    // Iterate a range of measures with a 'unit'
    for (Mass mass : 5kg to 6kg step 2.7r unit oz) {
      out.println(mass); // increments of 2.7 oz starting with 5kg
    }

    // Use 'inside' to check for containment on Comparable endpoints
    if ("le matos" inside "a" to "m~") {
      out.println("le matos");
    }
  }

  /**
   * Implements operator methods
   */
  public static class Foo implements ComparableUsing<Foo> {
    public final int x;

    public Foo(int x) {
      this.x = x;
    }

    /**
     * implement '+' operator
     */
    public Foo plus(Foo that) {
      return new Foo(x + that.x);
    }

    /**
     * ComparableUsing delegates to compareTo() for >, >=, <, <=
     */
    @Override
    public int compareTo(Foo that) {
      return x - that.x;
    }

    /**
     * Use compareTo() for == and !=
     */
    @Override
    public EqualityMode equalityMode() {
      return EqualityMode.CompareTo;
    }
  }
}
