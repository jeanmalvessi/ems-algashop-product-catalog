package com.algaworks.algashop.productcatalog.infrastructure.listener.product;

import com.algaworks.algashop.productcatalog.domain.model.product.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductEventListener {

    @Async
    @EventListener(ProductPriceChangedEvent.class)
    public void handle(ProductPriceChangedEvent event) {
        log.info("ProductPriceChangedEvent " + event);
    }

    @Async
    @EventListener(ProductPlacedOnSaleEvent.class)
    public void handle(ProductPlacedOnSaleEvent event) {
        log.info("ProductPlacedOnSaleEvent " + event);
    }

    @Async
    @EventListener(ProductAddedEvent.class)
    public void handle(ProductAddedEvent event) {
        log.info("ProductAddedEvent " + event);
    }

    @Async
    @EventListener(ProductDelistedEvent.class)
    public void handle(ProductDelistedEvent  event) {
        log.info("ProductDelistedEvent  " + event);
    }

    @Async
    @EventListener(ProductListedEvent.class)
    public void handle(ProductListedEvent event) {
        log.info("ProductListedEvent " + event);
    }

    @Async
    @EventListener(ProductRestockedEvent.class)
    public void handle(ProductRestockedEvent event) {
        log.info("ProductRestockedEvent  " + event);
    }

    @Async
    @EventListener(ProductSoldOutEvent.class)
    public void handle(ProductSoldOutEvent event) {
        log.info("ProductSoldOutEvent " + event);
    }
}
