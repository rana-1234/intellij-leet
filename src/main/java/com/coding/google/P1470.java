package com.coding.google;

import com.coding.help.Utils;

public class P1470 {

    int n;
    private boolean isItXIndex(int index){
        return index <= n;
    }
    public int[] shuffle(int[] nums, int n) {
        /*
            x1,x2.....xn,y1,y2......yn
            Output
            x1,y1,x2,y2.......xn,yn
            Using O(1) space

            int temp; // to hold a value temporary

            x1 -> 1
            y1 -> 2

            x2 -> 3
            y2 -> 4

            x3 -> 5
            y3 -> 6

            x4 -> 7

            ...

            xn -> n + (n-1)
            yn -> 2*n
            ----------------------------------------------------------------

           So, we would keep on shifting the integers to its correct value.
           Example
           start with x2
           its position is => x (2 + 1) = 3,
           we would replace x3 with x2, and keep x3 in memory to replace.
           x3 would go to (3 + 2) = 5, x5 and so on.

           One note, indices n+1 to 2n would be y indices, so we would choose 2*index for its position
         */

        if( nums.length <= 2){
            return nums;
        }
        this.n = n;
        int index = 2;
        int temp = nums[index-1];
        int counter = 2; // All the elements would be shuffled at the end

        while(counter <= 2*n){
            boolean xIndex = isItXIndex(index);
            if (xIndex){
                // x index
                int pos = 2*index - 1;
                int temp1 = nums[pos-1];
                nums[pos-1] = temp;
                temp = temp1;
                index = pos; // In next loop this element position would be found
            }
            else{
                // y index
                // y1 occurs at index n+1
                // so we have to fist do -n, and then multiply by 2 to get the index
                int pos = 2*(index-n);
                int temp1 = nums[pos-1];
                nums[pos-1] = temp;
                temp = temp1;
                index = pos; // In next loop this element position would be found
            }
            counter++;
        }
        return nums;
    }

    public static void main(String[] args) {

        int [] ans = new P1470().shuffle(new int [] {1,2,3,4,5,6}, 3);
        Utils.printIntArray(ans);

    }
}
