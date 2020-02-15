package manifoldsampleproject.extensions.java.util.Map;

import manifold.ext.api.Extension;
import manifold.ext.api.This;

import java.util.Collection;
import java.util.Map;

@Extension
public class MyMapExtension {
  public static <K, V> boolean containsKeys(@This Map<K, V>  thiz, Collection<K> keys) {
    return thiz.keySet().containsAll(keys);
  }
}