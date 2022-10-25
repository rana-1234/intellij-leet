package com.coding.codechef;

import com.coding.io.InputReader;

public class NEXTPERM {

    public static int [] getNextPermutation(int [] current){
        /*
            from back find the sequence in decreasing order
            wherever sequence is not in decreasing order

            1 2 3 4   9 8 7 6 5
            1 2 3 5   4 6 7 8 9
            Logic : Next permutation = bring the last character first, put the violating character in reverse order

            2 4 3 1
            3 1 2 4

            2 3 1

            1 2 3 7 9 8 6 5 4
            1 2 3 8  4 5 6 7 9

            current.substring(0,i-1) + current.charAt(with-1) + new StringBuilder(current.substring(i, with-1) + current.charAt(from) + current.substring(with)).reverse();

         */

        for(int i = current.length - 1 ; i > 0 ; i--){
            if(current[i] > current[i-1]){
               // so this character at i-1 is violating the sequence
                int from = i-1; // Marking this character
                int with = i;
                while(with < current.length && current[from] < current[with]){
                    with++;
                }
                int [] ans = new int[current.length];
                int p ;
                for(p = 0 ; p < i-1; p++){
                    ans[p] = current[p];
                }
                ans[p++] = current[with-1];
                int x = current.length - 1;
                while(x >= with){
                    ans[p++] = current[x];
                    x--;
                }
                ans[p++] = current[from];
                x = with-2;
                while(x >= i){
                    ans[p++] = current[x];
                    x--;
                }
                return ans;
            }
        }

        int [] ans = new int[current.length];
        for(int i = 0, j = current.length-1; j >= 0 ; j--, i++){
            ans[i] = current[j];
        }
        return ans;
    }

    public static void main(String[] args) throws Exception{
        InputReader in = new InputReader();
        int n = in.readInt();
        int k = in.readInt();
        for(int i = 0 ; i < k ; i++ ){
            int [] number = new int[n];
            for(int  j = 0 ; j < n ; j++){
                number[j] = in.readInt();
            }
            int [] ans =  getNextPermutation(number);
            for(int p = 0 ; p < ans.length ; p++){
                System.out.print(ans[p] + " ");
            }
            System.out.println();
        }
    }
}
