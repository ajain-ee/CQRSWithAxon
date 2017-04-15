package org.example.commandhandler;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.example.command.AddItemCommand;
import org.example.model.CartItem;
import org.example.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddItemCommandHandler {

    @Autowired
    private Repository repository;

    @CommandHandler
    public void handle(AddItemCommand addItemCommand) throws Throwable {
        final ShoppingCart shoppingCart = (ShoppingCart) repository.load(addItemCommand.getCartId());
        CartItem item = new CartItem(addItemCommand.getItemId(),addItemCommand.getCode(),addItemCommand.getName(),addItemCommand.getDescription(),addItemCommand.getQuantity());
        item.setShoppingcart(shoppingCart);
        shoppingCart.addItem(item);
    }
}
