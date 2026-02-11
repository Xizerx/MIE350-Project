# Frontend

## Stack
- TBD

## Structure (to be added)
```
frontend/
└── ...
```

## Connecting to the backend

All API calls go to `http://localhost:8080/api` during development.

See [`../docs/api-reference.md`](../docs/api-reference.md) for all available endpoints.

### Displaying product images
```html
<img src="http://localhost:8080/api/images/{filename}" />
```
The `imageUrl` field on every product gives you the path (e.g. `/images/abc123.jpg`).
Prepend the base URL to use it.

## Pages to build

| Page                           | Endpoints to call                                                                                                   |
|--------------------------------|---------------------------------------------------------------------------------------------------------------------|
| Product catalogue / storefront | `GET /products/active` or `GET /products/customer/{id}/matched`                                                     |
| Product detail                 | `GET /products/{id}`, `GET /inventory/product/{id}`                                                                 |
| Dashboard                      | `GET /inventory`, `GET /inventory/low-stock`,`GET /orders`, `PUT /orders/{id}/status`,`GET /analytics/last-30-days` |
                                                      |

