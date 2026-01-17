package com.nexusfinance.application.port.in;

import com.nexusfinance.application.dto.CreateInvestmentRequest;
import com.nexusfinance.application.dto.InvestmentResponse;

import java.util.List;

/**
 * Puerto de entrada para casos de uso de Investment.
 */
public interface InvestmentUseCase {
    
    /**
     * Crear una nueva inversión.
     * Valida saldo del usuario y disponibilidad del producto.
     */
    InvestmentResponse createInvestment(Long userId, CreateInvestmentRequest request);
    
    /**
     * Obtener una inversión por ID.
     */
    InvestmentResponse getInvestmentById(Long id);
    
    /**
     * Listar todas las inversiones de un usuario.
     */
    List<InvestmentResponse> getUserInvestments(Long userId);
    
    /**
     * Listar inversiones activas de un usuario.
     */
    List<InvestmentResponse> getActiveInvestments(Long userId);
    
    /**
     * Cancelar una inversión.
     * Devuelve el capital + rendimiento proporcional.
     */
    InvestmentResponse cancelInvestment(Long userId, Long investmentId);
}
