package manifoldsampleproject.extensions.java.util.Map;


import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;

import java.util.Collection;
import java.util.Map;

@Extension
public class MyMapExtension {
  public static <K, V> boolean containsKeys(@This Map<K, V>  thiz, Collection<K> keys) {
    return thiz.keySet().containsAll(keys);
  }

  public static class Entry {
    public static <K, V> String hiFromEntry(@This Map.Entry<K, V> thiz) {
      return "hiFromEntry";
    }
  }
}