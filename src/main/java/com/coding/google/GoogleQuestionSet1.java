package com.coding.google;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

// This is taken from this pdf : https://libaoj.in/LeetCode-Solutions/Google/Google.pdf
public class GoogleQuestionSet1 {

    public static final class ListNode{
        public int val;
        public ListNode next;
        public ListNode(){
        }
        public ListNode(int x){
            this.val = x;
        }
        public ListNode(int x, ListNode next){
            this.next = next;
            this.val = x;
        }

        public static ListNode getListNodeFrom(int[] ints) {
            int n = ints.length;

            if ( n == 0 ){
                return null;
            }

            ListNode listNode = new ListNode(ints[0]);
            ListNode temp = listNode;
            for(int i = 1 ; i < n ; i++){
                listNode.next = new ListNode(ints[i]);
                listNode = listNode.next;
            }
            return temp;
        }

        public void printList(){
            System.out.print(this.val + " ");
            if( this.next != null)
                this.next.printList();
            else
                System.out.println();
        }
    }

    // Q1. Two Sum

    /*
    Given an array of integers, return indices of the two numbers such that they add
    up to a specific target.
    You may assume that each input would have exactly one solution, and you may not
    use the same element twice.
    Example:
    Given nums = [2, 7, 11, 15], target = 9,
    Because nums[0] + nums[1] = 2 + 7 = 9,
    return [0, 1].
     */

    public static int [] getTwoSum(int [] nums, int requiredSum){
        // Sort and using two pointers find the sum O(nlogN)

        Arrays.sort(nums);
        int i = 0, j = nums.length-1;
        while(i < j){
            if ( nums[i] + nums[j] == requiredSum){
                return new int []{i, j};
            }
            else if(nums[i] + nums[j] < requiredSum){
                // increase the number
                i++;
            }
            else{
                j--;
            }
        }
        return new int []{-1,-1}; // No such sum exists

        // Using HashMap. Hash the number, and find for the target-nums[i] present in hashMap, if yes, the target sum is present, O(N)
    }

    /*
        Given a string, find the length of the longest substring without repeating
        characters.
        Example 1:
        Input: "abcabcbb"
        Output: 3
        Explanation: The answer is "abc", with the length of 3.
        Example 2:
        Input: "bbbbb"
        Output: 1
        Explanation: The answer is "b", with the length of 1.
        Example 3:
        Input: "pwwkew"
        Output: 3
        Explanation: The answer is "wke", with the length of 3.
         Note that the answer must be a substring, "pwke" is a subsequence
        and not a substring.
     */

    public static int getLongestSubstringWithoutRepeatingCharacters(String str){
        // We can use sliding window algorithm here.

        int i = -1, j = -1;
        int n = str.length();
        int [] hash = new int[26];
        int maxLength = 0;
        while(j < n-1){
            j++;
            if (hash[str.charAt(j) - 'a'] != 0) {
                // we got a repeating character.
                i++;
                maxLength = Math.max(maxLength, j - i); // j is pointing to one character extra.
                while (i < j && str.charAt(i) != str.charAt(j)) {
                    hash[str.charAt(i) - 'a'] = 0; // definitely it would be zero
                    i++;
                }
            }
            else{
                hash[str.charAt(j) - 'a'] = 1;
            }
        }

        return maxLength;

        // Another idea is to hash the characters with the indices. and whenever a repeating character comes just check the last seen index and take the length from there

        /*
            unordered_map<char, int> seen;
             int ret = 0, slow = 0, n = s.size();
             for (int fast = 0; fast < n; ++fast) {
             if (seen.count(s[fast]) != 0) slow = max(slow, seen[s[fast]] + 1);
             seen[s[fast]] = fast;
             ret = max(ret, fast - slow + 1);
             }
             return ret;
         */
    }

    // Problem 4 : Median of two sorted arrays
    /*
        here are two sorted arrays nums1 and nums2 of size m and n respectively.
        Find the median of the two sorted arrays. The overall run time complexity should
        be O(log (m+n)).
        You may assume nums1 and nums2 cannot be both empty.
        Example 1:
        nums1 = [1, 3]
        nums2 = [2]
        The median is 2.0
        Example 2:
        nums1 = [1, 2]
        nums2 = [3, 4]
        The median is (2 + 3)/2 = 2.5

     */

