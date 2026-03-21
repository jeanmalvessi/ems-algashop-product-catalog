package com.algaworks.algashop.productcatalog.infrastructure.listener.category;

import com.algaworks.algashop.productcatalog.application.category.event.CategoryUpdatedEvent;
import com.algaworks.algashop.productcatalog.infrastructure.persistence.category.ProductCategoryUpdater;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class CategoryEventListener {

    private final ProductCategoryUpdater productCategoryUpdater;

    @EventListener
    @Async
    public void handle(CategoryUpdatedEvent categoryUpdatedEvent) {
        log.info("Category updated received: {}", categoryUpdatedEvent.getCategoryId());
        productCategoryUpdater.copyCategoryDataToProducts(categoryUpdatedEvent);
    }
}
