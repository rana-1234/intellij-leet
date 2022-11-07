package com.coding.leetcode;

import java.util.*;

public class P6226 {

    public int destroyTargets(int[] nums, int space) {
        TreeMap<Integer, Integer> counter = new TreeMap<>();
        Arrays.sort(nums);
        int n = nums.length;
        if( n == 1){
            return nums[0];
        }

        counter.put(nums[0], 1);
        for(int i = 1 ; i < n ; i++){
            // O(N)
            // start, start + space, start + 2*space .... number
            // number = start + n*space
            // (number - start)%space = 0
            // if this number can be added in any set, we will add
            boolean createNew = true;
            int key = 0;
            // O(N)
            for(int entry : counter.keySet()){
                if((nums[i] - entry) % space == 0 ){
                    createNew = false;
                    counter.put(entry,counter.get(entry) + 1);
                    break;
                }
            }
            if(createNew){
                counter.put(nums[i], 1);
            }
        }

        // O(N)
        int maxEntry = Integer.MIN_VALUE , minNum = -1 ;
        for(Map.Entry<Integer, Integer> entry : counter.entrySet()){
            if(entry.getValue() > maxEntry){
              minNum = entry.getKey();
              maxEntry = entry.getValue();
            }
        }
        return minNum ;
    }
    public static void main(String[] args) {

    }
}
