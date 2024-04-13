package com.coding.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class P39 {

    public static List<List<Integer>> ans ;
    public static List<Integer> candidate;

    public void getAns(int currentIndex, int target, List<Integer> currentList){
        if ( target < 0 || currentIndex >= candidate.size()) {
            return ;
        }

        if( target == 0){
            ans.add(currentList);
            return;
        }

        // if currentIndex does not support the sum, or does support the current index

        List<Integer> withCurrentIndex = new ArrayList<>(currentList);
        withCurrentIndex.add(candidate.get(currentIndex));
        List<Integer> withoutCurrentIndex = new ArrayList<>(currentList);
        getAns(currentIndex,target-candidate.get(currentIndex), withCurrentIndex);
        getAns(currentIndex+1,target,withoutCurrentIndex);

    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        ans = new ArrayList<>();
        candidate = Arrays.stream(candidates).boxed().collect(Collectors.toList());
        getAns(0, target, new ArrayList<>());
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new P39().combinationSum(new int []{2,3,6,7}, 7));
    }
}
