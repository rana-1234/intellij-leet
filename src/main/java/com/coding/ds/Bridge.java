package com.coding.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bridge {

        List<List<Integer>> bridges;
        List<Integer> [] g;

        int [] discoveryTime; // time when this node was discovered
        boolean [] visited;
        int [] low; // denotes the minimum discovery time of the node v, which is reachable from this node or its descendants
        int t;

        public void dfs(int root, int parent){

            if(visited[root]){
                // Already visited then return
                return;
            }

            // Assign the discovery time
            discoveryTime[root] = ++t;
            low[root] = discoveryTime[root]; // this is the only node which can be disovered
            visited[root] = true;

            for ( int child : g[root]){
                if (child != parent){
                    // Let's not go back to the parent
                    dfs(child, root);
                    // Since we visited this child, we need to update the low value of root, there may be the way from node
                    // child to reach to ancestor of the root or root itself.
                    low[root] = Math.min(low[root], low[child]);

                    // Now this edge root - child would be a bridge if dis[root] < low[child]
                    // Means there is no back edge from this child node to any of its ancestor node
                    if ( low[child] > discoveryTime[root]){
                        bridges.add(List.of(root, child));
                    }
                }
            }
        }

        public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
            bridges = new ArrayList<>();
            g = new ArrayList [n];
            discoveryTime = new int[n];
            low = new int[n];
            visited = new boolean[n];
            Arrays.fill(low, 0);
            Arrays.fill(visited, false);
            Arrays.fill(discoveryTime,0);

            for(int i = 0 ; i < n ; i++) {
                g[i] = new ArrayList<>();
            }

            for(List<Integer> connection : connections) {
                g[connection.get(0)].add(connection.get(1));
                g[connection.get(1)].add(connection.get(0));
            }

            t = -1;
            for(int i = 0 ; i < n ; i++) {
                if (!visited[i]) {
                    dfs(i, -1);
                }
            }
            return bridges;
        }
}
