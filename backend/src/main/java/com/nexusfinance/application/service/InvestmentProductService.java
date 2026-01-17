package com.nexusfinance.application.service;

import com.nexusfinance.application.dto.CreateProductRequest;
import com.nexusfinance.application.dto.ProductResponse;
import com.nexusfinance.application.dto.UpdateProductRequest;
import com.nexusfinance.application.port.in.InvestmentProductUseCase;
import com.nexusfinance.domain.model.InvestmentProduct;
import com.nexusfinance.domain.model.ProductStatus;
import com.nexusfinance.domain.model.RiskLevel;
import com.nexusfinance.domain.repository.InvestmentProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvestmentProductService implements InvestmentProductUseCase {

    private final InvestmentProductRepository productRepository;

    public InvestmentProductService(InvestmentProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse createProduct(CreateProductRequest request) {
        // Validación de negocio: target >= minimum
        if (request.targetAmount().compareTo(request.minimumInvestment()) < 0) {
            throw new RuntimeException("Target amount must be greater than or equal to minimum investment");
        }

        InvestmentProduct product = InvestmentProduct.builder()
                .name(request.name())
                .description(request.description())
                .status(ProductStatus.ACTIVE)
                .riskLevel(request.riskLevel())
                .annualReturn(request.annualReturn())
                .minimumInvestment(request.minimumInvestment())
                .targetAmount(request.targetAmount())
                .currentAmount(BigDecimal.ZERO)
                .durationDays(request.durationDays())
                .closingDate(request.closingDate())
                .build();

        InvestmentProduct savedProduct = productRepository.save(product);
        return ProductResponse.fromEntity(savedProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getProductById(Long id) {
        InvestmentProduct product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return ProductResponse.fromEntity(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getAvailableProducts() {
        return productRepository.findAvailableProducts().stream()
                .map(ProductResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getProductsByStatus(ProductStatus status) {
        return productRepository.findByStatus(status).stream()
                .map(ProductResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getProductsByRiskLevel(RiskLevel riskLevel) {
        return productRepository.findByRiskLevel(riskLevel).stream()
                .map(ProductResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse updateProduct(Long id, UpdateProductRequest request) {
        InvestmentProduct product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // Actualizar solo los campos no nulos
        if (request.name() != null) {
            product.setName(request.name());
        }
        if (request.description() != null) {
            product.setDescription(request.description());
        }
        if (request.status() != null) {
            product.setStatus(request.status());
        }
        if (request.riskLevel() != null) {
            product.setRiskLevel(request.riskLevel());
        }
        if (request.annualReturn() != null) {
            product.setAnnualReturn(request.annualReturn());
        }
        if (request.minimumInvestment() != null) {
            product.setMinimumInvestment(request.minimumInvestment());
        }
        if (request.targetAmount() != null) {
            product.setTargetAmount(request.targetAmount());
        }
        if (request.durationDays() != null) {
            product.setDurationDays(request.durationDays());
        }
        if (request.closingDate() != null) {
            product.setClosingDate(request.closingDate());
        }

        InvestmentProduct updatedProduct = productRepository.save(product);
        return ProductResponse.fromEntity(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
