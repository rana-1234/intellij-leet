package com.coding.codesignal;

public class AlmostIncreasingSequence {

    static boolean solution(int[] sequence) {

        for ( int i = 1 ; i < sequence.length ; i++){
            if ( sequence[i-1] >= sequence[i]){
                // Mismatch
                // Let's remove i-1
                boolean isPossible = false;
                if((i-2 >= 0 && sequence[i-2] < sequence[i]) || (i-2 < 0 )){
                    isPossible = true;
                    for ( int j = i+1; j < sequence.length ; j++){
                        if(sequence[j-1] >= sequence[j]){
                            isPossible = false;
                            break;
                        }
                    }
                    if(isPossible){
                        return isPossible;
                    }
                }

                // Let's remove ith one
                if((i+1 < sequence.length && sequence[i-1] < sequence[i+1]) || (i+1 >= sequence.length)){
                    isPossible = true;
                    for ( int j = i+2; j < sequence.length ; j++){
                        if(sequence[j-1] >= sequence[j]){
                            isPossible = false;
                            break;
                        }
                    }
                    if(isPossible){
                        return isPossible;
                    }
                }
                return false;

            }
        }

        return true;

    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 1, 2, 3, 4, 4}));
    }

}
