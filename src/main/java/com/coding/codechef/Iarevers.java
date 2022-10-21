package com.coding.codechef;

import com.coding.io.InputReader;

import java.util.ArrayList;
import java.util.List;

public class Iarevers {

    public static void main(String[] args) throws Exception{
        InputReader in = new InputReader();
        List<List<String>> lineList = new ArrayList<>();
        List<String> wordList;
        try{
            while(true) {
                String line = in.readLine();
                String[] words = line.split(" ");
                wordList = new ArrayList<>();
                for (String word : words) {
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < word.length(); i++) {
                        if ((word.charAt(i) >= 'a' && word.charAt(i) <= 'z') || (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z')) {
                            builder.append(word.charAt(i));
                        }
                    }
                    if(builder.toString().length() > 0)
                        wordList.add(builder.toString());
                }
                if( wordList.size() > 0 )
                    lineList.add(wordList);
            }
        }
        catch (Exception e){
        }

        for(int i = lineList.size() - 1 ; i >= 0; i--){
            for(int j = lineList.get(i).size()-1 ; j > 0 ; j--) {
                System.out.print(lineList.get(i).get(j) + " ");
            }
            if(lineList.get(i).size() >= 1 )
                System.out.println(lineList.get(i).get(0));
        }
    }
}
