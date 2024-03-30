package com.coding.leetcode;

import java.sql.Time;
import java.util.*;

public class BWC115 {


    // Problem 1. 2899. Last Visited Integers
    public static List<Integer> lastVisitedIntegers(List<String> words) {
        List<Integer> numbers = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        int lastVisited = -1;
        for(String word : words){
            if( word.equals("prev")){
                if(lastVisited >= 0 ){
                    ans.add(numbers.get(lastVisited));
                    lastVisited--;
                }
                else {
                    ans.add(-1);
                }
            }
            else{
                // System.out.println(word);
                numbers.add(Integer.parseInt(word));
                lastVisited = numbers.size()-1;
            }
        }
        return ans;
    }

    // Problem 2. 2900. Longest Unequal Adjacent Groups Subsequence I
    public List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {

        List<String> ans = new ArrayList<>();

        int lastTaken = groups[0];
        ans.add(words[0]);
        for(int i = 1 ; i < n; i++) {
            if(groups[i] != lastTaken){
                lastTaken = groups[i];
                ans.add(words[i]);
            }
        }

        return ans;

    }


    public boolean atUnitHammingDistance(String s1, String s2){
        if( s1.length() != s2.length()){
            return false;
        }
        int misCount = 0;
        for(int i = 0 ;i < s1.length() && misCount <= 1; i++){
            if( s1.charAt(i) != s2.charAt(i)){
                misCount++;
            }
        }

        return misCount == 1;
    }

    // Problem 3: 2901. Longest Unequal Adjacent Groups Subsequence II
    public List<String> getWordsInLongestSubsequence2(int n, String[] words, int[] groups) {

        // Idea is to use more or less LIS (Long est increasing subsequence)
        // LIS[i] = 1 + LIS[j] for all j < i && A[i] > A[j] else 1
        // We can use similar pattern here, at index i, we would add the string at i into the set if HammingDistance(s[i], S[j]) == 1 && group[S[i]] != group[S[j]]

        if ( n == 1){
            return List.of(words[0]);
        }

        int [] lastIndexUsedAtI = new int[n];
        int [] maxSetSize = new int[n];
        lastIndexUsedAtI[0] = 0;
        maxSetSize[0] = 1;
        int startFrom = 0;
        int longestSetSize = 1;

        for(int i = 1 ; i < n ; i++){
            maxSetSize[i] = 1;
            lastIndexUsedAtI[i] = i;
            for ( int j = 0; j < i ; j++){
                if (groups[i] != groups[j] && atUnitHammingDistance(words[i], words[j]) && maxSetSize[i] < maxSetSize[j] + 1){
                    lastIndexUsedAtI[i] = j;
                    maxSetSize[i] = maxSetSize[j] + 1;
                }
            }

            if (longestSetSize < maxSetSize[i]){
                longestSetSize = maxSetSize[i];
                startFrom = i;
            }
        }

        List<String> ans = new ArrayList<>();
        while(true){
            ans.add(words[startFrom]);
            if (startFrom == lastIndexUsedAtI[startFrom]){
                break;
            }
            startFrom = lastIndexUsedAtI[startFrom];
        }
        Collections.reverse(ans);
        return ans;
    }


    public List<Integer> freq;
    public List<Integer> value;
    public Map<Map.Entry<Integer, Integer>, Integer> dp; // to memorize
    public int numSize ;
    public int mod = (int) 1e9 + 7;

    public int f(int s, int i){
        if ( s == 0){
            // we got 1 set here
            return 1;
        }
        if (s < 0 || i == numSize){
            // in either case we can't make the sum with the indices.
            return 0;
        }

        // Check if we have already calculated this
        if (dp.get(Map.entry(s, i)) != null){
            return dp.get(Map.entry(s,i));
        }

        int setCount = 0;
        for( int currentFreq = 0 ; currentFreq <= freq.get(i); currentFreq++){
            setCount = setCount + f(s - currentFreq*value.get(i), i+1);
            if ( setCount > mod){
                setCount %= mod;
            }
        }

        // Let's cache this result
        dp.put(Map.entry(s,i), setCount);

        return setCount;

    }

