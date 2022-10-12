package com.coding.leetcode;

import com.sun.source.tree.Tree;

import java.util.*;

public class P334 {

    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if( n < 3){
            return false;
        }

        int [] max = new int[n]; // what is the max in i...n
        int [] min = new int[n];  // what is the min in 0..i

       min[0] = nums[0];
       max[n-1] = nums[n-1];

        for(int i = 1, j = n-2 ; i < n && j >= 0; i++, j--){
            max[j] = Math.max(max[j+1], nums[j]);
            min[i] = Math.min(min[i-1], nums[i]);
        }

        for(int i = 1 ; i < n-1 ; i++){
            if( min[i-1] < nums[i] && nums[i] < max[i+1]){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int [] nums = {5,4,3,2,1};
        System.out.println(new P334().increasingTriplet(nums));
    }
}
