package com.coding.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class P2602 {

    // https://leetcode.com/problems/minimum-operations-to-make-all-array-elements-equal/description/

    public int upperBound(int [] nums, int key){
        int i = 0, j = nums.length - 1;
        int ansIndex = j+1;
        while ( i <= j ){
            int mid = (i+j)/2;
            if(nums[mid] > key){
                ansIndex = mid;
                j = mid-1;
            }
            else{
                i = mid+1;
            }
        }
        return ansIndex;
    }

    public List<Long> minOperations(int[] nums, int[] queries) {

        List<Long> answer = new ArrayList<>();
        Arrays.sort(nums);

        int n = nums.length;
        long [] prefixSum = new long [n];
        prefixSum[0] = nums[0];
        for(int i = 1; i < n; i++){
            prefixSum[i] = prefixSum[i-1] + nums[i];
        }

        for(int i = 0 ; i < queries.length; i++){
            int index = upperBound(nums, queries[i]); // this would be giving the first element index which is greater than this value
            long ans = 1L*index*queries[i] - (index-1 >= 0 ? prefixSum[index-1] : 0 ) +
                    prefixSum[n-1] - (index-1 >= 0 ? prefixSum[index-1] : 0) - 1L*(n-index)*queries[i];
            answer.add(ans);
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new P2602().minOperations(new int [] {2,9,10000,3}, new int []{1,2,3,4,5,6,7,8,9,10,11,100000000}));
    }
}
