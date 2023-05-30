package org.alex.countsort;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Function;

public class CountSort {
    public static void main(String[] args){
        int[] array = new int[] {64, 42, 73, 41, 32, 53, 16, 24, 57, 42, 74, 55, 36};
        countSort(array,100);
        System.out.println(Arrays.toString(array));

        Task[] tasks = new Task[20];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new Task("name", (int)(Math.random() * 100));
        }

        for (Task task : tasks) {
            System.out.println(task);
        }
        System.out.println("=======================================================");
        tasks = countSort(tasks, (Task task) -> task.priority, 100);

        for (Task task : tasks) {
            System.out.println(task);
        }

    }

    public static void countSort(int[] arr, int maxValue){
        int[] count = new int[maxValue];
        for (int k : arr) count[k]++;

        int arrayIndex = 0;
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                arr[arrayIndex] = i;
                arrayIndex++;
            }
        }
    }

    public static <T,R extends Integer> T[] countSort(T[] arr, Function<T, R> compareValue, int MAX_VALUE) {
        R[] values = (R[]) Array.newInstance(compareValue.apply((arr[0])).getClass(), arr.length);
        for (int i = 0; i < arr.length; i++) {
            values[i] = compareValue.apply(arr[i]);
        }

        int[] count = new int[MAX_VALUE];
        for (R r : values) count[r.intValue()]++;
        for (int i = 1; i < count.length; i++) count[i] = count[i] + count[i - 1];

        T[] out = (T[]) Array.newInstance(arr[0].getClass(), arr.length);
        for (T t : arr) {
            int value = compareValue.apply(t).intValue();
            out[count[value] - 1] = t;
            count[value]--;
        }
        return out;
    }

    public static class Task{
        String name;
        int priority;

        public Task(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "name='" + name + '\'' +
                    ", priority=" + priority +
                    '}';
        }
    }
}
