package com.nexusfinance.domain.model;

/**
 * Roles del sistema (RBAC).
 * INVESTOR: Usuario regular que invierte.
 * ADMIN: Administrador de la plataforma.
 * ANALYST: Puede ver métricas pero no modificar.
 */
public enum UserRole {
    INVESTOR,
    ADMIN,
    ANALYST
}
