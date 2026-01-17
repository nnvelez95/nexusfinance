package com.nexusfinance.infrastructure.persistence;

import com.nexusfinance.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Adaptador JPA para UserRepository.
 * Spring Data genera automáticamente la implementación.
 *
 * NOTA: Esta interfaz extiende JpaRepository (infraestructura),
 * pero la capa de aplicación solo conoce UserRepository (dominio).
 */
@Repository
public interface JpaUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
