package com.utkarsh.coffeemachine;

/**
 * Represents ingredient which is used to make a beverage
 */
public class Ingredient {

    private String name;
    private long quantity;

    public Ingredient(final String name, final long quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(final long quantity) {
        this.quantity = quantity;
    }
}
