package org.example.model;

import org.axonframework.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
public class ShoppingCart extends AbstractAggregateRoot {

    @Id
    @Column(name = "cartId")
    private Integer cartId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shoppingcart", cascade = CascadeType.ALL)
    List <CartItem> cartItemList;


    public ShoppingCart() {
    }

    public ShoppingCart(Integer cartId) {
        this.cartId = cartId;
        this.cartItemList = new ArrayList <>();
    }

    public ShoppingCart(Integer cartId, List <CartItem> cartItemList) {
        this.cartId = cartId;
        this.cartItemList = cartItemList;
    }

    @Override
    public Object getIdentifier() {
        return cartId;
    }

    public void addItem(CartItem item) {
        this.cartItemList.add(item);
    }

    public Integer getCartId() {
        return cartId;
    }

    public List <CartItem> getCartItemList() {
        return cartItemList;
    }

    //@Override
   /* public String toString() {
        return "ShoppingCart{" +
                "cartId='" + cartId + '\'' +
                ", cartItemList=" + cartItemList +
                '}';
    }*/

   public Map<String, Object> toMap() {
       Map hashmap = new HashMap();
       hashmap.put("cartId",this.cartId);
       final List <Map <String, Object>> items = cartItemList.stream().map(item -> item.toHashMap()).collect(Collectors.toList());
       hashmap.put("items",items);
       return hashmap;
   }
}
