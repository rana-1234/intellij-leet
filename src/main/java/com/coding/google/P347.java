package com.coding.google;

import java.util.*;

public class P347 {

    public int[] topKFrequent(int[] nums, int k) {

        // Using bucket sort
        List<Integer> [] buckets = new ArrayList [nums.length+1]; // Maximum freq
        for(int i = 0 ; i < nums.length + 1; i++){
            buckets[i] = new ArrayList<>();
        }
        int [] ans = new int[k];
        int maxFreq = 0;
        Map<Integer, Integer> numberToFreqMap = new HashMap<>();
        for(int i = 0 ; i < nums.length; i++) {
            numberToFreqMap.put(nums[i], numberToFreqMap.getOrDefault(nums[i], 0) + 1);
        }

        for( Map.Entry<Integer, Integer> entry : numberToFreqMap.entrySet())  {
            maxFreq = Math.max(maxFreq, entry.getValue());
            buckets[entry.getValue()].add(entry.getKey()); // Putting all in bucket
        }

        int j = 0;
        for(int i = maxFreq; i >= 0 && j < k ; i--){
            for(int val : buckets[i]){
                if ( j >= k ){
                    break;
                }
                ans[j++] = val;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int [] ans = new P347().topKFrequent(new int [] {1,1,1,2,2,3}, 2);
    }
}
