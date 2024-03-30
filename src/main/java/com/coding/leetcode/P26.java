package com.coding.leetcode;

public class P26 {

    public int removeDuplicates(int[] nums) {
        int ans = 1;
        int n = nums.length;

        if( n == 1){
            return ans;
        }

        int prev = nums[0];
        int j = 0;
        for(int i = 1; i < n; i++){
            if(prev != nums[i]){
                prev = nums[i];
                nums[++j] = prev;
                ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new P26().removeDuplicates(new int [] {0,0,1,1,1,2,2,3,3,4}));
    }
}
