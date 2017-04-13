package org.example.model;

import org.axonframework.domain.AbstractAggregateRoot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ShoppingCart extends AbstractAggregateRoot {

    @Id
    private String id;

    @Column
    private Integer quantity;

    public ShoppingCart() {
        // for axon
    }

    public ShoppingCart(String id) {
        this.id = id;
        this.quantity = 0;
    }

    @Override
    public Object getIdentifier() {
        return id;
    }

    public void addItem(Integer quantity){
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setIdentifier(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id='" + id + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
