package com.coding.leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P2538 {

    private List<Integer> [] tree;
    private int [] price;

    private long [] maxPrice;
    private long [] secondMax;
    private int n;

    long requiredAns = 0;

    private void addEdge(int from, int to){
        tree[from].add(to);
    }

    private void dfs(int root, int parent){
        long maxSum = 0;
        long secondMaxSum = -1;
        for(int nbr : tree[root]){
            if( nbr != parent){
                dfs(nbr, root);
                if (maxPrice[nbr] >= maxSum){
                    secondMaxSum = maxSum;
                    maxSum = maxPrice[nbr];
                }
            }
        }
        maxPrice[root] = maxSum + price[root];
        if(secondMaxSum != -1 && secondMaxSum != 0) {
            secondMax[root] = secondMaxSum + price[root];
        }
    }

    private void dfsFConsideringEveryNodeAsARoot(int root, int parent){

        if(parent != -1){
            // Two thing needs to be done, consider the firstMax of the parent node
            if ( maxPrice[parent] == maxPrice[root] + price[parent]){
                // it means that, the parent node had the maximum from this path only
                if(secondMax[parent] != -1) {
                    // it means, we can consider this path, compare with secondMax of parent + rest of the child
                    if (secondMax[parent] + price[root] >= maxPrice[root]) {
                        secondMax[root] = maxPrice[root];
                        maxPrice[root] = secondMax[parent] + price[root];
                    } else if (secondMax[parent] + price[root] >= secondMax[root]) {
                        secondMax[root] = secondMax[parent] + price[root];
                    } else {
                        // Nothing to worry here
                    }
                }

                if(secondMax[root] == -1){
                    // it means we can consider the value coming from parent
                    if (price[parent] + price[root] >= maxPrice[root]){
                        secondMax[root] = maxPrice[root];
                        maxPrice[root] = price[parent] + price[root];
                    }
                    else if (price[parent] + price[root] >= secondMax[root]){
                        secondMax[root] = price[parent] + price[root];
                    }
                }
            }
            else{
                // otherwise, the maxPrice of the root can come from its parent as well
                if ( maxPrice[parent] + price[root] >= maxPrice[root]){
                    secondMax[root] = maxPrice[root];
                    maxPrice[root] = maxPrice[parent] + price[root];

                }
                else if( maxPrice[parent] + price[root] >= secondMax[root]){
                    secondMax[root] = maxPrice[parent] + price[root];
                }
                else {
                    // there is no use of the maxPrice coming from the parent
                }

                if(secondMax[root] == -1){
                    // it means we can consider the value coming from parent
                    if (maxPrice[parent] + price[root] >= maxPrice[root]){
                        secondMax[root] = maxPrice[root];
                        maxPrice[root] = maxPrice[parent] + price[root];
                    }
                    else if (maxPrice[parent] + price[root] >= secondMax[root]){
                        secondMax[root] = maxPrice[parent] + price[root];
                    }
                }

            }
        }

        // the value in the same node is going to be always minimum since prices[i] > 0
        requiredAns = Math.max(requiredAns, maxPrice[root] - price[root]);


        for(int nbr: tree[root]){
            if ( nbr != parent){
                dfsFConsideringEveryNodeAsARoot(nbr, root);
            }
        }

    }

    public long maxOutput(int n, int[][] edges, int[] price) {
        this.n = n;
        this.price = price;
        maxPrice = new long[n];
        secondMax = new long[n];
        Arrays.fill(maxPrice, 0);
        Arrays.fill(secondMax, -1);

        tree = new ArrayList[n];
        for(int i = 0 ; i < n ; i++){
            tree[i] = new ArrayList<>();
        }

        // Add all edges;
        for(int [] edge : edges){
            addEdge(edge[0], edge[1]);
            addEdge(edge[1], edge[0]);
        }

        dfs(0, -1); // considering root as 0
        // At this point we know maxPrice for all the nodes including this root.


        dfsFConsideringEveryNodeAsARoot(0, -1);

        return requiredAns;

    }

    public static void main(String[] args) {
        System.out.println(new P2538().maxOutput(4, new int[][]{{0,3},{2,1},{1,3}}, new int[]{6,8,2,14}));
    }
}
