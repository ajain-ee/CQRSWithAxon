package org.example.api;

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
import javax.ws.rs.core.Response;

@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/cart")
public class ShoppingCartController {

    private CommandGateway commandGateway;
    private Repository repository;
    private EntityManager entityManager;

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
    public Response getItems(@PathParam("id") String id) throws InterruptedException {
        final ShoppingCart entity = entityManager.find(ShoppingCart.class, id);
        return Response.ok("{ \"cart-id\": \"" + entity.getId() + "\", \"quantity\": \"" + entity.getQuantity() + "\"}").build();

    }
}
