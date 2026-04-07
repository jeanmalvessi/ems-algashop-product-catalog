package com.algaworks.algashop.productcatalog.application.category.query;

import com.algaworks.algashop.productcatalog.application.PageModel;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface CategoryQueryApplicationService {
    PageModel<CategoryDetailOutput> filter(CategoryFilter filter);
    CategoryDetailOutput findById(UUID categoryId);
    OffsetDateTime lastModified();
}
