package com.algaworks.algashop.productcatalog.application;

public interface LocalEventPublisher {
    void send(Object message);
}
