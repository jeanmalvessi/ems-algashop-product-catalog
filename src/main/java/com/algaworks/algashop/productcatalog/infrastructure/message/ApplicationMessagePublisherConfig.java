package com.algaworks.algashop.productcatalog.infrastructure.message;

import com.algaworks.algashop.productcatalog.application.ApplicationMessagePublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationMessagePublisherConfig {

    @Bean
    public ApplicationMessagePublisher applicationMessagePublisher(ApplicationEventPublisher applicationEventPublisher) {
        return applicationEventPublisher::publishEvent;
    }
}
