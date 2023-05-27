package org.alex.algoritms;

public class SelectedSort {
    public static int sort(int[] arr){
        int step = 0;

        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int index = i;
            for (int j = i; j < arr.length; j++) {
                if(arr[j] < min) {
                    min = arr[j];
                    index = j;
                }
                step++;
            }
            if (min != arr[i]){
                int tmp = arr[index];
                arr[index] = arr[i];
                arr[i] = tmp;
            }

        }
        return step;
    }
}
