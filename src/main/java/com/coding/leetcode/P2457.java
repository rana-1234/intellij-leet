package com.coding.leetcode;

public class P2457 {

    private boolean isBeautiful(long n, int target){
        int sum = 0;
        while(n > 0){
            sum += n%10;
            n /= 10;
        }
        return sum <= target;
    }

    public long makeIntegerBeautiful(long n, int target) {

        long original = n, base = 1;
        while(!isBeautiful(n, target)){
            n = n/10 + 1; // 1234567 => 123457 (reducing sum by 7 by increasing the immediate)
            base *= 10; // base will tell us, how many digits we have changed
        }
        // 1240000 - 1234567 = this is our x which will be added to get a minimum beautiful number
        return n*base - original;
    }

    public static void main(String[] args) {
        System.out.print(new P2457().makeIntegerBeautiful(734504727, 10));
    }
}
