package org.alex.huffman;

import java.util.*;

public class Huffman {
    public static void main(String[] args){
        String text = "Давно выяснено, что при оценке дизайна и композиции читаемый текст мешает сосредоточиться. Lorem Ipsum используют потому, что тот обеспечивает более или менее стандартное заполнение шаблона, а также реальное распределение букв и пробелов в абзацах, которое не получается при простой дубликации \"Здесь ваш текст.. Здесь ваш текст.. Здесь ваш текст..\" Многие программы электронной вёрстки и редакторы HTML используют Lorem Ipsum в качестве текста по умолчанию, так что поиск по ключевым словам \"lorem ipsum\" сразу показывает, как много веб-страниц всё ещё дожидаются своего настоящего рождения. За прошедшие годы текст Lorem Ipsum получил много версий. Некоторые версии появились по ошибке, некоторые - намеренно (например, юмористические варианты).";
        TreeMap<Character,Integer> frequencies  = countFrequency(text);
        System.out.println(frequencies );

        ArrayList<CodeTreeNode> codeTreeNodes  = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            codeTreeNodes.add(new CodeTreeNode(entry.getKey(), entry.getValue()));
        }

        CodeTreeNode tree = huffman(codeTreeNodes);

        TreeMap<Character,String> codes = new TreeMap<>();
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            Character ch = entry.getKey();
            codes.put(ch, tree.getCodeForCharacter(ch, ""));
        }

        System.out.println(codes);

        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            encoded.append(codes.get(text.charAt(i)));
        }

        System.out.println("Размер исходной строки: " + text.getBytes().length * 8 + " бит");
        System.out.println("Размер сжатой строки: " + encoded.length() + " бит");
        System.out.println("Биты сжатой строки: " + encoded);



    }

    private static CodeTreeNode huffman(ArrayList<CodeTreeNode> codeTreeNodes){
        while(codeTreeNodes.size() > 1){
            Collections.sort(codeTreeNodes);
            CodeTreeNode left = codeTreeNodes.remove(codeTreeNodes.size() - 1);
            CodeTreeNode right = codeTreeNodes.remove(codeTreeNodes.size() - 1);

            CodeTreeNode parent = new CodeTreeNode(null, left.weight + right.weight, left, right);
            codeTreeNodes.add(parent);
        }
        return codeTreeNodes.get(0);
    }

    public static TreeMap<Character, Integer> countFrequency(String text){
        TreeMap<Character,Integer> freqMap = new TreeMap<>();
        for (int i = 0; i < text.length(); i++) {
            Character c = text.charAt(i);
            Integer count = freqMap.get(c);
            freqMap.put(c, count == null ? 1 : count + 1);
        }
        return freqMap;
    }

    private static class CodeTreeNode implements Comparable<CodeTreeNode>{
        Character content;
        int weight;
        CodeTreeNode left;
        CodeTreeNode right;
        public CodeTreeNode(Character content, int weight) {
            this.content = content;
            this.weight = weight;
        }

        public CodeTreeNode(Character content, int weight, CodeTreeNode left, CodeTreeNode right) {
            this.content = content;
            this.weight = weight;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(CodeTreeNode o) {
            return o.weight - weight;
        }

        @Override
        public String toString() {
            return "CodeTreeNode{" +
                    content + "=" + weight + '}';
        }

        public String getCodeForCharacter(Character ch, String parentPath){
            if(ch == content){
                return parentPath;
            }else{
                if (left != null){
                    String path = left.getCodeForCharacter(ch, parentPath + '0');
                    if (path != null)
                        return path;
                }
                if (right != null){
                    String path = right.getCodeForCharacter(ch, parentPath + '1');
                    if (path != null)
                        return path;
                }
            }
            return null;
        }
    }
}
