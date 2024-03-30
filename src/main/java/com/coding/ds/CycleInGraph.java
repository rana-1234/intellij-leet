package com.coding.ds;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CycleInGraph {

    public static class Graph{
        public int totalNode;
        public static class Node{
            boolean visited;
            boolean exploring;
            public List<Node> nbr = new ArrayList<>();
        }

        public Node [] nodes;


        public Graph(int totalNode){
            this.totalNode = totalNode;
            nodes = new Node [totalNode];
            for (int i = 0 ; i < totalNode; i++){
                nodes[i] = new Node();
            }
        }

        public void validateNode(int node) throws RuntimeException {
            if (node < 0 || node >= totalNode){
                throw new RuntimeException("Invalid node " + node + ", Node can be between 0 to " + (totalNode-1));
            }
        }

        public void addEdge(int from, int to){
            validateNode(from);
            validateNode(to);
            nodes[from].nbr.add(nodes[to]);
        }

        public void markNodeVisited(Node node){
            node.visited = true;
        }

        public void markNodeExploring(Node node){
            node.exploring = true;
        }

        public void unMarkNodeExploring(Node node){
            node.exploring = false;
        }

        public boolean detectCycleDfs(Node currentNode){
            markNodeVisited(currentNode);
            markNodeExploring(currentNode);
            markNodeVisited(currentNode);
            boolean isCyclePresent = false;

            for (Node child : currentNode.nbr){
                if (child.visited == true && child.exploring == true){
                    return true;
                }
                isCyclePresent = detectCycleDfs(child);
                if ( isCyclePresent)
                    break;
            }

            unMarkNodeExploring(currentNode);
            return false;
        }

        public boolean checkCycleInGraph(){
            return detectCycleDfs(nodes[0]);
        }
    }




    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        System.out.println("Cycle exists in Graph : " + graph.checkCycleInGraph());

    }
}
