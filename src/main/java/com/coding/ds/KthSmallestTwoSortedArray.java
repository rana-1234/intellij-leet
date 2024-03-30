package com.coding.ds;

import java.util.Arrays;

public class KthSmallestTwoSortedArray {

    // Method 1: Merge the two sorted arrays and return the element at k-1th index
    // O(m+n), O(m+n)
    // We can optimize to not use space by keeping on checking the number of elements scanned, if k == scanned, return that element from A1 or A2.
    public static int findKthSmallestMerge(int [] arr1, int arr2 [] , int k){
        int m = arr1.length;
        int n = arr2.length;

        if ( k < 0 || k > m+n){
            return -1;
        }

        int [] sorted = new int[m+n];

        int i = 0, j = 0, l = 0;

        while(i < m && j < n ){
            if ( arr1[i] < arr2[j]){
                sorted[l++] = arr1[i++];
            }
            else{
                sorted[l++] = arr2[j++];
            }
        }

        while ( i < m){
            sorted[l++] = arr1[i++];
        }
        while(j < n){
            sorted[l++] = arr2[j++];
        }

        return sorted[k-1];
    }

    // Method 2: Divide and Conquer - Idea is to find the mid of both arrays and discard those which does not support the answer

    public static int findKthSmallestDivideAndConquer(int [] arr1, int arr2 [], int n1, int n2, int k){

        // Precondition
        if ( n1 + n2 < k){
            return -1;
        }

        // Base cases

        if ( n1 == 0){
            return arr2[k-1];
        }
        if ( n2 == 0){
            return arr1[k-1];
        }

        if ( n1 == 1 || n2 == 1){

            int [] temp;
            if ( n1 == 1){
                temp = arr2;
                arr2 = arr1;
                arr1 = temp;
                n1 = n2;
                n2 = 1;
            }

            // arr1 = [1,4,8,10]
            // arr2 = [3]

            if ( k == 1){
                return Math.min(arr1[0], arr2[0]);
            }
            if ( k == n1+n2){
                // require among all
                return Math.max(arr1[n1-1], arr2[0]);
            }

            // example k == 3
            // otherwise we need something between these two
            if ( arr1[k-1] < arr2[0]){
                return arr1[k-1]; // kth largest
            }
            return Math.max(arr1[k-2], arr2[0]); // the largest element would be at the end

        }


        int mid1 = (n1-1)/2; // {2, 3, 6, 7, 9} = (5-1)/2 = 2 => 6
        int mid2 = (n2-1)/2; // {1, 4, 8, 10} = (4-1)/2 = 1 => 4

        if ( mid1 + 1 + mid2 + 1 > k ){ // total 5 elements, k = 3
            // Meaning from both the sorted arrays till mid, there are more than k elements

            if( arr1[mid1] > arr2[mid2]){
                // Here we can safely discard the elements which are after arr[mid1] => Since those will be greater elements than mid1 and including arr[mid1] we have more elements (more than k)

                return findKthSmallestDivideAndConquer(Arrays.copyOfRange(arr1, 0, mid1), arr2, mid1, n2, k);
            }
            else{
                // vice versa.
                // CopyOfRange does not include the end element (from, to) => from to to-1 would be copied
                return findKthSmallestDivideAndConquer(arr1, Arrays.copyOfRange(arr2, 0, mid2), n1, mid2 , k);
            }
        }
        else{
            // otherwise, there are lesser elements including these mid1 and mid2, we have to pick up more elements in the range

            if ( arr1[mid1] < arr2[mid2]){
                // Definitely all the elements from arr1[0] to arr arr1[mid] would contribute to the kth largest, since arr[mid1] is less than arr[mid2] and we require more elements to find kth largest
                return findKthSmallestDivideAndConquer(Arrays.copyOfRange(arr1, mid1+1, arr1.length), arr2, n1-(mid1+1), n2, k-(mid1+1));
            }
            else{
                // vice versa
                return findKthSmallestDivideAndConquer(arr1, Arrays.copyOfRange(arr2, mid2+1, arr2.length), n1, n2-(mid2+1), k - (mid2 +1));
            }
        }

    }

    public static void main(String[] args) {

        int arr1[] = {2, 3, 6, 7, 9};
        int arr2[] = {1, 4, 8, 10};
        int k = 4;
        System.out.println("By merging the array in another sorted array : " + findKthSmallestMerge(arr1, arr2,k));
        System.out.println("By Divide and Conquer method " + findKthSmallestDivideAndConquer(arr1, arr2,  arr1.length, arr2.length, k));
    }
}
