module manifold.sample.project {
  // Use the Manifold "uber" JAR for convenient access to all of Manifold's components and features
  requires manifold.all;

  // Include transitive dependencies manually since manifold.all is an "automatic" module
  // (it doesn't define a manifold-info.java file, thus no 'requires' to its dependencies)
  requires java.scripting;
  requires java.desktop;
  requires jdk.unsupported;

  // Register the sample Date proxy factory service implementation
  // (note the META-INF/services registration is still necessary for Java 8 and Java 9+ unnamed-module)
  provides manifold.ext.api.IProxyFactory
    with sample_project.extensions.java.time.chrono.ChronoLocalDateTime.Date_To_ChronoLocalDateTime;
}