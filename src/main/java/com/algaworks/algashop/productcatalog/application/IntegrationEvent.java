package com.algaworks.algashop.productcatalog.application;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IntegrationEvent {
    @JsonIgnore
    String getAggregateId();
}
