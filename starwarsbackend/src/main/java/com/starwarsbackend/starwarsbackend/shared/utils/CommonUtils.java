package com.starwarsbackend.starwarsbackend.shared.utils;

import java.util.Comparator;

import com.starwarsbackend.starwarsbackend.shared.model.Sortable;

public class CommonUtils {
  public static boolean isEmpty(String value) {
    return value == null || value.isBlank() || value.isEmpty();
  }

  public static <T extends Sortable> Comparator<T> generateComparatorToSort(String orderBy, String orderDirection) {
    if (CommonUtils.isEmpty(orderBy)) {
      orderBy = "name";
    }

    Comparator<T> comparator = switch (orderBy.toLowerCase()) {
      case "name" -> Comparator.comparing(T::getName, String.CASE_INSENSITIVE_ORDER);
      case "created" -> Comparator.comparing(T::getCreated);
      default -> Comparator.comparing(T::getName, String.CASE_INSENSITIVE_ORDER);
    };

    boolean isReversed = "desc".equalsIgnoreCase(orderDirection);

    if (isReversed) {
      comparator = comparator.reversed();
    }

    return comparator;
  }
}
