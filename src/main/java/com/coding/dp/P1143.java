package com.coding.dp;

import java.util.Arrays;

public class P1143 {

    public static String str1;
    public static String str2;

    int [][] dp ;

    public int lcs(int i, int j) {

        // Base case
        if ( i == str1.length() || j == str2.length() ){
            return 0;
        }

        if( dp[i][j] != -1 ){
            return dp[i][j];
        }

        if ( str1.charAt(i) == str2.charAt(j)){
            return dp[i][j] = 1 + lcs(i+1, j+1);
        }
        else{
            return dp[i][j] = Math.max(lcs(i, j+1), lcs(i+1, j));
        }

    }

    public int longestCommonSubsequence(String text1, String text2) {
        str1 = text1;
        str2 = text2;

        dp = new int[str1.length()][str2.length()];
        Arrays.stream(dp).forEach(x -> Arrays.fill(x, -1));
        return lcs(0, 0);
    }


    public static void main(String[] args) {
        System.out.println(new P1143().longestCommonSubsequence("Shashi", "Neha"));
    }
}
