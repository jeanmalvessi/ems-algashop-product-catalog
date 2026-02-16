package com.algaworks.algashop.productcatalog.application.category.query;

import com.algaworks.algashop.productcatalog.application.PageModel;

import java.util.UUID;

public interface CategoryQueryApplicationService {
    PageModel<CategoryDetailOutput> filter(Integer size, Integer number);
    CategoryDetailOutput findById(UUID categoryId);
}
