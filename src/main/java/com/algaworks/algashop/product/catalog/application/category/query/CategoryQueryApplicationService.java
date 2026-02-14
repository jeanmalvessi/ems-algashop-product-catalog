package com.algaworks.algashop.product.catalog.application.category.query;

import com.algaworks.algashop.product.catalog.application.PageModel;

import java.util.UUID;

public interface CategoryQueryApplicationService {
    PageModel<CategoryDetailOutput> filter(Integer size, Integer number);
    CategoryDetailOutput findById(UUID categoryId);
}
