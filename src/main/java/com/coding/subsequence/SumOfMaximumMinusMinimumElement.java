package com.coding.subsequence;

import java.util.Arrays;

public class SumOfMaximumMinusMinimumElement {

    /*
        Given an array arr[], the task is to compute the sum of (max{A} – min{A}) for every non-empty subset A of the array arr[].
Examples:



    Input: arr[] = { 4, 7 }
    Output: 3
    There are three non-empty subsets: { 4 }, { 7 } and { 4, 7 }.
    max({4}) – min({4}) = 0
    max({7}) – min({7}) = 0
    max({4, 7}) – min({4, 7}) = 7 – 4 = 3.
    Sum = 0 + 0 + 3 = 3
    Input: arr[] = { 4, 3, 1 }
    Output: 9


     https://www.geeksforgeeks.org/sum-of-maximum-element-minimum-element-for-all-the-subsets-of-an-array/

     So, we can't just generate all and then find the Maximum - Minimum

     What we can observe that, number of subsets in which Number is appearing as Maximum and Minimum. And some them up differently
     Example
        Let A = { 6, 3, 89, 21, 4, 2, 7, 9 }
        sorted(A) = { 2, 3, 4, 6, 7, 9, 21, 89 }

        Here we see, that Number 6,
        with {2,3,4} => All subsets when 6 is included it would be maximum (2^3 - 1) subset, basically 2^(position of 6) - 1
        with {7,9,21,81} => All subsets when 6 is included int would be minimum, similarly here 2^(n - position of 6 - 1) - 1

        Count this separately for each element and sum up in Max and Min, finally return the value zero.

     */

    int findDiff(int [] arr){
        Arrays.sort(arr);
        int max =  0;
        int min = 0;
        int n = arr.length;
        int [] pow_2 = new int[n+1];
        pow_2[0] = 1;
        for ( int i = 1; i <= n ; i++){
            pow_2[i] = 2*pow_2[i-1];
        }

        int sum = 0;

        for ( int i = 0 ; i < n ; i++ ){
            max = pow_2[i] - 1;
            min = pow_2[n - i - 1] - 1;
            sum += arr[i]*(max - min);
        }

        return sum;
    }


    public static void main(String[] args) {
        {
            SumOfMaximumMinusMinimumElement instance = new SumOfMaximumMinusMinimumElement();
            int [] arr = new int[]{ 4, 7 };
            System.out.println(instance.findDiff(arr));
        }

        {
            SumOfMaximumMinusMinimumElement instance = new SumOfMaximumMinusMinimumElement();
            int [] arr = new int[]{ 4, 3, 1 };
            System.out.println(instance.findDiff(arr));
        }
    }
}
