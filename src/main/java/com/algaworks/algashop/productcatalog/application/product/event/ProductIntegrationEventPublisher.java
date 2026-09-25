package com.algaworks.algashop.productcatalog.application.product.event;

import com.algaworks.algashop.productcatalog.application.IntegrationEvent;

public interface ProductIntegrationEventPublisher {
    void send(IntegrationEvent event);
}
