package com.nexusfinance.application.port.in;

import com.nexusfinance.application.dto.CreateProductRequest;
import com.nexusfinance.application.dto.ProductResponse;
import com.nexusfinance.application.dto.UpdateProductRequest;
import com.nexusfinance.domain.model.ProductStatus;
import com.nexusfinance.domain.model.RiskLevel;

import java.util.List;

/**
 * Puerto de entrada para casos de uso de InvestmentProduct.
 */
public interface InvestmentProductUseCase {
    
    /**
     * Crear un nuevo producto de inversión (ADMIN).
     */
    ProductResponse createProduct(CreateProductRequest request);
    
    /**
     * Obtener un producto por ID.
     */
    ProductResponse getProductById(Long id);
    
    /**
     * Listar todos los productos (ADMIN).
     */
    List<ProductResponse> getAllProducts();
    
    /**
     * Listar productos disponibles para inversión (PUBLIC).
     */
    List<ProductResponse> getAvailableProducts();
    
    /**
     * Filtrar productos por estado.
     */
    List<ProductResponse> getProductsByStatus(ProductStatus status);
    
    /**
     * Filtrar productos por nivel de riesgo.
     */
    List<ProductResponse> getProductsByRiskLevel(RiskLevel riskLevel);
    
    /**
     * Actualizar un producto (ADMIN).
     */
    ProductResponse updateProduct(Long id, UpdateProductRequest request);
    
    /**
     * Eliminar un producto (ADMIN).
     */
    void deleteProduct(Long id);
}
