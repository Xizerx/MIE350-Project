# Angelveil CMS: Backend Updates & Frontend Integration Guide

This document outlines the recent architectural changes to the Spring Boot backend to meet the MIE350 requirements, along with a comprehensive integration guide for the frontend team.

---

## 1. Backend Changelog

### Database & Schema Updates
* **Products (`Products.java`):** Added missing fields (`size`, `color`, `variant`, `description`, `imageUrl`) and a soft-delete flag (`active`). Shifted size/color data away from the Inventory table to properly associate it with the parent product.
* **Inventory (`Inventory.java`):** Streamlined to strictly handle warehouse data (`reorder_level`, `reorderQuantity`, `warehouseLocation`, `lastRestocked`).
* **Customers (`Customer.java`):** Created a new entity to store contact details and the four specialized size profile fields (`preferredNailSize`, `preferredNecklaceLength`, `preferredSunglassesSize`, `preferredStyle`).
* **Orders (`Order.java` & `OrderItem.java`):** Converted manual string fields to relational `@ManyToOne` mappings linking to the `Customer` and `Products` tables. Added `@GeneratedValue(strategy = GenerationType.IDENTITY)` for ID auto-incrementing. Added `BigDecimal` fields for strict financial tracking (`subtotal`, `tax`, `totalAmount`) and boolean flags for bundle tracking.
* **Mock Data (`data.sql`):** Expanded the H2 initialization script to exactly **57 rows** across 6 tables, completely satisfying the MIE350 rubric's minimum tuple requirement.

### Business Logic & Controller Implementations
* **Specialized Feature 1 (Size Matching):** Created `GET /products/customer/{id}/matched` to dynamically query products matching a specific user's size profile.
* **Specialized Feature 2 (Analytics):** Built `AnalyticsController.java` to aggregate live order data. Implemented math logic to calculate Total Revenue, Average Order Value (AOV), Conversion Rates, and Category Breakdowns on the fly.
* **Automated Order Processing:** Overhauled `POST /orders`. The server now automatically generates an order number, calculates 13% Ontario HST, tallies the final price, and strictly decrements inventory.
* **Stock Protection:** Added logical checks during checkout. If an order requests more stock than available, the server aborts the transaction and returns a `400 Bad Request`. Canceling an order via `DELETE /orders/{id}` automatically restores the stock.

### Testing
* Added `AngelveilIntegrationTests.java` (Java 11 compatible) utilizing `@SpringBootTest` and `MockMvc` to automate verification of the size-matching, analytics, math calculations, and stock rejection features. Database state rolls back automatically after each test via `@Transactional`.

---

## 2. Frontend Integration Guide

### Connection Details
* **Base URL:** `http://localhost:8085`
* **Headers:** Always send `Content-Type: application/json` for `POST` and `PUT` requests.
* **Images:** For now, product images can be referenced locally (e.g., `<img src="/images/midnight-almond.jpg" />`) or mapped via external URLs in the database.

### Product & Storefront APIs
Used to build the Catalogue page and individual Product Detail pages.

* `GET /products/active` — Fetch all products available for sale (excludes soft-deleted items).
* `GET /products/{id}` — Fetch a single product's details.
* `GET /products/customer/{customerId}/matched` — **(Special Feature)** Pass a customer ID, and the server returns only the products that perfectly match their saved size preferences.

### Customer Profile APIs
Used to build the User Settings and Registration pages.

* `GET /customers/{id}` — Fetch a user's full profile, including their preferred sizes.
* `POST /customers` — Register a new customer.
* `PUT /customers/{id}/size-profile` — Update a user's sizing preferences.
  * *Body Example:* `{"nailSize": "medium", "necklaceLength": "18in"}`

### Inventory Dashboard APIs
Used to build the admin Inventory Management page.

* `GET /inventory` — Fetch all warehouse stock levels.
* `GET /inventory/low-stock` — Fetch only the items that have dipped at or below their `reorder_level` (use this to trigger UI alert badges).
* `GET /inventory/product/{productId}` — Look up the exact stock count for a specific product ID (useful for the Product Details page to show "Only X left!").

### Checkout & Order Management APIs
Used to build the Cart/Checkout flow and the admin Order Management page.

* `GET /orders/status/{status}` — Fetch orders by state (e.g., `/orders/status/PENDING` or `/orders/status/SHIPPED`).
* `PUT /orders/{id}/status` — Move an order to the next stage.
  * *Body Example:* `{"status": "DELIVERED"}`
* `DELETE /orders/{id}` — Cancel an order. **Note:** The backend will automatically return the items to the inventory.

#### How to Submit an Order (`POST /orders`)
The backend calculates totals and taxes automatically. Just send the customer ID, the shipping cost, and the items in the cart.

**Request Body:**
```json
{
  "customer": { "id": 1 },
  "shippingCost": 5.00,
  "shippingAddress": "123 King St W, Toronto, ON",
  "orderItems": [
    { 
      "product": { "product_id": 172894 }, 
      "quantity": 2, 
      "unit_price": 24.99, 
      "includesApplicationKit": false 
    }
  ]
}
