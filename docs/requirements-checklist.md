# MIE350 Requirements Checklist

This document maps every course requirement to how Angelveil satisfies it.
Use this to verify compliance before each milestone submission.

---

## Database Requirements

| Requirement | Minimum | Angelveil | Status |
|---|---|---|---|
| Relations (tables) | 5 | 6 (Products, Inventory, Customers, Orders, OrderItems, Suppliers) | ✅ |
| Total attributes | 20 | 70+ across all tables | ✅ |
| Tuples (rows) | 50 | 90+ loaded at startup via DataInitializer | ✅ |
| Relational database | H2 | H2 in-memory | ✅ |

---

## Application Size Requirements

### 4 Distinct Functionalities

Each functionality = a full set of create / read / update / delete operations on a distinct domain object.

| # | Functionality | Endpoints | Status |
|---|---|---|---|
| 1 | **Product Management** | `GET/POST/PUT/DELETE /products` | ✅ Built |
| 2 | **Inventory Tracking** | `GET/POST/PUT /inventory`, `/low-stock`, `/add-stock` | ✅ Built |
| 3 | **Order Management** | `GET/POST/PUT/DELETE /orders`, status updates, cancellation | ✅ Built |
| 4 | **Customer Management** | `GET/POST/PUT/DELETE /customers`, size profile updates | ✅ Built |

> The three specialized features (Size Profile Matching, Variant Analytics, Bundle Tracking) are **on top of** these four — they add significant value to the grade beyond the minimum.

### 3 Distinct Dynamic Pages

Each page must change its content based on responses from the server.

| # | Page | Dynamic content from server | Status |
|---|---|---|---|
| 1 | **Inventory Dashboard** | Live stock levels, low-stock alerts, reorder flags | 🔲 Frontend to build |
| 2 | **Order Management** | Order list filtered by status, customer order history | 🔲 Frontend to build |
| 3 | **Analytics** | Revenue, top products, category breakdown, sales trend charts | 🔲 Frontend to build |
| 4 | **Product Catalogue** | Products filtered by category and/or customer size profile | 🔲 Frontend to build |
| 5 | **Customer Profile** | Customer details + matched products based on their size profile | 🔲 Frontend to build |

> Pages 4 and 5 are optional extras — we only need 3, but having 5 is better for the grade.

---

## Minimal Web Content Requirements

These must all be present somewhere in the frontend. Assign a page/section to each item.

| Requirement | What to build | Assigned to | Status |
|---|---|---|---|
| Purpose statement + functionality summary | About / Home page section | TBD | 🔲 |
| Team description + context + disclaimer | About page | TBD | 🔲 |
| Topic overview with proper citations | About / Info page — cite sources about inventory management, accessories industry | TBD | 🔲 |
| Links page (organized, relevant external sites) | Dedicated links page or sidebar | TBD | 🔲 |

### Suggested links to include
**Industry & Inspiration**
- [OPI](https://www.opi.com) — major nail brand
- [Static Nails](https://www.staticnails.com) — press-on nail brand (direct competitor reference)
- [Etsy — Press-on Nails](https://www.etsy.com/search?q=press+on+nails) — marketplace context
- [Mejuri](https://mejuri.com) — jewelry brand
- [Quay Australia](https://www.quayaustralia.com) — sunglasses brand

**Inventory Management Context**
- [Shopify Inventory Management Guide](https://www.shopify.com/blog/inventory-management)
- [Square Retail](https://squareup.com/ca/en/point-of-sale/retail) — comparable retail management tool

---

## Technology Requirements

| Requirement | Status |
|---|---|
| Java-based REST backend | ✅ Spring Boot 3.2, Java 21 |
| H2 Database | ✅ Configured in application.properties |
| Object-oriented business logic | ✅ Service layer with OOP patterns throughout |
| Front-end using tutorial tools | 🔲 Pending — check with instructor if needed |
| Third-party code referenced in reports | ⚠️ Spring Boot, H2 must be cited in final report |

---

## Third-Party Code to Cite in Report

| Library | Version | Purpose | License |
|---|---|---|---|
| Spring Boot | 3.2.1 | Backend framework | Apache 2.0 |
| Spring Data JPA | (bundled) | Database ORM | Apache 2.0 |
| H2 Database | (bundled) | In-memory relational DB | EPL / MPL |
| Spring Boot Validation | (bundled) | Input validation | Apache 2.0 |

---

## Milestone Compliance Summary

| Milestone | Requirement | Status |
|---|---|---|
| Jan 20 | Topic selected, roles assigned | ✅ |
| Jan 27 | Project plan with task breakdown | ✅ (Section A & B in Project Log) |
| Late Feb | Written progress report | 🔲 |
| Late Feb | Web application prototype | 🔲 Backend running. Frontend needed. |
| Late Mar | Project presentation | 🔲 |
| Apr 7 | Final report + web application | 🔲 |
| Apr 7 | 360° review | 🔲 |

---

## Open Items / Risks

| Item | Risk | Action needed |
|---|---|---|
| Frontend tech not yet decided | Medium — must use tutorial tools or get approval | Confirm with instructor |
| Links page not built | Low — simple to add | Assign to a team member |
| About/disclaimer page not built | Low | Assign to a team member |
| Topic overview citations not written | Medium — mandatory per spec | Research + write for progress report |
| Cloud deployment | Medium — frontend needs a URL | Set up Railway/Render before prototype deadline |
