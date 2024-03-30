package com.coding.ms;

import java.util.PriorityQueue;

public class SecondProblem {

    public static class Pair{
        public int distance;
        public int unit;

        public Pair(){

        }

        public Pair(int distance, int unit){
            this.distance = distance;
            this.unit = unit;
        }
    }

    public int solution(int[] D, int[] C, int P) {
        // Implement your solution here

        PriorityQueue<Pair> pq = new PriorityQueue<>((p,q)->{
            if ( p.distance < q.distance ){
                return -1;
            }
            else if ( p.distance > q.distance){
                return 1;
            }
            else{
                if( p.unit <= q.unit){
                    return -1;
                }
                return 1;
            }
        });

        for(int i = 0 ; i < D.length; i++){
            pq.add(new Pair(D[i], C[i]));
        }

        int ans = 0;

        while(!pq.isEmpty()){
            Pair first = pq.poll();
            if( first.unit > P ){
                break;
            }
            ans += 1;
            P -= first.unit;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new SecondProblem().solution(new int []{5,11,1,3}, new int []{6,1,3,2}, 7));
        System.out.println(new SecondProblem().solution(new int []{10,15,1}, new int []{10,1,2}, 3));
        System.out.println(new SecondProblem().solution(new int []{11,8,1}, new int []{9,18,18}, 7));
        System.out.println(new SecondProblem().solution(new int []{1,4,2,5}, new int []{4,9,2,3}, 19));
    }
}
