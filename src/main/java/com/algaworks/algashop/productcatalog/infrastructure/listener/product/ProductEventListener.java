package com.algaworks.algashop.productcatalog.infrastructure.listener.product;

import com.algaworks.algashop.productcatalog.application.product.event.ProductAddedIntegrationEvent;
import com.algaworks.algashop.productcatalog.application.product.event.ProductDelistedIntegrationEvent;
import com.algaworks.algashop.productcatalog.application.product.event.ProductIntegrationEventPublisher;
import com.algaworks.algashop.productcatalog.application.product.event.ProductListedIntegrationEvent;
import com.algaworks.algashop.productcatalog.application.product.event.ProductPriceChangedIntegrationEvent;
import com.algaworks.algashop.productcatalog.application.product.event.ProductPriceChangedV2IntegrationEvent;
import com.algaworks.algashop.productcatalog.application.utility.Mapper;
import com.algaworks.algashop.productcatalog.domain.model.product.ProductAddedEvent;
import com.algaworks.algashop.productcatalog.domain.model.product.ProductDelistedEvent;
import com.algaworks.algashop.productcatalog.domain.model.product.ProductListedEvent;
import com.algaworks.algashop.productcatalog.domain.model.product.ProductPlacedOnSaleEvent;
import com.algaworks.algashop.productcatalog.domain.model.product.ProductPriceChangedEvent;
import com.algaworks.algashop.productcatalog.domain.model.product.ProductRestockedEvent;
import com.algaworks.algashop.productcatalog.domain.model.product.ProductSoldOutEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductEventListener {

    private final ProductIntegrationEventPublisher integrationEventPublisher;
    private final Mapper mapper;

    @TransactionalEventListener
    @Async
    public void handle(ProductPriceChangedEvent event) {
        log.info("ProductPriceChangedEvent " + event);
        var integrationEvent = mapper.convert(event, ProductPriceChangedIntegrationEvent.class);
        integrationEventPublisher.send(integrationEvent);

        var v2IntegrationEvent = mapper.convert(event, ProductPriceChangedV2IntegrationEvent.class);
        integrationEventPublisher.send(v2IntegrationEvent);
    }

    @TransactionalEventListener
    @Async
    public void handle(ProductPlacedOnSaleEvent event) {
        log.info("ProductPlacedOnSaleEvent " + event);
        //integrationEventPublisher.send(event, event.getProductId().toString(),"product-catalog.product.events");
    }

    @TransactionalEventListener
    @Async
    public void handle(ProductAddedEvent event) {
        log.info("ProductAddedEvent " + event);
        var integrationEvent = mapper.convert(event, ProductAddedIntegrationEvent.class);
        integrationEventPublisher.send(integrationEvent);
    }

    @TransactionalEventListener
    @Async
    public void handle(ProductDelistedEvent  event) {
        log.info("ProductDelistedEvent  " + event);
        var integrationEvent = mapper.convert(event, ProductDelistedIntegrationEvent.class);
        integrationEventPublisher.send(integrationEvent);
    }

    @TransactionalEventListener
    @Async
    public void handle(ProductListedEvent event) {
        log.info("ProductListedEvent " + event);
        var integrationEvent = mapper.convert(event, ProductListedIntegrationEvent.class);
        integrationEventPublisher.send(integrationEvent);
    }

    @TransactionalEventListener
    @Async
    public void handle(ProductRestockedEvent event) {
        log.info("ProductRestockedEvent  " + event);
        //integrationEventPublisher.send(event, event.getProductId().toString(),"product-catalog.product.events");
    }

    @TransactionalEventListener
    @Async
    public void handle(ProductSoldOutEvent event) {
        log.info("ProductSoldOutEvent " + event);
        //integrationEventPublisher.send(event, event.getProductId().toString(),"product-catalog.product.events");
    }
}
