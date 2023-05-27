package org.alex.algoritms;

//import java.io.File;
//import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Algorithms {
    public static void main(String[] args) {
        int[] arr = intArray(100000);
        int[] bubbleSortArray = Arrays.copyOf(arr,arr.length);

        createThread(()->{
            System.out.println("BubbleSort time: "
                    + mesureTimr(()->bubbleSort(bubbleSortArray))
                    + " ms");
        }, "BubbleSort").start();

//        ArrayList<File> fileList = new ArrayList<>();
//        FileSearch.search(new File("F:\\"), fileList);
//        System.out.println("Find: " + fileList.size() + " files");
//        for (File file: fileList) {
//            System.out.println(file.getName());
//        }

        int[] selectedSortArray = Arrays.copyOf(arr,arr.length);
        createThread(()->{
            System.out.println("SelectedSort time: "
                    + mesureTimr(()->selectedSort(selectedSortArray))
                    + " ms");
        },"SelectedSort").start();


        int[] quickSortArray = Arrays.copyOf(arr,arr.length );
        createThread(()->{
            System.out.println("QuickSort time: "
                    + mesureTimr(()->quickSort(quickSortArray, 0, quickSortArray.length - 1))
                    + " ms");
        }, "QuickSort").start();

        int[] mergeSortArray = Arrays.copyOf(arr,arr.length );
        createThread(()->{
            System.out.println("MergeSort time: "
                    + mesureTimr(()->quickSort(mergeSortArray, 0, mergeSortArray.length - 1))
                    + " ms");
        }, "MergeSort").start();
    }

    public static int[] intArray(int size) throws IllegalArgumentException {
        if (size <= 0) throw new IllegalArgumentException("Size must be greater then 0");

        int[] arr = new int[size];
        Random rand = new Random();

        for (int i = 0; i < arr.length; i++) {
            int tmp  = rand.nextInt(size);
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

//  юыстрая сортировка
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

    public static long mesureTimr(Runnable task){
        long startTime = System.currentTimeMillis();
        task.run();
        return System.currentTimeMillis() - startTime;
    }

    public static Thread createThread(Runnable task, String taskName){
        return new Thread(task, taskName);
    }

//    алгоритм слияния
    public static int[] mergeSort(int[] arr){
        int[] tmp;
        int[] srcArray = arr;
        int[] destArray = new int[arr.length];

        int size = 1;
        
        while(size < arr.length){
            for (int i = 0; i < arr.length; i+= 2 * size) {
                merge(srcArray, i , srcArray, i + size, destArray,i , size);
            }

            tmp = destArray;
            destArray = srcArray;
            srcArray = tmp;

            size *= 2;
        }

        return srcArray;
    }

    public static void merge(int[] src1, int src1Start, int[] src2, int src2Start, int[] dest, int destStart, int size){
        int index1 = src1Start;
        int index2 = src2Start;

        int src1End = Math.min(src1Start + size, src1.length);
        int src2End = Math.min(src2Start + size, src1.length);

        if(src1Start + size > src1.length){
            for (int i = src1Start; i < src1End; i++) {
                dest[i] = src1[i];
            }
            return;
        }

        int iterationCount = src1End - src1Start + src2End - src2Start;

        for (int i = destStart; i < destStart + iterationCount; i++) {
            if (index1 < src1End && (index2 >= src2End || src1[index1] < src2[index2])){
                dest[i] = src1[index1];
                index1++;
            }else{
                dest[i] = src1[index2];
                index2++;
            }
        }
    }

}