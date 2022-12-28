package manifoldsampleproject.extensions.java.lang.String;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.Jailbreak;
import manifold.ext.rt.api.This;

import java.lang.String;

@Extension
public class MyStringExt {
  public static void echo( @This String thiz) {
    System.out.println(thiz);
  }

  /** Yikes!  Don't try this at home! */
  public static void setBytes(@This @Jailbreak String thiz, byte[] bytes) {
    thiz.value = bytes;
  }
}