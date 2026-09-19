package com.algaworks.algashop.productcatalog.application;

public interface IntegrationEventPublisher {
    void send(Object event, String key, String destination);
}
