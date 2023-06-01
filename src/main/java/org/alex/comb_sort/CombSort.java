package org.alex.comb_sort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class CombSort {
    public static void main(String[] args){
        int[] array1 = newArray(10000);
        int[] array2 = Arrays.copyOf(array1,array1.length);
        mesureTimr("Comb sort:", ()->combSort(array1));
        mesureTimr("Bubble sort:", ()-> bubbleSort(array2));
        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));
    }
    
    public static int[] newArray(int size){
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++)
            array[i] = (int)(Math.random() * size);
        return array;
    }

    public static void combSort(int[] array){
        int gap = array.length;
        boolean isSorted = true;
        while(!isSorted || gap > 1){
            if (gap > 1){
                gap = gap * 10 / 13;
            }else {
                gap = 1;
            }
            isSorted = false;
            for (int i = gap; i < array.length; i++) {
                if (array[i] < array[i - gap]){
                    isSorted = true;
                    int tmp = array[i];
                    array[i] = array[i - gap];
                    array[i - gap] = tmp;
                }
            }
        }
    }

    public static void bubbleSort(int[] arr) {
        if (arr.length <=1) return;

        long movesCount = 0;
        boolean isChanged = true;

        while(isChanged){
            isChanged = false;

            for (int i = 1; i < arr.length; i++) {
                if (arr[i] < arr[i-1]){
                    int tmp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = tmp;
                    isChanged = true;
                }
                movesCount++;
            }
        }
    }

    public static void mesureTimr(String message, Runnable task){
        long startTime = System.currentTimeMillis();
        task.run();
        long time = System.currentTimeMillis() - startTime;
        System.out.println(message + " " + time + " ms");
    }
    
}
