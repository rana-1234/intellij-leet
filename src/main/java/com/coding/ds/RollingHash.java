package com.coding.ds;

import java.util.ArrayList;
import java.util.List;

public class RollingHash {

    public static final int mod = 1000000007;
    public static long pow(long a, long b, long mod){
        long result = 1;
        a %= mod;
        while(b > 0 ){
            if ( (b&1) > 0){
                result = (result * a)%mod;
            }
            a *= a;
            a %= mod;
            b >>= 1;
        }

        return result;
    }

    public static void patternMatch(String text, String pattern){

        int m = pattern.length();
        int p = 31;
        long hash = 0;
        long cp = 1;
        long inverseMod = pow(p, mod-2, mod);

        for ( char c : pattern.toCharArray() ){
            hash = hash + ((c-'a'+1)*cp)%mod;
            hash %= mod;
            cp = (cp*p)%mod;
        }

        int i = 0, j = m-1;
        cp = 1;
        long currentHash = 0;
        for ( j = 0 ; j < m ; j++ ){
            currentHash = currentHash + ((text.charAt(j)-'a'+1)*cp)%mod;
            if ( currentHash < 0){
                currentHash += mod;
            }
            currentHash %= mod;
            cp = (cp*p)%mod; // p, p2, p3....
        }
        List<Integer> matchingIndices = new ArrayList<>();
        if ( currentHash == hash ){
            matchingIndices.add(0);
        }

//        System.out.println("Hash of pattern " + hash + " And hash till m-1 " + currentHash);

        cp = (cp*inverseMod)%mod;

        while ( j < text.length()){
            currentHash = (currentHash - (text.charAt(i) - 'a' + 1));
            if ( currentHash < 0 ){
                currentHash += mod;
            }
            currentHash = (currentHash * inverseMod)%mod; // divide all by p
            // Add the next hash character
            i++;
            currentHash = currentHash + (cp*(text.charAt(j) - 'a' + 1))%mod;
            if ( currentHash < 0){
                currentHash += mod;
            }
            currentHash %= mod;
//            System.out.println("Hash of pattern " + hash + " And hash till " + j + " is " + currentHash);
            if ( currentHash == hash ){
                matchingIndices.add(i);
            }
            j++;
        }

        System.out.println("All matching indices  are " + matchingIndices);
    }


    public static void main(String[] args) {
        final String text = "shashibhushasnRana";
        final String pat = "s";
        patternMatch(text, pat);
    }
}