    public static double getMedianOfTwoSortedArrays(int [] a1, int [] a2){
        // This can be found using the kth smallest element in two sorted arrays, using merge sort technique.

        // Check this class : KthSmallestTwoSortedArray
        // We can simply find, k = (a1.length + a2.length)/2

        // other approach, just keep on scanning the elements till we get to the kth element
        int n1 = a1.length;
        int n2 = a2.length;
        int totalLength = n1+n2;
        int med1Index = 0;
        int med2Index = -1;
        int num1 = 0;
        int num2 = 0;

        if( (n1 + n2) % 2 == 0){
            // even number of elements, n1 = 2, n2 = 4 => 6 elements so, median would be at 1,2,3,4,5,6 -> 3rd and 4th elements
            med1Index = (n1+n2)/2 ;
            med2Index = med1Index+1;
        }
        else{
            med1Index = (n1+n2)/2 + 1;
        }

        // Now discard those many elements
        int i = 0 , j = 0;
        while ( i < n1 && j < n2 && med1Index > 0){
            if ( a1[i] < a2[j]){
                med1Index--;
                num1 = a1[i];
                i++;
            }
            else{
                med1Index--;
                num1 = a2[j];
                j++;
            }
        }

        while(i < n1 && med1Index > 0){
            num1 = a1[i];
            i++;
            med1Index--;
        }

        while(j < n2 && med1Index > 0){
            num1 = a2[j];
            j++;
            med1Index--;
        }

        if ( med2Index != -1){
            if ( i < n1 &&  j < n2 ){
                if ( a1[i] < a2[j])
                    num2 = a1[1];
                else
                    num2 = a2[j];
            }
            else if( i < n1){
                num2 = a2[j];
            }
            else{
                num2 = a2[j];
            }
            return (num1 + num2)/2.0;
        }
        return num1;
    }

    // Problem 5 : 5. Longest Palindromic Substring

    /*
        Given a string s, find the longest palindromic substring in s. You may assume
        that the maximum length of s is 1000.
        Example 1:
        Input: "babad"
        Output: "bab"
        Note: "aba" is also a valid answer.
        Example 2:
        Input: "cbbd"
        Output: "bb"

     */

    public static String longestPalindromicSubstring(String str){
        // Standard dp problem.
        // dp[i][j] = true if string i to j is palindromic
        // dp[i][j] = dp[i-1][j-1] && str.charAt(i) == str.charAt(j)
        // dp[i][i] = true

        return null;
    }


    // Problem 5: ZigZagConverstion
    /*
        The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of
        rows like this: (you may want to display this pattern in a fixed font for better
        legibility)
        P   A   H   N
        A P L S I I G
        Y   I   R
        And then read line by line: "PAHNAPLSIIGYIR"
        Write the code that will take a string and make this conversion given a number
        of rows:
        string convert(string s, int numRows);
        Example 1:
        Input: s = "PAYPALISHIRING", numRows = 3
        Output: "PAHNAPLSIIGYIR"
        Example 2:
        Input: s = "PAYPALISHIRING", numRows = 4
        Output: "PINALSIGYAHRPI"
        Explanation:
        P      I       N
        A    L S    I  G
        Y  A   H  R
        P      I

     */

    public static String zigZagConversion(String str, int row){
        // We can do that by just looking into the pattern.
       List<Character> [] rows = new ArrayList[row];
       for(int i = 0 ; i < row; i++)
           rows[i] = new ArrayList<>();
       int rowIndex = 0, increment = 1 ;
       for ( int  i = 0 ; i < str.length(); i++){
           rows[rowIndex].add(str.charAt(i));
           if ( rowIndex == row - 1){
               increment = -1;
           }
           else if( rowIndex == 0){
               increment = 1;
           }
           rowIndex += increment;
       }

       StringBuilder sb = new StringBuilder();
       for (int i = 0 ; i < row; i++){
           sb.append(rows[i].stream().map(String :: valueOf).collect(Collectors.joining()));
       }

       return sb.toString();
    }


    // Problem 11 : Container With Most Water

    /*
    Given n non-negative integers a1, a2, ..., an , where each represents a point at
    coordinate (i, ai). n vertical lines are drawn such that the two endpoints of
    line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis
    forms a container, such that the container contains the most water.
    Note: You may not slant the container and n is at least 2.
    The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this
    case, the max area of water (blue section) the container can contain is 49.
    Example:
    Input: [1,8,6,2,5,4,8,3,7]
    Output: 49
       leetcode : https://leetcode.com/problems/container-with-most-water/
     */

    public static int containerWithMostMostWater(int [] height){
        // so there are two factors width and height
        // O(n*2) approach, where we check every pair and take the maximum
        /*
            Other approach, we can have maximum width from index 0 to index n-1, length n.
            Then we can reduce the width by 1, then we would contract the width by 1, by either going at index 1 or index n-2. But we would go to the index where the height of bar is maximum, so that we can optimize the area

         */

        int i = 0 , j = height.length - 1;
        int width = height.length - 1;
        int ans = 0;
        while ( i < j ){
            ans = Math.max(ans, width*Math.min(height[i], height[j]));
            if(height[i] < height[j]){
               i++;
            }
            else{
                j--;
            }
            width--;
        }
        return ans;

    }

    // Trapping Water Problem : https://leetcode.com/problems/trapping-rain-water/submissions/802668966/

