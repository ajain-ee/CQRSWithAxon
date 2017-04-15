package org.example.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column
    private String itemId;
    @Column
    private String code;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Integer quantity;

    @ManyToOne()
    @JoinColumn(name = "cartId")
    private ShoppingCart shoppingcart;

    public CartItem() {
    }

    public CartItem(String itemId, String code, String name, String description, Integer quantity) {
        this.itemId = itemId;
        this.code = code;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getItemId() {
        return itemId;
    }


    public ShoppingCart getShoppingcart() {
        return this.shoppingcart;
    }

    public void setShoppingcart(ShoppingCart shoppingcart) {
        this.shoppingcart = shoppingcart;
    }

    public Map<String,Object> toHashMap(){
        Map hashMap = new HashMap();
        hashMap.put("itemId", this.itemId);
        hashMap.put("code",this.code);
        hashMap.put("name",this.name);
        hashMap.put("quantity",this.quantity);
        hashMap.put("description",this.description);
        return hashMap;
    }
}
