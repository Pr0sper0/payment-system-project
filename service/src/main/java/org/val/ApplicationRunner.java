package org.val;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.val.config.AppConfig;

public class ApplicationRunner {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(AppConfig.class)) {

            context.getEnvironment().setActiveProfiles("dev");
            context.refresh();
        }
    }
}
