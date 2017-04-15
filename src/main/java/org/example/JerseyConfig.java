package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.api.ShoppingCartController;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JerseyConfig extends ResourceConfig {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    public JerseyConfig() {
        register(JacksonFeature.class);
        register(ShoppingCartController.class);
    }
}
