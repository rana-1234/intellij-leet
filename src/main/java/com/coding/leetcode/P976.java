package com.coding.leetcode;

import java.util.Arrays;
import java.util.Collections;

public class P976 {

    public int largestPerimeter(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        for(int i = n-3 ; i >= 0 ; i--){
            if( nums[i+2] < nums[i+1] + nums[i]){
                return nums[i+2] + nums[i+1] + nums[i];
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new P976().largestPerimeter(new int [] {1,2,1}));
        System.out.println(new P976().largestPerimeter(new int [] {2,1,2}));
        System.out.println(new P976().largestPerimeter(new int [] {1,2,1,10,12,1,12,121,21,2121,21,211,123,121,1,121}));
    }
}
