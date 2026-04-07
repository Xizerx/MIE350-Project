package com.example.cms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SupplierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldRetrieveSupplierSummaries() throws Exception {
        mockMvc.perform(get("/suppliers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].supplier_id").exists())
                .andExpect(jsonPath("$[0].revenue").exists());
    }

    @Test
    void shouldCreateNewSupplier() throws Exception {
        String newSupplierJson = "{" +
                "\"supplier_id\": 999999," +
                "\"name\": \"Test Supplier\"," +
                "\"email\": \"test@supplier.com\"," +
                "\"phone\": \"123-456-7890\"," +
                "\"number_products_supplied\": 0," +
                "\"revenue\": 0.0" +
                "}";

        mockMvc.perform(post("/suppliers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newSupplierJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Supplier"));
    }
}