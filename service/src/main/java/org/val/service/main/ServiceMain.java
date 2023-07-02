package org.val.service.main;

import org.val.common.common.CommonMessages;

public class ServiceMain {

  private static void printMessage(String message) {
    System.out.printf("\nService main said: %s ", message);
  }

  public static void main(String[] args) {

    CommonMessages commonMessage = new CommonMessages();
    printMessage(commonMessage.getHelloMessage());

    String property = System.getProperty("java.version");
    if (property.startsWith("17")) {
      printMessage(commonMessage.getSuccessMessage());
    } else if (property.startsWith("15")) {
      printMessage(commonMessage.getWarningMessage());
    } else {
      printMessage(commonMessage.getErrorMessage());
    }


  }

}
