package org.val.integration.repository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.testcontainers.shaded.org.hamcrest.collection.IsCollectionWithSize.hasSize;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.val.integration.IntegrationTestBase;

@AutoConfigureMockMvc
@RequiredArgsConstructor
public class RoleControllerIT extends IntegrationTestBase {

    private final MockMvc mockMvc;

    @Test
    void findAll() throws Exception{
        mockMvc.perform(get("/api/v1/roles"))
                .andExpect(status().isOk())
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("roles"))
                .andExpect(model().attribute("roles", hasSize(2)));
    }

}
