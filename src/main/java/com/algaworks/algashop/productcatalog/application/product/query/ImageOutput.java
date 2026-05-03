package com.algaworks.algashop.productcatalog.application.product.query;

import lombok.Data;

import java.util.UUID;

@Data
public class ImageOutput {
    private UUID id;
    private String url;
}
