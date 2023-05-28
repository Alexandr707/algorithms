package org.alex.string_search;

import java.util.ArrayList;
import java.util.Arrays;

public class KMPSearch {
    public static void main(String[] args){
        String text = "aababaaabaabaabaaaabaabaaabaab";
        String template = "aabaab";

        System.out.println("Entries: " + searchNaive(text,template));
        System.out.println("template: " + Arrays.toString(prefixFunction(template)));
        System.out.println("KMPSearch: " + KMPSearch(text, template));

    }

    public static ArrayList<Integer> searchNaive(String text, String template){
        ArrayList<Integer> foundedPositions = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            int j = 0;
            while(j < template.length() && i + j < text.length() && text.charAt(i + j) == template.charAt(j)) j++;
            if (j == template.length()) foundedPositions.add(i);
        }
        return foundedPositions;
    }

    public static int[] prefixFunction(String template){
        int[] values = new int[template.length()];

        for (int i = 1; i < template.length(); i++) {
            int j = 0;
            while (i + j < template.length() && template.charAt(j) == template.charAt(i + j)) {
                values[i + j] = Math.max(values[i + j], j + 1);
                j++;
            }
        }
        return values;
    }

    public static ArrayList<Integer> KMPSearch(String text, String template){
        ArrayList<Integer> entries = new ArrayList<>();

        int i = 0;
        int j = 0;
        int[] prefix = prefixFunction(template);

        while(i < text.length()){
            if (text.charAt(i) == template.charAt(j)){
                i++;
                j++;
            }
            if (j == template.length()){
                entries.add(i - j);
                j = prefix[j - 1];
            } else if (i < text.length() && text.charAt(i) != template.charAt(j)){
                if (j != 0){
                    j = prefix[j - 1];
                }else{
                    i = i + 1;
                }
            }
        }

        return entries;
    }


}
