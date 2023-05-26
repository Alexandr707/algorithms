package org.alex.algoritms;

public class BubbleSort {
    public static long sort(int[] arr) {
        if (arr.length <=1) return arr.length;

        long movesCount = 0;
        boolean isChanged = true;

        while(isChanged){
            isChanged = false;

            for (int i = 1; i < arr.length; i++) {
                if (arr[i] < arr[i-1]){
                    int tmp = arr[i-1];
                    arr[i-1] = arr[i];
                    arr[i] = tmp;
                    isChanged = true;
                }
                movesCount++;
            }
        }

        return movesCount;

    }
}
