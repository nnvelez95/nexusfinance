package com.nexusfinance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Clase principal de NexusFinance.
 * Microinversiones con arquitectura hexagonal.
 *
 * @author Aether
 * @version 1.0.0
 * @since 2026-01-17
 */
@SpringBootApplication
@EnableJpaAuditing  // Habilita auditoría automática (createdAt, updatedAt)
@EnableCaching      // Habilita Redis caching
public class NexusFinanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NexusFinanceApplication.class, args);
    }
}
