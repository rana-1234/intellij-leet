package com.coding.ms;

public class FirstProblem {

    public static int convert(char c){
        if ( c == 'A'){
            return 1;
        }
        else if(c == 'B'){
            return -1;
        }
        else{
            return 0;
        }
    }

    public int solution(String[] board) {
        // Implement your solution here

        int ans = 0;
        int n = board.length;
        int m = board[0].length();

        int [][] data = new int[n][m];
        data[0][0] = convert(board[0].charAt(0));

        // We got 1 rectangle with A = 0 , B = 0
        if( data[0][0] == 0){
            ans++;
        }

        // Calculate the first row
        for(int j = 1 ; j < n ; j++ ){
            data[0][j] = convert(board[0].charAt(j));
            data[0][j] += data[0][j-1];
            if( data[0][j] == 0){
                ans++;
            }
        }

        for(int i = 1 ; i < n ; i++){
            data[i][0] = convert(board[i].charAt(0));
            int rowWiseSum = data[i][0];
            data[i][0] += data[i-1][0];
            if ( data[i][0] == 0){
                ans++;
            }

            for(int j = 1 ; j < m ; j++){
                data[i][j] = convert(board[i].charAt(j));
                int temp = data[i][j];
                data[i][j] += data[i-1][j] + rowWiseSum;
                if( data[i][j] == 0){
                    ans++;
                }
                rowWiseSum += temp;
            }
        }
       return ans;
    }


    public static void main(String[] args) {
        System.out.println(new FirstProblem().solution(new String[]{"AB.", "B..", "..A"}));
    }
}
