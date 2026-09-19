package com.algaworks.algashop.productcatalog.infrastructure.message;

import com.algaworks.algashop.productcatalog.application.LocalEventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalEventPublisherConfig {

    @Bean
    public LocalEventPublisher localEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        return applicationEventPublisher::publishEvent;
    }
}
