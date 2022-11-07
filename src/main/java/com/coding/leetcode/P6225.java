package com.coding.leetcode;

public class P6225 {

    public String oddString(String[] words) {
        String first = words[0];
        int len = first.length();
        int [] diff = new int [len];

        for(int i = 1 ; i < len ; i++){
            diff[i-1] = first.charAt(i) - first.charAt(i-1);
        }

        String second = words[1];
        int [] another = new int [len];
        for(int i = 1 ; i < len ; i++){
            another[i-1] = second.charAt(i)- second.charAt(i-1);
        }

        boolean both = true;
        for(int i = 0 ; i < len ; i++ ){
            if( another[i] != diff[i]){
                both = false;
                break;
            }
        }
        String third = words[2];
        if(!both){
            int [] x = new int [len];
            for(int i = 1 ; i < len ; i++){
                x[i-1] = third.charAt(i) - third.charAt(i-1);
            }
            for(int i = 0; i < len ; i++){
                if(x[i] != diff[i]){
                    return first;
                }
                if(x[i] != another[i]){
                    return second;
                }
            }
            return "";
        }
        else {
            for (int j = 2; j < words.length; j++) {
                for (int i = 1; i < len; i++) {
                    if (words[j].charAt(i) - words[j].charAt(i - 1) != diff[i - 1]) {
                        return words[j];
                    }
                }
            }
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println(new P6225().oddString(new String[]{"adc","wzy","abc"}));
        System.out.println(new P6225().oddString(new String[]{"aaa","bob","ccc","ddd"}));
    }
}
