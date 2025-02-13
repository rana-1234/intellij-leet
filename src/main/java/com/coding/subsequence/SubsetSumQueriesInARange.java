package com.coding.subsequence;

import com.coding.help.Utils;

import java.util.BitSet;

public class SubsetSumQueriesInARange {
    /*
       given an array[] of n positive integers and m queries. each query consists of two integers l and r represented by a range. for each query, find the count of numbers that lie in the given range which can be expressed as the sum of any subset of given array.

        prerequisite : subset sum queries using bitset ≤

        examples:


    input : arr[] = { 1, 2, 2, 3, 5 }, m = 4 l = 1, r = 2 l = 1, r = 5 l = 3, r = 6 l = 9, r = 30
    output : 2 5 4 5
    explanation : for the first query, in range [1, 2] all numbers i.e. 1 and 2 can be expressed as a subset sum, 1 as 1, 2 as 2. for the second query, in range [1, 5] all numbers i.e. 1, 2, 3, 4 and 5 can be expressed as subset sum, 1 as 1, 2 as 2, 3 as 3, 4 as 2 + 2 or 1 + 3, 5 as 5. for the third query, in range [3, 6], all numbers i.e. 3, 4, 5 and 6 can be expressed as subset sum. for the last query, only numbers 9, 10, 11, 12, 13 can be expressed as subset sum, 9 as 5 + 2 + 2, 10 as 5 + 2 + 2 + 1, 11 as 5 + 3 + 2 + 1, 12 as 5 + 3 + 2 + 2 and 13 as 5 + 3 + 2 + 2 + 1.

       Read here : https://www.geeksforgeeks.org/subset-sum-queries-in-a-range-using-bitset/

       So the learning here is we can use BitMask to find all the subset sum
       O(N*MaxSum) time

       For e.g.
        [1, 2, 4] => Array
        Now we have initial sum as {0} => Mean mask = 0000001
        Now comes, 1. So 1 can be part of it or not, if not, then sum is {0} => 0000001
                         if yes then it would be added with all {0} -> {0+1, 1} And 1 itself, so {1} => 0000010
        Now we need to union of these two sets, {0,1} => or in bit mask we have to do the | of it. 0000011

        Now, let's talk about 2.
          case 1: Don't include {0,1} =>     0000011
          case 2: Include => {0,1}-> {2, 3} => So what is happening, the existing bit mask is left shifted by 2 (adding 1) => 0001100 (previous one
           left shifted)
            And then do the or (Union) => 0001111 saying {0,1,2,3} => All this can be formed

        Superb !!

    Similar questions : https://www.geeksforgeeks.org/find-all-distinct-subset-or-subsequence-sums-of-an-array-set-2/

    */


    int [] find(int [] arr, int [][] queries){
        int n = arr.length;
        int sum = 0;
        for ( int a : arr){
            sum += a;
        }

        boolean [] dp = new boolean[sum+1];
        dp[0] = true; // like bit set

        for ( int a : arr){
            for ( int s = sum ; s >= a ; s--) {
                dp[s] |= dp[s - a]; // dp[s1] can be formed with if s-a has been formed already. Adding a would form it.
            }
        }

        // Now create a prefix array to count all the sums
        int [] prefix = new int[sum+2];
        prefix[0] = 0;
        for ( int i = 0 ; i <= sum ; i++){
            prefix[i+1] = prefix[i] + (dp[i] == true ? 1 : 0);
        }

        int [] ans = new int[queries.length];
        int j = 0;
        for ( int [] q : queries){
            q[1] = Math.min(q[1], sum);
            ans[j++] = prefix[q[1]] - prefix[q[0] - 1]; // counting all the ones set
        }

        return ans;
    }

    public static void main(String[] args) {
        {
            SubsetSumQueriesInARange instance = new SubsetSumQueriesInARange();
            int [] arr = new int[]{ 1, 2, 2, 3, 5 };
            int [][] queries = new int[][]{{1,2},{1, 5},{3,6},{9,30}};
            int [] ans = instance.find(arr, queries);
            Utils.printIntArray(ans);
        }
    }
}
