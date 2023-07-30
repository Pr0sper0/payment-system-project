package org.val.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.val.entity.Product;
import org.val.util.HibernateUtil;

public class ProductRepositoryTest {

    private static final Integer PRODUCT_ID = 1;
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private Session session;

    ProductRepository productRepository;

    public Product getProduct() {
        return Product.builder()
                .name("Product1")
                .description("Description1")
                .price(new BigDecimal("100.00"))
                .build();
    }

    @AfterAll
    static void closeSessionFactory() {
        sessionFactory.close();
    }

    @BeforeEach
    public void init() {
        session = sessionFactory.openSession();
        productRepository = ProductRepository.getInstance(session);
        Transaction transaction = session.beginTransaction();
    }

    @AfterEach
    public void clear() {
        session.getTransaction().rollback();
        session.close();
    }



    @Test
    void methodSave_WhenProductSaved_ShouldReturnExactSize() {

        Product product = getProduct();

        productRepository.save(product);

        assertThat(productRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    void deleteProduct_WhenProductExists_ShouldReturnTrue() {

        Product product = getProduct();

        productRepository.save(product);

        boolean result = productRepository.delete(PRODUCT_ID);

        assertThat(result).isTrue();

    }

    @Test
    void updateProduct_WhenProductExists_ShouldUpdateWithoutErrors() {
        Product product = getProduct();

        productRepository.save(product);
        product.setPrice(new BigDecimal(200));

        productRepository.update(product);
        session.flush();

        Optional<Product> result = productRepository.findAll().stream().toList().stream().findFirst();
        assertThat(result).isPresent();
        assertThat(result.get().getPrice()).isEqualTo(new BigDecimal(200));
    }

    @Test
    void findProductByID_WhenProductWithDefinedIDExists_ShouldReturnTrue() {
        Product product = getProduct();
        productRepository.save(product);

        Optional<Product> result = productRepository.findAll().stream().toList().stream().findFirst();
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(PRODUCT_ID);

    }


}
