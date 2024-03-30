package com.coding.ds;

public class KMPAlgorithm {

    public static final class KMP{
        public String text;

        /*
            Idea is to store the longest prefix which is also suffix, so whenever there is a mismatch, we would start searching from the position starting from the suffix.

            For e.g.
            pattern : prefix1 ....... suffix1

            let say pattern match fails at index [i], till i-1, there was a match. So the longest suffix till i-1, which is also a prefix, will surely match. So we would start from the index of prefix of the pattern which is also a suffix at i-1;
         */

        public KMP(String text){
            this.text = text;
        }


        private int [] getLpsArray(String pattern){
            int len = pattern.length();

            /*
                        a b c d d d a s a a b c s d d e s a b c
                lps     0 0 0 0 0 0 1 0 1 1 2 3 0 0 0 0 0 1 2 3

             */

            int [] lps = new int[len];

            lps[0] = 0;
            int j = 0;
            for(int i = 1 ; i < len; ){

                if(pattern.charAt(i) == pattern.charAt(j)){
                    // j starts from 0
                    j++;
                    lps[i] = j;
                    i++;
                }
                else {
                    // there is a mismatch
                    // we will use again same idea, here. we would update the j to lps[i-1], which would give the index of the suffix which is also a prefix and then match

                    if (j == 0) {
                        // there is no match, so the lps[i] = 0;
                        lps[i] = 0;
                        i++;
                    }
                    else{
                        // tip: since upto j-1, we had the match with i-1, so the suffix ending at j-1 (which is also a prefix), that would also match till i-1, so we can update the j = lps[j-1]
                        // why not j = lps[i-1], it would just give the previous jth index, and it will stuck into a loop.
                        j = lps[j - 1];
                    }
                }
            }

            return lps;
        }

        public boolean isMatch(String pattern){
            int [] lps = getLpsArray(pattern);

            int i = 0, j = 0;
            int m = pattern.length(), n = text.length();
            while(i < n && j < m){
                if ( pattern.charAt(j) == text.charAt(i)){
                    i++;
                    j++;
                }
                else{
                    // We got the mismatch. So, we would start searching from the lps of pattern at j
                    if( j == 0 ){
                        i++; // we can start the text search from here now
                    }
                    else {
                        j = lps[j]; // this would give the exact next character till i-1 already matched.
                    }
                }
                if ( j == m){
                    System.out.println(String.format("(%s, %s) Match found at : %s", text,pattern,i));
                }

            }

            return j == m;
        }
    }


    public static void main(String[] args) {
        KMP kmp = new KMP("shashibhushanrana");
        System.out.println("rana is present in text : " +  kmp.isMatch("rana"));
        System.out.println("neha is present in text : " +kmp.isMatch("neha"));
        System.out.println("bhushan is present in text : " + kmp.isMatch("bhushan"));
        System.out.println("anrana is present in text : " + kmp.isMatch("anrana"));
        System.out.println("shashi is present in text : " + kmp.isMatch("shashi"));
    }
}
