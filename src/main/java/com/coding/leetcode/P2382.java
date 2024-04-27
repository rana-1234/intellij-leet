package com.coding.leetcode;

import com.coding.help.Utils;

import java.util.*;

public class P2382 {


    public static class Node{
        int start;
        int end;
        long sum;

        public Node(int start, int end, long sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }
    }
    public long[] maximumSegmentSum(int[] nums, int[] removeQueries) {

        TreeSet<Node> splitSet = new TreeSet<>((x, y) -> x.start - y.start);
        int n = nums.length;
        long [] prefixSum = new long[n+1];
        prefixSum[0] = 0;

        for(int i = 0 ; i < n ; i++) {
            prefixSum[i+1] += prefixSum[i] + nums[i];
        }
        splitSet.add(new Node(0, n-1, prefixSum[n]));
        long [] ans = new long[n];

        TreeMap<Long, Integer> treeMap = new TreeMap<>();
        treeMap.put(prefixSum[n], 1);

        for(int i = 0 ; i < n-1 ; i++){
            int removeIndex = removeQueries[i];
            Node splittingNode = splitSet.lower(new Node(removeIndex+1, n, -1));
            splitSet.remove(splittingNode);

            // Sum must be present in the maxSums;
            int freq = treeMap.get(splittingNode.sum);

            if ( freq == 1){
                // remove this from the treeMap
                treeMap.remove(splittingNode.sum);
            }
            else{
                treeMap.put(splittingNode.sum, freq-1);
            }

            if ( splittingNode.start == splittingNode.end){
                // Nothing to be done here
            }
            // Split this node at the index removeIndex
            else if ( removeIndex == splittingNode.start){
                long sum = splittingNode.sum - nums[removeIndex];
                Node remaining = new Node(splittingNode.start + 1, splittingNode.end, sum);
                splitSet.add(remaining);
                treeMap.put(sum, treeMap.getOrDefault(sum, 0) + 1); // update the frequency of the sum
            }
            else if( removeIndex == splittingNode.end){
                long sum = splittingNode.sum - nums[removeIndex];
                Node remaining = new Node(splittingNode.start, splittingNode.end-1, sum);
                splitSet.add(remaining);
                treeMap.put(sum, treeMap.getOrDefault(sum, 0) + 1);
            }
            else{
                long sum1 = prefixSum[removeIndex] - prefixSum[splittingNode.start];
                Node leftSplit = new Node(splittingNode.start, removeIndex-1, sum1);
                splitSet.add(leftSplit);
                treeMap.put(sum1, treeMap.getOrDefault(sum1, 0) + 1);

                long sum2 = prefixSum[splittingNode.end+1] - prefixSum[removeIndex+1];
                Node rightSplit = new Node(removeIndex+1, splittingNode.end, sum2);
                splitSet.add(rightSplit);
                treeMap.put(sum2, treeMap.getOrDefault(sum2, 0)+1);
            }
            ans[i] = treeMap.lastKey(); // Maximum sum
        }
        ans[n-1] = 0;
        return ans;
    }

    public static void main(String[] args) {
        Utils.printLongArray(new P2382().maximumSegmentSum(new int [] {1,2,5,6,1}, new int[]{0,3,2,4,1}));
    }
}
