package com.example.cms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldRetrieveAllActiveProducts() throws Exception {
        mockMvc.perform(get("/products/active"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].active").value(true));
    }

    @Test
    void shouldRetrieveSingleProduct() throws Exception {
        mockMvc.perform(get("/products/172894"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Midnight Almond"))
                .andExpect(jsonPath("$.category").value("nails"));
    }
}