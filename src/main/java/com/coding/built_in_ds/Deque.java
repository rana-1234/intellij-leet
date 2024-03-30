package com.coding.built_in_ds;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

public class Deque {

    public static void main(String[] args) {
        java.util.Deque<Integer> deque = new LinkedList<>();
        java.util.Deque<Integer> deque1 = new ArrayDeque<>();


        deque.addFirst(1);
        deque.addFirst(2);

        deque.addLast(3);
        deque.addLast(4);


        System.out.println("Peeking first " + deque.peekFirst());
        System.out.println("Polling first " + deque.pollFirst());
        System.out.println("Peeking first " + deque.peekFirst());
        System.out.println("Peeking last " + deque.peekLast());
        System.out.println("Polling last " + deque.pollLast());

    }
}
