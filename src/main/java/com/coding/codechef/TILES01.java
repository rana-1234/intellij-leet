package com.coding.codechef;

import com.coding.io.InputReader;

public class TILES01 {


    public static void main(String[] args) throws Exception{
        InputReader in = new InputReader();
        int N = in.readInt();

        if( N == 1){
            System.out.println(1);
        }
        else if(N == 2){
            System.out.println(2); // 11, 00
        }
        else {
            int mod = 15746;
            int[] dpEndingAt1 = new int[N + 1];
            int[] dpEndingAt0 = new int[N + 1];

            dpEndingAt1[1] = 1;
            dpEndingAt1[2] = 1;  // 11
            dpEndingAt0[1] = 0;
            dpEndingAt0[2] = 1; // 00

            for (int i = 3; i <= N ; i++){
                dpEndingAt1[i] = (dpEndingAt1[i-1] + dpEndingAt0[i-1])%mod; // 01 or 11
                dpEndingAt0[i] = (dpEndingAt1[i-2] + dpEndingAt0[i-2])%mod; // 100 or 000
            }
            System.out.println((dpEndingAt1[N] + dpEndingAt0[N])%mod);
        }
    }
}
