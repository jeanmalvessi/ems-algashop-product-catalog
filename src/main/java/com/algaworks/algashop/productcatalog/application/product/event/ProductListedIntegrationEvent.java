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
@NoArgsConstructor
@AllArgsConstructor
public class ProductListedIntegrationEvent implements IntegrationEvent {
    private UUID productId;
    private OffsetDateTime listedAt;

    @Override
    public String getAggregateId() {
        return productId != null ? productId.toString() : null;
    }
}
