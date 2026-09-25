package com.algaworks.algashop.productcatalog.application.product.event;

import com.algaworks.algashop.productcatalog.application.IntegrationEvent;
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
@AllArgsConstructor
@NoArgsConstructor
public class ProductAddedIntegrationEvent implements IntegrationEvent {
    private UUID productId;
    private OffsetDateTime addedAt;

    @Override
    public String getAggregateId() {
        return productId != null ? productId.toString() : null;
    }
}
