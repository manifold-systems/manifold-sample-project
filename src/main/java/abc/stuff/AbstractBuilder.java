package abc.stuff;

import manifold.ext.api.Self;

public abstract class AbstractBuilder {
  String _name;

  public @Self AbstractBuilder withName(String name) {
    _name = name;
    return this;
  }

  protected String getName() {
    return _name;
  }

  public abstract Object build();
}
