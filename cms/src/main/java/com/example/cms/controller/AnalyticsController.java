package com.example.cms.controller;

import com.example.cms.model.entity.Order;
import com.example.cms.model.entity.OrderItem;
import com.example.cms.model.repository.OrderRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final OrderRepository orderRepository;

    public AnalyticsController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Single dynamic endpoint driven by Appsmith date pickers
    @GetMapping
    public Map<String, Object> getAnalyticsByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        // Convert dates to start of day and end of day to capture all orders within the range
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(23, 59, 59);

        return generateAnalyticsReport(Timestamp.valueOf(start), Timestamp.valueOf(end));
    }

    // Helper method to crunch the numbers
    private Map<String, Object> generateAnalyticsReport(Timestamp startDate, Timestamp endDate) {
        List<Order> orders = orderRepository.findByCreatedAtBetween(startDate, endDate);

        Map<String, Object> report = new HashMap<>();

        if (orders.isEmpty()) {
            report.put("message", "No orders found in this date range.");
            report.put("totalRevenue", BigDecimal.ZERO);
            report.put("totalOrders", 0);
            report.put("totalCustomers", 0);
            report.put("averageOrderValue", BigDecimal.ZERO);
            report.put("conversionRate", 0.0);
            report.put("categoryBreakdown", new ArrayList<>());
            report.put("salesTrend", new ArrayList<>());
            report.put("topSellingProducts", new ArrayList<>());
            return report;
        }

        // --- Core Metrics ---
        BigDecimal totalRevenue = BigDecimal.ZERO;
        Set<Long> uniqueCustomers = new HashSet<>();
        int deliveredCount = 0;

        // --- Aggregation Maps ---
        Map<String, BigDecimal> categoryRevenue = new HashMap<>();
        Map<String, Integer> categoryOrderCount = new HashMap<>();
        Map<String, BigDecimal> dailyRevenueMap = new TreeMap<>(); // TreeMap keeps dates sorted chronologically
        Map<Integer, Map<String, Object>> productStatsMap = new HashMap<>();

        for (Order order : orders) {
            // 1. Core Totals
            BigDecimal orderTotal = order.getTotalAmount() != null ? order.getTotalAmount() : BigDecimal.ZERO;
            totalRevenue = totalRevenue.add(orderTotal);

            if (order.getCustomer() != null) {
                uniqueCustomers.add(order.getCustomer().getId());
            }

            if ("DELIVERED".equalsIgnoreCase(order.getStatus())) {
                deliveredCount++;
            }

            // 2. Daily Sales Trend Logic
            if (order.getCreatedAt() != null) {
                String dateKey = new java.text.SimpleDateFormat("yyyy-MM-dd").format(order.getCreatedAt());
                dailyRevenueMap.put(dateKey, dailyRevenueMap.getOrDefault(dateKey, BigDecimal.ZERO).add(orderTotal));
            }

            // 3. Item-Level Breakdown (Categories & Products)
            if (order.getOrderItems() != null) {
                for (OrderItem item : order.getOrderItems()) {
                    if (item.getProduct() != null) {
                        String category = item.getProduct().getCategory();
                        Integer productId = item.getProduct().getProduct_id();
                        String productName = item.getProduct().getName();
                        int quantity = item.getQuantity() != null ? item.getQuantity() : 0;
                        BigDecimal itemRevenue = item.getUnit_price().multiply(new BigDecimal(quantity));

                        // Update Categories
                        categoryRevenue.put(category, categoryRevenue.getOrDefault(category, BigDecimal.ZERO).add(itemRevenue));
                        categoryOrderCount.put(category, categoryOrderCount.getOrDefault(category, 0) + 1);

                        // Update Top Products
                        productStatsMap.putIfAbsent(productId, new HashMap<>(Map.of(
                                "productId", productId,
                                "productName", productName,
                                "category", category,
                                "unitsSold", 0,
                                "revenue", BigDecimal.ZERO
                        )));

                        Map<String, Object> pStat = productStatsMap.get(productId);
                        pStat.put("unitsSold", (Integer) pStat.get("unitsSold") + quantity);
                        pStat.put("revenue", ((BigDecimal) pStat.get("revenue")).add(itemRevenue));
                    }
                }
            }
        }

        // --- Math for Averages & Rates ---
        int totalOrders = orders.size();
        BigDecimal averageOrderValue = totalRevenue.divide(new BigDecimal(totalOrders), 2, RoundingMode.HALF_UP);
        double conversionRate = ((double) deliveredCount / totalOrders) * 100.0;

        // --- Format Data for JSON Output ---

        // Format Categories
        List<Map<String, Object>> categoryBreakdownList = new ArrayList<>();
        for (String category : categoryRevenue.keySet()) {
            categoryBreakdownList.add(Map.of(
                    "category", category,
                    "revenue", categoryRevenue.get(category),
                    "totalOrders", categoryOrderCount.get(category)
            ));
        }

        // Format Daily Trend
        List<Map<String, Object>> salesTrendList = new ArrayList<>();
        for (Map.Entry<String, BigDecimal> entry : dailyRevenueMap.entrySet()) {
            salesTrendList.add(Map.of(
                    "date", entry.getKey(),
                    "revenue", entry.getValue()
            ));
        }

        // Format and Sort Top Products (Highest units sold first)
        List<Map<String, Object>> topProductsList = new ArrayList<>(productStatsMap.values());
        topProductsList.sort((a, b) -> ((Integer) b.get("unitsSold")).compareTo((Integer) a.get("unitsSold")));

        // Limit to top 10 items so the frontend table doesn't get massive
        if (topProductsList.size() > 10) {
            topProductsList = topProductsList.subList(0, 10);
        }

        // --- Assemble the final JSON object ---
        report.put("totalRevenue", totalRevenue);
        report.put("totalOrders", totalOrders);
        report.put("totalCustomers", uniqueCustomers.size());
        report.put("averageOrderValue", averageOrderValue);
        report.put("conversionRate", Math.round(conversionRate * 100.0) / 100.0);
        report.put("categoryBreakdown", categoryBreakdownList);
        report.put("salesTrend", salesTrendList);
        report.put("topSellingProducts", topProductsList);

        return report;
    }
}