package com.coding.dp;

import java.util.Arrays;

public class P91 {

    public int numDecodings(String s) {
        if(s.length() < 1 ||  s.charAt(0) == '0'){
            return 0; // No such decoding
        }

        int n = s.length();
        int [] dp = new int[n];
        // dp [i] === number of decoding till (0, i)
        Arrays.fill(dp, 0);
        dp[0] = 1 ;

        for(int i = 1 ; i < n ; i++ ){
            // Handling 0
            if ( s.charAt(i) == '0'){
                if(s.charAt(i-1) == '0' || s.charAt(i-1) > '2' )
                    return 0; // No decoding
                else{
                    dp[i] = i-2 >=  0 ? dp[i-2] : 1;
                }
            }
            else{
                if ((s.charAt(i-1) == '1') || (s.charAt(i-1) == '2' && s.charAt(i) >= '1' && s.charAt(i) <= '6')){
                    dp[i] = dp[i-1] + (i-2 >= 0 ? dp[i-2]: 1);
                }
                else{
                    dp[i] = dp[i-1];
                }
            }
        }
        return dp[n-1];
    }


    public static void main(String[] args) {
        System.out.println(new P91().numDecodings("12"));
    }
}
