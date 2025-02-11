package com.coding.leetcode;
import java.util.*;

public class P3434 {

    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int [][] sorted = new int[n][2];

        for ( int i = 0 ; i < n ; i++){
            sorted[i] = new int []{startTime[i], endTime[i]};
        }

        Arrays.sort(sorted, (p,q) -> Integer.compare(p[0], q[0]));

        // System.out.print("Sorted :  ");
        // for ( int i = 0 ; i < n ; i++){
        //      System.out.print("[" + sorted[i][0] + "," + sorted[i][1] + "] ");
        // }
        // System.out.println();

        int [] diff = new int [n+1];
        int ce = 0;

        for ( int i = 0; i < n ; i++){
            diff[i] = sorted[i][0] - ce;
            ce = sorted[i][1];
        }
        diff[n] = eventTime - ce;
        Stack<Integer> maxGapInRight = new Stack<>();
        maxGapInRight.add(-1);

        for ( int i = n ; i >= 1 ; i--){
            if ( maxGapInRight.peek() <= diff[i] ){
                maxGapInRight.push(diff[i]);
            }
            else{
                maxGapInRight.push(maxGapInRight.peek());
            }
        }

        int ans = 0;
        int left_max = 0;
        for ( int i = 0 ; i < n ; i++){
            ans = Math.max(ans, diff[i+1] + diff[i]);
            int next_meeting_len = sorted[i][1] - sorted[i][0];
            // check if the meeting can fit in the left gap
            if ( left_max >= next_meeting_len){
                ans = Math.max(ans, diff[i+1] + diff[i] + next_meeting_len);
            }

            maxGapInRight.pop();
            if ( maxGapInRight.peek() >= next_meeting_len){
                ans = Math.max(ans, diff[i+1] + diff[i] + next_meeting_len);
            }

            left_max = Math.max(left_max, diff[i]);
        }

        return ans;
    }


    public static void main(String[] args) {
        {
            P3434 p3434 = new P3434();
            System.out.println(p3434.maxFreeTime(34, new int[]{0,17}, new int[]{14,19}));
        }
    }
}
