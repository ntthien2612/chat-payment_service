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

file .jar được build sẵn:
./mvnw clean package -DskipTests

./mvnw spring-boot:run

# 1. Biên dịch lại project
./mvnw clean package -DskipTests

# 2. Build lại Docker image
docker-compose build payment_service

# 3. Restart service (không cần stop hết)
docker-compose up -d payment_service


1️⃣ Build và chạy container

docker-compose up --build

Chạy lệnh sau để dừng và xóa container:
docker-compose down

1️⃣ restart container

docker-compose up --build -d

Xóa images, volumes, networks (nếu cần):
docker rmi $(docker images -q) && docker volume rm $(docker volume ls -q) && docker network prune -f

🙋 Author
Developed by [Nguyen Thanh Thien]


✅ Cách chạy
Build jar:
bash
./mvnw clean package -DskipTests

Start Docker:
bash
docker-compose up --build


✅ Cách chạy local (không dùng Docker)
Đảm bảo Docker đã bật Kafka, Redis, MySQL:

bash
docker-compose up -d db kafka zookeeper redis
Chạy app Spring Boot bằng Maven:

bash
./mvnw spring-boot:run

docker-compose start
