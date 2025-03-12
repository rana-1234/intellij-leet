package com.coding.leetcode;

import java.util.HashMap;

public class P3473 {

    int [] nums;
    int k;
    int m;
    int n;
    long INF = (long)1e15;
    HashMap<Integer, Long> [][] dp;

    long f(int pos, int currentLen, int count){

        if ( pos == n ){
            if ( count == k && currentLen >= m ){
                // Exactly k sub array we have found;
                return 0;
            }
            // all other cases are invalid
            return INF;
        }

        if ( count >= k+1){
            return INF;
        }

        Long already = dp[pos][count].get(currentLen);
        if ( already != null ){
            return already;
        }

        long ans = -INF; // initializing the answer
        if ( currentLen == 0){
            // choice to start or skip
            long bySkipping = f(pos+1, currentLen, count);
            // Handle invalid cases
            if ( bySkipping != INF){
                // valid case
                ans = Math.max(ans, bySkipping);
            }

            // starting a new sub array from here
            long startingNew = f(pos+1, currentLen + 1, count + 1);
            if ( startingNew != INF ){
                // valid case
                ans = Math.max(ans, nums[pos] +  startingNew);
            }
        }
        else{
            // we have choices to continue
            long byCont = f(pos+1, currentLen+1, count);
            if ( byCont != INF){
                ans = Math.max(ans, nums[pos] + byCont);
            }

            // or just stop before
            if ( currentLen >= m ){
                long byTerm = f(pos, 0, count);
                if ( byTerm != INF){
                    ans = Math.max(ans, byTerm);
                }
            }
        }
        long ret = ans == -INF ? INF : ans;
        dp[pos][count].put(currentLen, ret);
        return ret;

    }

    public int maxSum(int[] nums, int k, int m) {
        /*
            Hmm, so we need to choose k sub arrays with length atleast m, and we
            want to maximize the sum for all the sub arrays

            So, we need to explore all choices

            When I am at element x, we have following choices to explore
            1. Include this element in previous sub array if already exists
            2. Or start a new sub array from here
            3. Or simply close the array here (if length atleast m)

        */
        this.nums = nums;
        this.k = k;
        this.m = m;
        n = nums.length;
        dp = new HashMap [n][k+1];
        for (int i = 0 ; i < n ; i++ ){
            for ( int j = 0 ; j <= k ; j++ ){
                dp[i][j] = new HashMap<>();
            }
        }
        long res = f(0,0,0);
        // System.out.println("Res received = " + res);
        return (int) res;
    }

    public static void main(String[] args) {
        {
            P3473 instance = new P3473();
            int [] nums = new int[] {-3,9,-14};
            int k = 2;
            int m = 1;
            System.out.println(instance.maxSum(nums, k, m));
        }
    }
}
