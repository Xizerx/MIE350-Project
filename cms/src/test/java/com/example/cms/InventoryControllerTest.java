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
public class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldRetrieveLowStockItems() throws Exception {
        mockMvc.perform(get("/inventory/low-stock"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                // Based on data.sql, product 555222 should be in low stock
                .andExpect(jsonPath("$[*].product.product_id").value(org.hamcrest.Matchers.hasItem(555222)));
    }

    @Test
    void shouldRetrieveInventoryByProductId() throws Exception {
        mockMvc.perform(get("/inventory/product/172894"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stock_quantity").exists())
                .andExpect(jsonPath("$.warehouseLocation").value("Zone-A1"));
    }
}