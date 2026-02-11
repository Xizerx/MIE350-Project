# Angelveil 💜

Inventory management web application for an accessories company selling press-on nails, chain necklaces, sunglasses, and more.

Built as part of **MIE350 — Design and Analysis of Information Systems**, University of Toronto, Winter 2026.

---

## What is this app?

Angelveil is an internal tool for managing the business. It lets staff:

- Track product inventory and get low-stock alerts
- Process and manage customer orders
- Match products to customers based on their saved size preferences
- View sales analytics — what's selling, what's not, revenue trends
- Manage bundle products (e.g. press-on nail kits) where selling one kit automatically decrements the component inventory

---

## Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 21 + Spring Boot |
| Database | H2 (in-memory, dev) → to be migrated to cloud DB |
| Frontend | TBD |
| Deployment | TBD (Railway / Render) |

---

## Repo Structure

```
angelveil/
├── docs/
│   ├── data-structure.md     ← database schema & relationships
│   ├── api-reference.md      ← all API endpoints
│   └── diagrams/
│       └── er-diagram.png    ← entity relationship diagram
├── backend/
│   └── README.md             ← backend setup instructions
└── frontend/
    └── README.md             ← frontend setup instructions
```

---

## Three Specialized Features

### 1. Size Profile Matching
Every customer has a stored size profile (nail size, necklace length, sunglasses size, style preference). The app uses this to filter and surface products that actually fit them — instead of showing everything.

### 2. Variant Analytics
A dedicated reporting page that tracks how products are performing: conversion rate, average transaction value, top/bottom sellers, and revenue broken down by category and over time.

### 3. Component Bundle Tracking
Some products are sold as kits (e.g. press-on nails + application kit). When a bundle is sold, the system automatically decrements both the bundle stock and the sub-component stock — so you never oversell.

---

## Team

| Name | Role |
|---|---|
| Yushu | Backend architecture |
| Martha | Database design |
| Giovanna | Database & analytics |
| Harbin | Order processing |
| Junyang | Inventory service |
| Ayesha | Sample data & testing |

---

## Status

- [x] Data structure finalized
- [x] API contract defined
- [x] Backend — Product Management (Functionality 1)
- [x] Backend — Inventory Tracking (Functionality 2)
- [x] Backend — Order Management (Functionality 3)
- [x] Backend — Customer Management (Functionality 4)
- [x] Backend — Size Profile Matching (Specialized Feature 1)
- [x] Backend — Variant Analytics (Specialized Feature 2)
- [x] Backend — Bundle Tracking (Specialized Feature 3)
- [ ] Frontend implementation (3+ dynamic pages required)
- [ ] About / disclaimer / links pages
- [ ] Cloud deployment
- [ ] Integration & testing

## Course Requirements Met
See [`docs/requirements-checklist.md`](docs/requirements-checklist.md) for full compliance audit.
