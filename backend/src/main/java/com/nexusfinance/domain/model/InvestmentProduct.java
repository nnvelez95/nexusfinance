package com.nexusfinance.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidad de dominio: Producto de inversión.
 * 
 * Reglas de negocio:
 * - El rendimiento anual debe estar entre 0% y 100%
 * - La inversión mínima debe ser > 0
 * - El monto objetivo debe ser >= inversión mínima
 * - La fecha de cierre debe ser posterior a la fecha actual
 * 
 * Complejidad de validación: O(1)
 */
@Entity
@Table(name = "investment_products", indexes = {
        @Index(name = "idx_product_status", columnList = "status"),
        @Index(name = "idx_product_risk", columnList = "riskLevel")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvestmentProduct extends BaseEntity {

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private ProductStatus status = ProductStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RiskLevel riskLevel;

    /**
     * Rendimiento anual esperado (porcentaje).
     * Ejemplo: 8.5 representa 8.5% anual
     */
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal annualReturn;

    /**
     * Inversión mínima requerida.
     */
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal minimumInvestment;

    /**
     * Monto objetivo a recaudar.
     */
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal targetAmount;

    /**
     * Monto actualmente recaudado.
     */
    @Column(nullable = false, precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal currentAmount = BigDecimal.ZERO;

    /**
     * Duración en días del producto.
     */
    @Column(nullable = false)
    private Integer durationDays;

    /**
     * Fecha de cierre del producto.
     */
    @Column(nullable = false)
    private LocalDate closingDate;

    /**
     * Método de dominio: Validar si el producto está disponible para inversión.
     * @return true si está activo y no está agotado
     */
    public boolean isAvailableForInvestment() {
        return this.status == ProductStatus.ACTIVE 
                && this.currentAmount.compareTo(this.targetAmount) < 0
                && LocalDate.now().isBefore(this.closingDate)
                && this.isActive;
    }

    /**
     * Método de dominio: Calcular el porcentaje de progreso.
     * @return porcentaje de 0 a 100
     */
    public BigDecimal getProgressPercentage() {
        if (this.targetAmount.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return this.currentAmount
                .multiply(BigDecimal.valueOf(100))
                .divide(this.targetAmount, 2, java.math.RoundingMode.HALF_UP);
    }

    /**
     * Método de dominio: Validar si una inversión es válida.
     * @param amount Monto a invertir
     * @return true si cumple con el mínimo y no excede el objetivo
     */
    public boolean isValidInvestmentAmount(BigDecimal amount) {
        if (amount.compareTo(this.minimumInvestment) < 0) {
            return false;
        }
        BigDecimal newTotal = this.currentAmount.add(amount);
        return newTotal.compareTo(this.targetAmount) <= 0;
    }

    /**
     * Método de dominio: Registrar una nueva inversión.
     * @param amount Monto invertido
     */
    public void addInvestment(BigDecimal amount) {
        this.currentAmount = this.currentAmount.add(amount);
        
        // Auto-cerrar si alcanzó el objetivo
        if (this.currentAmount.compareTo(this.targetAmount) >= 0) {
            this.status = ProductStatus.SOLD_OUT;
        }
    }
}