    public static int maximumWaterTrapped(int [] height){
        int n = height.length;

        int [] maxFromLeft = new int[n];
        int [] maxFromRight = new int[n];

        maxFromLeft[0] = height[0];
        for(int i = 1 ; i < n ; i++){
            maxFromLeft[i] = Math.max(maxFromLeft[i-1], height[i]);
        }
        maxFromRight[n-1]= height[n-1];
        for(int i = n-2; i >=0 ; i--){
            maxFromRight[i] = Math.max(maxFromRight[i+1], height[i]);
        }

        int ans = 0;
        for(int i = 0 ; i < n ; i++){
            ans += Math.min(maxFromRight[i], maxFromLeft[i]) - height[i];
        }

        return ans;
    }

    // P14 : Longest Common Prefix
    /*

        Write a function to find the longest common prefix string amongst an array of
        strings.
        If there is no common prefix, return an empty string "".
        Example 1:
        Input: ["flower","flow","flight"]
        Output: "fl"
        Example 2:
        Input: ["dog","racecar","car"]
        Output: ""
        Explanation: There is no common prefix among the input strings.
        Note:
        All given inputs are in lowercase letters a-z.
     */

    public static String longestCommonPrefix(String [] strs){
        /*
            This is simply take the string with minimum length and scan all the characters in all the strings till we don't get mismatch.
            O(1) and O(Number of characters in all Strings)

         */

        return null;
    }


    // P19 : Remove Nth Node From End of List
    /*
        Given a linked list, remove the n-th node from the end of list and return its
        head.
        Example:
        Given linked list: 1->2->3->4->5, and n = 2.
        After removing the second node from the end, the linked list becomes 1->2->3->5.
        Note:
        Solution
        05/23/2020:
        23. Merge k Sorted Lists
        Description
            Given n will always be valid.
        Follow up:
            Could you do this in one pass?
     */

    public static void removeNthNodeFromList(ListNode head, int n){

        // Idea is to do it one pass by first reaching to the nth node from the start, then from that node we would start running slow pointer and fast pointer together till fast pointer does not reach to null. The slow pointer would reach to the nth node from last.

        ListNode fast = head, slow = head;
        while(n > 0 ) {
            fast = fast.next;
            n--;
        }

        while(fast.next != null ){
            fast = fast.next ;
            slow = slow.next ;
        }

        // remove the nth node
        slow.next = slow.next.next;

    }

    // P23. Merge K Sorted Lists
    /*
        Merge k sorted linked lists and return it as one sorted list. Analyze and
        describe its complexity.
        Example:
        Input:
        [
         1->4->5,
         1->3->4,
         2->6
        ]
        Output: 1->1->2->3->4->4->5->6

     */


    public static ListNode mergeKSortedList(ListNode [] listNodes){
        /*
            We can use the idea of Merge sort here. We will sort the list Nodes by halving the array
         */

        int n  = listNodes.length;
        if ( n < 1){
            return null;
        }
        if ( n == 1){
            return listNodes[0];
        }

        ListNode first = mergeKSortedList(Arrays.copyOfRange(listNodes, 0, n/2));
        ListNode second = mergeKSortedList(Arrays.copyOfRange(listNodes, n/2, n));

        // perform the merge here

        BiFunction<ListNode, ListNode, ListNode> merger = (l1, l2)->{
            // Apply merge Logic here, To be filled
            return l1;
        };

        ListNode mergedList = merger.apply(first, second);
        return mergedList;
    }

    // P24. Swap nodes in pairs
    /*
        Given a linked list, swap every two adjacent nodes and return its head.
        You may not modify the values in the list's nodes, only nodes itself may be
        changed.
        Example:
        Given 1->2->3->4, you should return the list as 2->1->4->3.
     */

    public static ListNode swap(ListNode curr, ListNode ne){
        if(curr == null ){
            return null;
        }
        if( ne == null){
            return curr;
        }
        ListNode nextHead = swap(ne.next, ne.next == null ? null : ne.next.next);
        curr.next = nextHead;
        ne.next = curr;
        return ne;
    }
    public static ListNode swapNodesInPair(ListNode head){
       return swap(head, head == null ? null : head.next);
    }

    // P26. . Remove Duplicates from Sorted Array
    /*
            Given a sorted array nums, remove the duplicates in-place such that each element
            appear only once and return the new length.
            Do not allocate extra space for another array, you must do this by modifying the
            input array in-place with O(1) extra memory.
            Example 1:
            Given nums = [1,1,2],
            Your function should return length = 2, with the first two elements of nums
            being 1 and 2 respectively.
            It doesn't matter what you leave beyond the returned length.
            Example 2:
            Given nums = [0,0,1,1,1,2,2,3,3,4],
            Your function should return length = 5, with the first five elements of nums
            being modified to 0, 1, 2, 3, and 4 respectively.
            It doesn't matter what values are set beyond the returned length.
            Clarification:
            Confused why the returned value is an integer but your answer is an array?
            Note that the input array is passed in by reference, which means modification to
            the input array will be known to the caller as well.
            Internally you can think of this:
            // nums is passed in by reference. (i.e., without making a copy)
            int len = removeDuplicates(nums);
            // any modification to nums in your function would be known by the caller.
            // using the length returned by your function, it prints the first len elements.

     */

