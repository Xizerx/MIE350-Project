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
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldRetrieveAllActiveCustomers() throws Exception {
        mockMvc.perform(get("/customers/active"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].firstName").exists());
    }

    @Test
    void shouldUpdateCustomerSizeProfile() throws Exception {
        String sizeProfileJson = "{\"nailSize\": \"small\", \"necklaceLength\": \"14in\"}";

        mockMvc.perform(put("/customers/1/size-profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(sizeProfileJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.preferredNailSize").value("small"))
                .andExpect(jsonPath("$.preferredNecklaceLength").value("14in"));
    }

    @Test
    void shouldSoftDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/customers/1"))
                .andExpect(status().isOk());

        // Verify customer is no longer in the active list
        mockMvc.perform(get("/customers/active"))
                .andExpect(jsonPath("$[*].id").value(org.hamcrest.Matchers.not(1)));
    }
}