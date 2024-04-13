package com.coding.dp;

public class P55 {

    public boolean canJump(int[] nums) {

        if( nums.length == 1){
            return true;
        }

        if( nums[0] == 0){
            return false;
        }

        int steps = nums[0];
        int maxReach = nums[0];
        int jump = 1;
        for(int i = 1 ; i < nums.length && steps > 0 ; i++) {
            maxReach = Math.max(maxReach, i + nums[i]);
            steps--;
            if ( steps == 0){
                // we have taken all the steps that we could have taken from initial position
                // Now we need to make a jump
                if( maxReach < nums.length) {
                    jump++;
                }
                steps = maxReach - i;
            }
        }
        return maxReach >= nums.length-1;
    }

    public static void main(String[] args) {
        System.out.println(new P55().canJump(new int [] {2,3,1,1,4}));
    }
}
