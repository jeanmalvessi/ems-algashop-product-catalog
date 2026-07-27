package com.algaworks.algashop.productcatalog.application.security;

import java.util.UUID;

public interface SecurityCheckApplicationService {
    UUID getAuthenticatedUserId();
    boolean isAuthenticated();
    boolean isMachineAuthenticated();
}
