package com.coding.codechef;
import com.coding.io.InputReader;
public class LeadGame {
    private static InputReader inputReader;

    public static void main(String[] args) throws Exception {
        inputReader = new InputReader(" ");
        int n = inputReader.readInt();
        long lead = 0;
        int winner = 1;
        long player1Score = 0, player2Score = 0;

        for(int i = 0 ; i < n ; i++ ){
            // read round score for player 1 and player 2
            player1Score += inputReader.readInt();
            player2Score += inputReader.readInt();

            if(player1Score - player2Score > lead){
                // lead for player1 is more than existing
                winner = 1;
                lead = player1Score - player2Score;
            }
            else if(player2Score - player1Score > lead){
                // lead for player2 is more than existing
                winner = 2;
                lead = player2Score - player1Score;
            }
        }

        System.out.println(winner + " " + lead);
    }

}
