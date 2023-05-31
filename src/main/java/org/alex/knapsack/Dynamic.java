package org.alex.knapsack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dynamic {
    public static void main(String[] args){
        int[] weights = {2,2,4,7,8,3};
        int[] prices = {1,3,5,7,2,6};

        int maxWeight = 11;
        int count = weights.length;

        int[][] A = new int[count + 1][];
        for (int i = 0; i < count + 1; i++)
            A[i] = new int[maxWeight + 1];

        for (int c = 0; c <= count; c++) {
            for (int w = 0; w <= maxWeight; w++) {
                if (c == 0 || w == 0) {
                    A[c][w] = 0;
                } else {
                    if (w >= weights[c - 1]){
                        A[c][w] = Math.max(A[c - 1][w], A[c - 1][w - weights[c - 1]] + prices[c - 1]);
                    }else {
                        A[c][w] = A[c - 1][w];
                    }
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        traceResult(A, weights, count, maxWeight, result);
        System.out.println(result);
        for (int idx: result) {
            System.out.print("Weight: " + weights[idx - 1] + " - " + "price: " + prices[idx - 1]);
            System.out.println();
        }
    }

    public static void traceResult(int[][] A, int[] weights, int c, int w, List<Integer> list){
        if (A[c][w] == 0) return;
        if (A[c - 1][w] == A[c][w]){
            traceResult(A, weights, c - 1, w, list);
        } else {
            traceResult(A, weights, c - 1, w - weights[c - 1], list);
            list.add(0, c);
        }

    }
}
