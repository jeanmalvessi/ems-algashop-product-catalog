package com.algaworks.algashop.productcatalog.infrastructure.persistence.category;

import com.algaworks.algashop.productcatalog.application.PageModel;
import com.algaworks.algashop.productcatalog.application.category.query.CategoryDetailOutput;
import com.algaworks.algashop.productcatalog.application.category.query.CategoryQueryApplicationService;
import com.algaworks.algashop.productcatalog.application.utility.Mapper;
import com.algaworks.algashop.productcatalog.domain.model.category.Category;
import com.algaworks.algashop.productcatalog.domain.model.category.CategoryNotFoundException;
import com.algaworks.algashop.productcatalog.domain.model.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryQueryServiceImpl implements CategoryQueryApplicationService {

    private final CategoryRepository categoryRepository;
    private final Mapper mapper;

    @Override
    public PageModel<CategoryDetailOutput> filter(Integer size, Integer number) {
        return null;
    }

    @Override
    public CategoryDetailOutput findById(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        return mapper.convert(category, CategoryDetailOutput.class);
    }
}
