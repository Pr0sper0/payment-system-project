package org.val.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;
import org.val.converter.BirthdayConverter;
import org.val.entity.Account;
import org.val.entity.Card;
import org.val.entity.Order;
import org.val.entity.Product;
import org.val.entity.Role;
import org.val.entity.User;
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
public class AppConfig {


    private SessionFactory sessionFactory;
    private Session session;

    @Bean
    public SessionFactory sessionFactory() {
        org.hibernate.cfg.Configuration cfg = HibernateUtil.buildConfiguration();
        cfg.configure();
        return cfg.buildSessionFactory();
    }

    public AppConfig() {

    }

    @Autowired
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
