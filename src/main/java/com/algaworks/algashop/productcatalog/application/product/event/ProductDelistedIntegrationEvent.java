package com.algaworks.algashop.productcatalog.application.product.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDelistedIntegrationEvent {
    private UUID productId;
    private OffsetDateTime delistedAt;
}
