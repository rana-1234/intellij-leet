package com.coding.leetcode;

import java.util.HashMap;
import java.util.Map;

public class P219 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        for(int i = 0 ; i < nums.length; i++){
            if(m.containsKey(nums[i]) && (i - m.get(nums[i]) <= k)){
                return true;
            }
            m.put(nums[i], i);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new P219().containsNearbyDuplicate(new int []{1,0,1,1}, 1));
        System.out.println(new P219().containsNearbyDuplicate(new int []{1,2,3,1}, 3));
        System.out.println(new P219().containsNearbyDuplicate(new int []{1,2,3,1,2,3}, 2));
        System.out.println(new P219().containsNearbyDuplicate(new int []{1,0,1,1}, 0));
    }
}
