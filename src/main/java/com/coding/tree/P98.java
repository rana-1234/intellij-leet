package com.coding.tree;

public class P98 {

    public boolean isValidBST(TreeNode root, long min, long max){
        if( root == null){
            return true;
        }

        else if( root.val <= min || root.val >= max){
            return false;
        }
        else{
            return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
        }
    }

    public boolean isValidBST(TreeNode root) {
        // [5,4,6,null,null,3,7]
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static void main(String[] args) {

    }
}
