package org.alex.countsort;

import java.util.Arrays;

public class CountSort {
    public static void main(String[] args){
        int[] array = new int[] {64, 42, 73, 41, 32, 53, 16, 24, 57, 42, 74, 55, 36};
        countSort(array,100);
        System.out.println(Arrays.toString(array));

    }

    public static void countSort(int[] arr, int maxValue){
        int[] count = new int[maxValue];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }

        int arrayindex = 0;
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                arr[arrayindex] = i;
                arrayindex++;
            }
        }
    }

}
