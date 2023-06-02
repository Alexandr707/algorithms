package org.alex.knapsack;

import java.util.ArrayList;
import java.util.List;

public class Greedy {
    public static void main(String[] args){
        int[] weights = {2,2,4,7,8,3};
        int[] prices = {1,3,5,7,2,6};

        int maxWeight = 15;

        List<Integer> indexes = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        int resultWeight = 0;

        for (int i = 0; i < weights.length; i++)
            indexes.add(i);

        while (!indexes.isEmpty()){
            int maxValue = prices[indexes.get(0)];
            int maxIndex = indexes.get(0);
            for (int i = 1; i < indexes.size(); i++) {
                int idx = indexes.get(i);
                if (maxValue < prices[idx] && weights[idx] + resultWeight <= maxWeight){
                    maxIndex = idx;
                    maxValue = prices[maxIndex];
                }
            }
            resultWeight += weights[maxIndex];
            if (resultWeight > maxWeight) {
                break;
            }
            result.add(maxIndex);
            indexes.remove(Integer.valueOf(maxIndex));
        }

        int totalWeight = 0;
        int totalPrice = 0;
        System.out.println("Result:");
        for (int idx :  result) {
            System.out.println("weight: " + weights[idx] + ", price: " + prices[idx]);
            totalWeight += weights[idx];
            totalPrice += prices[idx];
        }
        System.out.println("Summ:");
        System.out.println("weight: " + totalWeight + ", price: " + totalPrice);

    }
}
