package com.coding.subarray;

/*
    Problem
    https://www.geeksforgeeks.org/maximum-length-of-same-indexed-subarrays-from-two-given-arrays-satisfying-the-given-condition/

   Given two arrays arr[] and brr[] and an integer C, the task is to find the maximum possible length, say K, of the same indexed subarrays such that the sum of the maximum element in the K-length subarray in brr[] with the product between K and sum of the K-length subarray in arr[] does not exceed C.

Examples:



    Input: arr[] = {2, 1, 3, 4, 5}, brr[] = {3, 6, 1, 3, 4}, C = 25
    Output: 3
    Explanation: Considering the subarrays arr[] = {2, 1, 3} (Sum = 6) and brr[] = {3, 6, 1} (Maximum element = 6), Maximum element + sum * K = 6 + 6 * 3 = 24, which is less than C(= 25).




    Input: arr[] ={1, 2, 1, 6, 5, 5, 6, 1}, brr[] = {14, 8, 15, 15, 9, 10, 7, 12}, C = 40
    Output: 3
    Explanation: Considering the subarrays arr[] = {1, 2, 1} (Sum = 4) and brr[] = {14, 8, 15} (Maximum element = 6), Maximum element + sum * K = 15 + 4 * 3 = 27, which is less than C(= 40).


    Approach:
        The Key idea here is the BinarySearch over K should trigger in Mind along with SlidingWindow.
        Let say 1 to N is the maximum range of K.
            Do the sliding window to find sum and RangeQueryDS to find Max in the K window. (which can also be done by the Deque as well)

        return the max K among all
 */

import java.util.Deque;
import java.util.LinkedList;

public class MaximumSubArrayLengthWithCondition {

    public boolean possible(int k, int [] arr, int [] brr, int C){

        Deque<int []> deq = new LinkedList<>();
        int i = 0;
        int sum = 0;
        while ( i < k ){
            sum += arr[i];
            while ( !deq.isEmpty() && deq.peekLast()[0] <= brr[i]){
                deq.pollLast();
            }
            deq.addLast(new int[]{brr[i], i}); // acts like a monotonous stack from back
            i++;
        }

        if (deq.peekFirst()[0] + sum*k <= C){
            return true;
        }

        int j = 0;
        while ( i < arr.length){
            sum = sum + arr[i] - arr[j];
            while (!deq.isEmpty() && deq.peekLast()[0] <= brr[i]){
                deq.pollLast();
            }
            deq.addLast(new int[]{brr[i], i});
            while ( !deq.isEmpty() && deq.peekFirst()[1] <= j){
                deq.pollFirst(); // remove the elements which are not in the sub array of length of k
            }
            if (deq.peekFirst()[0] + sum*k <= C){
                return true;
            }
            i++;
            j++;
        }

        return false;
    }

    public int maxK(int [] arr, int [] brr, int C){
        int k = 0;
        int n = arr.length;
        int low = 1, high = n;
        while ( low <= high) {
            int mid = (low + high) / 2;
            if (possible(mid, arr, brr, C)) {
                k = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return k;
    }

    public static void main(String[] args) {
        {
            MaximumSubArrayLengthWithCondition instance = new MaximumSubArrayLengthWithCondition();
            int arr[] = {2, 1, 3, 4, 5};
            int brr[] = {3, 6, 1, 3, 4};
            int C = 25;
            System.out.println(instance.maxK(arr, brr, C));
        }

        {
            MaximumSubArrayLengthWithCondition instance = new MaximumSubArrayLengthWithCondition();
            int arr[] ={1, 2, 1, 6, 5, 5, 6, 1}, brr[] = {14, 8, 15, 15, 9, 10, 7, 12}, C = 40;
            System.out.println(instance.maxK(arr, brr, C));
        }
    }

}
