package sample_project.extensions.java.lang.String;

import manifold.ext.api.Extension;
import manifold.ext.api.This;
import java.lang.String;

@Extension
public class MyStringExt {
  public static void echo( @This String thiz) {
    System.out.println(thiz);
  }
}