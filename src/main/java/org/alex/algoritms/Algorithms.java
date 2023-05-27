package org.alex.algoritms;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Algorithms {
    public static void main(String[] args) {
        int[] arr = intArray(40);
        int[] bubbleSortArray = Arrays.copyOf(arr,arr.length);
        printArray(arr);
        System.out.println("BubbleSort count: " + BubbleSort.sort(bubbleSortArray));
        printArray(bubbleSortArray);

//        ArrayList<File> fileList = new ArrayList<>();
//        FileSearch.search(new File("F:\\"), fileList);
//        System.out.println("Find: " + fileList.size() + " files");
//        for (File file: fileList) {
//            System.out.println(file.getName());
//        }

        int[] selectedSortArray = Arrays.copyOf(arr,arr.length);
        System.out.println("SelectedSort count: " + SelectedSort.sort(selectedSortArray));
        printArray(selectedSortArray);

    }
    public static int[] intArray(int size) throws IllegalArgumentException {
        if (size <= 0) throw new IllegalArgumentException("Size must be greater then 0");

        int[] arr = new int[size];
        Random rand = new Random();

        for (int i = 0; i < arr.length; i++) {
            int tmp  = rand.nextInt(500);
            arr[i] = tmp;
        }
        return arr;
    }

    public static void printArray( int[] array){
        for (int el: array) {
            System.out.print(el + " ");
        }
        System.out.println();
    }


}