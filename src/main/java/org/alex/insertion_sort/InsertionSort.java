package org.alex.insertion_sort;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args){
        int[] array = new int[] {64, 42, 73, 41, 32, 53, 16, 24, 57, 42, 74, 55, 36};

        System.out.println(Arrays.toString(array));
        insertionSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void insertionSort(int[] array){
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i;
            while(j > 0 && current < array[j - 1]){
                array[j] = array[j - 1];
                j--;
            }
            array[j] = current;
        }
    }

}
