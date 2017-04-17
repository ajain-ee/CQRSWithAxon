package org.example.eventhandler;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.example.event.AddItemEvent;
import org.example.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class AddItemEventHandler {

    @Autowired
    DataSource dataSource;

    @EventHandler
    public void handleItemAddedEvent(AddItemEvent addItemEvent){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        final CartItem item = addItemEvent.getItemAdded();

        jdbcTemplate.update("insert into shopping_view (CART_Id, ITEM_ID, NAME, CODE, DESCRIPTION, QUANTITY) values (?,?,?,?,?,?) ", new Object[] {addItemEvent.getCartId(), item.getId(), item.getCode(),item.getName(),item.getDescription(), item.getQuantity()});

    }
}