    public static int removeDuplicates(int[] nums) {
        // Idea is simple, we just need maintain a pointer and just keep on adding the non-duplicate elements in the running index

        int ans = 1;
        int n = nums.length;
        if( n == 1){
            return ans;
        }
        int prev = nums[0];
        for(int i = 1; i < n; i++){
            if(prev != nums[i]){
                prev = nums[i];
                nums[ans] = prev;
                ans++;
            }
        }
        return ans;
    }

    // P27. Remove elements
    /*
    Given an array nums and a value val, remove all instances of that value in-place
    and return the new length.
    Do not allocate extra space for another array, you must do this by modifying the
    input array in-place with O(1) extra memory.
    The order of elements can be changed. It doesn't matter what you leave beyond
    the new length.
    Example 1:
    Given nums = [3,2,2,3], val = 3,
    Your function should return length = 2, with the first two elements of nums
    being 2.
    It doesn't matter what you leave beyond the returned length.
    Example 2:
    Given nums = [0,1,2,2,3,0,4,2], val = 2,
    Your function should return length = 5, with the first five elements of nums
    containing 0, 1, 3, 0, and 4.
    Note that the order of those five elements can be arbitrary.
    It doesn't matter what values are set beyond the returned length.
    Clarification:
    Confused why the returned value is an integer but your answer is an array?
    Note that the input array is passed in by reference, which means modification to
    the input array will be known to the caller as well.
    Internally you can think of this:
    // nums is passed in by reference. (i.e., without making a copy)
    int len = removeElement(nums, val);
    // any modification to nums in your function would be known by the caller.
    // using the length returned by your function, it prints the first len elements.
    for (int i = 0; i < len; i++) {
     print(nums[i]);
    }
     */

    public static int removeElement(int[] nums, int val) {
        int ans = 0;
        int n = nums.length;

        for(int i = 0 ; i < n ; i++){
            if (nums[i] != val){
                nums[ans] = nums[i];
                ans++;
            }
        }

        return ans;
    }

    // P33. Search in Rotated Sorted Array
    /*
        Suppose an array sorted in ascending order is rotated at some pivot unknown to
        you beforehand.
        Solution
        04/28/2020:
        (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
        You are given a target value to search. If found in the array return its index,
        otherwise return -1.
        You may assume no duplicate exists in the array.
        Your algorithm's runtime complexity must be in the order of O(log n).
        Example 1:
        Input: nums = [4,5,6,7,0,1,2], target = 0
        Output: 4
        Example 2:
        Input: nums = [4,5,6,7,0,1,2], target = 3
        Output: -1

     */

    public static int searchInRotatedSortedArray(int [] nums){
        return 0;
    }

    public static void main(String[] args) {
        int [] getTwoSumReturns = getTwoSum(new int [] {2, 7, 11, 15}, 9);
        System.out.println( "[" + getTwoSumReturns[0] + "," + getTwoSumReturns[1] + "]");
        System.out.println("Maximum non-repeating substring :  " + getLongestSubstringWithoutRepeatingCharacters("abcabcbb"));
        // 1 2 2 3 4
        System.out.println("Median of [1,2,4] and [2,3] is : " + getMedianOfTwoSortedArrays(new int [] {1,2,4}, new int [] {2,3}));
        // 1,2,2,4,4,6
        System.out.println("Median of [1,2,4] and [2,4,6] is : " + getMedianOfTwoSortedArrays(new int [] {1,2,4}, new int [] {2,4,6}));
        System.out.println("Zig zag conversion : "+ zigZagConversion("PAYPALISHIRING", 4));
        System.out.println("Max water : " + containerWithMostMostWater(new int [] {1,8,6,2,5,4,8,3,7}));
        System.out.println("Max water trapped : " + maximumWaterTrapped(new int [] {1,8,6,2,5,4,8,3,7}));
        ListNode head = ListNode.getListNodeFrom(new int[] {1,2,3,4,5,6,7,8,9});
        head.printList();
        removeNthNodeFromList(head, 4);
        System.out.print( "After removing element at index 4 from last : "); head.printList();
        head = swapNodesInPair(head);
        System.out.print("After swapping nodes in pairs : "); head.printList();
        System.out.println("After removing duplicates : "  + removeDuplicates(new int [] {1,2,2,3,4,5,6}));

    }
}
