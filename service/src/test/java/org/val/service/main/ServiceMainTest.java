package org.val.service.main;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ServiceMainTest {

  @Test
  public void checkFormattedString() {
    String message = "Hello World!";
    String expected = "Service main said: Hello World! ";
    String actual = String.format("Service main said: %s ", message);
    assertEquals(expected, actual);
  }
}