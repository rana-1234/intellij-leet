package com.coding.leetcode.tree;

import java.util.*;
import java.util.stream.Collectors;

public class P1569 {


    public static int [][] nCr ;
    public static int MOD = 1000000007;

    int countWays(List<Integer> nums){
        if ( nums.size() < 3){
            // Base case
            return 1;
        }
        // Root node will be always at 0th index
        List<Integer> leftSubtree = new ArrayList<>();
        List<Integer> rightSubtree = new ArrayList<>();

        for(int i = 1; i < nums.size(); i++){
            if ( nums.get(i) < nums.get(0)){
                leftSubtree.add(nums.get(i));
            }
            else{
                rightSubtree.add(nums.get(i));
            }
        }

        int leftSubtreeAns = countWays(leftSubtree);
        int rightSubtreeAns = countWays(rightSubtree);
        int leftAndRight = (int)((1L*leftSubtreeAns*rightSubtreeAns)%MOD);
        int finalAns = (int)((1L*leftAndRight*nCr[nums.size()-1][leftSubtree.size()])%MOD);
        return finalAns;

    }

    public int numOfWays(int[] nums) {
       int totalRow = nums.length;

       nCr = new int[totalRow+1][totalRow+1];
       nCr[0][0] = 1;
       nCr[1][0] = 1;
       nCr[1][1] = 1;
       for (int n = 2 ; n <= totalRow; n++) {
           nCr[n][0] = 1;
           for (int r = 1; r <= n; r++) {
             nCr[n][r] = (nCr[n-1][r] + nCr[n-1][r-1])%MOD;
           }
       }
       return countWays(Arrays.stream(nums).boxed().collect(Collectors.toList())) - 1 ;
    }

    public static void main(String[] args) {
        {
            int [] nums = {4,2,7,1,3,6,8,5,9,10,12,11,20,18,19,13,15,14,17,16,30,21,22,24,23,27,29,28,26,25};
            System.out.println(new P1569().numOfWays(nums));
        }

        {
            int [] nums = {3,4,5,1,2};
            System.out.println(new P1569().numOfWays(nums));
        }
    }
}
