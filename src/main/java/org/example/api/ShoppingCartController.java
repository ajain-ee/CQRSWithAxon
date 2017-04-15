package org.example.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.repository.Repository;
import org.example.command.AddItemCommand;
import org.example.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/cart")
public class ShoppingCartController {

    private CommandGateway commandGateway;
    private Repository repository;
    private EntityManager entityManager;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    public ShoppingCartController(CommandGateway commandGateway, Repository repository, EntityManager entityManager) {
        this.commandGateway = commandGateway;
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @POST
    @Path("/items")
    @Transactional
    public void addItems(AddItemCommand command) throws InterruptedException {
        commandGateway.send(command);
    }

    @GET
    @Path("/items/{id}")
    public Map<String, Object> getItems(@PathParam("id") Integer id) throws InterruptedException, JsonProcessingException {
        final ShoppingCart shoppingCart = entityManager.find(ShoppingCart.class, id);
        return shoppingCart.toMap();
    }
}
