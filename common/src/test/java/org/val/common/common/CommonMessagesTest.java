package org.val.common.common;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CommonMessagesTest {

  CommonMessages commonMessage;

  public CommonMessagesTest() {
    commonMessage = new CommonMessages();
  }

  @Test
  public void testMain() {
    assertEquals("Hello from common", commonMessage.getHelloMessage());
  }

  @Test
  public void testSuccessMessage() {
    assertEquals("Common message: \"SUCCESS!\"", commonMessage.getSuccessMessage());
  }

  @Test
  public void testWarningMessage() {
    assertEquals("Common message: \"WARNING!\"", commonMessage.getWarningMessage());
  }

  @Test
  public void testErrorMessage() {
    assertEquals("Common message: \"ERROR!\"", commonMessage.getErrorMessage());
  }
}