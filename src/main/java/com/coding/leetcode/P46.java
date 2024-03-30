package com.coding.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P46 {

    public static int [] data;

    public List<List<Integer>> permute(int mask) {

        List<List<Integer>> ans = new ArrayList<>();

        for(int i = 0 ; i < data.length; i++ ){
            if (((mask >> i) & 1) == 0){
                // We can take this recur for all
                mask |= (1 << i);
                List<List<Integer>> list = permute(mask);

                for(List<Integer> per : list) {
                    per.add(data[i]);
                    ans.add(per);
                }
                // unmask this for next iteration
                mask &= ~(1 << i);
            }

        }

        if(ans.size() == 0) {
            ans.add(new ArrayList<>());
        }

        return ans;
    }

    public List<List<Integer>> permute(int[] nums) {
        data = nums;
        return permute(0);
    }

    public static void main(String[] args) {

        {
            P46 permute = new P46();
            int[] nums = {1,2,3};
            List<List<Integer>> ans = permute.permute(nums);
            for(int i = 0 ; i < ans.size(); i++){
                System.out.println(String.format("%s", ans.get(i)));
            }
        }
    }
}
