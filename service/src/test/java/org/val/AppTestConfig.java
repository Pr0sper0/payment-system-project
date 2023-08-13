package org.val;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
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
public class AppTestConfig {

    private static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER =
            new PostgreSQLContainer<>("postgres:1.17.6")
                    .withDatabaseName("postgres")
                    .withUsername("admin")
                    .withPassword("pass");

    private final SessionFactory sessionFactory;

    Session session;
    @Bean
    public SessionFactory sessionFactory() {
        org.hibernate.cfg.Configuration cfg = HibernateUtil.buildConfiguration();
        cfg.configure();
        return cfg.buildSessionFactory();
    }

    @Autowired
    public AppTestConfig(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



    @PostConstruct
    public void init() {
        session = sessionFactory.openSession();
    }

    @Bean
    public AccountRepository accountRepository() {
        return AccountRepository.of(session);
    }

    @Bean
    public CardRepository cardRepository() {
        return CardRepository.of(session);
    }

    @Bean
    public OrderRepository orderRepository() {
        return OrderRepository.of(session);
    }

    @Bean
    public ProductRepository productRepository() {
        return ProductRepository.of(session);
    }

    @Bean
    public RoleRepository roleRepository() {
        return RoleRepository.of(session);
    }

    @Bean
    public UserRepository userRepository() {
        return UserRepository.of(session);
    }

    @PreDestroy
    public void destroy() {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    @Autowired
    @Lazy
    public void setContext(AbstractApplicationContext context) {
        // Closing sessionFactory
        context.registerShutdownHook();
    }

}
