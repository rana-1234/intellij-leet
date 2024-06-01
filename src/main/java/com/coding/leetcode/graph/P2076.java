package com.coding.leetcode.graph;

import com.coding.help.Utils;

import java.util.*;

public class P2076 {


    Set<Integer> [] fG;
    Set<Integer> [] eG;
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {

        fG = new HashSet [n];
        eG = new HashSet [n];

        for(int i = 0; i < n; i++) {
            fG[i] = new HashSet<>();
            fG[i].add(i);
            eG[i] = new HashSet<>();
        }

        for (int [] rest : restrictions) {
            eG[rest[0]].add(rest[1]);
            eG[rest[1]].add(rest[0]);
        }


        int querySize = requests.length;
        boolean [] ans = new boolean [querySize];

        for(int i = 0 ; i < querySize; i++){

            int friend1 = requests[i][0];
            int friend2 = requests[i][1];

            Set<Integer> friendSetOfFriend1 = fG[friend1];
            Set<Integer> friendSetOfFriend2 = fG[friend2];
            Set<Integer> commonFriends = new HashSet<Integer>();
            commonFriends.addAll(friendSetOfFriend1);
            commonFriends.addAll(friendSetOfFriend2);

            Set<Integer> enemySetOfFriend1 = eG[friend1];
            Set<Integer> enemySetOfFriend2 = eG[friend2];
            Set<Integer> commonEnemies = new HashSet<>();
            commonEnemies.addAll(enemySetOfFriend1);
            commonEnemies.addAll(enemySetOfFriend2);

            boolean possible = true;
            for(int f : commonFriends){
                if (commonEnemies.contains(f)){
                    possible = false;
                    break;
                }
            }

            if (!possible){
                ans[i] = false;
            }
            else{
                ans[i] = true;
                for (int f : commonFriends){
                    eG[f] = commonEnemies;
                    fG[f] = commonFriends;
                }

            }
        }
        return ans;
    }

    public static void main(String[] args) {
        {
            int n = 13;
            int [][] restrictions = {{0,1},{2,3},{6,7},{7,8},{4,5},{9,10},{11,10},{12,10}};
            int [][] requests = {{2,1},{7,4},{11,12},{12,2},{11,0}};
            Utils.printBooleanArray(new P2076().friendRequests(n, restrictions, requests));
        }

        {
            int n = 5;
            int [][] restrictions = {{0,1},{1,2},{2,3}};
            int [][] requests = {{0,4},{1,2},{3,1},{3,4}};

            Utils.printBooleanArray(new P2076().friendRequests(n, restrictions, requests));
        }

        {
            int n = 3;
            int [][] restrictions = {{0,1}};
            int [][] requests = {{0,2},{2,1}};

            Utils.printBooleanArray(new P2076().friendRequests(n, restrictions, requests));
        }

    }
}
