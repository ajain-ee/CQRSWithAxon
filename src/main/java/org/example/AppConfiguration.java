package org.example;

import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.common.jpa.SimpleEntityManagerProvider;
import org.axonframework.contextsupport.spring.AnnotationDriven;
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

        return new GenericJpaRepository(entityManagerProvider, ShoppingCart.class);
    }

}
