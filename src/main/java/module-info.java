//!! Note this module-info.java file is unnecessary, but exists to demonstrate how to set one
//!! up if your project must be a JPMS multi-module application.
module manifold.sample.project {
  requires manifold.util;
  requires manifold.rt;
  requires manifold.ext.rt;
  requires manifold.graphql.rt;
  requires manifold.json.rt;
  requires manifold.xml.rt;
  requires manifold.yaml.rt;
  requires manifold.csv.rt;
  requires manifold.js.rt;
  requires manifold.templates.rt;
  requires manifold.science;
  requires manifold.collections;

  requires java.desktop;
  requires jdk.unsupported;

  // Register the sample Date proxy factory service implementation
  // (note the META-INF/services registration is still necessary for Java 8 and Java 9+ unnamed-module)
  provides manifold.ext.rt.api.IProxyFactory
    with sample_project.extensions.java.time.chrono.ChronoLocalDateTime.Date_To_ChronoLocalDateTime;
}