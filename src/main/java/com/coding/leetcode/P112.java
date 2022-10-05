package com.coding.leetcode;

import com.coding.tree.TreeNode;

public class P112 {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if( root == null){
            return false;
        }

        if( root.left == null && root.right == null){
            return root.val == targetSum;
        }
        
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    public static void main(String[] args) {
        System.out.println(new P112().hasPathSum(null, 0));
    }
}
