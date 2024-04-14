package com.coding.tree;

public class P100 {

    public boolean isSameTree(TreeNode p, TreeNode q) {

        if ( p == null ){
            return q == null;
        }

        if( q == null){
            return p == null;
        }

        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

    }

    public static void main(String[] args) {

    }
}
