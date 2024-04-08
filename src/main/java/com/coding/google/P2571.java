package com.coding.google;


import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/minimum-operations-to-reduce-an-integer-to-0/description/
public class P2571 {

    public int minOperations(int n) {
        int ans = 0;

        List<Integer> bin = new ArrayList<>();
        while( n > 0 ){
            if ((n&1) > 0 ){
                bin.add(1);
            }
            else{
                bin.add(0);
            }
            n >>= 1;
        }

        int[] binArray = bin.stream().mapToInt(Integer::intValue).toArray();

        for(int i = 0 ; i < binArray.length; i++){
            // the consecutive 1's can be removed by adding 1,
            if(binArray[i] == 1){
                ans++; // Adding or removing
                int j = i+1;
                boolean insideLoop = false;
                while(j < binArray.length && binArray[j] == 1){
                    insideLoop = true;
                    j++;
                }
                // Either j is pointing to zero or end
                if( j == binArray.length && insideLoop == true){
                    ans++;
                }
                else if(insideLoop){
                    binArray[j] = 1;
                }
                i = j-1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new P2571().minOperations(39));
    }
}
