# 💬 Real-Time Chat System

A full-stack **real-time chat application** built with **Angular (frontend)** and **Spring Boot (backend)**, integrating **WebSocket for live messaging**, **Kafka for event streaming**, **MySQL for persistence**, **JWT for secure authentication**, and **Docker** for containerized deployment.

---

## 📌 Features

- 🔐 User authentication with JWT
- 👥 Real-time 1-1 messaging using WebSocket (STOMP)
- 🧵 Kafka integration for message broadcasting & decoupling
- 💾 Persist chat messages with JPA + MySQL
- ⚡ Redis (optional) for caching frequently accessed chat history
- 📦 Docker support for local development & deployment

---

## 🏗️ System Architecture
           ┌────────────┐
           │   Angular  │
           │  Frontend  │
           └─────┬──────┘
                 │
    ┌────────────▼────────────┐
    │  Spring Boot Backend    │
    │ (REST API + WebSocket)  │
    └─────┬───────────────┬───┘
          │               │
 ┌────────▼────┐   ┌──────▼─────────┐
 │ Kafka Broker│   │  MySQL DB      │
 │ (Message Bus)│   │  (Chat, Users)│
 └─────────────┘   └────────────────┘

 ## 🛠 Tech Stack

| Layer        | Technology                |
|--------------|---------------------------|
| Frontend     | Angular                   |
| Backend      | Spring Boot               |
| Messaging    | WebSocket (STOMP), Kafka  |
| Auth         | JWT (JSON Web Token)      |
| Database     | MySQL, Spring Data JPA    |
| Cache (opt.) | Redis                     |
| DevOps       | Docker, Docker Compose    |

## ⚙️ Setup Instructions

### ✅ Prerequisites

- Java 17+
- Node.js + Angular CLI
- Docker + Docker Compose
- MySQL client (e.g. MySQL Workbench or CLI)

---

### 🐳 Run with Docker

🧰 Useful Commands

npm install
ng serve

./mvnw spring-boot:run

🙋 Author
Developed by [Nguyen Thanh Thien]



