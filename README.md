# 🌌 Star Wars Web Application

A microservices-based web application that displays data about Star Wars **People** and **Planets** by consuming the [SWAPI](https://swapi.py4e.com/). It provides searching, sorting, and pagination features, following clean architecture and software engineering best practices.

---

## 🏗 Architecture

- **Backend:** Java 17, Spring Boot REST API
- **Frontend:** Angular 17 SPA (Single Page Application)
- **Containerization:** Docker Compose orchestrates backend and frontend services

---

## ✨ Features

- Display **People** and **Planets** in separate tables
- Search by name (case-insensitive, partial match)
- Sort by `name` or `created` fields, ascending or descending
- Pagination with 15 items per page
- Backend implements sorting logic following the Open/Closed principle
- Error handling centralized and clean separation of concerns

---

## ⚙️ Prerequisites

- Docker (tested with 20.x+)
- Docker Compose (tested with 1.29.x or higher)

For local development (optional):

- Java 17 and Maven
- Node.js (v18+) and Angular CLI

---

## 🚀 Quick Start: Run with Docker Compose

Clone the repository, then from the root folder:

```bash
docker-compose up --build
```

This will start two services:

| Service  | URL                   |
| -------- | --------------------- |
| Backend  | http://localhost:6969 |
| Frontend | http://localhost:4200 |

---

## 🖥 Usage

Open the frontend in your browser at `http://localhost:4200`.
You will find two main views:

- **People** — table with Star Wars characters
- **Planets** — table with Star Wars planets

Each view supports:

- **Search:** case-insensitive partial search by name
- **Sort:** by `name` or `created` (ascending/descending)
- **Pagination:** 15 items per page

---

## 🧪 Running Tests

Backend tests are implemented with JUnit and can be run via Maven:

```bash
cd backend
./mvnw test
```

## 🔗 Backend API

| Resource | Endpoint  | Query Parameters                              | Description                              |
| -------- | --------- | --------------------------------------------- | ---------------------------------------- |
| People   | `/people` | `search`, `page`, `orderBy`, `orderDirection` | Search/filter, paginate and sort people  |
| Planets  | `/planet` | `search`, `page`, `orderBy`, `orderDirection` | Search/filter, paginate and sort planets |

### Supported Query Parameters

- `search`: `string` — Partial name filter (case-insensitive)
- `page`: `integer` ≥ 1 — Pagination (15 items per page)
- `orderBy`: `"name"` or `"created"` — Sort field
- `orderDirection`: `"asc"` or `"desc"` — Sort direction

---

## 💡 Code Quality and Design

- Separation of concerns with Controller, Service, Repository layers
- Sorting implemented using the Open/Closed principle via a generic comparator interface
- Centralized error handling with Spring `@ControllerAdvice`
- Clean, maintainable, and well-structured codebase following best practices
