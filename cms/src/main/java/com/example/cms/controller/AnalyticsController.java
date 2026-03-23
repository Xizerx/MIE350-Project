package com.example.cms.controller;

import com.example.cms.model.entity.Order;
import com.example.cms.model.entity.OrderItem;
import com.example.cms.model.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final OrderRepository orderRepository;

    public AnalyticsController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/last-30-days")
    public Map<String, Object> getLast30DaysAnalytics() {
        // 1. Define the time range (Last 30 days to right now)
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime thirtyDaysAgo = now.minusDays(30);

        return generateAnalyticsReport(Timestamp.valueOf(thirtyDaysAgo), Timestamp.valueOf(now));
    }

    @GetMapping("/current-month")
    public Map<String, Object> getCurrentMonthAnalytics() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfMonth = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);

        return generateAnalyticsReport(Timestamp.valueOf(startOfMonth), Timestamp.valueOf(now));
    }

    // Helper method to crunch the numbers for any given date range
    private Map<String, Object> generateAnalyticsReport(Timestamp startDate, Timestamp endDate) {
        List<Order> orders = orderRepository.findByCreatedAtBetween(startDate, endDate);

        Map<String, Object> report = new HashMap<>();

        if (orders.isEmpty()) {
            report.put("message", "No orders found in this date range.");
            return report;
        }

        // --- Core Metrics ---
        BigDecimal totalRevenue = BigDecimal.ZERO;
        Set<Long> uniqueCustomers = new HashSet<>();
        int deliveredCount = 0;

        // --- Aggregation Maps for breakdown ---
        Map<String, BigDecimal> categoryRevenue = new HashMap<>();
        Map<String, Integer> categoryOrderCount = new HashMap<>();

        for (Order order : orders) {
            // Add to total revenue
            if (order.getTotalAmount() != null) {
                totalRevenue = totalRevenue.add(order.getTotalAmount());
            }

            // Track unique customers
            if (order.getCustomer() != null) {
                uniqueCustomers.add(order.getCustomer().getId());
            }

            // Track conversion (delivered orders)
            if ("DELIVERED".equalsIgnoreCase(order.getStatus())) {
                deliveredCount++;
            }

            // Category Breakdown Logic
            if (order.getOrderItems() != null) {
                for (OrderItem item : order.getOrderItems()) {
                    if (item.getProduct() != null) {
                        String category = item.getProduct().getCategory();

                        // Calculate item revenue
                        BigDecimal itemRevenue = item.getUnit_price().multiply(new BigDecimal(item.getQuantity()));

                        // Update Category Maps
                        categoryRevenue.put(category, categoryRevenue.getOrDefault(category, BigDecimal.ZERO).add(itemRevenue));
                        categoryOrderCount.put(category, categoryOrderCount.getOrDefault(category, 0) + 1);
                    }
                }
            }
        }

        // --- Calculate Averages and Rates ---
        int totalOrders = orders.size();
        BigDecimal averageOrderValue = totalRevenue.divide(new BigDecimal(totalOrders), 2, RoundingMode.HALF_UP);
        double conversionRate = ((double) deliveredCount / totalOrders) * 100.0;

        // --- Format Category Breakdown for JSON ---
        List<Map<String, Object>> categoryBreakdownList = new ArrayList<>();
        for (String category : categoryRevenue.keySet()) {
            Map<String, Object> catStats = new HashMap<>();
            catStats.put("category", category);
            catStats.put("revenue", categoryRevenue.get(category));
            catStats.put("totalOrders", categoryOrderCount.get(category));
            categoryBreakdownList.add(catStats);
        }

        // --- Assemble the final JSON object ---
        report.put("totalRevenue", totalRevenue);
        report.put("totalOrders", totalOrders);
        report.put("totalCustomers", uniqueCustomers.size());
        report.put("averageOrderValue", averageOrderValue);
        report.put("conversionRate", Math.round(conversionRate * 100.0) / 100.0); // Round to 2 decimals
        report.put("categoryBreakdown", categoryBreakdownList);

        // Note: For a fully complete MIE350 submission, you can add similar looping logic
        // to populate the "topSellingProducts" and "salesTrend" arrays here.

        return report;
    }
}