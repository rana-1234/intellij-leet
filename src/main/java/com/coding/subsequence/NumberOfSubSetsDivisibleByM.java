package com.coding.subsequence;

public class NumberOfSubSetsDivisibleByM {
    // Understanding : https://www.geeksforgeeks.org/number-of-subsets-with-sum-divisible-by-m/

    /*

        Given an array of integers, find a number of subsequences such that the sum of the subsequence is divisible by m. It is given that sum of array elements is small.
        Input : arr[] = {1, 2, 3};
        m = 3;
        Output : 3
        Subsequence of given set are
        {1}, {2}, {3}, {1, 2}, {2, 3}, {1, 3} and {1, 2, 3}.
        And their sums are 1, 2, 3, 3, 5, 4 and 6.
        Input : arr[] = {1, 2, 3, 4};
                m = 2;
        Output : 7
        {2}, {4}, {1, 3}, {2, 4}, {1, 2, 3}, {1, 3, 4}
        and {1, 2, 3, 4}


     */

    public int count(int [] arr, int m){
        // Brute force generate all and then check if sum % m == 0, then count it.

        /*
            Better using dp.
            dp[i][j]
                * Denotes number of sub-sequences  till i, such that there sum is j
            dp[i][0] = 1 // Don't take anything
            dp[0][arr[i]] = 1;
         */
        int n = arr.length;
        int totalSum = 0;
        for ( int i = 0 ; i < n ; i++){
            totalSum += arr[i];
        }
        int [][] dp = new int[n][totalSum+1];

        dp[0][arr[0]] = 1;

        for ( int i = 1 ; i < n ; i++ ){

            // Let's make all the sum
            for ( int sum = 0 ; sum <= totalSum - arr[i] ; sum++){
                dp[i][sum] += dp[i-1][sum]; // Not including this number in the previous sums
                dp[i][sum + arr[i]] += dp[i-1][sum]; // by adding the current number in all existing sum, we get this
            }
            dp[i][arr[i]] += 1; // this sum
        }

        int ans = 0;

        for ( int sum = 0 ; sum <= totalSum ; sum++){
            if ( sum %m == 0){
                ans += dp[n-1][sum];
            }
        }

        System.out.println("Total number of subsets " + ans);
        return ans;

    }

    public static void main(String[] args) {
        {
            NumberOfSubSetsDivisibleByM instance = new NumberOfSubSetsDivisibleByM();
            instance.count(new int[]{1, 2, 3}, 3);
        }
        {
            NumberOfSubSetsDivisibleByM instance = new NumberOfSubSetsDivisibleByM();
            instance.count(new int[]{1, 2, 3, 4}, 2);

        }
    }
}
