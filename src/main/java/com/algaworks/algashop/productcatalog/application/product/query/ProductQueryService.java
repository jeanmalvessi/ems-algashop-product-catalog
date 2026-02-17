package com.algaworks.algashop.productcatalog.application.product.query;

import com.algaworks.algashop.productcatalog.application.PageModel;

import java.util.UUID;

public interface ProductQueryService {
    ProductDetailOutput findById(UUID productId);
    PageModel<ProductSummaryOutput> filter(Integer size, Integer page);
}
