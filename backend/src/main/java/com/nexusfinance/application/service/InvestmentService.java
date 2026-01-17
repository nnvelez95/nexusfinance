package com.nexusfinance.application.service;

import com.nexusfinance.application.dto.CreateInvestmentRequest;
import com.nexusfinance.application.dto.InvestmentResponse;
import com.nexusfinance.application.port.in.InvestmentUseCase;
import com.nexusfinance.domain.model.Investment;
import com.nexusfinance.domain.model.InvestmentProduct;
import com.nexusfinance.domain.model.User;
import com.nexusfinance.domain.repository.InvestmentProductRepository;
import com.nexusfinance.domain.repository.InvestmentRepository;
import com.nexusfinance.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvestmentService implements InvestmentUseCase {

    private final InvestmentRepository investmentRepository;
    private final UserRepository userRepository;
    private final InvestmentProductRepository productRepository;

    public InvestmentService(
            InvestmentRepository investmentRepository,
            UserRepository userRepository,
            InvestmentProductRepository productRepository) {
        this.investmentRepository = investmentRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public InvestmentResponse createInvestment(Long userId, CreateInvestmentRequest request) {
        // 1. Validar usuario
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // 2. Validar producto
        InvestmentProduct product = productRepository.findById(request.productId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + request.productId()));

        // 3. Validar disponibilidad del producto
        if (!product.isAvailableForInvestment()) {
            throw new RuntimeException("Product is not available for investment");
        }

        // 4. Validar monto de inversión
        if (!product.isValidInvestmentAmount(request.amount())) {
            throw new RuntimeException(
                "Investment amount must be >= " + product.getMinimumInvestment() 
                + " and not exceed remaining capacity"
            );
        }

        // 5. Validar saldo del usuario
        if (user.getBalance().compareTo(request.amount()) < 0) {
            throw new RuntimeException("Insufficient balance. Available: " + user.getBalance());
        }

        // 6. Crear inversión
        LocalDate startDate = LocalDate.now();
        LocalDate maturityDate = startDate.plusDays(product.getDurationDays());

        Investment investment = Investment.builder()
                .user(user)
                .product(product)
                .amount(request.amount())
                .startDate(startDate)
                .maturityDate(maturityDate)
                .build();

        // Calcular rendimiento esperado
        investment.setExpectedReturn(investment.calculateExpectedReturn());

        // 7. Actualizar balance del usuario (descontar inversión)
        user.updateBalance(request.amount().negate());
        userRepository.save(user);

        // 8. Actualizar producto (incrementar currentAmount)
        product.addInvestment(request.amount());
        productRepository.save(product);

        // 9. Guardar inversión
        Investment savedInvestment = investmentRepository.save(investment);

        return InvestmentResponse.fromEntity(savedInvestment);
    }

    @Override
    @Transactional(readOnly = true)
    public InvestmentResponse getInvestmentById(Long id) {
        Investment investment = investmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Investment not found with id: " + id));
        return InvestmentResponse.fromEntity(investment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvestmentResponse> getUserInvestments(Long userId) {
        return investmentRepository.findByUserId(userId).stream()
                .map(InvestmentResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvestmentResponse> getActiveInvestments(Long userId) {
        return investmentRepository.findActiveByUserId(userId).stream()
                .map(InvestmentResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public InvestmentResponse cancelInvestment(Long userId, Long investmentId) {
        // 1. Obtener inversión
        Investment investment = investmentRepository.findById(investmentId)
                .orElseThrow(() -> new RuntimeException("Investment not found with id: " + investmentId));

        // 2. Validar que pertenece al usuario
        if (!investment.getUser().getId().equals(userId)) {
            throw new RuntimeException("Investment does not belong to user");
        }

        // 3. Validar que se puede cancelar
        if (!investment.canBeCancelled()) {
            throw new RuntimeException("Investment cannot be cancelled");
        }

        // 4. Cancelar inversión (calcula rendimiento proporcional)
        investment.cancel();

        // 5. Devolver dinero al usuario (capital + rendimiento proporcional)
        User user = investment.getUser();
        BigDecimal amountToReturn = investment.getTotalAmount();
        user.updateBalance(amountToReturn);
        userRepository.save(user);

        // 6. Actualizar producto (reducir currentAmount)
        InvestmentProduct product = investment.getProduct();
        product.setCurrentAmount(product.getCurrentAmount().subtract(investment.getAmount()));
        productRepository.save(product);

        // 7. Guardar cambios
        Investment cancelledInvestment = investmentRepository.save(investment);

        return InvestmentResponse.fromEntity(cancelledInvestment);
    }
}
