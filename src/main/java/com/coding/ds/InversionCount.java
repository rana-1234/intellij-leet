package com.coding.ds;

import java.util.*;
import java.util.function.Function;

public class InversionCount {



    // Method 1: Using the BITree to get the inversionCount

    public static int getInvCountBITree(int [] arr){

        // a = {8, 4, 2, 1}
        int n = arr.length;
        // First let's make all the numbers in array 0 to n-1 relative to each other

        int [] newArray = Arrays.copyOfRange(arr, 0, n); // {1,2,4,8}
        Arrays.sort(newArray);

        Function<Integer, Integer> lowerBound = (ele)->{
            // gives the total number of element less than this element strictly
            int i = 0 , j = n-1;
            while(i < j){
                int mid = (j+i)/2; // mid element
                if( ele > newArray[mid]){
                    i = mid+1;
                }
                else{
                    j = mid;
                }
            }
            return i;
        };

        for(int i = 0 ; i < n ; i++ ){
            arr[i] = lowerBound.apply(arr[i]);
        }

        //arr = {3, 2, 1, 0}

        //Now create a BIT on it.

        BinaryIndexedTree.BITree biTree = new BinaryIndexedTree.BITree(n); // this is max size

        int ans = 0;
        for (int i = 0 ; i < n; i++){
            // Put the index to be
            ans += biTree.get(n-1) - biTree.get(arr[i]); // finding all the elements less than arr[i-1] which have already appeared
            biTree.update(arr[i], 1); // Marking the arr[i]th index 1, to represent ith element is present
        }

        return ans;
    }

    public static void main(String[] args) {

        int arr[] = {8, 4, 2, 1};

        System.out.println("Number of inversions are : " + getInvCountBITree(arr));

    }
}
