package org.example.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.UUID;

public class AddItemCommand {

    @TargetAggregateIdentifier
    private String itemId;

    private Integer quantity;

    private String code;

    private String description;

    private String name;

    private Integer cartId;

    public AddItemCommand() {
        // TODO Auto-generated constructor stub
        itemId = UUID.randomUUID().toString();

    }

    public AddItemCommand(String itemId) {
        this.itemId = itemId;
    }

    public AddItemCommand(String itemId, Integer quantity, String code, String description, String name, Integer cartId) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.code = code;
        this.description = description;
        this.name = name;
        this.cartId = cartId;
    }

    public Integer getCartId() {
        return cartId;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }


    public String getItemId() {
        return itemId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;

    }

    public Integer getQuantity() {
        return quantity;
    }
}
