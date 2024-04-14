package com.coding.tree;

public class P572 {


    public boolean isSubtree(TreeNode root, TreeNode subRoot, boolean strictCheck) {
        if ( root == null && subRoot == null ){
            return true;
        }

        if( root == null && subRoot != null ){
            return false;
        }

        if( root != null && subRoot == null){
            return false;
        }

        boolean ans = false;
        if( root.val == subRoot.val){
            ans = isSubtree(root.left, subRoot.left, true)&& isSubtree(root.right, subRoot.right, true);
        }

        if( ans == false && strictCheck == false ){
            ans = isSubtree(root.left, subRoot, false) || isSubtree(root.right, subRoot, false);
        }

        return ans;
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot){
        return isSubtree(root, subRoot, false);
    }
    public static void main(String[] args) {

    }
}
