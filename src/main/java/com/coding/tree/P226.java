package com.coding.tree;

public class P226 {

    public void invert(TreeNode root){
        if ( root == null ){
            return;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invert(root.left);
        invert(root.right);
    }

    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
    }

    public static void main(String[] args) {

    }
}
