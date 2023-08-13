package org.val.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;
import org.val.repository.AccountRepository;
import org.val.repository.CardRepository;
import org.val.repository.OrderRepository;
import org.val.repository.ProductRepository;
import org.val.repository.RoleRepository;
import org.val.repository.UserRepository;
import org.val.util.HibernateUtil;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "org.val")
@NoArgsConstructor
public class AppConfig {
    @Bean
    public SessionFactory sessionFactory() {
        org.hibernate.cfg.Configuration cfg = HibernateUtil.buildConfiguration();
        cfg.configure();
        return cfg.buildSessionFactory();
    }

    @Bean
    public Session session(SessionFactory sessionFactory) {
        return (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(),
                new Class[]{Session.class},
                (proxy, method, args) -> method.invoke(sessionFactory.openSession(), args));
    }

    @Bean
    public AccountRepository accountRepository(Session session) {
        return new AccountRepository(session);
    }

    @Bean
    public CardRepository cardRepository(Session session) {
        return CardRepository.of(session);
    }

    @Bean
    public OrderRepository orderRepository(Session session) {
        return OrderRepository.of(session);
    }

    @Bean
    public ProductRepository productRepository(Session session) {
        return new ProductRepository(session);
    }

    @Bean
    public RoleRepository roleRepository(Session session) {
        return new RoleRepository(session);
    }

    @Bean
    public UserRepository userRepository(Session session) {
        return new UserRepository(session);
    }

    @Autowired
    @Lazy
    public void setContext(AbstractApplicationContext context) {
        // Closing sessionFactory
        context.registerShutdownHook();
    }

}
