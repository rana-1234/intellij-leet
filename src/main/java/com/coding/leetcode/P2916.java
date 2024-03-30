package com.coding.leetcode;

import java.util.HashMap;
import java.util.Map;

public class P2916 {

    public static final long mod = (long)1e9 + 7;
    public static final class SegmentTree{

        long [] data;
        long [] rangeSum;
        long [] pendingUpdate;
        int n ;
        public SegmentTree(int n){
            this.n = n;
            data = new long[4*this.n];
            pendingUpdate = new long[4*this.n];
            rangeSum = new long[4*this.n];
        }

        // we are always adding 1 here
        private long update(int start, int end, int rS , int rE , int ci){

            // no overlap or interval is wrong
            if( end < rS || rE < start || rE < rS ){
                return 0;
            }

            if (rS == rE){
                // Leaf node
                data[ci] += 1 + pendingUpdate[ci];
                pendingUpdate[ci] = 0;
                rangeSum[ci] = 1;
                return (data[ci]*data[ci])%mod;
            }

            // Perform lazy updates here, complete overlap
            if (start <= rS && rE <= end){
                // Complete overlap
                pushPendingUpdate(2*ci + 1, 1);
                pushPendingUpdate(2*ci + 2, 1);
                data[ci] = getNextUpdate(data[ci], rE-rS+1, rangeSum[ci]);
                rangeSum[ci] += rE - rS + 1; // update the range sum for next computation
                return data[ci];
            }

            // Partial overlap
            int mid = (rS + rE)/2; // 3,4 (3+4/2) = 7/2 = 3
            long leftVal  = update(start, end, rS, mid, 2*ci + 1);
            long rightVal = update(start, end, mid+1, rE, 2*ci + 2);
            data[ci] = (leftVal + rightVal)%mod;
            rangeSum[ci] = rangeSum[2*ci+1]+ rangeSum[2*ci+2];
            return data[ci];
        }

        public void update(int start, int end){
            update(start, end, 0, n-1, 0);
        }

        private long getNextUpdate(long cV, long numberOfCounts, long rangeSum){
            return (cV + numberOfCounts + 2*rangeSum)%mod;
        }

        private void pushPendingUpdate(int ci, long update){
            if ( ci < 4*n ){
                pendingUpdate[ci] += update;
            }
        }

        private long getSquareSum(int start, int end, int rS, int rE, int ci){
            // Push the updates to child if pending first
            if( end < rS || rE < start || rE < rS ){
                return 0 ;
            }

            if( rS == rE ){
                // Leaf node, we put here always current value not, squared
                data[ci] += pendingUpdate[ci];
                pendingUpdate[ci] = 0;
                return (data[ci]*data[ci])%mod;
            }

            if(pendingUpdate[ci] != 0){
                // First push the updates down
                long updatedData = data[ci];
                long pendingUpdateToChild = pendingUpdate[ci];
                while(pendingUpdate[ci] > 0 ){
                    updatedData = getNextUpdate(updatedData, rE-rS+1, rangeSum[ci]);
                    pendingUpdate[ci]--;
                    rangeSum[ci] += rE - rS + 1;
                }
                // update data
                data[ci] = updatedData;
                // rangeSym is already updated in loop

                // push the updates to the child
                pushPendingUpdate(2*ci+1, pendingUpdateToChild);
                pushPendingUpdate(2*ci+2, pendingUpdateToChild);
            }

            // complete overlap here
            if (start <= rS && rE <= end ){
                // Return the calculated value, if any data is pending to process, it would be processed above
                return data[ci];
            }

            // otherwise for partial updates, get the value from both the child and update the data and return
            int mid = (rS+rE)/2;
            long dataFromLeftSeg = getSquareSum(start,end, rS, mid, 2*ci+1);
            long dataFromRightSeg = getSquareSum(start,end, mid+1, rE, 2*ci+2);
            data[ci] = (dataFromLeftSeg + dataFromRightSeg)%mod;
            return data[ci];
        }

        // node contains square of the element
        public long getSquareSum(int start, int end){
            return getSquareSum(start, end, 0, n-1, 0);
        }
    }

    public static SegmentTree segTree;

    public static int sumCounts(int[] nums) {
        int n = nums.length;
        if ( n == 1){
            return 1;
        }

        // dp[i] = dp[i-1] + sum(f[j,i]) for all j = 0 to i
        // sum(f[j,i]) = sum(f[0,k]) + sum(f[k+1, i]) where k is the last seen index of nums[i]

        segTree = new SegmentTree(n);

        long [] dp = new long[n];
        dp[0] = 1;

        Map<Integer, Integer> lastSeenIndex = new HashMap<>();
        lastSeenIndex.put(nums[0], 0);
        segTree.update(0,0);// updating first unique count, cA = [1,0,0,0]

        // range update from 0 to 0 with 1


        for(int i = 1 ; i < n ; i++){
            // rangeUpdate(j+1, i) since elements gets increased
            // then rangeSquareQuery from (0, j) and rangeSquareQuery from (j+1, i)

            int j = lastSeenIndex.getOrDefault(nums[i], -1);
            segTree.update(j+1, i); // increment the elements for j+1, i

            long firstHalf = segTree.getSquareSum(0, j);
            long secondHalf = segTree.getSquareSum(j+1, i);
            dp[i] = (dp[i-1] + firstHalf + secondHalf)%mod;

            lastSeenIndex.put(nums[i], i);
        }

        return (int)dp[n-1];
    }

    public static void main(String[] args) {
        sumCounts(new int []{1,2,3,4,5,2,3,5,1,1,1}); // 557
    }
}
