package org.alex.bogo;

import java.util.Arrays;

public class BogoSort {
    public static void main(String[] args){
        int[] array =  new int[] {64, 42, 73, 41, 32, 53, 16, 24, 57, 42, 74, 55, 36};
        System.out.println("BogoSort time: " + mesureTime(()->{
            while( !isSorted(array) )
                shuffle(array);
        }) + " ms");
        System.out.println(Arrays.toString(array));
    }

    public static void shuffle(int[] array){
        int tmp;
        int idx;
        for (int i = 0; i < array.length; i++) {
            idx = (int)(Math.random() * array.length);

            tmp = array[idx];
            array[idx] = array[i];
            array[i] = tmp;
        }
    }

    public static boolean isSorted(int[] array){
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i])
                return false;
        }
        return true;
    }

    public static long mesureTime(Runnable task){
        long startTime = System.currentTimeMillis();
        task.run();
        return System.currentTimeMillis() - startTime;
    }
}
