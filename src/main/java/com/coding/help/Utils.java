package com.coding.help;

import com.coding.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public List<List<Integer>> targetSumNodeList;
    public void findAllPathSum(TreeNode root, int targetSum, List<Integer> currentPathList){
        if( root == null) {
            return;
        }
        currentPathList.add(root.val);
        if( root.val == targetSum){
            targetSumNodeList.add( new ArrayList<>(currentPathList));
        }
        findAllPathSum(root.left, targetSum-root.val, currentPathList);
        findAllPathSum(root.right, targetSum-root.val, currentPathList);
        currentPathList.remove(currentPathList.size()-1); // remove after explore is done
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        targetSumNodeList = new ArrayList<>();
        findAllPathSum(root, targetSum, new ArrayList<>());
        return targetSumNodeList;
    }


    public long minimumReplacement(int[] nums) {
        /*
            observations =>
                index i > j, and if a[i] < a[j] => then j needs to be devided in such a way that final divison should be in non-decreasing order

                [3,9,3]

                we will process from back,
                3 -> 9
                3 -> 3 -> 6 (1 op)
                3 -> 3 -> 3 -> 3 (1 op)
                3 -> 3 -> 3 -> 3-> 3

                10,12,18,20,10,50
                10,12,18,10,10,10,50
                10,12,8
                10,4,8
                6,4
                2,4

                10,12,18,20,10

                At the time of breaking
                    num1,num2,last => num1 + num2 = num

                make sure
                    1) num1 <= num2 and num2 <= last
                    2) make num1 as maximum as possible

            How?


        */
        int n = nums.length;
        long ans = 0;

        if( n <= 1){
            return ans;
        }

        int last = nums[n-1];
        for(int i = n-2 ; i >= 0; i--){
            if( nums[i] > last){
                int k = (int)Math.ceil(nums[i]/(1.0*last));
                ans += k-1;
                last = nums[i]/k;
            }
            else{
                last = nums[i];
            }
        }

        return ans;
    }

    public static String reverseWords(String s) {
        return Arrays.stream(s.split(" ")).map(x -> new StringBuilder(x).reverse().toString()).collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {

        System.out.println(reverseWords("Hey Shashi, I love you man !!"));
    }
}
