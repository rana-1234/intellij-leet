package com.coding.codechef;

import com.coding.io.InputReader;

import java.util.Map;

class SegmentTree{
    private int [] arr;
    private int size;
    private Map.Entry<Integer, Integer> [] seg;
    public SegmentTree(int [] initialBookOrder){
        arr = initialBookOrder;
        this.size = arr.length;
        seg = new Map.Entry[4*this.size];
        buildSeg(0, 0 , this.size-1);
    }

    private void buildSeg(int root, int start, int end){
        if( start == end){
            seg[root] = Map.entry(-1, arr[start]); // Leaf node indicator
            return;
        }
        int middle = (start + end)/2;
        buildSeg(2*root+1, start, middle);
        buildSeg(2*root+2, middle+1, end);
        seg[root] = Map.entry(middle-start+1, end-middle); // total number of books in left and right
    }

    public int getBook(int position){
        return getBookFromSeg(0, position);
    }

    private int getBookFromSeg(int root, int position){
        if(seg[root].getKey() == -1 ){
            // this is only for leaf nodes
            return seg[root].getValue(); // this stores book id
        }

        if(seg[root].getKey() >= position){
            // Book will be in left side of tree
            seg[root] = Map.entry(seg[root].getKey()-1 , seg[root].getValue()); // From left side 1 book will be removed
            return getBookFromSeg(2*root+1, position);
        }
        else{
            // Book will be in right side of tree
            seg[root] = Map.entry(seg[root].getKey(), seg[root].getValue()-1); // From right side 1 book will be removed
            return getBookFromSeg(2*root+2, position - seg[root].getKey()); // we have to find book at that position
        }
    }
}


public class BookList {
    private static InputReader inputReader;

    public static void main(String[] args) throws Exception{
        inputReader = new InputReader();
        inputReader.readInt();
        int [] bookList = inputReader.readIntegerArray();
        SegmentTree segmentTree = new SegmentTree(bookList);
        int n = inputReader.readInt();
        for(int i = 0; i < n ; i++)
            System.out.println(segmentTree.getBook(inputReader.readInt()));
    }
}
