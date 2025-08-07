package com.starwarsbackend.starwarsbackend.shared.utils;

public class CommonUtils {
  public static boolean isEmpty(String value) {
    return value == null || value.isBlank() || value.isEmpty();
  }
}
