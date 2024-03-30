package com.coding.built_in_ds;

import java.util.Collections;
import java.util.PriorityQueue;

public class Heap {

    public static class Custom{
        int a;
        int b;

        @Override
        public String toString() {
            return "Custom{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
        // Max heap

        pq.add(10);
        pq.add(20);
        pq.add(30);
        pq.add(40);
        pq.add(5);

        System.out.println("Peeking " +  pq.peek());
        System.out.println("Polling " + pq.poll());

        pq = new PriorityQueue<Integer>(); // Min Heap

        pq.add(10);
        pq.add(20);
        pq.add(30);
        pq.add(40);
        pq.add(5);

        System.out.println("Peeking " +  pq.peek());
        System.out.println("Polling " + pq.poll());

        PriorityQueue<Custom> custom = new PriorityQueue<>((c1, c2)->c2.a - c1.a); // Max

        PriorityQueue<Custom> reverseCustom = new PriorityQueue<>((c1, c2)->c1.a - c2.a); // Min

        Custom c1 = new Custom();
        c1.a = 10;
        c1.b = 5;

        Custom c2 = new Custom();
        c2.a = 5;
        c2.b = 10;

        custom.add(c1);
        custom.add(c2);

        reverseCustom.add(c1);
        reverseCustom.add(c2);

        System.out.println(custom.peek());
        System.out.println(reverseCustom.peek());
    }
}
