package com.coding.codechef;

import com.coding.io.InputReader;

import java.util.Collections;
import java.util.PriorityQueue;

public class EndCorr {

    public static void main(String[] args) throws Exception {
        InputReader in = new InputReader();
        int n = in.readInt();
        int m = in.readInt();

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i  = 0 ; i < n + m ; i++){
            int wealthOrVisit = in.readInt();
            if( wealthOrVisit == -1){
                System.out.println(pq.poll());
            }
            else{
                pq.add(wealthOrVisit);
            }
        }

    }
}
