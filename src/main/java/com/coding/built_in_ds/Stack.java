package com.coding.built_in_ds;

public class Stack {

    public static void main(String[] args) {
        java.util.Stack <Integer> stack = new java.util.Stack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        System.out.println("Popping Stack " + stack.pop());
        System.out.println("Peeking Stack " + stack.peek());
        System.out.println("Popping Stack " + stack.pop());
    }
}
