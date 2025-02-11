package com.coding.leetcode;

public class P3448 {

    public long countSubstrings(String s) {

        // we can make use of dp here to count the number of subarrays such that
        // deigit is divisible by last_non_zero digit, giving remainder to be zero
        int n = s.length(); //
        int [][][] dp = new int [n][10][10];
        // (i,j,k) => means count of the number of subarrays ending at i, with divisor j giving remainder k

        for ( int j = 1; j <= 9 ; j++ ){
            dp[0][j][(s.charAt(0) - '0')%j] = 1; // the digit itself
        }

        for ( int i = 1 ; i < n ; i++ ){
            for ( int j = 1 ; j <= 9 ; j++ ){
                for ( int rem = 0 ; rem < j ; rem++ ){
                    // so we know the number of strings, when divisor is j and rem is k
                    // including this digit would be (rem + d)%j = new_remainder with j, which will
                    // have same count as the count of i-1, j, rme
                    dp[i][j][((s.charAt(i) - '0') + rem*10)%j] += dp[i-1][j][rem];
                }
                dp[i][j][(s.charAt(i) - '0')%j]++; // starting a new substring from here
            }
        }

        long ans = 0;
        for ( int i = 0 ; i < n ; i++ ){
            for ( int j = 0 ; j <= 9 ; j++ ){
                System.out.print(i + " : " + j + " :=> ");
                for ( int k = 0 ; k < j ; k++ ){
                    System.out.print(dp[i][j][k] + " ");
                }
                System.out.println();
            }
            if ( s.charAt(i) != '0' ){
                // then need to take the previous digit sum
                ans += dp[i][s.charAt(i) - '0'][0];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        {
            String test = "102";
            P3448 p3448 = new P3448 ();
            p3448.countSubstrings(test);
        }
    }
}
