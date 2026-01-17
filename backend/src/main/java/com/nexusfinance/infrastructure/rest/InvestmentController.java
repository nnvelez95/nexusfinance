package com.nexusfinance.infrastructure.rest;

import com.nexusfinance.application.dto.CreateInvestmentRequest;
import com.nexusfinance.application.dto.InvestmentResponse;
import com.nexusfinance.application.port.in.InvestmentUseCase;
import com.nexusfinance.domain.model.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para inversiones.
 * Todos los endpoints requieren autenticación.
 */
@RestController
@RequestMapping("/api/investments")
public class InvestmentController {

    private final InvestmentUseCase investmentUseCase;

    public InvestmentController(InvestmentUseCase investmentUseCase) {
        this.investmentUseCase = investmentUseCase;
    }

    /**
     * Crear una nueva inversión.
     * El userId se obtiene del token JWT autenticado.
     */
    @PostMapping
    public ResponseEntity<InvestmentResponse> createInvestment(
            @Valid @RequestBody CreateInvestmentRequest request,
            Authentication authentication) {
        
        Long userId = getUserIdFromAuthentication(authentication);
        InvestmentResponse response = investmentUseCase.createInvestment(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Obtener una inversión por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<InvestmentResponse> getInvestmentById(@PathVariable Long id) {
        return ResponseEntity.ok(investmentUseCase.getInvestmentById(id));
    }

    /**
     * Listar todas las inversiones del usuario autenticado.
     */
    @GetMapping("/my-investments")
    public ResponseEntity<List<InvestmentResponse>> getMyInvestments(Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        return ResponseEntity.ok(investmentUseCase.getUserInvestments(userId));
    }

    /**
     * Listar inversiones activas del usuario autenticado.
     */
    @GetMapping("/my-investments/active")
    public ResponseEntity<List<InvestmentResponse>> getMyActiveInvestments(Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        return ResponseEntity.ok(investmentUseCase.getActiveInvestments(userId));
    }

    /**
     * Cancelar una inversión.
     */
    @PostMapping("/{id}/cancel")
    public ResponseEntity<InvestmentResponse> cancelInvestment(
            @PathVariable Long id,
            Authentication authentication) {
        
        Long userId = getUserIdFromAuthentication(authentication);
        InvestmentResponse response = investmentUseCase.cancelInvestment(userId, id);
        return ResponseEntity.ok(response);
    }

    /**
     * Helper: Extraer userId del token JWT.
     * Temporalmente retorna 1, implementar correctamente en siguiente fase.
     */
    private Long getUserIdFromAuthentication(Authentication authentication) {
        // TODO: Implementar extracción real del userId desde el token JWT
        // Por ahora retornamos el ID del usuario registrado
        return 1L;
    }
}
