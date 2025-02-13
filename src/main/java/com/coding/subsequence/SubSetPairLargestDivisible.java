package com.coding.subsequence;

import java.util.Arrays;

public class SubSetPairLargestDivisible {

    /*

        Given an array of n distinct elements, find length of the largest subset such that every pair in the subset is such that the larger element of the pair is divisible by smaller element.

        Examples:

        Input : arr[] = {10, 5, 3, 15, 20}
        Output : 3
        Explanation: The largest subset is {5, 10, 20}.
        10 is divisible by 5, and 20 is divisible by 10.
        Input : arr[] = {18, 1, 3, 6, 13, 17}
        Output : 4
        Explanation: The largest subset is {1, 3, 6, 18}.
        In the subsequence, 3 is divisible by 1,
        6 is divisible by 3, and 18 is divisible by 6.

        Read here : https://www.geeksforgeeks.org/largest-divisible-pairs-subset/

     */


    public int longestLength(int [] arr){
        int n = arr.length;
        Arrays.sort(arr);
        int max_number = arr[n-1];
        int [] dp = new int[max_number+1]; // denotes the length of the largest subset ending at number i

        dp[arr[0]] = 1;
        int max_len = 1;

        for ( int i = 1 ; i < n ; i++ ){
            for ( int div = 1 ; div <= Math.sqrt(arr[i]); div++){
                if ( arr[i] % div == 0){
                    int max_div = 1 + dp[div];
                    int max_div_by = 1 + dp[arr[i]/div];
                    dp[arr[i]] = Math.max(dp[arr[i]],max_div);
                    dp[arr[i]] = Math.max(dp[arr[i]], max_div_by);
                }
            }

            max_len = Math.max(max_len, dp[arr[i]]);
        }

        return max_len;
    }




    public static void main(String[] args) {
        {
            SubSetPairLargestDivisible instance = new SubSetPairLargestDivisible();
            int [] input = new int[]{10, 5, 3, 15, 20};
            System.out.println(instance.longestLength(input));
        }

        {
            SubSetPairLargestDivisible instance = new SubSetPairLargestDivisible();
            int [] input = new int[] {18, 1, 3, 6, 13, 17};
            System.out.println(instance.longestLength(input));
        }

        {
            SubSetPairLargestDivisible instance = new SubSetPairLargestDivisible();
            int [] input = new int[]{3, 5, 10, 20, 21, 33};
            System.out.println(instance.longestLength(input));

        }
    }
}
