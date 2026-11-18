package com.algaworks.algashop.productcatalog.infrastructure.message;

import com.algaworks.algashop.productcatalog.domain.model.DomainEventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Configuration
public class DomainEventPublisherConfig {

    @Bean
    public DomainEventPublisher domainEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        return event -> {
            //No transaction active = Immediate publishing
            if (!TransactionSynchronizationManager.isSynchronizationActive()) {
                applicationEventPublisher.publishEvent(event);
                return;
            }
            // Transaction active = Publishing before commit 
            TransactionSynchronizationManager.registerSynchronization(
                    new TransactionSynchronization() {
                        @Override
                        public void beforeCommit(boolean readOnly) {
                            applicationEventPublisher.publishEvent(event);
                        }
                    }
            );
        };
    }
}
