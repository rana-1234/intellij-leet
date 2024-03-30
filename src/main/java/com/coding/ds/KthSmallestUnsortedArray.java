package com.coding.ds;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.function.BiFunction;
import java.util.function.Function;

public class KthSmallestUnsortedArray {


    // Method 1 : Sort and return at k-1 index
    // O(nlogn), O(1)

    public static int findKthSmallest(int [] array, int k){
        if ( k <= 0 || k > array.length){
            return -1;
        }

        Arrays.sort(array);
        return array[k-1];
    }

    // Method 2: Use binary search to find the exact element which is kth smallest
    // O(nlog(max-min)), O(1)

    public static int findKthSmallestBinarySearch(int [] array, int k){

        if ( k <= 0 || k > array.length){
            return -1;
        }

        Function<Integer, Integer> func = (a) -> {
            int count = 0;
            for (int i = 0 ; i < array.length; i++){
                if( array[i] <= a){
                    count++;
                }
            }
            // number of elements less than k
            return count;
        };

        int maxE = Integer.MIN_VALUE;
        int minE = Integer.MAX_VALUE;

        for(int i = 0 ; i < array.length; i++){
            maxE = Math.max(maxE, array[i]);
            minE = Math.min(minE, array[i]);
        }


        while ( minE < maxE) {
            int mid = (maxE + minE) / 2;
            int count = func.apply(mid);

            // Number of elements less than mid

            if (count < k) {
                // Total number less than this are few, so we need to increase the search
                minE = mid + 1; // Search in higher range
            } else {
                // otherwise search in lower range
                maxE = mid;
            }
        }
        return minE;
    }

    // Method 3, using the quick sort technique. While selecting the pivot, if pivot itself is the kth element, then return it or else search in left range or right range
    // O(n^2) is worst case, O(1). Array is sorted and the first element

    public static int findKthSmallestQuickSort(int [] array, int k){

        if ( k <= 0 || k > array.length){
            return -1;
        }

        BiFunction<Integer, Integer, Integer> partitioner = (l, r) -> {
            // Assumes l and r always in bound
            // 2 3 8 9 12
            int pivot = array[r]; // the last index as pivot
            int curr = l-1, i = 0;
            for (i = 0 ; i < r ; i++){
                if( array[i] <= pivot){
                    int temp = array[curr+1];
                    array[curr+1] = array[i];
                    array[i] = temp;
                    curr = curr+1;
                }
            }

            // Swap the pivot
            int temp = array[curr+1];
            array[curr+1] = array[r];
            array[r] = temp;
            return curr+1; // index of the element
        };

        int left = 0, right = array.length-1;

        while(left < right){
            int currentIndex = partitioner.apply(left, right);

            if (currentIndex == k-1){
                // Exactly this element at k-1th index is the kth largest
                return array[currentIndex];
            }
            else if(currentIndex < k-1 ){
                // we have to search from currentIndex to right
                left = currentIndex + 1;
            }
            else{
                // this element is bigger than kth smallest
                right = currentIndex -1;
            }
        }
        // It does not matter whether we send left or right, the above while loop would return
        return left;
    }

    // Method 4: Using the Max Heap of size k.
    // O(nlogk) and space O(k)

    public static int findKthSmallestMaxHeap(int array[], int k){
        if ( k <= 0 || k > array.length){
            return -1;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)-> b-a);
        for ( int i = 0 ; i < array.length; i++){
            // Let's put first and then if size increases remove
            maxHeap.add(array[i]);
            if (maxHeap.size() > k ){
                maxHeap.poll();
            }
        }

        return maxHeap.peek();
    }

    // Method 5: Using idea behind counting sort. We can have count the number of elements from 0 to maxElement and then return the kth one

    public static int findKthSmallestCountSort(int [] array, int k){
        if ( k <= 0 || k > array.length){
            return -1;
        }

        int maxE = Integer.MIN_VALUE;
        for (int i = 0 ; i < array.length; i++){
            maxE = Math.max(maxE, array[i]);
        }

        int [] freq = new int[maxE+1];
        for ( int i = 0 ; i < array.length; i++){
            freq[array[i]]++;
        }

        int counter = k;
        for ( int i = 0 ; i < maxE+1 ; i++){
            if (freq[i] > 0 && freq[i] >= counter){
                return i;
            }
            counter -= freq[i];
        }

        // This case would never occur though
        return -1;
    }


    public static void main(String[] args) {
        int a [] = { 1, 4, 5, 3, 19, 3};
        int k = 4;
        System.out.println("From sorting: " + findKthSmallest(a, k));
        System.out.println("From Binary Search: " + findKthSmallestBinarySearch(a, k));
        System.out.println("From Quick Sort: " + findKthSmallestQuickSort(a, k));
        //Array would have been altered here due to Quick Sort
        System.out.println("From Max Heap: " + findKthSmallestMaxHeap(a, k));
        System.out.println("From Count Sort: " + findKthSmallestCountSort(a, k));

    }
}
