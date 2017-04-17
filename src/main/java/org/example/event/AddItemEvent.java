package org.example.event;

import org.example.model.CartItem;

public class AddItemEvent {

    private Integer cartId;
    private CartItem itemAdded;


    public AddItemEvent() {
    }

    public AddItemEvent(Integer cartId, CartItem itemAdded) {
        this.cartId = cartId;
        this.itemAdded = itemAdded;
    }

    public Integer getCartId() {
        return cartId;
    }

    public CartItem getItemAdded() {
        return itemAdded;
    }

}


