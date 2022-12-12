package com.kumanoit.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PriceCheck {
    public static void main(String[] args) {
        List<String> products = Arrays.asList("chocolate", "cheese", "tomato");
        List<Float> productPrices = new ArrayList<>();
        productPrices.add(15f);
        productPrices.add(300.90f);
        productPrices.add(23.44f);
        List<String> productSold = Arrays.asList("cheese", "tomato", "chocolate");
        List<Float> soldPrice =  new ArrayList<>();
        soldPrice.add(300.90f);
        soldPrice.add(23.44f);
        soldPrice.add(10f);
        System.out.println(priceCheck(products, productPrices, productSold, soldPrice));
    }

    public static int priceCheck(List<String> products, List<Float> productPrices, List<String> productSold, List<Float> soldPrice) {
        Map<String, Float> priceMap = new HashMap<>();
        for(int i = 0; i < products.size(); i++) {
            priceMap.put(products.get(i), productPrices.get(i));
        }

        int errorCount = 0;
        for(int i = 0; i < productSold.size(); i++) {
            if (priceMap.get(productSold.get(i)).compareTo(soldPrice.get(i)) != 0) {
                errorCount++;
            }
        }
        return errorCount;
    }

}
