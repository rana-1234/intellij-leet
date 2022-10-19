package com.coding.leetcode;

public class P1335 {

    private int [] jobDifficult;
    private int totalJob;
    private int [][] maxDifficult;
    private int totalDays;

    public int getBestScheduling(int startedFrom, int cj, int cd){

        if( cj >= totalJob || cd > totalDays){
            return 0;
        }
        // we can either schedule this job in same day or we can schedule it in next day

        // Scheduling on the same day

        if (totalJob - cj == totalDays - cd + 1){
            // we have to schedule this job in next day no, choice
            return maxDifficult[startedFrom][cj] + getBestScheduling(cj+1, cj+1, cd+1);
        }
        else{
            return Math.min(maxDifficult[startedFrom][cj] + getBestScheduling(cj+1, cj+1, cd+1) , getBestScheduling(startedFrom, cj+1, cd));
        }

    }

    public int minDifficulty(int[] jobDifficulty, int d) {
        // it's like putting the d-1 vertical lines among jobDifficult array
        // And take the maximum one from these d sections

        int n = jobDifficulty.length;
        if( n < d){
            return -1; // Each day we can't schedule a job
        }

        totalDays = d;
        this.jobDifficult = jobDifficulty;
        totalJob = n;
        maxDifficult = new int [n][n];
        for(int i = 0; i < n ; i++)
            maxDifficult[i][i] = jobDifficulty[i];
        for(int i = 0 ; i < n ; i++)
            for(int j = i+1 ; j < n ; j++)
                maxDifficult[i][j] = Math.max(maxDifficult[i][j-1],jobDifficulty[j]);

        return getBestScheduling(0, 0, 1);

    }

    public static void main(String[] args) {
        System.out.println(new P1335().minDifficulty(new int []{6,5,4,3,2,1}, 2));
        System.out.println(new P1335().minDifficulty(new int []{9,9,9}, 4));
        System.out.println(new P1335().minDifficulty(new int []{1,2,4,6,3,2,4,121,223,11,23,21,21,121,33,12,13,21,10,21,3,2,1,1,12}, 4));


    }
}
