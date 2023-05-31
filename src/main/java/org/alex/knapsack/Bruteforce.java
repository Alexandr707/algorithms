package org.alex.knapsack;

import java.util.ArrayList;
import java.util.List;

public class Bruteforce {
    public static void main(String[] args){
        int[] weights = {2,5,4,7,8,3};
        int[] prices = {3,1,5,7,2,6};

        int maxWeight = 16;
        long count = 2L << weights.length;

        int maxPrice = 0;
        int maxState = 0;

        for (int i = 0; i < count; i++) {
            int price = state(i, prices);
            int weight = state(i, weights);
            if (weight <= maxWeight){
                if (price > maxPrice){
                    maxPrice = price;
                    maxState = i;
                }
            }
        }

        int powerOfTwo = 1;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < weights.length; i++) {
            if ((powerOfTwo & maxState) != 0){
                System.out.println(weights[i] + " - " + prices[i]);
                list.add(i);
            }
            powerOfTwo <<=1;
        }

        System.out.println(list);
        System.out.println("max weight: " + maxWeight);
        System.out.println("max price: " + maxPrice);
    }

    public static int state(int state, int[] values){
        int powerOfTwo = 1;
        int result = 0;
        for (int value : values) {
            if ((powerOfTwo & state) != 0)
                result += value;
            powerOfTwo <<= 1;
        }
        return result;
    }
}
