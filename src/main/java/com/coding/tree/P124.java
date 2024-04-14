package com.coding.tree;

public class P124 {

    public int maxAns = Integer.MIN_VALUE;

    public int maxPathSumHelper(TreeNode root) {

        if( root == null ){
            return 0;
        }

        if( root.left == null && root.right == null ){
            maxAns = Math.max(maxAns, root.val);
            return Math.max(0,root.val); // leaf node
        }
        // let's calculate value for this node
        int valueFromLeft = maxPathSumHelper(root.left);
        int valueFromRight = maxPathSumHelper(root.right);
        // At the root, we are updating the maxAns when root is in the middle of path
        maxAns = Math.max(maxAns, root.val + valueFromLeft + valueFromRight);
        // Returning the path sum including this node and either one of the left or right child
        return Math.max(0, root.val + Math.max(valueFromLeft, valueFromRight));

    }

    public int maxPathSum(TreeNode root) {
        maxPathSumHelper(root);
        return maxAns;
    }

    public static void main(String[] args) {

    }
}
