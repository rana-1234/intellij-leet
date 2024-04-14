package com.coding.tree;

public class P230 {
    int ans = -1;
    int k ;

    public void kthSmallest(TreeNode root) {

        if( root != null){
            kthSmallest(root.left);
            k--;
            if( k == 0){
                ans = root.val;
            }
            kthSmallest(root.right);
        }

    }

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        kthSmallest(root);
        return ans;
    }

    public static void main(String[] args) {

    }
}
