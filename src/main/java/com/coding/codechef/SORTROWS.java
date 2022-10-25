package com.coding.codechef;

import com.coding.io.InputReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SORTROWS {

    public static void main(String[] args) throws Exception{
        InputReader in = new InputReader();
        int n = in.readInt();

        List<List<Integer>> list = new ArrayList<>();

        for(int i = 0 ; i < n ; i++){
            List<Integer> l = new ArrayList<>();
            while(true){
                int x = in.readInt();
                if( x == -1){
                    break;
                }
                l.add(x);
            }
            list.add(l);
        }

        Collections.sort(list, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                if (o1 == null || o1.isEmpty()){
                    return -1;
                }
                else if(o2 == null || o2.isEmpty()){
                    return 1;
                }

                int n1 = o1.size(), n2 = o2.size();
                int i = 0, j = 0;
                while(i < n1 && j < n2){
                    if(o1.get(i) < o2.get(j)){
                        return -1;
                    }
                    else if(o1.get(i) > o2.get(j)){
                        return 1;
                    }
                    i++;
                    j++;
                }
                if( i == n1){
                    return -1;
                }
                else{
                    return 1;
                }
            }
        });

        for(List<Integer> l : list){
            for(int x : l){
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }
}
