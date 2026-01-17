# 💼 NexusFinance Backend

<div align="center">

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.2-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-7-DC382D?style=for-the-badge&logo=redis&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

**Plataforma de micro-inversiones con Clean Architecture**

[Características](#-características) •
[Arquitectura](#️-arquitectura) •
[Instalación](#-instalación) •
[API](#-api-endpoints) •
[Roadmap](#-roadmap)

</div>

---

## 📖 Descripción

NexusFinance es una plataforma backend de micro-inversiones construida siguiendo los principios de **Clean Architecture**, **Domain-Driven Design (DDD)** y **SOLID**. El proyecto está diseñado con enfoque en escalabilidad, mantenibilidad y seguridad empresarial.

### ✨ Características Principales

- ✅ **Clean Architecture** - Separación clara de responsabilidades en capas
- ✅ **Autenticación JWT** - Sistema de seguridad stateless
- ✅ **Domain-Driven Design** - Modelado basado en el dominio del negocio
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
│   │   │   ├── AuthResponse.java
│   │   │   ├── LoginRequest.java
│   │   │   └── RegisterRequest.java
│   │   ├── port/in/
│   │   │   └── AuthenticationUseCase.java
│   │   └── service/
│   │       └── AuthenticationService.java
│   │
│   ├── domain/
│   │   ├── exception/
│   │   │   └── DomainException.java
│   │   ├── model/
│   │   │   ├── BaseEntity.java
│   │   │   ├── User.java
│   │   │   └── UserRole.java
│   │   └── repository/
│   │       └── UserRepository.java
│   │
│   └── infrastructure/
│       ├── config/
│       │   ├── RedisConfig.java
│       │   └── SecurityConfig.java
│       ├── persistence/
│       │   ├── JpaUserRepository.java
│       │   └── UserRepositoryAdapter.java
│       ├── rest/
│       │   ├── AuthController.java
│       │   └── HealthController.java
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

### Health Check

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

#### Usar el Token JWT

Para endpoints protegidos, incluye el token en el header `Authorization`:

```http
GET /api/protected-endpoint
Authorization: Bearer eyJhbGciOiJIUzM4NCJ9...
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

---

## 🔒 Seguridad

### Características Implementadas

- ✅ Autenticación JWT stateless
- ✅ Contraseñas hasheadas con BCrypt (factor 10)
- ✅ CSRF deshabilitado (API REST)
- ✅ Session management: STATELESS
- ✅ Endpoints públicos: `/api/auth/**`, `/health`
- ✅ Validación de entrada con Bean Validation

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

---

## 🚧 Roadmap

### Fase 4: CRUD de Productos de Inversión
- Entidad `InvestmentProduct`
- CRUD completo (Admin)
- Endpoints públicos para listar productos
- Validaciones de negocio

### Fase 5: Sistema de Inversiones
- Entidad `Investment`
- Lógica de inversión (crear, cancelar)
- Validación de saldo
- Cálculo de rendimientos

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

Desarrollado con ❤️ para la comunidad de desarrolladores Java/Spring Boot.

---

<div align="center">

**[⬆ Volver arriba](#-nexusfinance-backend)**

</div>
