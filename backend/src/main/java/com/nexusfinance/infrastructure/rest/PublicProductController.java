package com.nexusfinance.infrastructure.rest;

import com.nexusfinance.application.dto.ProductResponse;
import com.nexusfinance.application.port.in.InvestmentProductUseCase;
import com.nexusfinance.domain.model.RiskLevel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador público para productos de inversión.
 * No requiere autenticación.
 */
@RestController
@RequestMapping("/api/public/products")
public class PublicProductController {

    private final InvestmentProductUseCase productUseCase;

    public PublicProductController(InvestmentProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    /**
     * Listar productos disponibles para inversión.
     */
    @GetMapping("/available")
    public ResponseEntity<List<ProductResponse>> getAvailableProducts() {
        return ResponseEntity.ok(productUseCase.getAvailableProducts());
    }

    /**
     * Obtener un producto por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productUseCase.getProductById(id));
    }

    /**
     * Filtrar productos por nivel de riesgo.
     */
    @GetMapping("/risk/{riskLevel}")
    public ResponseEntity<List<ProductResponse>> getProductsByRiskLevel(@PathVariable RiskLevel riskLevel) {
        return ResponseEntity.ok(productUseCase.getProductsByRiskLevel(riskLevel));
    }
}
