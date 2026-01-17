package com.nexusfinance.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * Entidad de dominio: Usuario de la plataforma.
 * Representa inversores con sus credenciales y balance.
 *
 * Reglas de negocio:
 * - Email único (constraint a nivel DB)
 * - Balance inicial = 0
 * - Password debe hashearse antes de persistir
 *
 * Complejidad de validación: O(1)
 */
@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_user_email", columnList = "email", unique = true)
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false)
    private String password;  // Hasheado con BCrypt

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserRole role = UserRole.INVESTOR;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(name = "email_verified", nullable = false)
    private Boolean emailVerified = false;

    /**
     * Método de dominio: Validar si el usuario puede invertir.
     * @param amount Monto a invertir
     * @return true si tiene saldo suficiente
     */
    public boolean canInvest(BigDecimal amount) {
        return this.balance.compareTo(amount) >= 0 && this.isActive && this.emailVerified;
    }

    /**
     * Método de dominio: Actualizar balance tras inversión.
     * @param amount Monto (positivo=depósito, negativo=retiro)
     */
    public void updateBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }
}
