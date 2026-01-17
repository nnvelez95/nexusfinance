# 💼 NexusFinance Backend

<div align="center">

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.2-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-7-DC382D?style=for-the-badge&logo=redis&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Status](https://img.shields.io/badge/Status-Phase_5_Completed-success?style=for-the-badge)

**Plataforma de micro-inversiones con Clean Architecture**

[Características](#-características-principales) •
[Arquitectura](#️-arquitectura) •
[Instalación](#-instalación) •
[API](#-api-endpoints) •
[Roadmap](#-roadmap)

</div>

---

## 📖 Descripción

NexusFinance es una plataforma backend de micro-inversiones construida siguiendo los principios de **Clean Architecture**, **Domain-Driven Design (DDD)** y **SOLID**. El proyecto está diseñado con enfoque en escalabilidad, mantenibilidad y seguridad empresarial.

### 🎯 Última Actualización: Fase 5 Completada

**Sistema de Inversiones implementado:**
- 💰 Lógica completa de inversiones (crear, consultar, cancelar)
- 🔄 Gestión automática de balances de usuarios y productos
- 📊 Cálculo de rendimientos esperados y proporcionales
- ✅ Validaciones robustas de saldo y disponibilidad
- 🎯 Actualización automática de estado de productos (SOLD_OUT)
- 🔐 Endpoints protegidos con JWT

### ✨ Características Principales

- ✅ **Clean Architecture** - Separación clara de responsabilidades en capas
- ✅ **Autenticación JWT** - Sistema de seguridad stateless
- ✅ **Domain-Driven Design** - Modelado basado en el dominio del negocio
- ✅ **Sistema de Inversiones** - Lógica completa de inversiones con cálculo de rendimientos
- ✅ **Gestión de Productos** - CRUD completo con validaciones de negocio
- ✅ **Transacciones Atómicas** - Operaciones financieras seguras
- ✅ **Docker Compose** - Despliegue simplificado con PostgreSQL y Redis
- ✅ **Spring Security 6** - Protección robusta de endpoints
- ✅ **Bean Validation** - Validación de datos de entrada
- ✅ **Auditoría de Entidades** - Timestamps automáticos en todas las entidades

---

## 🛠️ Stack Tecnológico

### Backend Framework
- **Java 21** (Eclipse Temurin)
- **Spring Boot 3.3.2**
  - Spring Web (REST APIs)
  - Spring Data JPA (Persistencia)
  - Spring Security 6 (Autenticación JWT)
  - Spring Validation (Bean Validation)
  - Spring Data Redis (Caché y Rate Limiting)

### Bases de Datos
- **PostgreSQL 16** - Base de datos relacional principal
- **Redis 7** - Caché en memoria y gestión de sesiones

### Seguridad
- **JWT (jjwt 0.12.5)** - JSON Web Tokens para autenticación
- **BCrypt** - Hash seguro de contraseñas

### DevOps
- **Docker & Docker Compose** - Containerización
- **Maven** - Gestión de dependencias y build

---

## 🏗️ Arquitectura

NexusFinance implementa **Clean Architecture** con separación en 4 capas:

```
┌─────────────────────────────────────────────────────────┐
│                    REST API Layer                        │
│            (Controllers - Infrastructure)                │
└──────────────────┬──────────────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────────────┐
│              Application Layer                           │
│         (Use Cases, DTOs, Services)                      │
└──────────────────┬──────────────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────────────┐
│               Domain Layer                               │
│    (Entities, Value Objects, Business Rules)             │
└──────────────────┬──────────────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────────────┐
│            Infrastructure Layer                          │
│  (Persistence, Security, Configuration)                  │
└─────────────────────────────────────────────────────────┘
```

### 📂 Estructura de Capas

#### 1. Domain (Núcleo de negocio)
- `model/` - Entidades del dominio (`User`, `Investment`, `Product`)
- `repository/` - Interfaces de repositorios (contratos)
- `exception/` - Excepciones de dominio

#### 2. Application (Casos de uso)
- `port/in/` - Puertos de entrada (interfaces de casos de uso)
- `service/` - Implementación de casos de uso
- `dto/` - Data Transfer Objects

#### 3. Infrastructure (Detalles técnicos)
- `rest/` - Controladores REST
- `persistence/` - Adaptadores JPA
- `security/` - Configuración de seguridad JWT
- `config/` - Configuración de Spring

---

## 📁 Estructura del Proyecto

```
backend/
├── src/main/java/com/nexusfinance/
│   ├── NexusFinanceApplication.java
│   │
│   ├── application/
│   │   ├── dto/
│   │   │   ├── auth/
│   │   │   │   ├── AuthResponse.java
│   │   │   │   ├── LoginRequest.java
│   │   │   │   └── RegisterRequest.java
│   │   │   ├── product/
│   │   │   │   ├── CreateProductRequest.java
│   │   │   │   ├── UpdateProductRequest.java
│   │   │   │   └── ProductResponse.java
│   │   │   └── investment/
│   │   │       ├── CreateInvestmentRequest.java
│   │   │       └── InvestmentResponse.java
│   │   ├── port/in/
│   │   │   ├── AuthenticationUseCase.java
│   │   │   ├── product/
│   │   │   │   ├── CreateProductUseCase.java
│   │   │   │   ├── GetProductUseCase.java
│   │   │   │   ├── UpdateProductUseCase.java
│   │   │   │   └── DeleteProductUseCase.java
│   │   │   └── investment/
│   │   │       ├── CreateInvestmentUseCase.java
│   │   │       ├── GetInvestmentUseCase.java
│   │   │       └── CancelInvestmentUseCase.java
│   │   └── service/
│   │       ├── AuthenticationService.java
│   │       ├── ProductService.java
│   │       └── InvestmentService.java
│   │
│   ├── domain/
│   │   ├── exception/
│   │   │   ├── DomainException.java
│   │   │   ├── InsufficientBalanceException.java
│   │   │   ├── ProductNotAvailableException.java
│   │   │   └── InvestmentNotCancellableException.java
│   │   ├── model/
│   │   │   ├── BaseEntity.java
│   │   │   ├── User.java
│   │   │   ├── UserRole.java
│   │   │   ├── InvestmentProduct.java
│   │   │   ├── ProductStatus.java
│   │   │   ├── RiskLevel.java
│   │   │   ├── Investment.java
│   │   │   └── InvestmentStatus.java
│   │   └── repository/
│   │       ├── UserRepository.java
│   │       ├── InvestmentProductRepository.java
│   │       └── InvestmentRepository.java
│   │
│   └── infrastructure/
│       ├── config/
│       │   ├── RedisConfig.java
│       │   └── SecurityConfig.java
│       ├── persistence/
│       │   ├── JpaUserRepository.java
│       │   ├── UserRepositoryAdapter.java
│       │   ├── JpaInvestmentProductRepository.java
│       │   ├── InvestmentProductRepositoryAdapter.java
│       │   ├── JpaInvestmentRepository.java
│       │   └── InvestmentRepositoryAdapter.java
│       ├── rest/
│       │   ├── AuthController.java
│       │   ├── HealthController.java
│       │   ├── PublicProductController.java
│       │   ├── AdminProductController.java
│       │   └── InvestmentController.java
│       └── security/
│           ├── CustomUserDetailsService.java
│           ├── JwtAuthenticationFilter.java
│           └── JwtService.java
│
├── src/main/resources/
│   └── application.yml
│
├── Dockerfile
├── pom.xml
└── mvnw
```

---

## 🚀 Instalación

### Requisitos Previos

- Java 21 o superior
- Docker & Docker Compose
- Maven 3.8+

### Variables de Entorno

Crea un archivo `.env` o configura las siguientes variables:

```env
# PostgreSQL
POSTGRES_USER=postgres
POSTGRES_PASSWORD=nexus2026secure
POSTGRES_DB=nexusfinance_db

# Redis
SPRING_DATA_REDIS_HOST=nexusfinance-redis
SPRING_DATA_REDIS_PORT=6379

# JWT
JWT_SECRET=4Kn9x2Wm5Tp8Vq3Yr7Zs1Cb6Fg0Hj4Lm9Pn2Rt5Uv8Xw1Az3Cd6Ef9Gh2Ik5Jl8
JWT_EXPIRATION=86400000  # 24 horas en milisegundos
```

### Pasos de Instalación

#### 1. Clonar el repositorio
```bash
git clone https://github.com/tu-usuario/nexusfinance.git
cd nexusfinance
```

#### 2. Levantar con Docker Compose
```bash
docker-compose up --build
```

La aplicación estará disponible en: `http://localhost:8080`

#### 3. Compilar localmente (opcional)
```bash
cd backend
./mvnw clean package -DskipTests
./mvnw spring-boot:run
```

---

## 📡 API Endpoints

### 📋 Resumen de Endpoints

| Categoría | Método | Endpoint | Autenticación | Descripción |
|-----------|--------|----------|---------------|-------------|
| **Health** | GET | `/health` | ❌ No | Health check del servicio |
| **Auth** | POST | `/api/auth/register` | ❌ No | Registrar nuevo usuario |
| **Auth** | POST | `/api/auth/login` | ❌ No | Login y obtención de JWT |
| **Productos (Público)** | GET | `/api/public/products/available` | ❌ No | Listar productos disponibles |
| **Productos (Público)** | GET | `/api/public/products/{id}` | ❌ No | Obtener producto por ID |
| **Productos (Público)** | GET | `/api/public/products/risk/{level}` | ❌ No | Filtrar por nivel de riesgo |
| **Productos (Admin)** | POST | `/api/admin/products` | ✅ Sí | Crear nuevo producto |
| **Productos (Admin)** | GET | `/api/admin/products` | ✅ Sí | Listar todos los productos |
| **Productos (Admin)** | GET | `/api/admin/products/{id}` | ✅ Sí | Obtener producto por ID |
| **Productos (Admin)** | GET | `/api/admin/products/status/{status}` | ✅ Sí | Filtrar por estado |
| **Productos (Admin)** | PUT | `/api/admin/products/{id}` | ✅ Sí | Actualizar producto |
| **Productos (Admin)** | DELETE | `/api/admin/products/{id}` | ✅ Sí | Eliminar producto |
| **Inversiones** | POST | `/api/investments` | ✅ Sí | Crear nueva inversión |
| **Inversiones** | GET | `/api/investments/{id}` | ✅ Sí | Obtener inversión por ID |
| **Inversiones** | GET | `/api/investments/my-investments` | ✅ Sí | Listar mis inversiones |
| **Inversiones** | GET | `/api/investments/my-investments/active` | ✅ Sí | Listar mis inversiones activas |
| **Inversiones** | POST | `/api/investments/{id}/cancel` | ✅ Sí | Cancelar inversión |

---

### 🔓 Endpoints Públicos (sin autenticación)

#### Health Check

```http
GET /health
```

**Respuesta:**
```json
{
  "status": "UP",
  "timestamp": "2026-01-17T08:00:00Z"
}
```

---

### Autenticación

#### Registrar Usuario

```http
POST /api/auth/register
Content-Type: application/json
```

**Payload:**
```json
{
  "firstName": "Juan",
  "lastName": "Pérez",
  "email": "juan@example.com",
  "password": "password123"
}
```

**Respuesta (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzM4NCJ9...",
  "type": "Bearer",
  "userId": 1,
  "email": "juan@example.com",
  "firstName": "Juan",
  "lastName": "Pérez"
}
```

---

#### Login

```http
POST /api/auth/login
Content-Type: application/json
```

**Payload:**
```json
{
  "email": "juan@example.com",
  "password": "password123"
}
```

**Respuesta (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzM4NCJ9...",
  "type": "Bearer",
  "userId": 1,
  "email": "juan@example.com",
  "firstName": "Juan",
  "lastName": "Pérez"
}
```

---

### Productos de Inversión (Público)

#### Listar Productos Disponibles

```http
GET /api/public/products/available
```

**Respuesta:**
```json
[
  {
    "id": 1,
    "name": "Fondo de Renta Fija",
    "description": "Inversión segura en bonos gubernamentales",
    "status": "ACTIVE",
    "riskLevel": "LOW",
    "annualReturn": 8.5,
    "minimumInvestment": 1000.00,
    "targetAmount": 100000.00,
    "currentAmount": 45000.00,
    "progressPercentage": 45.0,
    "durationDays": 365,
    "closingDate": "2026-12-31",
    "availableForInvestment": true
  }
]
```

#### Obtener Producto por ID

```http
GET /api/public/products/{id}
```

#### Filtrar por Nivel de Riesgo

```http
GET /api/public/products/risk/{riskLevel}
```

**Niveles de riesgo válidos:** `LOW`, `MEDIUM`, `HIGH`

---

### 🔒 Endpoints Protegidos (requieren JWT)

**Header requerido:**
```http
Authorization: Bearer eyJhbGciOiJIUzM4NCJ9...
```

---

### Productos de Inversión (Admin)

#### Crear Producto

```http
POST /api/admin/products
Content-Type: application/json
Authorization: Bearer <TOKEN>
```

**Payload:**
```json
{
  "name": "Fondo de Renta Fija",
  "description": "Inversión segura en bonos gubernamentales",
  "riskLevel": "LOW",
  "annualReturn": 8.5,
  "minimumInvestment": 1000,
  "targetAmount": 100000,
  "durationDays": 365,
  "closingDate": "2026-12-31"
}
```

**Respuesta (201 Created):**
```json
{
  "id": 1,
  "name": "Fondo de Renta Fija",
  "description": "Inversión segura en bonos gubernamentales",
  "status": "ACTIVE",
  "riskLevel": "LOW",
  "annualReturn": 8.5,
  "minimumInvestment": 1000.00,
  "targetAmount": 100000.00,
  "currentAmount": 0.00,
  "progressPercentage": 0.0,
  "durationDays": 365,
  "closingDate": "2026-12-31",
  "availableForInvestment": true
}
```

#### Listar Todos los Productos

```http
GET /api/admin/products
Authorization: Bearer <TOKEN>
```

#### Obtener Producto por ID

```http
GET /api/admin/products/{id}
Authorization: Bearer <TOKEN>
```

#### Filtrar por Estado

```http
GET /api/admin/products/status/{status}
Authorization: Bearer <TOKEN>
```

**Estados válidos:** `ACTIVE`, `INACTIVE`, `COMPLETED`, `CANCELLED`

#### Actualizar Producto

```http
PUT /api/admin/products/{id}
Content-Type: application/json
Authorization: Bearer <TOKEN>
```

**Payload:**
```json
{
  "name": "Fondo de Renta Fija Plus",
  "description": "Inversión segura en bonos gubernamentales con mayor rendimiento",
  "riskLevel": "LOW",
  "annualReturn": 9.0,
  "minimumInvestment": 1500,
  "targetAmount": 150000,
  "durationDays": 365,
  "closingDate": "2026-12-31"
}
```

#### Eliminar Producto

```http
DELETE /api/admin/products/{id}
Authorization: Bearer <TOKEN>
```

**Respuesta (204 No Content)**

---

### Inversiones (Usuario Autenticado)

#### Crear Inversión

```http
POST /api/investments
Content-Type: application/json
Authorization: Bearer <TOKEN>
```

**Payload:**
```json
{
  "productId": 1,
  "amount": 10000
}
```

**Respuesta (201 Created):**
```json
{
  "id": 1,
  "userId": 1,
  "userEmail": "juan@example.com",
  "productId": 1,
  "productName": "Fondo de Renta Fija",
  "amount": 10000.00,
  "expectedReturn": 232.88,
  "actualReturn": null,
  "totalAmount": 10232.88,
  "status": "ACTIVE",
  "startDate": "2026-01-17",
  "maturityDate": "2027-01-17",
  "completionDate": null,
  "canBeCancelled": true,
  "createdAt": "2026-01-17T09:00:00"
}
```

**Validaciones:**
- Usuario debe tener saldo suficiente
- Producto debe estar disponible (ACTIVE y con cupo)
- Monto debe cumplir el mínimo de inversión
- Producto no debe estar vencido

#### Obtener Inversión por ID

```http
GET /api/investments/{id}
Authorization: Bearer <TOKEN>
```

**Respuesta (200 OK):**
```json
{
  "id": 1,
  "userId": 1,
  "userEmail": "juan@example.com",
  "productId": 1,
  "productName": "Fondo de Renta Fija",
  "amount": 10000.00,
  "expectedReturn": 232.88,
  "actualReturn": null,
  "totalAmount": 10232.88,
  "status": "ACTIVE",
  "startDate": "2026-01-17",
  "maturityDate": "2027-01-17",
  "completionDate": null,
  "canBeCancelled": true,
  "createdAt": "2026-01-17T09:00:00"
}
```

#### Listar Mis Inversiones

```http
GET /api/investments/my-investments
Authorization: Bearer <TOKEN>
```

**Respuesta (200 OK):**
```json
[
  {
    "id": 1,
    "userId": 1,
    "userEmail": "juan@example.com",
    "productId": 1,
    "productName": "Fondo de Renta Fija",
    "amount": 10000.00,
    "expectedReturn": 232.88,
    "actualReturn": null,
    "totalAmount": 10232.88,
    "status": "ACTIVE",
    "startDate": "2026-01-17",
    "maturityDate": "2027-01-17",
    "completionDate": null,
    "canBeCancelled": true,
    "createdAt": "2026-01-17T09:00:00"
  }
]
```

#### Listar Mis Inversiones Activas

```http
GET /api/investments/my-investments/active
Authorization: Bearer <TOKEN>
```

#### Cancelar Inversión

```http
POST /api/investments/{id}/cancel
Authorization: Bearer <TOKEN>
```

**Respuesta (200 OK):**
```json
{
  "id": 1,
  "userId": 1,
  "userEmail": "juan@example.com",
  "productId": 1,
  "productName": "Fondo de Renta Fija",
  "amount": 10000.00,
  "expectedReturn": 232.88,
  "actualReturn": 116.44,
  "totalAmount": 10116.44,
  "status": "CANCELLED",
  "startDate": "2026-01-17",
  "maturityDate": "2027-01-17",
  "completionDate": "2026-07-17",
  "canBeCancelled": false,
  "createdAt": "2026-01-17T09:00:00"
}
```

**Lógica de cancelación:**
- Solo inversiones ACTIVE pueden cancelarse
- Se calcula rendimiento proporcional según días transcurridos
- Se devuelve: capital + rendimiento proporcional
- Se actualiza balance del usuario
- Se actualiza currentAmount del producto

---

## 📝 Ejemplos de Uso con cURL

### 1. Registrar Usuario

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Juan",
    "lastName": "Pérez",
    "email": "juan@example.com",
    "password": "password123"
  }'
```

### 2. Login y Obtener Token

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "juan@example.com",
    "password": "password123"
  }'
```

### 3. Crear Producto de Inversión

```bash
curl -X POST http://localhost:8080/api/admin/products \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <TOKEN>" \
  -d '{
    "name": "Fondo de Renta Fija",
    "description": "Inversión segura en bonos gubernamentales",
    "riskLevel": "LOW",
    "annualReturn": 8.5,
    "minimumInvestment": 1000,
    "targetAmount": 100000,
    "durationDays": 365,
    "closingDate": "2026-12-31"
  }'
```

### 4. Listar Productos Disponibles (sin token)

```bash
curl http://localhost:8080/api/public/products/available
```

### 5. Filtrar por Nivel de Riesgo

```bash
curl http://localhost:8080/api/public/products/risk/LOW
```

### 6. Actualizar Producto

```bash
curl -X PUT http://localhost:8080/api/admin/products/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <TOKEN>" \
  -d '{
    "name": "Fondo de Renta Fija Plus",
    "description": "Inversión actualizada",
    "riskLevel": "LOW",
    "annualReturn": 9.0,
    "minimumInvestment": 1500,
    "targetAmount": 150000,
    "durationDays": 365,
    "closingDate": "2026-12-31"
  }'
```

### 7. Eliminar Producto

```bash
curl -X DELETE http://localhost:8080/api/admin/products/1 \
  -H "Authorization: Bearer <TOKEN>"
```

### 8. Crear Inversión

```bash
curl -X POST http://localhost:8080/api/investments \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <TOKEN>" \
  -d '{
    "productId": 1,
    "amount": 10000
  }'
```

### 9. Listar Mis Inversiones

```bash
curl http://localhost:8080/api/investments/my-investments \
  -H "Authorization: Bearer <TOKEN>"
```

### 10. Listar Mis Inversiones Activas

```bash
curl http://localhost:8080/api/investments/my-investments/active \
  -H "Authorization: Bearer <TOKEN>"
```

### 11. Cancelar Inversión

```bash
curl -X POST http://localhost:8080/api/investments/1/cancel \
  -H "Authorization: Bearer <TOKEN>"
```

---

## 🚀 Flujo Completo de Inversión

### Ejemplo de caso de uso end-to-end:

```bash
# 1. Registrar usuario
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Juan",
    "lastName": "Pérez",
    "email": "juan@example.com",
    "password": "password123"
  }'

# 2. Login y obtener token
TOKEN=$(curl -s -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "juan@example.com",
    "password": "password123"
  }' | jq -r '.token')

# 3. Dar balance inicial al usuario (temporal - vía DB)
docker exec -it nexusfinance-postgres psql -U postgres -d nexusfinance_db \
  -c "UPDATE users SET balance = 50000.00 WHERE email = 'juan@example.com';"

# 4. Ver productos disponibles
curl http://localhost:8080/api/public/products/available | jq

# 5. Crear inversión
curl -X POST http://localhost:8080/api/investments \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "productId": 1,
    "amount": 10000
  }' | jq

# 6. Ver mis inversiones
curl http://localhost:8080/api/investments/my-investments \
  -H "Authorization: Bearer $TOKEN" | jq

# 7. (Opcional) Cancelar inversión después de un tiempo
curl -X POST http://localhost:8080/api/investments/1/cancel \
  -H "Authorization: Bearer $TOKEN" | jq
```

---

## 🗄️ Modelo de Datos

### Tabla `users`

```sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    is_active BOOLEAN NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL CHECK (role IN ('INVESTOR', 'ADMIN', 'ANALYST')),
    balance NUMERIC(15,2) NOT NULL DEFAULT 0,
    email_verified BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE UNIQUE INDEX idx_user_email ON users(email);
```

### Tabla `investment_products`

```sql
CREATE TABLE investment_products (
    id BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    is_active BOOLEAN NOT NULL,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (status IN ('ACTIVE', 'INACTIVE', 'COMPLETED', 'CANCELLED')),
    risk_level VARCHAR(20) NOT NULL CHECK (risk_level IN ('LOW', 'MEDIUM', 'HIGH')),
    annual_return NUMERIC(5,2) NOT NULL,
    minimum_investment NUMERIC(15,2) NOT NULL,
    target_amount NUMERIC(15,2) NOT NULL,
    current_amount NUMERIC(15,2) NOT NULL DEFAULT 0,
    duration_days INTEGER NOT NULL,
    closing_date DATE NOT NULL
);

CREATE INDEX idx_product_status ON investment_products(status);
CREATE INDEX idx_product_risk_level ON investment_products(risk_level);
CREATE INDEX idx_product_closing_date ON investment_products(closing_date);
```

### Tabla `investments`

```sql
CREATE TABLE investments (
    id BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    is_active BOOLEAN NOT NULL,
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    amount NUMERIC(15,2) NOT NULL,
    expected_return NUMERIC(15,2) NOT NULL,
    actual_return NUMERIC(15,2),
    status VARCHAR(20) NOT NULL CHECK (status IN ('ACTIVE', 'COMPLETED', 'CANCELLED', 'EXPIRED')),
    start_date DATE NOT NULL,
    maturity_date DATE NOT NULL,
    completion_date DATE,
    CONSTRAINT fk_investment_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_investment_product FOREIGN KEY (product_id) REFERENCES investment_products(id)
);

CREATE INDEX idx_investment_user ON investments(user_id);
CREATE INDEX idx_investment_product ON investments(product_id);
CREATE INDEX idx_investment_status ON investments(status);
CREATE INDEX idx_investment_maturity_date ON investments(maturity_date);
```

### Enums del Sistema

#### ProductStatus
- `ACTIVE` - Producto activo y disponible para inversión
- `INACTIVE` - Producto temporalmente inactivo
- `COMPLETED` - Producto que alcanzó su objetivo
- `CANCELLED` - Producto cancelado

#### RiskLevel
- `LOW` - Bajo riesgo (≤ 10% retorno anual)
- `MEDIUM` - Riesgo medio (11-20% retorno anual)
- `HIGH` - Alto riesgo (> 20% retorno anual)

#### UserRole
- `INVESTOR` - Usuario inversor estándar
- `ADMIN` - Administrador del sistema
- `ANALYST` - Analista financiero

#### InvestmentStatus
- `ACTIVE` - Inversión activa y vigente
- `COMPLETED` - Inversión que alcanzó su madurez
- `CANCELLED` - Inversión cancelada antes de tiempo
- `EXPIRED` - Inversión vencida sin completar

---

## ⚙️ Lógica de Negocio

### Validaciones de Productos de Inversión

#### En el Dominio (`InvestmentProduct`)
- **`isAvailableForInvestment()`**: Verifica si un producto está disponible para recibir inversiones
  - Estado debe ser `ACTIVE`
  - `currentAmount` < `targetAmount`
  - Fecha de cierre no debe haber pasado

- **`getProgressPercentage()`**: Calcula el porcentaje de completitud del producto
  - Retorna: `(currentAmount / targetAmount) * 100`

#### Validaciones en DTOs (Bean Validation)

**CreateProductRequest / UpdateProductRequest:**
- `name`: Requerido, entre 3-200 caracteres
- `description`: Requerido, entre 10-1000 caracteres
- `riskLevel`: Debe ser LOW, MEDIUM o HIGH
- `annualReturn`: Debe ser > 0 y ≤ 100
- `minimumInvestment`: Debe ser ≥ 100
- `targetAmount`: Debe ser ≥ 1000
- `durationDays`: Debe ser ≥ 30
- `closingDate`: Debe ser fecha futura

---

### Sistema de Inversiones

#### Cálculo de Rendimientos

**Rendimiento Esperado:**
```
expectedReturn = amount × (annualReturn / 365) × durationDays
```

**Ejemplo:**
- Inversión: $10,000
- Retorno anual: 8.5%
- Duración: 365 días
- Rendimiento: $10,000 × (8.5 / 365) × 365 = $850

**Rendimiento Proporcional (al cancelar):**
```
proportionalReturn = expectedReturn × (daysElapsed / totalDurationDays)
```

**Ejemplo de cancelación:**
- Rendimiento esperado: $850
- Días transcurridos: 180 de 365
- Rendimiento proporcional: $850 × (180 / 365) = $419.18

#### Métodos del Dominio (`Investment`)

- **`calculateExpectedReturn()`**: Calcula el rendimiento esperado al vencimiento
- **`calculateProportionalReturn()`**: Calcula rendimiento proporcional según días transcurridos
- **`canBeCancelled()`**: Verifica si la inversión puede ser cancelada (solo ACTIVE)

#### Validaciones de Inversión

**Al crear inversión:**
- Usuario debe tener saldo suficiente (balance ≥ amount)
- Producto debe estar disponible (`isAvailableForInvestment()`)
- Monto debe cumplir el mínimo de inversión del producto
- Producto no debe estar vencido (closingDate > hoy)
- Producto debe tener cupo disponible (currentAmount + amount ≤ targetAmount)

**Al cancelar inversión:**
- Solo inversiones con estado `ACTIVE` pueden cancelarse
- No se pueden cancelar inversiones `COMPLETED`, `CANCELLED` o `EXPIRED`

#### Actualizaciones Automáticas

**Al crear inversión:**
1. Se descuenta el monto del balance del usuario
2. Se incrementa el `currentAmount` del producto
3. Si `currentAmount` alcanza `targetAmount`, el producto cambia a estado `SOLD_OUT`

**Al cancelar inversión:**
1. Se calcula el rendimiento proporcional
2. Se devuelve al usuario: capital + rendimiento proporcional
3. Se reduce el `currentAmount` del producto
4. Si el producto estaba `SOLD_OUT`, vuelve a `ACTIVE`
5. Se marca la inversión como `CANCELLED` con `completionDate` = hoy

#### Transacciones Atómicas

Todas las operaciones financieras se ejecutan con `@Transactional` para garantizar:
- Atomicidad: Todo se ejecuta o nada
- Consistencia: Los datos quedan en estado válido
- Aislamiento: Las operaciones concurrentes no interfieren
- Durabilidad: Los cambios se persisten correctamente

---

## 🔒 Seguridad

### Características Implementadas

- ✅ Autenticación JWT stateless
- ✅ Contraseñas hasheadas con BCrypt (factor 10)
- ✅ CSRF deshabilitado (API REST)
- ✅ Session management: STATELESS
- ✅ Endpoints públicos: `/api/auth/**`, `/api/public/**`, `/health`
- ✅ Endpoints protegidos: `/api/admin/**`, `/api/investments/**`
- ✅ Validación de entrada con Bean Validation
- ✅ Control de acceso basado en roles (RBAC)
- ✅ Transacciones atómicas para operaciones financieras
- ✅ Manejo de excepciones personalizado

### Excepciones de Dominio

El sistema implementa excepciones específicas para cada caso de negocio:

- `InsufficientBalanceException` - Saldo insuficiente para invertir
- `ProductNotAvailableException` - Producto no disponible para inversión
- `InvestmentNotCancellableException` - Inversión no puede ser cancelada
- `ResourceNotFoundException` - Recurso no encontrado
- `DomainException` - Excepción base del dominio

### Buenas Prácticas

- Inyección de dependencias por constructor
- Secrets en variables de entorno
- Token expiration configurable (24 horas por defecto)
- Filtros de autenticación personalizados

---

## ✅ Estado del Proyecto

### Fases Completadas

#### ✅ Fase 1: Setup Inicial
- Configuración de Clean Architecture
- Docker Compose (PostgreSQL + Redis)
- Configuración de Spring Boot
- Health Check endpoint

#### ✅ Fase 2: Entidades Core
- Modelo de dominio `User`
- Repositorios JPA
- BaseEntity con auditoría

#### ✅ Fase 3: Autenticación JWT
- Registro de usuarios (`/api/auth/register`)
- Login con JWT (`/api/auth/login`)
- JwtService (generación y validación de tokens)
- JwtAuthenticationFilter
- CustomUserDetailsService
- SecurityConfig (Spring Security 6)
- Password encoding con BCrypt

#### ✅ Fase 4: CRUD de Productos de Inversión
- Entidad `InvestmentProduct` con lógica de dominio
- Enums: `ProductStatus`, `RiskLevel`
- Repositorios y adaptadores
- DTOs: `CreateProductRequest`, `UpdateProductRequest`, `ProductResponse`
- Casos de uso completos (CRUD + filtros)
- Endpoints públicos (`/api/public/products/*`)
- Endpoints de administración (`/api/admin/products/*`)
- Validaciones de negocio
- Métodos de dominio: `isAvailableForInvestment()`, `getProgressPercentage()`

#### ✅ Fase 5: Sistema de Inversiones
- Entidad `Investment` con lógica de dominio
- Enum `InvestmentStatus` (ACTIVE, COMPLETED, CANCELLED, EXPIRED)
- Relaciones bidireccionales: User ↔ Investment ↔ InvestmentProduct
- Repositorios y adaptadores
- DTOs: `CreateInvestmentRequest`, `InvestmentResponse`
- Casos de uso de inversión (crear, consultar, cancelar)
- Validación de saldo del usuario
- Validación de disponibilidad del producto
- Actualización automática de balances (user.balance y product.currentAmount)
- Cálculo de rendimientos esperados (`calculateExpectedReturn()`)
- Cancelación con rendimiento proporcional (`calculateProportionalReturn()`)
- Endpoints protegidos (`/api/investments/*`)
- Métodos de dominio: `canBeCancelled()`, gestión de estado del producto (SOLD_OUT)
- Transacciones atómicas con `@Transactional`
- Excepciones de dominio personalizadas

---

## 🚧 Roadmap

### Fase 6: Transacciones y Balance
- Entidad `Transaction`
- Depósitos y retiros
- Historial de transacciones
- Balance en tiempo real

### Fase 7: Rate Limiting y Caché
- Rate Limiting con Redis
- Caché de productos
- Optimización de consultas

### Fase 8: Testing
- Unit Tests (JUnit 5)
- Integration Tests
- Tests de seguridad

### Fase 9: CI/CD
- GitHub Actions
- Despliegue automatizado

---

## 🐳 Comandos Docker

### Levantar servicios
```bash
docker-compose up -d
```

### Ver logs
```bash
docker-compose logs -f backend
```

### Detener servicios
```bash
docker-compose down
```

### Rebuild completo (limpiar caché)
```bash
docker-compose down --rmi all --volumes
docker-compose up --build
```

### Conectar a PostgreSQL
```bash
docker exec -it nexusfinance-postgres psql -U postgres -d nexusfinance_db
```

### Conectar a Redis
```bash
docker exec -it nexusfinance-redis redis-cli
```

### Consultas SQL Útiles

```sql
-- Ver todos los usuarios con sus balances
SELECT id, first_name, last_name, email, balance, created_at 
FROM users 
ORDER BY created_at DESC;

-- Ver productos de inversión con su progreso
SELECT 
    id, 
    name, 
    status, 
    risk_level,
    annual_return,
    current_amount,
    target_amount,
    ROUND((current_amount / target_amount * 100), 2) as progress_percentage,
    closing_date
FROM investment_products 
ORDER BY created_at DESC;

-- Ver inversiones con información del usuario y producto
SELECT 
    i.id,
    u.email as investor_email,
    p.name as product_name,
    i.amount,
    i.expected_return,
    i.actual_return,
    i.status,
    i.start_date,
    i.maturity_date,
    i.completion_date
FROM investments i
JOIN users u ON i.user_id = u.id
JOIN investment_products p ON i.product_id = p.id
ORDER BY i.created_at DESC;

-- Ver total invertido por usuario
SELECT 
    u.email,
    COUNT(i.id) as total_investments,
    SUM(i.amount) as total_invested,
    SUM(CASE WHEN i.status = 'ACTIVE' THEN i.amount ELSE 0 END) as active_amount
FROM users u
LEFT JOIN investments i ON u.id = i.user_id
GROUP BY u.email
ORDER BY total_invested DESC;

-- Actualizar balance de un usuario (para testing)
UPDATE users SET balance = 50000.00 WHERE email = 'juan@example.com';
```

---

## 🧪 Testing

### Ejecutar tests
```bash
./mvnw test
```

### Test de cobertura
```bash
./mvnw verify
```

---

## 📊 Logs y Monitoreo

### Configuración de Logs

```yaml
logging:
  level:
    com.nexusfinance: DEBUG
    org.springframework.security: DEBUG
    org.hibernate.SQL: DEBUG
```

---

## 🤝 Contribución

Las contribuciones son bienvenidas. Por favor, sigue estos pasos:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -m 'feat: agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

### Convenciones de Commits (Conventional Commits)

- `feat:` - Nueva funcionalidad
- `fix:` - Corrección de bugs
- `docs:` - Documentación
- `refactor:` - Refactorización
- `test:` - Tests
- `chore:` - Tareas de mantenimiento

---

## 📄 Licencia

Este proyecto está bajo la Licencia MIT.

---

## 👨‍💻 Autor

**Norberto Velez**

Desarrollado con ❤️ utilizando Clean Architecture, DDD y principios SOLID.

---

<div align="center">

**[⬆ Volver arriba](#-nexusfinance-backend)**

</div>
