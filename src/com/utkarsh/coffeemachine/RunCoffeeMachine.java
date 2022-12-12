package com.utkarsh.coffeemachine;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class RunCoffeeMachine {
    public static void main(String[] args) throws ParseException, InterruptedException {
        // Reading input to create objects
        String input = getInput();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(input);

        JSONObject machine = (JSONObject) jsonObject.get("machine");
        JSONObject outlets = (JSONObject) machine.get("outlets");
        int count = Integer.parseInt(outlets.get("count_n").toString());

        JSONObject totalItemsQuantity = (JSONObject) machine.get("total_items_quantity");

        JSONObject beverages = (JSONObject) machine.get("beverages");
        final List<Beverage> beverageList = new ArrayList<>();
        for (Object beverage : beverages.keySet()) {
            String beverageName = beverage.toString();
            Beverage bvg = new Beverage(beverageName);
            JSONObject ingredients = (JSONObject) beverages.get(beverageName);
            for (Object ingredient : ingredients.keySet()) {
                final String ingredName = ingredient.toString();
                final long ingredQuantity = Long.parseLong(ingredients.get(ingredName).toString());
                bvg.addIngredients(new Ingredient(ingredName, ingredQuantity));
            }
            beverageList.add(bvg);
        }

        // Creating chaipoint class which will have "count" outlets and a list of beverage to serve
        ChaiPoint chaiPoint = new ChaiPoint(count, beverageList);
        for (Object entry : totalItemsQuantity.keySet()) {
            chaiPoint.addIngredients(entry.toString(), Long.parseLong(totalItemsQuantity.get(entry).toString()));
        }

        Thread[] threads = new Thread[count];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        chaiPoint.serveBeverage();
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            });
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
            threads[i].join();
        }
    }

    private static String getInput() {
        return "{\n" +
                "  \"machine\": {\n" +
                "    \"outlets\": {\n" +
                "      \"count_n\": 3\n" +
                "    },\n" +
                "    \"total_items_quantity\": {\n" +
                "      \"hot_water\": 500,\n" +
                "      \"hot_milk\": 500,\n" +
                "      \"ginger_syrup\": 100,\n" +
                "      \"sugar_syrup\": 100,\n" +
                "      \"tea_leaves_syrup\": 100\n" +
                "    },\n" +
                "    \"beverages\": {\n" +
                "      \"hot_tea\": {\n" +
                "        \"hot_water\": 200,\n" +
                "        \"hot_milk\": 100,\n" +
                "        \"ginger_syrup\": 10,\n" +
                "        \"sugar_syrup\": 10,\n" +
                "        \"tea_leaves_syrup\": 30\n" +
                "      },\n" +
                "      \"hot_coffee\": {\n" +
                "        \"hot_water\": 100,\n" +
                "        \"ginger_syrup\": 30,\n" +
                "        \"hot_milk\": 400,\n" +
                "        \"sugar_syrup\": 50,\n" +
                "        \"tea_leaves_syrup\": 30\n" +
                "      },\n" +
                "      \"black_tea\": {\n" +
                "        \"hot_water\": 300,\n" +
                "        \"ginger_syrup\": 30,\n" +
                "        \"sugar_syrup\": 50,\n" +
                "        \"tea_leaves_syrup\": 30\n" +
                "      },\n" +
                "      \"green_tea\": {\n" +
                "        \"hot_water\": 100,\n" +
                "        \"ginger_syrup\": 30,\n" +
                "        \"sugar_syrup\": 50,\n" +
                "        \"green_mixture\": 30\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }

}
