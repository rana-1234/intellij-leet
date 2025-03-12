package com.coding.subarray;

public class CountWaysToSplitArrayIntoTwoEqualSumsByChangingSignOfOneElement {

    // https://www.geeksforgeeks.org/count-ways-to-split-array-into-two-equal-sum-subarrays-by-changing-sign-of-any-one-array-element/
    /*

        Given an array arr[] consisting of N integers, the task is to count ways to split array into two subarrays of equal sum by changing the sign of any one array element.

Examples:


    Input: arr[] = {2, 2, -3, 3}
    Output: 2
    Explanation:
    Changing arr[0] = 2 to arr[0] = -2, the array becomes {-2, 2, -3, 3}. Only 1 possible split is {-2, 2} and {-3, 3}.
    Changing arr[1] = 2 to arr[1] = -2, the array becomes {2, -2, -3, 3}. Only 1 possible split is {-2, 2} and {-3, 3}.
    Changing arr[2] = -3 to arr[2] = 3, the array becomes {2, 2, 3, 3}. No way to split the array.
    Changing arr[3] = 3 to arr[2] = -3, the array becomes {2, 2, -3, -3}. No way to split the array.
    Therefore, the total number of ways to split = 1 + 1 + 0 + 0 = 2.

    Input: arr[] = {2, 2, 1, -3, 3}
    Output: 0

       Idea here is that,
       if you partition the array at any index, and change any of the one element sign in left or right, then
       if changing in right
            Let say length in right = n, and sum = SumR
            and Sum in left = SumL

            If n*SumL == (n-2)SumR => Its possible to split
          Similarly if we are changing in left side. just change the fomula, How formula is working

          let say y1 + y2 + .... + yn = x1 + x2 + ... + xn - 2x1 (changing sign of x1)
          or                          = x1 + x2 + ... + xn - 2x2 (changing sign of x2)

          Add up all
            n * (y1 + .... + yn) = (n-2)(x1 + ... + xn)


     */

    int countWays(int [] arr){
        long total_sum = 0;
        long left_sum = 0;
        int ways = 0;
        for ( int a : arr){
            total_sum += a;
        }
        int n = arr.length;
        int len = 1;
        while ( len < n ){
            left_sum += arr[len-1];
            total_sum -= arr[len-1];

            if ( (n-len)*left_sum == (n - len - 2)*total_sum
                || (len)*total_sum == (len - 2)*left_sum){
                ways++;
            }
            len++;
        }
        return ways;
    }


    public static void main(String[] args) {
        {
            CountWaysToSplitArrayIntoTwoEqualSumsByChangingSignOfOneElement instance =
                    new CountWaysToSplitArrayIntoTwoEqualSumsByChangingSignOfOneElement();
            int [] arr =  {2, 2, -3, 3};
            System.out.println(instance.countWays(arr));
        }
        {
            CountWaysToSplitArrayIntoTwoEqualSumsByChangingSignOfOneElement instance =
                    new CountWaysToSplitArrayIntoTwoEqualSumsByChangingSignOfOneElement();
            int [] arr =  {2, 2, 1, -3, 3};
            System.out.println(instance.countWays(arr));
        }
    }
}
