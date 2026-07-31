package com.algaworks.algashop.productcatalog.application.security;

import java.util.UUID;

public interface SecurityChecks {
    UUID getAuthenticatedUserId();
    boolean isAuthenticated();
    boolean isMachineAuthenticated();
}
