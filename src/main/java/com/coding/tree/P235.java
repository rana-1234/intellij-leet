package com.coding.tree;

public class P235 {


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if( root == null ) return null;

        if ((root.val >= p.val && root.val <= q.val) ||  (root.val <= p.val && root.val >= q.val)) {
            return root;
        }

        if ( root.val >= p.val && root.val >= q.val ) {
            if ( root.val == p.val ) {
                return root;
            }
            if ( root.val == q.val ) {
                return root;
            }
            return lowestCommonAncestor(root.left, p, q);
        }

        if( root.val <= p.val && root.val <= q.val ) {
            if ( root.val == p.val ) {
                return root;
            }
            if ( root.val == q.val ) {
                return root;
            }
            return lowestCommonAncestor(root.right, p,q);
        }
        return null;
    }


    public static void main(String[] args) {

    }
}
