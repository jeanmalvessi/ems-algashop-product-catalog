package com.algaworks.algashop.productcatalog.infrastructure.listener.product;

import com.algaworks.algashop.productcatalog.application.IntegrationEventPublisher;
import com.algaworks.algashop.productcatalog.domain.model.product.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductEventListener {

    private final IntegrationEventPublisher integrationEventPublisher;

    @Async
    @EventListener(ProductPriceChangedEvent.class)
    public void handle(ProductPriceChangedEvent event) {
        log.info("ProductPriceChangedEvent " + event);
        integrationEventPublisher.send(event, "product-catalog.product.events");
    }

    @Async
    @EventListener(ProductPlacedOnSaleEvent.class)
    public void handle(ProductPlacedOnSaleEvent event) {
        log.info("ProductPlacedOnSaleEvent " + event);
        integrationEventPublisher.send(event, "product-catalog.product.events");
    }

    @Async
    @EventListener(ProductAddedEvent.class)
    public void handle(ProductAddedEvent event) {
        log.info("ProductAddedEvent " + event);
        integrationEventPublisher.send(event, "product-catalog.product.events");
    }

    @Async
    @EventListener(ProductDelistedEvent.class)
    public void handle(ProductDelistedEvent  event) {
        log.info("ProductDelistedEvent  " + event);
        integrationEventPublisher.send(event, "product-catalog.product.events");
    }

    @Async
    @EventListener(ProductListedEvent.class)
    public void handle(ProductListedEvent event) {
        log.info("ProductListedEvent " + event);
        integrationEventPublisher.send(event, "product-catalog.product.events");
    }

    @Async
    @EventListener(ProductRestockedEvent.class)
    public void handle(ProductRestockedEvent event) {
        log.info("ProductRestockedEvent  " + event);
        integrationEventPublisher.send(event, "product-catalog.product.events");
    }

    @Async
    @EventListener(ProductSoldOutEvent.class)
    public void handle(ProductSoldOutEvent event) {
        log.info("ProductSoldOutEvent " + event);
        integrationEventPublisher.send(event, "product-catalog.product.events");
    }
}
