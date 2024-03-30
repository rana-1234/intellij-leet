package com.coding.ds;

import java.util.Arrays;

public class UnionSet {

    public int n;
    public int [] parent;
    public int [] rank;

    public UnionSet(int n){
        this.n = n;
        parent = new int[n];
        rank = new int[n];
        Arrays.fill(parent, -1);
        Arrays.fill(rank, 0);
    }

    public int getRoot(int node){
        // We will also perform path compression here

        if( parent[node] == -1){
            return parent[node] = node; // this is the parent of the node
        }
        return parent[node] = getRoot(parent[node]);
    }

    public void merge(int node1, int node2){
        int parent1 = getRoot(node1);
        int parent2 = getRoot(node2);

        if( parent1 != parent2){
            // otherwise already belongs to same set
            if(rank[parent1] < rank[parent2]){
                parent[parent1] = parent2;
            }
            else{
                parent[parent2] = parent1;
            }

            if(rank[parent1] == rank[parent2]){
                rank[parent1]++;
            }
        }
    }

    public static void main(String[] args) {

    }
}
