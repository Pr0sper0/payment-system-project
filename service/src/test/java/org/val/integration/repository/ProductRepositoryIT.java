package org.val.integration.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.val.integration.IntegrationTestBase;
import org.val.repository.ProductRepository;

public class ProductRepositoryIT extends IntegrationTestBase {

    @Autowired
    ProductRepository productRepository;

    @Test
    void testFindAll_WhenProductsAreGiven_ShouldReturnExactSize() {

        assertThat(productRepository.findAll()).hasSize(2);
    }
}
