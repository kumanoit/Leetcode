package com.utkarsh.coffeemachine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represent machine which has outlets and it contains all ingredients to make beverage.
 */
public class ChaiPoint {
    int outlets;
    Map<String, Long> ingredients;
    List<Beverage> beverageList;

    public ChaiPoint(final int outlets, final List<Beverage> beverageList) {
        this.outlets = outlets;
        ingredients = new HashMap<>();
        this.beverageList = beverageList;
    }

    public void addIngredients(final String name, final long quantity) {
        ingredients.put(name, quantity);
    }

    /**
     * this is a multi-threaded implementation to handle requests in parallel
     *
     * @throws InterruptedException
     */
    public void serveBeverage() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (beverageList.size() == 0) {
                    wait(1000);
                }
                Beverage beverage = beverageList.remove(0);
                final String nonServiceableReason = canBeServed(beverage);
                if (nonServiceableReason == null) {
                    for (Ingredient ingredient : beverage.getIngredientList()) {
                        String ingreName = ingredient.getName();
                        long quantity = ingredient.getQuantity();
                        long leftQuantity = ingredients.get(ingreName) - quantity;
                        ingredients.put(ingreName, leftQuantity);
                    }
                    System.out.println(beverage.getName() + " is prepared.");
                } else {
                    System.out.println(beverage.getName() + " cannot be prepared because " + nonServiceableReason);
                }
                notify();
                Thread.sleep(1000);
            }
        }
    }

    // this method can be used to add/refill an ingredient in machine
    public void addIngredient(final Ingredient ingredient) {
        if (!ingredients.containsKey(ingredient)) {
            ingredients.put(ingredient.getName(), ingredient.getQuantity());
        }
        ingredients.put(ingredient.getName(), ingredients.get(ingredient.getName()) + ingredient.getQuantity());
    }

    /**
     * @param beverage
     * @return null: if beverage can be served based on availability of ingredients
     * string: which gives the reason if an ingredient is missing/insufficient
     */
    private String canBeServed(Beverage beverage) {
        for (Ingredient ingredient : beverage.ingredientList) {
            String ingreName = ingredient.getName();
            long quantity = ingredient.getQuantity();
            if (!ingredients.containsKey(ingreName)) {
                return ingreName + " is not available";
            } else if (ingredients.get(ingreName) < quantity) {
                return ingreName + " is not sufficient";
            }
        }
        return null;
    }

    public void addBeverage(Beverage beverage) {
        this.beverageList.add(beverage);
    }
}
