package org.val.config;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;

@Configuration
@PropertySource("classpath:application.yaml")
@NoArgsConstructor
public class AppConfig {
//    @Bean
//    public SessionFactory sessionFactory() {
//        org.hibernate.cfg.Configuration cfg = HibernateUtil.buildConfiguration();
//        cfg.configure();
//        return cfg.buildSessionFactory();
//    }
//
//    @Bean
//    public Session session(SessionFactory sessionFactory) {
//        return (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(),
//                new Class[]{Session.class},
//                (proxy, method, args) -> method.invoke(sessionFactory.openSession(), args));
//    }

    @Autowired
    @Lazy
    public void setContext(AbstractApplicationContext context) {
        // Closing sessionFactory
        context.registerShutdownHook();
    }

}
