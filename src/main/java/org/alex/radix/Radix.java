package org.alex.radix;

import java.util.ArrayList;
import java.util.List;

public class Radix {
    public static void main(String[] args){
        System.out.println(getInRadix(25555,30));
    }

    public static String getInRadix(int number, int radix){
        List<Character> digits = getDigitsTable();
        if (radix < 2 || radix >= digits.size() || number < 0)
            throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder();
        while (number > 0){
            sb.insert(0 , digits.get(number % radix));
            number = number / radix;
        }
        return sb.toString();
    }

    private static List<Character> getDigitsTable(){
        List<Character> digits = new ArrayList<>();
        for (char i = '0'; i <= '9'; i++)
            digits.add(i);
        for (char i = 'A'; i <= 'Z'; i++)
            digits.add(i);
        return digits;
    }

}
