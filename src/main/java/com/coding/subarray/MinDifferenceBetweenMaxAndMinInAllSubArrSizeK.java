package com.coding.subarray;

import com.coding.help.Utils;
import jdk.jshell.execution.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MinDifferenceBetweenMaxAndMinInAllSubArrSizeK {

    /*
        Given an array arr[] of size N and integer Y, the task is to find a minimum of all the differences between the maximum and minimum elements in all the sub-arrays of size Y.

Examples:


    Input: arr[] = { 3, 2, 4, 5, 6, 1, 9 }  Y = 3
    Output: 2
    Explanation:
    All subarrays of length = 3 are:
    {3, 2, 4} where maximum element = 4 and  minimum element = 2  difference = 2
    {2, 4, 5} where maximum element = 5 and  minimum element = 2  difference = 3
    {4, 5, 6} where maximum element = 6 and  minimum element = 4  difference = 2
    {5, 6, 1} where maximum element = 6 and  minimum element = 1  difference = 5
    {6, 1, 9} where maximum element = 9 and  minimum element = 6  difference = 3
    Out of these, the minimum is 2.


    Input: arr[] = { 1, 2, 3, 3, 2, 2  } Y = 4
    Output: 1
    Explanation:
    All subarrays of length = 4 are:
    {1, 2, 3, 3} maximum element = 3 and  minimum element = 1  difference = 2
    {2, 3, 3, 2} maximum element = 3 and  minimum element = 2  difference = 1
    {3, 3, 2, 2} maximum element = 3 and  minimum element = 2  difference = 1
    Out of these, the minimum is 1.

       Read here : https://www.geeksforgeeks.org/min-difference-between-maximum-and-minimum-element-in-all-y-size-subarrays/

      So, we can have the NGE and NME of the current index i.
      A =  { 3, 2, 4, 5, 6, 1,  9 }
      NG = { 1, , 4, 5, 6, 6 , 9 } // ith element would be the index upto which ith element is maximum
      NS = { 2, 1, 1, 1, 1, 1 , 9 }
      Then from this two subArray make two subArrays of length N - Y, which holds maximum element in the range 0,Y at index 0m 1,Y+1 at index and
      so on

      So simply check if


       One more approach is Queue implementation
       1) For each index i, push the element in the queue if it is greater than the element at back of the queue. Remove the back and push this.
       2) Check if front element in the queue is out of the range (i - k) length, then pop it.
       3) At the top, you would have maximum element.

       Similarly, keep one queue for min. Then just take the difference.

     */

    int [] findMaxSubArray(int [] arr, int k ){

        Utils.printIntArray(arr, "Input Array  ");
        int j = 0;
        Stack<Integer> st = new Stack<>();
        st.push(j);
        int n = arr.length;
        int [] max_arr = new int[n];

        for ( int i = 1 ; i < n ; i++ ) {
            while (!st.isEmpty() && arr[i] > arr[st.peek()]) {
                max_arr[st.pop()] = i - 1; // the element at the index which is top of the stack is no longer maximum, and it's range stops here
            }
            st.push(i);
        }
        // Now loop for the remaining Array

        while ( !st.isEmpty()){
            max_arr[st.pop()] = n-1;
        }
        Utils.printIntArray(max_arr, "Max Array    ");

        List<Integer> subArrayMax = new ArrayList<>();
        for ( int i = 0 ; i <= n - k; i++){
            if ( max_arr[j] < i + k - 1 || j < i){
                j++;
            }
            subArrayMax.add(arr[j]);
        }

        int [] max_sub_arr = subArrayMax.stream().mapToInt(Integer::intValue).toArray();
        Utils.printIntArray(max_sub_arr, "Max Sub Array");

        return max_sub_arr;

    }

    int [] findMinSubArray(int [] arr, int k){

        int n = arr.length;
        int [] min_arr = new int[n]; // min_arr[i] = is the index upto which arr[i] is maximum

        Stack<Integer> st= new Stack<>();
        st.push(0);
        for ( int i = 1 ; i < n ; i+=1){

            while ( !st.isEmpty() && arr[st.peek()] > arr[i]){
                // the peek index in stack is greater than the current element so before pushing the min_element, let's
                // update the index
                min_arr[st.pop()] = i-1 ; // Since, this element would be minimum till that index only, after that we are seeing new minimum
            }
            st.push(i); // push this element
        }

        while ( !st.isEmpty()){
            min_arr[st.pop()] = n-1; // rest this element is minimum till index n-1
        }

        Utils.printIntArray(min_arr, "MIN Index    ");

        List<Integer> min_sub_array = new ArrayList<>();
        int j = 0;
        for ( int i = 0 ;  i <= n-k; i++){
            if ( j < i || min_arr[j] < i + k - 1){
                j++;
            }
            min_sub_array.add(arr[j]);
        }

        int [] min_sub_arr = min_sub_array.stream().mapToInt(Integer::intValue).toArray();
        Utils.printIntArray(min_sub_arr, "Min Sub Array");

        return min_sub_arr;
    }

    int findMinDifference(int [] arr, int k){
        int [] max_sub_arr = findMaxSubArray(arr, k);
        int [] min_sub_arr = findMinSubArray(arr, k);

        int ans = Integer.MAX_VALUE;
        for (int i = 0 ; i < max_sub_arr.length; i+= 1){
            ans = Math.min(ans, max_sub_arr[i] - min_sub_arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        {
            MinDifferenceBetweenMaxAndMinInAllSubArrSizeK instance = new MinDifferenceBetweenMaxAndMinInAllSubArrSizeK();
            int [] arr = new int[] { 3, 2, 4, 5, 6, 1, 9 };
            int k = 3;
            System.out.println(instance.findMinDifference(arr, k));
        }
    }
}
