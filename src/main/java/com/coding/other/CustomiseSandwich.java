package com.coding.other;

import java.util.ArrayList;
import java.util.List;

public class CustomiseSandwich {

    static class SandwichConfiguration {
        int bread;
        List<Integer> sauces;

        SandwichConfiguration(int bread, List<Integer> sauces) {
            this.bread = bread;
            this.sauces = sauces;
        }
    }

    public static SandwichConfiguration customiseSandwich(int[] breadPrices, int[] saucePrices, int budget) {
        int minCost = Integer.MAX_VALUE;
        SandwichConfiguration bestConfig = null;

        // Generate all possible combinations of sauces (with repetitions)
        List<List<Integer>> sauceCombinations = new ArrayList<>();
        int maxSauces = Math.min(2, saucePrices.length);
        sauceCombinations.add(new ArrayList<>());
        for (int i = 0; i < maxSauces; i++) {
            List<List<Integer>> newCombinations = new ArrayList<>();
            for (List<Integer> combination : sauceCombinations) {
                for (int saucePrice : saucePrices) {
                    List<Integer> newCombination = new ArrayList<>(combination);
                    newCombination.add(saucePrice);
                    newCombinations.add(newCombination);
                }
            }
            sauceCombinations = newCombinations;
        }

        // Find the best sandwich configuration
        for (int breadPrice : breadPrices) {
            for (List<Integer> sauces : sauceCombinations) {
                int totalCost = breadPrice + sauces.stream().mapToInt(Integer::intValue).sum();
                if (totalCost <= budget && totalCost < minCost) {
                    minCost = totalCost;
                    bestConfig = new SandwichConfiguration(breadPrice, sauces);
                }
            }
        }

        return bestConfig;
    }

    public static void main(String[] args) {
        int[] breadPrices = {2, 3, 4};
        int[] saucePrices = {1, 2, 3};
        int budget = 6;

        SandwichConfiguration bestConfig = customiseSandwich(breadPrices, saucePrices, budget);
        if (bestConfig != null) {
            System.out.println("Best configuration: Bread - " + bestConfig.bread + ", Sauces - " + bestConfig.sauces);
        } else {
            System.out.println("No configuration within budget");
        }
    }
}

