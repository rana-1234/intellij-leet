package com.coding.ds;

public class ZAlgorithm {

    // source : https://www.geeksforgeeks.org/z-algorithm-linear-time-pattern-searching-algorithm/

    /*
        What is Z Array?

        For a string str[0..n-1], Z array is of same length as string. An element Z[i] of Z array stores length of the
        longest substring starting from str[i] which is also a prefix of str[0..n-1].
        The first entry of Z array is meaning less as complete string is always prefix of itself.

        How to construct Z array?
        A Simple Solution is to run two nested loops, the outer loop goes to every index and the inner loop finds length
        of the longest prefix that matches the substring starting at the current index. The time complexity of this solution
        is O(n2).
        We can construct Z array in linear time.

        The idea is to maintain an interval [L, R] which is the interval with max R
        such that [L,R] is prefix substring (substring which is also prefix).
        Steps for maintaining this interval are as follows –
        1) If i > R then there is no prefix substring that starts before i and
           ends after i, so we reset L and R and compute new [L,R] by comparing
           str[0..] to str[i..] and get Z[i] (= R-L+1).
        2) If i <= R then let K = i-L,  now Z[i] >= min(Z[K], R-i+1)  because
           str[i..] matches with str[K..] for atleast R-i+1 characters (they are in
           [L,R] interval which we know is a prefix substring).
           Now two sub cases arise –
              a) If Z[K] < R-i+1  then there is no prefix substring starting at
                 str[i] (otherwise Z[K] would be larger)  so  Z[i] = Z[K]  and
                 interval [L,R] remains same.
              b) If Z[K] >= R-i+1 then it is possible to extend the [L,R] interval
                 thus we will set L as i and start matching from str[R]  onwards  and
                 get new R then we will update interval [L,R] and calculate Z[i] (=R-L+1).

     */

    public static void main(String[] args) {

        String s = "aaazaaaaz";
        int n = s.length();
        int [] z = new int[n];
        z[0] = 0; // First is okay to keep it is

        int left = 0, right = 0; // My Z Array Index
        for(int k = 1 ; k < n ; k++ ){
            if ( k > right){
                left = right = k; //  left....right => effectively its happening like s.charAt(right) match starts from index 0 (right -left) = 0,1,2
                while(right < n && s.charAt(right) == s.charAt(right-left)){
                    right++;
                }
                z[k] = right - left; // and the length of the z box is the z[k]
                right--; // And now right would exceed k, in case of any match, so the else block is for operating on the z range(left, right)
            }
            else {
                //
                int k1 = k - left;
                // if values does not stretches till right bound (k1), then we would simply copy the value
                if (z[k1] + k <= right) {
                    // value we are copying at index k1. z[k1] < right - (k - 1) // basically z[k1] + (k-1) < right
                    z[k] = z[k1];
                } else {
                    // otherwise there can be more length further and we need to extend right
                    left = k; // setting left as k, from where we need to start the match again
                    while (right < n && s.charAt(right) == s.charAt(right - left)) {
                        right++;
                    }
                    z[k] = right - left;
                    right--;
                }
            }
        }

        System.out.println(s);
        for ( int e : z){
            System.out.print(" " + e);
        }

    }
}
