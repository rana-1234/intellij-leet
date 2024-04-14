package com.coding.tree;

public class P104 {

    public int maxDepth(TreeNode root) {

        if ( root == null){
            return 0;
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));

    }

    public static void main(String[] args) {


    }
}
