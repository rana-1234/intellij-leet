package com.coding.tree;

import com.sun.source.tree.Tree;

public class P105 {

    int [] pre;
    int [] in;
    static int preIdxStart;

    public TreeNode build(int inIdxStart, int inIdxEnd){

        if (inIdxStart > inIdxEnd){
            return null;
        }

        TreeNode node = new TreeNode();
        node.val = pre[preIdxStart++]; // this is the root of the tree

        // Build the left subtree and then right subtree
        /*
            PreOrder -> (val, left, right)
            InOrder -> (left, val, right)
            3,9,20,15,7
            9,3,15,20,7
         */
        int breakPointForLeft = inIdxEnd + 1 ;
        for ( int i = inIdxStart ;  i <= inIdxEnd ; i++ ){
            if ( pre[preIdxStart-1] == in[i]){
                // left of it, that is from inIdxStart to i-1, would be left subtree and rest would be right
                breakPointForLeft = i;
                break;
            }
        }
        node.left = build(inIdxStart, breakPointForLeft-1);
        node.right = build(breakPointForLeft+1, inIdxEnd);
        return node;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        pre = preorder;
        in = inorder;
        preIdxStart = 0;
        return build(0, in.length - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new P105().buildTree(new int []{3,9,20,15,7}, new int []{9,3,15,20,7});
        System.out.println();
    }
}
