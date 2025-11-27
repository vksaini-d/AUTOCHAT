# AutoChat Backend

This is the backend service for the AutoChat application, built with NestJS, PostgreSQL, and Redis.

## Prerequisites

- Node.js (v18+)
- Docker & Docker Compose

## Setup

1.  **Install Dependencies**:
    ```bash
    cd backend
    npm install
    ```

2.  **Start Infrastructure (DB & Redis)**:
    ```bash
    docker-compose up -d
    ```

3.  **Setup Database**:
    ```bash
    npx prisma generate
    npx prisma db push
    ```

4.  **Run Application**:
    ```bash
    # Development mode
    npm run start:dev
    ```

## API Documentation

Once the server is running, visit `http://localhost:3000/api` to view the Swagger documentation.

## Modules

- **Auth**: JWT-based authentication (Login/Register).
- **Users**: User management.
- **Personas**: CRUD for ChatTune personas.
- **Platforms**: Webhook receivers for messaging platforms.
- **Reply**: Core logic engine (currently mocks LLM responses).
