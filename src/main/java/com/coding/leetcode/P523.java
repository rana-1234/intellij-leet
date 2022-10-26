package com.coding.leetcode;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class P523 {

    public boolean checkSubarraySum(int[] nums, int k) {

        Set<Integer> modSet=new HashSet();
        int currSum = 0, prevSum=0;
        //when we add prevSum=0 in set it will actually check if currSum is divided by k
        for(int n : nums) {
            currSum += n;
            if(modSet.contains(currSum%k)) {
                return true;
            }
            currSum %=k;
            modSet.add(prevSum);
            prevSum = currSum;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new P523().checkSubarraySum(new int[]{1,0}, 2));
    }
}
