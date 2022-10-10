package com.coding.leetcode;

import java.util.Arrays;

public class P16 {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = nums[0] + nums[1] + nums[2];
        for(int i = 0 ; i < n ; i++){
            int required = target - nums[i];
            int j = i+1;
            int k = n-1;
            while(j < k){
                if(Math.abs(target-ans) > Math.abs(nums[i] + nums[j] + nums[k] - target)){
                    ans = nums[i] + nums[j] + nums[k];
                }
                if(nums[j] + nums[k] > required){
                    k--;
                }
                else if(nums[j] + nums[k] == required){
                    return target;
                }
                else{
                    j++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int [] arr = {-1,2,1,-4};
        int target = 1;
        System.out.println(new P16().threeSumClosest(arr, target));
    }
}
