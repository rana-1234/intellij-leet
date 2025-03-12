package com.coding.subarray;

import java.util.HashMap;

/*
    https://www.geeksforgeeks.org/largest-subarray-with-frequency-of-all-elements-same/
    Given an array arr[] of N integers, the task is to find the size of the largest subarray with frequency of all elements the same.

Examples:


    Input: arr[] = {1, 2, 2, 5, 6, 5, 6}
    Output: 6
    Explanation:
    The subarray = {2, 2, 5, 6, 5, 6} has frequency of every element is 2.

    Input: arr[] = {1, 1, 1, 1, 1}
    Output: 5
    Explanation:
    The subarray = {1, 1, 1, 1, 1} has frequency of every element is 5.

    Approach is brute force, generate all the subArrays and check the frequency stored in hashMap

 */
public class LargestElementWithAllElementFrequencySame {

    int maxLen(int [] arr){
        int ans = 1;
        int n = arr.length;
        for ( int i = 0 ; i < n ; i++){
            HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();
            // to track the frequency of the element
            HashMap<Integer, Integer> element_count = new HashMap<>(); // stores the number of elements with the frequency
            // At any time if its size becomes 1, means there are equal frequency elements
            freq.put(arr[i], 1);
            element_count.put(1, 1);
            for (int j = i+1; j < n; j++){
                int cf = freq.getOrDefault(arr[j], 0);
                if ( cf == 0){
                    // first time
                    element_count.put(1, element_count.getOrDefault(1, 0) + 1);
                    freq.put(arr[j], 1);
                }
                else{
                    freq.put(arr[j], cf + 1);
                    int no_of_elements = element_count.get(cf);
                    if ( no_of_elements == 1){
                        element_count.remove(cf);
                    }
                    else{
                        element_count.put(cf, no_of_elements - 1);
                    }
                    element_count.put(cf + 1, element_count.getOrDefault(cf + 1, 0) + 1);
                }

                if ( element_count.size() == 1){
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return  ans;
    }

    public static void main(String[] args) {
        {
            LargestElementWithAllElementFrequencySame instance = new LargestElementWithAllElementFrequencySame();
            int [] arr = {1, 2, 2, 5, 6, 5, 6};
            System.out.println(instance.maxLen(arr));
        }
        {
            LargestElementWithAllElementFrequencySame instance = new LargestElementWithAllElementFrequencySame();
            int [] arr = {1, 1, 1, 1, 1};
            System.out.println(instance.maxLen(arr));
        }
    }
}
