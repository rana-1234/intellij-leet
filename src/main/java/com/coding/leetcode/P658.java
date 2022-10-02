package com.coding.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class P658 {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        PriorityQueue<Integer> lesser = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> greater = new PriorityQueue<>();

        int n = arr.length;

        for(int i = 0 ; i < n ; i++){
            if( arr[i] >= x){
                // Put it in greater
                greater.add(arr[i]);
            }
            else{
                lesser.add(arr[i]);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for(int i = 1 ; i <= k ; i++){
            if(greater.isEmpty() && lesser.isEmpty()){
                break;
            }

            if(greater.isEmpty()){
                ans.add(lesser.poll());
                continue;
            }

            if(lesser.isEmpty()){
                ans.add(greater.poll());
                continue;
            }

            int greaterPeek = greater.peek() ;
            int lesserPeek = lesser.peek();

            if(Math.abs(greaterPeek - x) < Math.abs(lesserPeek - x)){
                ans.add(greater.poll());
            }
            else if(Math.abs(greaterPeek - x) == Math.abs(lesserPeek - x)){
                if( greaterPeek <= lesserPeek){
                    ans.add(greater.poll());
                }
                else{
                    ans.add(lesser.poll());
                }
            }
            else {
                ans.add(lesser.poll());
            }
        }
        Collections.sort(ans);
        return ans;
    }

    public static void main(String[] args) {

        int [] arr = {1,2,3,4,5};
        int k = 4, x = -1;

        System.out.println(new P658().findClosestElements(arr, k , x));
    }
}
