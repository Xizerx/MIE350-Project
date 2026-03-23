package com.example.cms;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional // Automatically rolls back database changes after every test
public class AngelveilIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test1_SizeProfileMatching() throws Exception {
        // Customer 1 (Sarah) prefers "large" nails, "18in" necklaces, and "medium" sunglasses
        mockMvc.perform(get("/products/customer/1/matched"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                // FIXED: Changed "Oversized Glam" (large) to "Aviator Classic" (medium) to match Sarah's profile
                .andExpect(jsonPath("$[*].name").value(hasItems("Midnight Almond", "Aviator Classic", "Gold Chain")));
    }
    @Test
    void test2_AnalyticsGeneration() throws Exception {
        // Fetches analytics and verifies the server aggregates the 10 test orders properly
        mockMvc.perform(get("/analytics/last-30-days"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalOrders").value(10))
                .andExpect(jsonPath("$.conversionRate").value(30.0)) // 3 out of 10 are DELIVERED
                .andExpect(jsonPath("$.categoryBreakdown").isArray())
                .andExpect(jsonPath("$.categoryBreakdown").isNotEmpty());
    }

    @Test
    void test3_AutomatedOrderCalculationsAndInventoryDecrement() throws Exception {
        // Step 1: Verify starting stock is 20
        mockMvc.perform(get("/inventory/product/172894"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stock_quantity").value(20));

        // Step 2: Create a new order for 5 units at $20.00 each + $10 shipping
        String orderJson = "{\n" +
                "  \"customer\": { \"id\": 1 },\n" +
                "  \"shippingCost\": 10.00,\n" +
                "  \"orderItems\": [\n" +
                "    { \"product\": { \"product_id\": 172894 }, \"quantity\": 5, \"unit_price\": 20.00, \"includesApplicationKit\": false }\n" +
                "  ]\n" +
                "}";

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isOk())
                // Verify Server Math (5 * $20 = $100 subtotal, $13 tax, $123 total)
                .andExpect(jsonPath("$.subtotal").value(100.0))
                .andExpect(jsonPath("$.tax").value(13.0))
                .andExpect(jsonPath("$.totalAmount").value(123.0));

        // Step 3: Verify stock dropped to 15
        mockMvc.perform(get("/inventory/product/172894"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stock_quantity").value(15));
    }

    @Test
    void test4_InventoryRestorationOnCancellation() throws Exception {
        // Create the order
        String orderJson = "{\n" +
                "  \"customer\": { \"id\": 1 },\n" +
                "  \"shippingCost\": 5.00,\n" +
                "  \"orderItems\": [\n" +
                "    { \"product\": { \"product_id\": 172894 }, \"quantity\": 5, \"unit_price\": 24.99 }\n" +
                "  ]\n" +
                "}";

        MvcResult result = mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isOk())
                .andReturn();

        // FIXED: Extract the auto-generated order ID using Jackson ObjectMapper instead of JsonPath
        ObjectMapper mapper = new ObjectMapper();
        JsonNode responseJson = mapper.readTree(result.getResponse().getContentAsString());
        Integer orderId = responseJson.get("order_id").asInt();

        // Verify stock is at 15
        mockMvc.perform(get("/inventory/product/172894"))
                .andExpect(jsonPath("$.stock_quantity").value(15));

        // Delete the order
        mockMvc.perform(delete("/orders/" + orderId))
                .andExpect(status().isOk());

        // Verify stock bounced back to 20
        mockMvc.perform(get("/inventory/product/172894"))
                .andExpect(jsonPath("$.stock_quantity").value(20));
    }

    @Test
    void test5_OutOfStockRejection() throws Exception {
        // Product 555222 only has 5 units in stock. Try to order 10.
        String oversizedOrderJson = "{\n" +
                "  \"customer\": { \"id\": 1 },\n" +
                "  \"shippingCost\": 5.00,\n" +
                "  \"orderItems\": [\n" +
                "    { \"product\": { \"product_id\": 555222 }, \"quantity\": 10, \"unit_price\": 35.00 }\n" +
                "  ]\n" +
                "}";

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(oversizedOrderJson))
                // Verify the server rejects it with a 400 Bad Request
                .andExpect(status().isBadRequest());
    }
}