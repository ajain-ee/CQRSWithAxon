package org.example.command;

public class RemoveItemCommand {

    private String itemId;
    private Integer quantity;
    private String code;
    private String description;
    private String name;

    public RemoveItemCommand() {
    }

    public String getItemId() {
        return itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}


