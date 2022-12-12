package com.utkarsh.coffeemachine;

import java.util.ArrayList;
import java.util.List;

/**
 * this represents a beverage which has a name and a list of ingredients it contains.
 */
public class Beverage {
    String name;
    List<Ingredient> ingredientList;

    public Beverage(final String name) {
        this.name = name;
        this.ingredientList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(final List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public void addIngredients(final Ingredient ingredient) {
        ingredientList.add(ingredient);
    }

}
