package com.starwarsbackend.starwarsbackend.shared.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonUtilsTest {

  @Test
  void testIsEmpty() {
    assertTrue(CommonUtils.isEmpty(null));
    assertTrue(CommonUtils.isEmpty(""));
    assertTrue(CommonUtils.isEmpty("   "));
    assertFalse(CommonUtils.isEmpty("text"));
    assertFalse(CommonUtils.isEmpty("  text  "));
  }
}
