package com.coding.codechef;

import com.coding.io.InputReader;

public class Longpali {
    public static void main(String[] args) throws Exception{
        InputReader inputReader = new InputReader();
        int n = inputReader.readInt();
        String word = inputReader.readWord() + "$";

        boolean [][] arr = new boolean [n+1][n+1]; // arr[i][j] = true if string(i,j) is palindrome
        int maxi = 0 , maxj = 0, maxLen = 1;
        for(int i = 0 ; i < n ; i++) {
            arr[i][i] = true; // obviously
            arr[i][i+1] = word.charAt(i) == word.charAt(i+1); if(arr[i][i+1] == true && maxLen < 2){
                maxLen = 2;
                maxi = i;
                maxj = i+1;
            }
        }
        for(int len = 3 ; len <= n ; len++ ){
            for(int i = 0, j = len-1; j < n ; j++, i++){
                arr[i][j] = word.charAt(i) == word.charAt(j) && arr[i+1][j-1];
                if(arr[i][j] == true && j-i+1 > maxLen){
                    maxi = i;
                    maxj = j;
                }
            }
        }

        System.out.println(word.substring(maxi, maxj+1));
    }
}
