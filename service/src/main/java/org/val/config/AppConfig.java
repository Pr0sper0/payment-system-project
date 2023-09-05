package org.val.config;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.val.web.config.WebConfiguration;

@Configuration
@Import(WebConfiguration.class)
//@PropertySource("classpath:application.yaml")
@NoArgsConstructor
public class AppConfig {


}
