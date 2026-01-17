package com.nexusfinance.infrastructure.rest;

import com.nexusfinance.application.dto.CreateProductRequest;
import com.nexusfinance.application.dto.ProductResponse;
import com.nexusfinance.application.dto.UpdateProductRequest;
import com.nexusfinance.application.port.in.InvestmentProductUseCase;
import com.nexusfinance.domain.model.ProductStatus;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador de administración para productos de inversión.
 * Requiere rol ADMIN (implementar en siguiente fase).
 */
@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    private final InvestmentProductUseCase productUseCase;

    public AdminProductController(InvestmentProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    /**
     * Crear un nuevo producto.
     */
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest request) {
        ProductResponse response = productUseCase.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Listar todos los productos (incluyendo inactivos).
     */
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productUseCase.getAllProducts());
    }

    /**
     * Obtener un producto por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productUseCase.getProductById(id));
    }

    /**
     * Filtrar productos por estado.
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ProductResponse>> getProductsByStatus(@PathVariable ProductStatus status) {
        return ResponseEntity.ok(productUseCase.getProductsByStatus(status));
    }

    /**
     * Actualizar un producto.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequest request) {
        return ResponseEntity.ok(productUseCase.updateProduct(id, request));
    }

    /**
     * Eliminar un producto.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productUseCase.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
