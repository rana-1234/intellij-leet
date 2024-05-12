package com.coding.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class P179 {

    public String largestNumber(int[] nums) {

        nums = Arrays.stream(nums).boxed().sorted((x, y) -> {

            String xS = String.valueOf(x);
            String yS = String.valueOf(y);

            String xSAndyS = xS+yS;
            String ySAndxS = yS+xS;

            if (xSAndyS.compareTo(ySAndxS) < 0){
                return -1;
            }
            return 1;
        }).mapToInt(Integer::intValue).toArray();

        StringBuilder sb = new StringBuilder();

        for (int num : nums) {
           sb.append(num);
        }

        String ans = sb.toString();
        int i = 0;
        while(i < ans.length() && ans.charAt(i) == '0')i++;
        ans = ans.substring(i);
        if ( ans.length() == 0){
           ans = "0";
        }
        return ans;
    }

    public static void main(String[] args) {
        {
            int [] nums = {90, 9, 900, 99, 8, 10};
            System.out.println(new P179().largestNumber(nums));
        }

        {
            int [] nums = {3,30,34,5,9};
            System.out.println(new P179().largestNumber(nums));

        }
    }

}
