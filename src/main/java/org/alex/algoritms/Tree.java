package org.alex.algoritms;

public class Tree {
    public static void main(String[] args){
        int[] numbers = {3,5,4,9,2,1,6,5,4,7};
        Tree tree = new Tree(numbers[0]);

        for (int i = 1; i < numbers.length; i++) {
            tree.add(numbers[i]);
        }

        int summ = 0;
        for (int n :numbers) {
            summ += n;
        }

        System.out.println("Array summ: " + summ);
        System.out.println("Tree summ: " + tree.sum());
    }
    private int value;
    private Tree left;
    private Tree right;


    Tree(int value){
        this.value = value;
        left = right = null;
    }

    Tree(int value, Tree left, Tree right){
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public int sum(){
        int summ = value;

        if (left != null) summ += left.sum();

        if (right != null) summ += right.sum();

        return summ;
    }

    Tree add(int n){
        if (value > n) {
            if(right == null) right = new Tree(n);
            else right.add(n);
        } else{
            if(left == null) left = new Tree(n);
            else left.add(n);
        }

        return this;
    }
}
