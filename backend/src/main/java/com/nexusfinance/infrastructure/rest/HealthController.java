package com.nexusfinance.infrastructure.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Endpoint de verificación de salud del sistema.
 * Verifica conectividad con PostgreSQL y Redis.
 *
 * URL: GET /api/health
 * Respuesta: 200 OK si todo está operativo.
 */
@RestController
@RequestMapping("/api/health")
public class HealthController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * Health check endpoint.
     * @return Estado de servicios (DB, Redis, timestamp).
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("timestamp", LocalDateTime.now());
        health.put("service", "NexusFinance API");

        // Verificar PostgreSQL
        try (Connection connection = dataSource.getConnection()) {
            health.put("database", connection.isValid(2) ? "CONNECTED" : "DISCONNECTED");
        } catch (Exception e) {
            health.put("database", "ERROR: " + e.getMessage());
        }

        // Verificar Redis
        try {
            redisTemplate.opsForValue().set("health_check", "ok");
            String value = redisTemplate.opsForValue().get("health_check");
            health.put("redis", "ok".equals(value) ? "CONNECTED" : "DISCONNECTED");
        } catch (Exception e) {
            health.put("redis", "ERROR: " + e.getMessage());
        }

        return ResponseEntity.ok(health);
    }
}
