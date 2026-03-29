package com.algaworks.algashop.productcatalog.domain.model;

public interface DomainEventPublisher {
    void publish(Object event);
}
