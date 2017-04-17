package org.example;

import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.common.jpa.SimpleEntityManagerProvider;
import org.axonframework.contextsupport.spring.AnnotationDriven;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.repository.GenericJpaRepository;
import org.example.model.ShoppingCart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
@AnnotationDriven
public class AppConfiguration {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public SimpleCommandBus commandBus() {
        SimpleCommandBus simpleCommandBus = new SimpleCommandBus();

        return simpleCommandBus;
    }

    @Bean
    public DefaultCommandGateway gateway(){
        return new DefaultCommandGateway(commandBus());
    }

    @Bean
    public GenericJpaRepository genericJpaRepository(){
        SimpleEntityManagerProvider entityManagerProvider = new SimpleEntityManagerProvider(entityManager);


        final GenericJpaRepository genericJpaRepository = new GenericJpaRepository(entityManagerProvider, ShoppingCart.class);

        /**
         * Configuring the repository with an event bus which allows the repository
         * to be able to publish domain events
         */
        genericJpaRepository.setEventBus(eventBus());

        return genericJpaRepository;

    }

    @Bean
    public SimpleEventBus eventBus() {
        return new SimpleEventBus();
    }

    /*@Bean
    AnnotationEventListenerBeanPostProcessor
    annotationEventListenerBeanPostProcessor() {
        *//**
         * The AnnotationEventListenerBeanPostProcessor
         finds all beans that has methods annotated with @EventHandler
         * and subscribe them to the eventbus.
         *//*
        AnnotationEventListenerBeanPostProcessor listener =
                new AnnotationEventListenerBeanPostProcessor();
        listener.setEventBus(eventBus());
        return listener;
    }*/


}
