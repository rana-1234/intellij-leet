package com.coding.built_in_ds;

import java.util.LinkedList;

public class Queue {

    public static void main(String[] args) {
        java.util.Queue <Integer> queue = new LinkedList();

        queue.add(1);
        queue.add(2);
        queue.offer(3);

        System.out.println("Polling " + queue.poll());
        System.out.println("Peeking " + queue.peek());
        System.out.println("Polling " + queue.poll());
    }
}
