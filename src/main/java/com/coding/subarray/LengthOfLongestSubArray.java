package com.coding.subarray;

public class LengthOfLongestSubArray {

    /*
        Given an array arr[] of size N and an integer k, our task is to find the length of longest subarray whose sum of elements is not divisible by k. If no such subarray exists then return -1.
Examples:



    Input: arr[] = {8, 4, 3, 1, 5, 9, 2}, k = 2
    Output: 5
    Explanation:
    The subarray is {8, 4, 3, 1, 5} with sum = 21, is not divisible by 2.
    Input: arr[] = {6, 3, 12, 15}, k = 3
    Output: -1
    Explanation:
    There is no subarray which is not divisible by 3.

       Read more here : https://www.geeksforgeeks.org/length-of-longest-subarray-whose-sum-is-not-divisible-by-integer-k/

       The first non-zero mod, or the last non-zero mod index can be the largest. Why ?
       (a + b)%mod = a%mod + b%mod
       So, removing the number such that a%b == 0, is not going to help, instead remove the number a%b != 0. So, the left_most index where a%b != 0
        remove all the numbers before it, or let be the right_most index where a%b != 0, remove all the elements after it.

        If entire sum is not divisible by k, return n
        if no left_most or right_index, return 0 or -1


     */

    int longestSubArrayLength(int [] arr, int k){
        int sum = 0;
        int left_most = -1;
        int right_most = -1;
        int i = 0;
        for ( int a : arr){

            if ( a % k != 0){
                if ( left_most == -1){
                    left_most = i;
                }

                right_most = i;
            }
            sum += a;
            i++;
        }

        if ( sum %k != 0 ){
            return i;
        }
        if ( left_most == -1){
            return left_most;
        }

        return i - Math.min(left_most + 1, (i - right_most));
    }

    public static void main(String[] args) {
        {
            LengthOfLongestSubArray instance = new LengthOfLongestSubArray();
            int [] arr = new int[]{8, 4, 3, 1, 5, 9, 2};
            int k = 2;
            System.out.println(instance.longestSubArrayLength(arr, k));
        }
    }
}
