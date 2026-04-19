package com.algaworks.algashop.productcatalog.presentation;

import com.algaworks.algashop.productcatalog.application.PageModel;
import com.algaworks.algashop.productcatalog.application.product.management.ProductInput;
import com.algaworks.algashop.productcatalog.application.product.management.ProductManagementApplicationService;
import com.algaworks.algashop.productcatalog.application.product.query.ProductDetailOutput;
import com.algaworks.algashop.productcatalog.application.product.query.ProductFilter;
import com.algaworks.algashop.productcatalog.application.product.query.ProductQueryService;
import com.algaworks.algashop.productcatalog.application.product.query.ProductSummaryOutput;
import com.algaworks.algashop.productcatalog.domain.model.category.CategoryNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
//@CrossOrigin("*")
public class ProductController {

    private final ProductQueryService productQueryService;
    private final ProductManagementApplicationService productManagementApplicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDetailOutput create(@RequestBody @Valid ProductInput input) {
        try {
            return productManagementApplicationService.create(input);
        } catch (CategoryNotFoundException e) {
            throw new UnprocessableContentException(e.getMessage(), e);
        }
    }

    @PutMapping("/{productId}")
    public ProductDetailOutput update(@PathVariable UUID productId, @RequestBody @Valid ProductInput input) {
        return productManagementApplicationService.update(productId, input);
    }

    @PutMapping("/{productId}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enable(@PathVariable UUID productId) {
        productManagementApplicationService.enable(productId);
    }

    @DeleteMapping("/{productId}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disable(@PathVariable UUID productId) {
        productManagementApplicationService.disable(productId);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailOutput> findById(@PathVariable UUID productId) {
        /*if (productId.equals(UUID.fromString("5805e415-1ca0-45e2-9764-6fd5d1eb2339"))) {
            return ResponseEntity.badRequest().build();
        }
        if (Math.random() < 0.8) {
            try {
                Thread.sleep(Duration.ofSeconds(20));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }*/
        ProductDetailOutput product = productQueryService.findById(productId);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(Duration.ofMinutes(1)).cachePublic())
                .eTag("product:id:" + product.getId() + ":v:" + product.getVersion())
                .lastModified(product.getUpdatedAt().toInstant())
                .body(product);
    }

    @GetMapping
    public PageModel<ProductSummaryOutput> filter(ProductFilter filter) {
        return productQueryService.filter(filter);
    }

    @PostMapping("/{productId}/restock")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void restock(@PathVariable UUID productId, @RequestBody @Valid ProductQuantityModel productQuantityModel) {
        productManagementApplicationService.restock(productId, productQuantityModel.getQuantity());
    }

    @PostMapping("/{productId}/withdraw")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void withdraw(@PathVariable UUID productId, @RequestBody @Valid ProductQuantityModel productQuantityModel) {
        productManagementApplicationService.withdraw(productId, productQuantityModel.getQuantity());
    }
}
