package com.nexusfinance.application.port.in;

import com.nexusfinance.application.dto.AuthResponse;
import com.nexusfinance.application.dto.LoginRequest;
import com.nexusfinance.application.dto.RegisterRequest;

public interface AuthenticationUseCase {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
