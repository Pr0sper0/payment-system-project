package org.val.common.common;

import org.val.common.util.MessageUtil;

public final class CommonMessages {

  private static final String HELLO_MESSAGE = "Hello from common";
  MessageUtil messageUtil;

  public CommonMessages() {
    messageUtil = new MessageUtil();
  }

  public String getHelloMessage() {
    return HELLO_MESSAGE;
  }

  public String getSuccessMessage() {
    return "Common message: " + messageUtil.prop.getProperty("message.success");
  }

  public String getWarningMessage() {
    return "Common message: " + messageUtil.prop.getProperty("message.warning");
  }

  public String getErrorMessage() {
    return "Common message: " + messageUtil.prop.getProperty("message.error");
  }
}