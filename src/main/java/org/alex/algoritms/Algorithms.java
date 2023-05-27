package org.alex.algoritms;

//import java.io.File;
//import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Algorithms {
    public static void main(String[] args) {
        int[] arr = intArray(40);
        int[] bubbleSortArray = Arrays.copyOf(arr,arr.length);
        printArray(arr);
        System.out.println("BubbleSort count: " + bubbleSort(bubbleSortArray));
        printArray(bubbleSortArray);

//        ArrayList<File> fileList = new ArrayList<>();
//        FileSearch.search(new File("F:\\"), fileList);
//        System.out.println("Find: " + fileList.size() + " files");
//        for (File file: fileList) {
//            System.out.println(file.getName());
//        }

        int[] selectedSortArray = Arrays.copyOf(arr,arr.length);
        System.out.println("SelectedSort count: " + selectedSort(selectedSortArray));
        printArray(selectedSortArray);

        int[] quickSortArray = Arrays.copyOf(arr,arr.length );
        quickSort(quickSortArray, 0, quickSortArray.length - 1);
        printArray(quickSortArray);

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

    public static void swap(int[] arr, int i, int j){
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

//    сорировка пузырьком
    public static long bubbleSort(int[] arr) {
        if (arr.length <=1) return arr.length;

        long movesCount = 0;
        boolean isChanged = true;

        while(isChanged){
            isChanged = false;

            for (int i = 1; i < arr.length; i++) {
                if (arr[i] < arr[i-1]){
                    swap(arr, i, i-1);
                    isChanged = true;
                }
                movesCount++;
            }
        }

        return movesCount;
    }

    // сортировка выбором
    public static long selectedSort(int[] arr){
        long step = 0;

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
                swap(arr, i, index);
            }

        }
        return step;
    }

    public static void quickSort(int[] arr, int from, int to){
        if (from < to) {
            int divideIndex = partition(arr, from, to);

            quickSort(arr, from, divideIndex - 1);

            quickSort(arr, divideIndex, to);
        }
    }

    public static int partition(int[] arr, int from,int to){
        int rightIndex = to;
        int leftidex = from;
        int pivot = arr[from + (rightIndex - leftidex) / 2];

        while(leftidex <= rightIndex){
            while (arr[leftidex] < pivot) leftidex++;

            while(arr[rightIndex] > pivot ) rightIndex--;

            if (leftidex <= rightIndex){
                swap(arr, leftidex,rightIndex);
                leftidex++;
                rightIndex--;
            }
        }
        return leftidex;
    }


}