package org.alex.eratosfen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Eratosfen {
    public static void main(String[] args){
        List<Integer> numbers = primeNumbers(1000);
        System.out.println(numbers);

        System.out.println(isPrimaryNumber(39));

    }

    public static List<Integer> primeNumbers(int maxValue){
        boolean[] nums = new boolean[maxValue + 1];
        Arrays.fill(nums,true);
        List<Integer> primeNumbers = new ArrayList<>();

        for (int i = 2; i * i < maxValue; i++) {
            if (nums[i])
                for (int j = i * 2; j <= maxValue ; j+=i) {
                    nums[j] = false;
                }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]) primeNumbers.add(i);
        }
        return primeNumbers;
    }

    public static boolean isPrimaryNumber(int number){
        for (int i = 2; i * i <number; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }


}