    // Problem 4: 2902. Count of Sub-Multisets With Bounded Sum
    public int countSubMultisets(List<Integer> nums, int l, int r) {
        // https://www.youtube.com/watch?v=V6AoVVxt18Y best video to explain it.

        /*
            if elements were unique in the nums list,
                then we would have got the number of subsets for sum = s as follows

            f(s, i) = f(s, i+1) + f (s-nums[i], i+1)
                    = not including the number in the set + including the number in the set to get sum = s

            However, when the numbers are repeating, as per above formula, we would count the set {1,2,1} and {1,1,2} as two different sum for (s =4), however as per the problem we should count this one set.

            So, we can re-structure the equation as
            f(s, i) = f(s-k*nums[i], i+1) for k = 0 to freq(nums[i]) in the list. We take zero nums[i], or take 1 nums[i] or take 2 nums[i] etc.
         */

        freq = new ArrayList<>();
        value = new ArrayList<>();
        dp = new HashMap<>();
        Map<Integer, Integer> valueFreq = new HashMap<>();

        for(int i = 0 ; i < nums.size(); i++)
            valueFreq.put(nums.get(i), valueFreq.getOrDefault(nums.get(i), 0) + 1);

        valueFreq.entrySet().forEach(entry -> {
            value.add(entry.getKey());
            freq.add(entry.getValue());
        });
        numSize = value.size();
        // Now we would collect all the results from l to r. Since all sum must have been calculated while calculating sum r.
        int ans = 0;
        if ( l == 0 ){
            ans += valueFreq.get(0) + 1;
            l++;
        }


        for(int i = l ; i <= r ; i++){
            ans = ans + f(i, 0);
            if( ans > mod){
                ans = ans % mod;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        BWC115 bwc = new BWC115();

        bwc.getWordsInLongestSubsequence2(6, new String [] {"aca","dd","aab","dac","adb","bad"}, new int [] {2,6,2,1,4,3});

        System.out.println(bwc.countSubMultisets(List.of(2,1,4,2,7), 1, 5));
        System.out.println(bwc.countSubMultisets(List.of(1,2,1,3,5,2), 3, 5));
        System.out.println(bwc.countSubMultisets(List.of(0,0,0,0,0), 0, 0));


        long startTime = System.currentTimeMillis();
        System.out.println(bwc.countSubMultisets(List.of(2,6,1,1,1,5,0,6,7,2,4,4,12,8,7,4,1,10,1,2,6,3,0,3,4,16,3,6,1,7,1,1,8,1,4,2,4,1,7,1,3,3,2,6,0,1,3,5,1,4,2,0,4,1,9,0,2,2,2,1,5,1,0,9,5,6,5,2,3,1,7,1,2,4,1,5,1,2,2,0,2,2,4,1,1,2,1,5,0,1,3,3,0,1,3,4,0,1,3,0,2,5,2,1,0,19,8,2,25,0,2,0,2,1,0,1,2,11,0,1,2,7,3,7,8,9,1,3,0,1,5,2,1,3,2,1,4,2,1,25,8,0,2,3,2,12,0,4,2,10,2,3,3,5,2,2,8,5,1,5,1,2,3,3,2,1,0,5,2,3,0,4,1,8,8,5,1,5,3,3,3,7,10,5,3,2,2,2,7,0,1,2,4,12,8,1,0,2,7,4,3,1,1,3,2,5,5,0,2,2,8,2,4,6,4,6,2,11,1,1,6,7,4,0,7,6,14,18,1,3,1,1,3,8,1,1,5,3,5,10,1,5,0,0,17,15,6,1,1,5,2,15,3,2,3,1,2,0,22,4,1,4,3,6,2,17,2,3,1,5,6,10,5,2,10,0,3,0,9,11,4,3,1,8,3,6,2,5,7,1,2,7,0,2,4,4,4,10,2,3,9,4,1,8,4,1,2,4,8,0,4,1,4,8,5,0,1,3,3,23,26,7,2,1,17,5,7,1,11,5,11,3,5,4,8,8,5,14,2,6,2,1,5,6,2,4,8,4,2,6,12,7,3,8,6,2,3,5,17,10,2,2,0,0,2,0,5,3,6,1,3,2,13,5,1,2,9,0,3,0,1,14,6,0,1,5,5,1,4,11,3,11,4,0,11,3,1,3,1,11,1,6,2,5,7,4,2,0,0,1,6,3,6,2,4,7,9,0,5,5,0,1,3,0,0,4,4,0,1,3,4,4,8,9,5,6,7,4,2,3,13,1,2,1,6,2,1,3,1,1,11,22,3,1,1,2,1,3,0,4,3,2,1,13,2,1,7,3,0,9,7,6,1,1,3,4,3,2,0,3,3,4,5,2,0,2,4,14,3), 330, 1442));
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}
