package com.coding.leetcode;

import java.util.Arrays;

public class P838 {

    public String pushDominoes(String dominoes) {
        /*
            Idea:
                R .... L => All the dominoes within R and L will be impacted
        */

        int n = dominoes.length();

        if( n == 1){
            return dominoes;
        }

        char [] finalState = new char[n];
        Arrays.fill(finalState, '.'); // undecided yet

        for(int i = 0 ; i < n ; i++){
            if(dominoes.charAt(i) == 'R'){
                // finds its immediate left
                int j = i+1;
                boolean nextRightFound = false;
                while(j < n){
                    if(dominoes.charAt(j) == 'R'){
                        nextRightFound = true;
                        break;
                    }

                    if(dominoes.charAt(j) == 'L'){
                        break;
                    }
                    j++;
                }

                if(nextRightFound){
                    while(i < j ){
                        finalState[i] = 'R';
                        i++;
                    }
                    i--;
                    continue;
                }
                // R         L
                // i ....... j
                finalState[i] = 'R';
                boolean leftFound = false;
                if( j < n ) {
                    finalState[j] = 'L';
                    leftFound = true;
                }
                for(int k = i+1 ; k <= j-1 ; k++){
                    if(!leftFound) {
                        // Obvious
                        finalState[k] = 'R';
                        continue;
                    }
                    if( k - i == j - k){
                        // both are at eqi distant
                        finalState[k] = '.';
                    }
                    else if(k - i < j - k){
                        // it will fall right side
                        finalState[k] = 'R';
                    }
                    else{
                        finalState[k] = 'L';
                    }
                }
                i = j;
            }
            else if(dominoes.charAt(i) == 'L'){
                // this domino will make fall all the dominoes to its right;
                int j = i;
                while(j >= 0 && finalState[j] == '.'){
                    finalState[j] = 'L';
                    j--;
                }
            }
        }
        return new String(finalState);
    }

    public static void main(String[] args) {
        String [] testcases = {".L.R...LR..L..", "RR.L"};
        for(String s : testcases){
            System.out.println("{input:" + s + ", output:" + new P838().pushDominoes(s)+"}");
        }
    }
}
