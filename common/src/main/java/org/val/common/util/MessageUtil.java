package org.val.common.util;

import java.io.IOException;
import java.util.Properties;
import org.val.common.common.CommonMessages;

public class MessageUtil {
  public final Properties prop;
  public MessageUtil() {
    prop = getProperties();
  }

  private Properties getProperties() {
    Properties prop = new Properties();
    try {
      //load a properties file from class path, inside static method
      prop.load(CommonMessages.class.getClassLoader().getResourceAsStream("common.properties"));
    }  catch (NullPointerException | IOException ex) {
      System.out.println("Cannot process properties file");
    }
    return prop;
  }
}
