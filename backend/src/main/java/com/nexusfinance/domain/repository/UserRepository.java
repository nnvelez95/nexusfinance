package com.nexusfinance.domain.repository;

import com.nexusfinance.domain.model.User;

import java.util.Optional;

/**
 * Puerto de salida (Output Port) para persistencia de usuarios.
 * Esta interfaz pertenece al DOMINIO (sin dependencias de Spring Data).
 *
 * La implementación JPA estará en infrastructure/persistence.
 */
public interface UserRepository {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    User save(User user);

    boolean existsByEmail(String email);

    void deleteById(Long id);
}
