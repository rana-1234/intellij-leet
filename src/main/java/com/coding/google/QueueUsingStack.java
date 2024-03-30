package com.coding.google;

import java.util.Stack;

public class QueueUsingStack {

    public static Stack<Integer> inStack = new Stack<>();
    public static Stack<Integer> outStack = new Stack<>();


    // in = 1 => {in = {1}, out : {}}
    // in = 2 => {in = {1,2}, out: {}}
    // out => {in = {}, out = {2, 1}} => 1
    // in = 3 => {in = {2, 3}, out = {}}

    public static void enqueue(int value){
        while(!outStack.isEmpty()){
            inStack.push(outStack.pop());
        }
        inStack.push(value);
    }


    public static int deque(){
        while(!inStack.empty()){
            outStack.push(inStack.pop());
        }
        return outStack.pop();
    }

    public static void main(String[] args) {
        enqueue(5);
        enqueue(4);
        System.out.println(deque());
        enqueue(3);
        System.out.println(deque());
        System.out.println(deque());
    }
}
