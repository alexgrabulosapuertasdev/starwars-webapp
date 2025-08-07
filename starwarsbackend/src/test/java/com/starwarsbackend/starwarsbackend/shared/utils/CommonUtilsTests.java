package com.starwarsbackend.starwarsbackend.shared.utils;

import org.junit.jupiter.api.Test;

import com.starwarsbackend.starwarsbackend.shared.model.Sortable;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class CommonUtilsTests {
  static class TestEntity implements Sortable {
    private final String name;
    private final String created;

    TestEntity(String name, String created) {
      this.name = name;
      this.created = created;
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public String getCreated() {
      return created;
    }
  }

  @Test
  void testIsEmpty() {
    assertTrue(CommonUtils.isEmpty(null));
    assertTrue(CommonUtils.isEmpty(""));
    assertTrue(CommonUtils.isEmpty("   "));
    assertFalse(CommonUtils.isEmpty("text"));
    assertFalse(CommonUtils.isEmpty("  text  "));
  }

  @Test
  void testSortByNameAsc() {
    var e1 = new TestEntity("Luke", "2020-01-01");
    var e2 = new TestEntity("Anakin", "2020-01-02");
    var list = List.of(e1, e2);
    var sorted = list.stream()
        .sorted(CommonUtils.<TestEntity>generateComparatorToSort("name", "asc"))
        .toList();
    assertEquals("Anakin", sorted.get(0).getName());
  }

  @Test
  void testSortByNameDesc() {
    var e1 = new TestEntity("Luke", "2020-01-01");
    var e2 = new TestEntity("Anakin", "2020-01-02");
    var list = List.of(e1, e2);
    var sorted = list.stream()
        .sorted(CommonUtils.<TestEntity>generateComparatorToSort("name", "desc"))
        .toList();
    assertEquals("Luke", sorted.get(0).getName());
  }

  @Test
  void testSortByCreatedAsc() {
    var e1 = new TestEntity("Luke", "2020-01-01");
    var e2 = new TestEntity("Luke", "2019-01-01");
    var list = List.of(e1, e2);
    var sorted = list.stream()
        .sorted(CommonUtils.<TestEntity>generateComparatorToSort("created", "asc"))
        .toList();
    assertEquals("2019-01-01", sorted.get(0).getCreated());
  }

  @Test
  void testSortByCreatedDesc() {
    var e1 = new TestEntity("Luke", "2020-01-01");
    var e2 = new TestEntity("Luke", "2019-01-01");
    var list = List.of(e1, e2);
    var sorted = list.stream()
        .sorted(CommonUtils.<TestEntity>generateComparatorToSort("created", "desc"))
        .toList();
    assertEquals("2020-01-01", sorted.get(0).getCreated());
  }

  @Test
  void testSortByDefaultWhenOrderByIsEmpty() {
    var e1 = new TestEntity("Luke", "2020-01-01");
    var e2 = new TestEntity("Anakin", "2020-01-01");
    var list = List.of(e1, e2);
    var sorted = list.stream()
        .sorted(CommonUtils.<TestEntity>generateComparatorToSort("", "asc"))
        .toList();
    assertEquals("Anakin", sorted.get(0).getName());
  }

  @Test
  void testSortByDefaultWhenOrderByIsInvalid() {
    var e1 = new TestEntity("Luke", "2020-01-01");
    var e2 = new TestEntity("Anakin", "2020-01-01");
    var list = List.of(e1, e2);
    var sorted = list.stream()
        .sorted(CommonUtils.<TestEntity>generateComparatorToSort("invalid", "asc"))
        .toList();
    assertEquals("Anakin", sorted.get(0).getName());
  }
}
