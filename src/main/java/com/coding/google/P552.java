package com.coding.google;

import java.util.Arrays;

public class P552 {
    /*

    */

    int n;
    public static int MAX = 100001;

    static long [][][][] dp = new long[MAX][3][2][2];
    static long mod = 1000000007;

    static {
        for(int i = 0 ; i < MAX ; i++){
            for(int j = 0 ; j < 3 ; j++){
                for(int k = 0 ; k < 2 ; k++){
                    for(int l = 0 ; l < 2 ; l++){
                        dp[i][j][k][l] = -1;
                    }
                }
            }
        }
    }


    public long f ( int currentIndex, int lastChar, int aExists, int canTakeL ){

    /*
        0 = A
        1 = L
        2 = P

     */
        if (currentIndex == 1) {
            return 1; // 1 combo
        }

        if( dp[currentIndex][lastChar][aExists][canTakeL] != -1){
            return dp[currentIndex][lastChar][aExists][canTakeL];
        }

        int ans ;

        if (aExists == 1) {
            // Can't take a here

            if (lastChar == 2) {
                 dp[currentIndex][lastChar][aExists][canTakeL] = f(currentIndex - 1, 2, 1, 1)
                        // taking P at end
                        + f(currentIndex - 1, 1, 1, 1);
                // taking L at end
            } else if (lastChar == 1) {
                // for sure lastChar == 1
                if (canTakeL == 1) {
                    // Means we can append L here
                     dp[currentIndex][lastChar][aExists][canTakeL] = f(currentIndex - 1, 1, 1, 0)
                            // taking L at end
                            + f(currentIndex - 1, 2, 1, 1);
                    // taking P at end
                } else {
                    // taking only P here
                    dp[currentIndex][lastChar][aExists][canTakeL] = f(currentIndex - 1, 2, 1, 1);
                }
            } else {
                // last char == 0 (A)
                 dp[currentIndex][lastChar][aExists][canTakeL] = f(currentIndex - 1, 2, 1, 1) +
                        // taking P
                        f(currentIndex - 1, 1, 1, 1);
                // taking L
            }
        } else {
            // Can include a here
            if (lastChar == 2) {
                // its P
                dp[currentIndex][lastChar][aExists][canTakeL] = f(currentIndex - 1, 2, 0, 1) +
                        // taking P
                        f(currentIndex - 1, 1, 0, 1) +
                        // taking L
                        f(currentIndex - 1, 0, 1, 1);
                // taking A here
            } else {
                // Last char is L here
                if (canTakeL == 1) {
                    dp[currentIndex][lastChar][aExists][canTakeL] = f(currentIndex - 1, 2, aExists, 1) +
                            // taking P
                            f(currentIndex - 1, 0, 1, 1) +
                            // taking A
                            f(currentIndex - 1, 1, aExists, 0);
                } else {
                    // L can't be taken
                    dp[currentIndex][lastChar][aExists][canTakeL] = f(currentIndex - 1, 0, 1, 1) +
                            // taking A
                            f(currentIndex - 1, 2, aExists, 1);
                }
            }
        }

        dp[currentIndex][lastChar][aExists][canTakeL] %= mod;
        return dp[currentIndex][lastChar][aExists][canTakeL];
    }


    public int checkRecord(int n) {
        
        return (int) ((f(n, 0, 1, 1) +
                f(n, 1, 0, 1) +
                f(n, 2, 0, 1)) % mod);

    }


    public static void main(String[] args) {
        for(int i = 1 ; i < 10; i++){
            System.out.println(new P552().checkRecord(i));
        }


    }
}
