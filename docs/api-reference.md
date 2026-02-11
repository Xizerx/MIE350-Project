# API Reference

## Overview

| | |
|---|---|
| **Base URL** | `http://localhost:8080/api` (dev) — cloud URL TBD |
| **Format** | JSON — always set `Content-Type: application/json` |
| **CORS** | Enabled for all origins in dev |
| **Auth** | None yet |

### Response codes
| Code | Meaning |
|---|---|
| `200` | Success (GET / PUT) |
| `201` | Created (POST) |
| `204` | Deleted (DELETE) |
| `400` | Bad request — check the `error` field in the response body |
| `404` | ID not found |
| `500` | Server error — check the IntelliJ console |

---

## Products

| Method | Endpoint | What it does |
|---|---|---|
| `GET` | `/products` | All products (including inactive) |
| `GET` | `/products/active` | Active products only — **use this for the storefront** |
| `GET` | `/products/{id}` | Single product |
| `GET` | `/products/category/{category}` | Filter by category |
| `GET` | `/products/categories` | List of all category strings |
| `GET` | `/products/bundles` | Bundle/kit products only |
| `GET` | `/products/customer/{customerId}/matched` |  Products filtered by this customer's size profile |
| `POST` | `/products` | Create a product |
| `PUT` | `/products/{id}` | Update a product |
| `DELETE` | `/products/{id}` | Soft-delete (sets `active = false`) |

### Product image upload
| Method | Endpoint | What it does |
|---|---|---|
| `POST` | `/products/{id}/image` | Upload image — `multipart/form-data`, field name `file` |
| `DELETE` | `/products/{id}/image` | Remove product image |
| `GET` | `/images/{filename}` | Serve image — use directly in `<img src="...">` |

---

## Inventory

| Method | Endpoint | What it does |
|---|---|---|
| `GET` | `/inventory` | All inventory records |
| `GET` | `/inventory/{id}` | Single record |
| `GET` | `/inventory/product/{productId}` | Inventory for a specific product |
| `GET` | `/inventory/low-stock` |  Items at or below reorder threshold — use for alerts |
| `GET` | `/inventory/out-of-stock` | Items with 0 stock |
| `GET` | `/inventory/category/{category}` | Inventory by product category |
| `GET` | `/inventory/product/{productId}/check-stock?quantity=N` | Returns `{ "inStock": true/false }` |
| `POST` | `/inventory` | Create an inventory record |
| `PUT` | `/inventory/{id}` | Update reorder levels / warehouse location |
| `POST` | `/inventory/product/{productId}/add-stock` | Add stock — body: `{ "quantity": 50 }` |

---

## Orders

| Method | Endpoint | What it does |
|---|---|---|
| `GET` | `/orders` | All orders |
| `GET` | `/orders/{id}` | Single order with all items |
| `GET` | `/orders/number/{orderNumber}` | Look up by order number string |
| `GET` | `/orders/customer/{customerId}` | All orders for a customer |
| `GET` | `/orders/customer/{customerId}/history` | Customer orders, newest first |
| `GET` | `/orders/status/{status}` | Filter by status |
| `GET` | `/orders/date-range?startDate=...&endDate=...` | Date range filter (ISO 8601) |
| `POST` | `/orders` |  Create order — auto-decrements inventory, calculates totals |
| `PUT` | `/orders/{id}/status` | Update status — body: `{ "status": "SHIPPED" }` |
| `DELETE` | `/orders/{id}` | Cancel order — restores inventory |

### Creating an order — request body
```json
{
  "customer": { "id": 1 },
  "shippingCost": 9.99,
  "shippingAddress": "123 Maple St, Toronto, ON M5V 3A8",
  "orderItems": [
    {
      "product": { "id": 3 },
      "quantity": 2,
      "includesApplicationKit": false
    },
    {
      "product": { "id": 8 },
      "quantity": 1,
      "includesApplicationKit": true
    }
  ]
}
```
> Do **not** send `subtotal`, `tax`, `totalAmount`, or `unitPrice` — the server calculates all of these automatically.

---

## Analytics

| Method | Endpoint | What it does |
|---|---|---|
| `GET` | `/analytics/last-30-days` |  Key metrics for past 30 days |
| `GET` | `/analytics/current-month` | Metrics for this calendar month |
| `GET` | `/analytics?startDate=...&endDate=...` | Custom date range |

### Analytics response shape
```json
{
  "totalRevenue": 1500.00,
  "totalOrders": 10,
  "totalCustomers": 8,
  "averageOrderValue": 150.00,
  "conversionRate": 75.50,
  "topSellingProducts": [
    {
      "productId": 1,
      "productName": "Classic French Tips",
      "category": "press-on nails",
      "unitsSold": 15,
      "revenue": 194.85,
      "percentageOfTotal": 12.99
    }
  ],
  "categoryBreakdown": [
    {
      "category": "press-on nails",
      "totalOrders": 6,
      "revenue": 800.00,
      "uniqueCustomers": 5,
      "averageOrderValue": 133.33
    }
  ],
  "salesTrend": [
    {
      "period": "2026-01",
      "revenue": 800.00,
      "orders": 6,
      "customers": 5
    }
  ]
}
```

---

## Customers

| Method | Endpoint | What it does |
|---|---|---|
| `GET` | `/customers` | All customers |
| `GET` | `/customers/active` | Active customers only |
| `GET` | `/customers/{id}` | Single customer with full size profile |
| `GET` | `/customers/email/{email}` | Find by email |
| `GET` | `/customers/nail-size/{size}` | Customers by nail size preference |
| `GET` | `/customers/city/{city}` | Customers by city |
| `POST` | `/customers` | Create customer |
| `PUT` | `/customers/{id}` | Update customer |
| `PUT` | `/customers/{id}/size-profile` |  Update size profile only |
| `DELETE` | `/customers/{id}` | Soft-delete |

### Updating a size profile — request body
```json
{
  "nailSize": "Medium",
  "necklaceLength": "18in",
  "sunglassesSize": "Small",
  "style": "Modern"
}
```

---

## The Three Special Features — How They Work

###  Size Profile Matching
1. Customer logs in → frontend fetches `GET /customers/{id}`
2. Customer's preferred sizes are stored in their profile
3. Frontend calls `GET /products/customer/{id}/matched`
4. Server filters products by the customer's sizes and returns only matching ones
5. Customer can update their preferences via `PUT /customers/{id}/size-profile` at any time

###  Variant Analytics
1. Frontend calls `GET /analytics/last-30-days` on page load
2. Use `salesTrend[]` → line chart (period on x-axis, revenue on y-axis)
3. Use `categoryBreakdown[]` → pie or bar chart
4. Use `topSellingProducts[]` and `lowPerformingProducts[]` → ranked table
5. `conversionRate` = % of orders that reached `DELIVERED` status

###  Component Bundle Tracking
1. Bundle products have `isBundle = true`
2. When creating an order, set `includesApplicationKit: true` on the order item
3. The server automatically decrements both the bundle stock AND the kit sub-inventory
4. If there isn't enough stock for any item in the order, the whole order is rejected with a `400` error and a clear message saying which product is out of stock
