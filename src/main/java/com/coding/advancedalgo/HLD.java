package com.coding.advancedalgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HLD {

    /*
        Heavy light decomposition Algorithm
        This algorithm decomposes the tree into chins (linear ds) on which we can perform any operation possible on linear data structures, e.g. segment tree, fenwick tree, square root decomposition, Mor's algorithm, and so on.


     */

    /*
        Using HLD we would solve today the LCA problem
        SPOJ : https://www.spoj.com/problems/LCA/
     */

    public static List<Integer> g []; // tree
    public static int [] chainHead; // chain head of node i
    public static int [] heavyChild; // Heavy child of node i

    public static int [] parent; // parent of node i
    public static int [] nodeSize; // size of subtree rooted at node i
    public static int [] depth; // depth of node i
    public static int [] lt ; // liner array after flatting the tree on array
    public static int [] seg; // segment tree on the lt array
    public static int [] indexInLt ; // index of node i in Lt
    public static int [] value; // value of node i, not getting used in LCA
    public static int N; // number of nodes
    public static int idx; // the index to place the node values at lt

    public static void dfsForHLD(int node, int par, int dep){

        parent[node] = par;
        depth[node] = dep;
        int heavyC = -1, sizeOfHeavyChild = 0;

        for(int child : g[node]){
            if ( child != par ){
                dfsForHLD(child, node,dep+1);
                if ( sizeOfHeavyChild < nodeSize[child] ){
                    heavyC = child;
                    sizeOfHeavyChild = nodeSize[child];
                }
                nodeSize[node] += nodeSize[child];
            }
        }

        // mark the current node as the heavy child of th node
        if ( heavyC != -1){
            heavyChild[node] = heavyC;
        }
    }

    public static void decomposeTreeHLD(int node, int ch){
        // put the chain head of current node
        chainHead[node] = ch;
        lt[++idx] = value[node]; // put the node
        indexInLt[node] = idx;

        // first put the heavy node in Lt and then put rest node

        if( heavyChild[node] != -1){
            decomposeTreeHLD(heavyChild[node],ch);
        }

        for(int child : g[node]){
            if ( child != parent[node] && child != heavyChild[node] ){
                decomposeTreeHLD(child,child); // make the chain head for the first node
            }
        }
    }

    public static int findLCA(int node1, int node2){
        /*
            We need to bring both the nods in the same chain and then the node in upper height would be the LCA
            At most logN chain switching
         */

        while(chainHead[node1] != chainHead[node2]){

            // Assuming node 1 is in more depth
            if( depth[chainHead[node1]] > depth[chainHead[node2]]){
                node1 = parent[chainHead[node1]];
            }
            else{
                node2 = parent[chainHead[node2]];
            }
        }

        if ( depth[node1] > depth[node2]){
            node1 = node2;
        }

        return node1;
    }

    public static int pathSum(int node1, int node2){

        /*
            We would do the same, we would go up the tree chain and keep on summing,(range sum in segment tree)
            pathSum(node1, node2) = pathSum(node1, lca(node1,node2) + pathSum(lca(node1,node2), node2)
            At most logN chain switching

            In same way we can find the max or min value.

            In the update of any value, we have to do followings
            1) Update node val, val[node] = new_val
            2) Update the same in lt, lt[indexInLt[node]] = new_val
            3) Update the same in seg, updateSegTree(1, N, 1, indexInLt[node], new_val)
                -> Here we can do the lazy updates as well.
         */

        int sum = 0;
        while(chainHead[node1] != chainHead[node2]){
            if (depth[chainHead[node1]] < depth[chainHead[node2]]){
                int temp = node1;
                node1 = node2;
                node2 = temp;
            }
            // node1 is in more depth
            // Here order matters because chainHead[node] would be always before the node we are looking, so queryStart would be always less than query end
            sum += querySegTree(1, N, 1, indexInLt[chainHead[node1]], indexInLt[node1]);
            node1 = parent[chainHead[node1]];

        }

        // Here both the nodes are in same chain

        if ( depth[node1] < depth[node2]){
            int temp = node1;
            node1 = node2;
            node2 = temp;
        }
        // the node deeper in the tree would always have the indexInLt[node] greater
        sum += querySegTree(1, N, 1, indexInLt[node2], indexInLt[node1]);
        return sum;
    }

    public static void createSegTree(int start, int end, int root){
        if ( start == end ){
            seg[root] = lt[start];
            return;
        }

        int mid = (start+end)/2; // (1,2) => (1,1), (2,2)
        createSegTree(start, mid, 2*root);
        createSegTree(mid+1, end, 2*root+1);
        seg[root] = seg[2*root] + seg[2*root+1]; // sum of segment tree [start, end]
    }

    public static int querySegTree(int start, int end, int root, int queryStart, int queryEnd){
        if ( queryStart > end || queryEnd < start ){
            return 0;
        }

        if ( queryStart <= start && end <= queryEnd ){
            return seg[root];
        }

        int mid = (start+end)/2;
        int left = querySegTree(start, mid, 2*root, queryStart, queryEnd);
        int right = querySegTree(mid+1, end, 2*root+1, queryStart, queryEnd);
        return left + right;
    }

    public static void solve(int n, int [][] edges, int [][] queries){
        N = n;
        g = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            g[i] = new ArrayList<>();
        }
        chainHead = new int[N+1];
        Arrays.fill(chainHead,-1);

        parent = new int[N+1];
        Arrays.fill(parent, -1);

        heavyChild = new int[N+1];
        Arrays.fill(heavyChild, -1);

        nodeSize = new int[N+1];
        Arrays.fill(nodeSize, 1);

        depth = new int[N+1];
        Arrays.fill(depth, -1);

        lt = new int[N+1];
        Arrays.fill(lt, -1);

        value = new int[N+1];
        for(int i = 1; i <= N; i++){
            value[i] = i;
        }

        indexInLt = new int[N+1];
        Arrays.fill(indexInLt, -1);

        seg = new int[4*N+10];

        idx = 0;

        // create tree
        for ( int i = 0 ; i < edges.length; i++ ){
            g[edges[i][0]].add(edges[i][1]);
            g[edges[i][1]].add(edges[i][0]);
        }
        // parent of node  1 is -1
        dfsForHLD(1, -1, 1);

        // start from root node
        decomposeTreeHLD(1,1);

        // create segment tree for query on it
        createSegTree(1, N, 1); // start from 1 to N, starting root as 1


        for(int i = 0 ; i < queries.length; i++){
            int node1 = queries[i][0];
            int node2 = queries[i][1];
            System.out.println("LCA of  "+node1+" and "+node2+" is "+ findLCA(node1,node2));
            System.out.println("Sum of "+node1+" and "+node2+  " is "+ pathSum(node1,node2));
        }
    }


    public static void main(String[] args) {
        {
            int N = 13;
            int [][] edges = {{1,2},{1,3},{1,4},{3,5},{3,6},{3,7},{6,8},{6,9},{7,10},{7,11},{10,12},{10,13}};
            int [][] queries = {{9,12}, {13,2}};
            solve(N, edges,queries);
        }
    }
}
