package com.coding.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P1323 {

    public int maximum69Number (int num) {
        List<Integer> nums = new ArrayList<>();
        while(num>0){
            nums.add(num%10);
            num /= 10;
        }

        int ans = 0;
        boolean flipped = false;
        for(int i = nums.size() - 1 ; i >= 0 ; i--){
            if(!flipped && nums.get(i) == 6){
                flipped = true;
                ans = ans*10 + 9;
            }
            else{
                ans = ans*10 + nums.get(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new P1323().maximum69Number(966666));
    }
}
