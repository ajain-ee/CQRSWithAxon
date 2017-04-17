package org.example.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.repository.Repository;
import org.example.command.AddItemCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/cart")
public class ShoppingCartController {

    private CommandGateway commandGateway;
    private Repository repository;
    private EntityManager entityManager;
    private DataSource dataSource;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    public ShoppingCartController(CommandGateway commandGateway, Repository repository, EntityManager entityManager, DataSource dataSource) {
        this.commandGateway = commandGateway;
        this.repository = repository;
        this.entityManager = entityManager;
        this.dataSource = dataSource;
    }

    @POST
    @Path("/items")
    @Transactional
    public void addItems(AddItemCommand command) throws InterruptedException {
        commandGateway.send(command);
    }

    @GET
    @Path("/items/{id}")
    public List <Map <String, Object>> getItems(@PathParam("id") Integer id) throws InterruptedException, JsonProcessingException {
        // final ShoppingCart shoppingCart = entityManager.find(ShoppingCart.class, id);
        //return shoppingCart.toMap();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        final String sql = "select * from shopping_view where cart_id=" + id;

        List <Map <String, Object>> queryResult =
                jdbcTemplate.query(sql,
                        (rs, rowNum) -> {
                            Map <String, Object> view = new HashMap <String, Object>();
                            view.put("cartId", rs.getInt("cart_id"));
                            view.put("itemId", rs.getString("Item_ID"));
                            view.put("code", rs.getString("code"));
                            view.put("name", rs.getString("name"));
                            view.put("description", rs.getString("description"));
                            view.put("quantity", rs.getString("quantity"));
                            return view;

                        });

        return queryResult;
    }

}
