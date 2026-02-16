package com.algaworks.algashop.productcatalog.presentation;

import com.algaworks.algashop.productcatalog.application.PageModel;
import com.algaworks.algashop.productcatalog.application.category.management.CategoryInput;
import com.algaworks.algashop.productcatalog.application.category.management.CategoryManagementApplicationService;
import com.algaworks.algashop.productcatalog.application.category.query.CategoryDetailOutput;
import com.algaworks.algashop.productcatalog.application.category.query.CategoryQueryApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryQueryApplicationService categoryQueryApplicationService;
    private final CategoryManagementApplicationService categoryManagementApplicationService;

    @GetMapping
    public PageModel<CategoryDetailOutput> filter(@RequestParam(defaultValue = "0") Integer page,
                                                  @RequestParam(defaultValue = "10") Integer size) {
        return categoryQueryApplicationService.filter(size,page);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDetailOutput create(@RequestBody @Valid CategoryInput input) {
        UUID categoryId = categoryManagementApplicationService.create(input);
        return categoryQueryApplicationService.findById(categoryId);
    }

    @GetMapping("/{categoryId}")
    public CategoryDetailOutput findById(@PathVariable UUID categoryId) {
        return categoryQueryApplicationService.findById(categoryId);
    }

    @PutMapping("/{categoryId}")
    public CategoryDetailOutput update(
            @PathVariable UUID categoryId,
            @RequestBody @Valid CategoryInput input) {
        categoryManagementApplicationService.update(categoryId, input);
        return categoryQueryApplicationService.findById(categoryId);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disable(@PathVariable UUID categoryId) {
        categoryManagementApplicationService.disable(categoryId);
    }
}
