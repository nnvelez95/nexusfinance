package com.nexusfinance.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Entidad de dominio: Inversión realizada por un usuario.
 * 
 * Reglas de negocio:
 * - El monto debe ser >= producto.minimumInvestment
 * - El usuario debe tener saldo suficiente
 * - Solo se puede cancelar si no ha vencido
 * - Los rendimientos se calculan proporcionalmente
 * 
 * Complejidad de cálculo: O(1)
 */
@Entity
@Table(name = "investments", indexes = {
        @Index(name = "idx_investment_user", columnList = "user_id"),
        @Index(name = "idx_investment_product", columnList = "product_id"),
        @Index(name = "idx_investment_status", columnList = "status")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Investment extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private InvestmentProduct product;

    /**
     * Monto invertido.
     */
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    /**
     * Rendimiento esperado al vencimiento.
     */
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal expectedReturn;

    /**
     * Rendimiento real obtenido (al completarse o cancelarse).
     */
    @Column(precision = 15, scale = 2)
    private BigDecimal actualReturn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private InvestmentStatus status = InvestmentStatus.ACTIVE;

    /**
     * Fecha de inicio de la inversión.
     */
    @Column(nullable = false)
    private LocalDate startDate;

    /**
     * Fecha de vencimiento (calculada).
     */
    @Column(nullable = false)
    private LocalDate maturityDate;

    /**
     * Fecha real de finalización (cuando se completa o cancela).
     */
    @Column
    private LocalDate completionDate;

    /**
     * Método de dominio: Calcular rendimiento esperado.
     * @return rendimiento total esperado
     */
    public BigDecimal calculateExpectedReturn() {
        // Rendimiento anual del producto
        BigDecimal annualReturnRate = product.getAnnualReturn().divide(BigDecimal.valueOf(100), 4, java.math.RoundingMode.HALF_UP);
        
        // Días de la inversión
        long daysInvested = product.getDurationDays();
        
        // Cálculo proporcional: amount * (annualReturn / 365) * days
        BigDecimal dailyRate = annualReturnRate.divide(BigDecimal.valueOf(365), 6, java.math.RoundingMode.HALF_UP);
        BigDecimal totalReturn = amount.multiply(dailyRate).multiply(BigDecimal.valueOf(daysInvested));
        
        return totalReturn.setScale(2, java.math.RoundingMode.HALF_UP);
    }

    /**
     * Método de dominio: Calcular rendimiento proporcional al cancelar.
     * @return rendimiento proporcional a los días transcurridos
     */
    public BigDecimal calculateProportionalReturn() {
        long totalDays = ChronoUnit.DAYS.between(startDate, maturityDate);
        long daysElapsed = ChronoUnit.DAYS.between(startDate, LocalDate.now());
        
        if (daysElapsed <= 0 || totalDays <= 0) {
            return BigDecimal.ZERO;
        }
        
        // Rendimiento proporcional
        BigDecimal proportion = BigDecimal.valueOf(daysElapsed)
                .divide(BigDecimal.valueOf(totalDays), 4, java.math.RoundingMode.HALF_UP);
        
        return expectedReturn.multiply(proportion).setScale(2, java.math.RoundingMode.HALF_UP);
    }

    /**
     * Método de dominio: Calcular monto total a recibir (inversión + rendimiento).
     * @return monto total
     */
    public BigDecimal getTotalAmount() {
        BigDecimal returnAmount = actualReturn != null ? actualReturn : expectedReturn;
        return amount.add(returnAmount);
    }

    /**
     * Método de dominio: Validar si la inversión puede ser cancelada.
     * @return true si está activa y no ha vencido
     */
    public boolean canBeCancelled() {
        return this.status == InvestmentStatus.ACTIVE 
                && LocalDate.now().isBefore(this.maturityDate)
                && this.isActive;
    }

    /**
     * Método de dominio: Cancelar la inversión.
     */
    public void cancel() {
        if (!canBeCancelled()) {
            throw new RuntimeException("Investment cannot be cancelled");
        }
        
        this.status = InvestmentStatus.CANCELLED;
        this.completionDate = LocalDate.now();
        this.actualReturn = calculateProportionalReturn();
    }

    /**
     * Método de dominio: Completar la inversión (al vencimiento).
     */
    public void complete() {
        this.status = InvestmentStatus.COMPLETED;
        this.completionDate = LocalDate.now();
        this.actualReturn = this.expectedReturn;
    }

    /**
     * Método de dominio: Marcar como expirada.
     */
    public void expire() {
        this.status = InvestmentStatus.EXPIRED;
        this.completionDate = LocalDate.now();
        this.actualReturn = this.expectedReturn;
    }
}
