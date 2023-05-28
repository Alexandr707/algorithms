package org.alex.algoritms;

//import java.io.File;
//import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Algorithms {
    public static void main(String[] args) {
        int[] arr = intArray(8_000_000);

//        ArrayList<File> fileList = new ArrayList<>();
//        FileSearch.search(new File("F:\\"), fileList);
//        System.out.println("Find: " + fileList.size() + " files");
//        for (File file: fileList) {
//            System.out.println(file.getName());
//        }

        int[] bubbleSortArray = Arrays.copyOf(arr,arr.length);
//        createThread(()-> System.out.println("BubbleSort time: "
//                + mesureTimr(()->bubbleSort(bubbleSortArray))
//                + " ms"), "BubbleSort").start();

//        int[] selectedSortArray = Arrays.copyOf(arr,arr.length);
//        createThread(()-> System.out.println("SelectedSort time: "
//                + mesureTimr(()->selectedSort(selectedSortArray))
//                + " ms"),"SelectedSort").start();


        int[] quickSortArray = Arrays.copyOf(arr,arr.length );
        createThread(()-> System.out.println("QuickSort time: "
                + mesureTimr(()->quickSort(quickSortArray, 0, quickSortArray.length - 1))
                + " ms"), "QuickSort").start();

        int[] mergeSortArray = Arrays.copyOf(arr,arr.length );
        System.out.println("MergeSort time: "
                + mesureTimr(()->quickSort(mergeSortArray, 0, mergeSortArray.length - 1))
                + " ms");

//        System.out.println("BinarySearchRecursive time: "
//                + mesureTimr(()->binarySearchRecursive(mergeSortArray, 0, mergeSortArray.length - 1, 568))
//                + " ms");

        System.out.println("BinarySearch time: "
                + mesureTimr(()->binarySearch(mergeSortArray, 5686548))
                + " ms");

        System.out.println("LinearSearch time: "
                + mesureTimr(()->linearSeach(mergeSortArray, 568))
                + " ms");


    }

//    линейный поиск
    public static int linearSeach(int[] arr, int elementToSearch){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == elementToSearch) return i;
        }
        return -1;
    }

//    бинарный поиск
    public static int binarySearch(int[] arr, int elementToSearch){
        int startIndex = 0;
        int endIndex = arr.length;
        int middleIndex;

        while(endIndex > startIndex){
            middleIndex = startIndex + (endIndex - startIndex) / 2;

            if (arr[middleIndex] == elementToSearch) return middleIndex;

            if (arr[middleIndex] > elementToSearch) endIndex = middleIndex -1;
            else startIndex = middleIndex;

        }
        return -1;
    }

//    бинарный поиск рекурсивный
    public static int binarySearchRecursive(int[] arr, int startIndex, int endIndex, int elementToFind){
        if (endIndex >= startIndex){
            int middleIndex = startIndex + (endIndex - startIndex) / 2;

            if (arr[middleIndex] == elementToFind) return middleIndex;

            if (startIndex == endIndex) return -1;

            if(arr[middleIndex] > elementToFind ){
                return binarySearchRecursive(arr,startIndex,middleIndex - 1, elementToFind);
            }else{
                return binarySearchRecursive(arr,middleIndex,endIndex,elementToFind);
            }
        }
        return -1;
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