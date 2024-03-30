package com.coding.leetcode;

public class P2934 {

    public static final class Trie{
        Trie zero, one;

        public Trie(){
            zero = null;
            one = null;
        }
    }

    public static Trie root;
    public static int ans = Integer.MIN_VALUE;

    public static boolean isBitSetAt(int index, int num){
        return ((1 << index)&num) > 0;
    }

    public static Trie insertInTrie(int num, int currentIndex, Trie currentRoot){
        if ( currentIndex == -1 ){
            return null;
        }
        if ( currentRoot == null){
            currentRoot = new Trie();
        }
        if (isBitSetAt(currentIndex, num)){
            currentRoot.one = insertInTrie(num, currentIndex-1, currentRoot.one);
        }
        else{
            currentRoot.zero = insertInTrie(num, currentIndex-1, currentRoot.zero);
        }
        return currentRoot;
    }

    public static int traverseTreeAndGetAns(Trie currentRoot, int num, int currentIndex){
        int currentAns = 0;
        int lastVisited = 0;
        while(currentIndex > 0 && currentRoot != null){
            if ( isBitSetAt(currentIndex, num)){
                currentRoot = currentRoot.one;
                lastVisited = 1;
            }
            else{
                currentRoot = currentRoot.zero;
                lastVisited = 0;
            }
            currentAns += (1 << currentIndex);
            currentIndex--;
        }

        if( currentIndex == 0 ){
            return currentAns + ((lastVisited == 0 && (num&1) > 0 ) || (lastVisited == 1 && (num&1) == 0) ? 1 : 0) ;
        }
        return 0;
    }

    public static int query(int num, int currentIndex, Trie currentRoot){

        int currentLocalAns = 0;

        if( isBitSetAt(currentIndex, num)){
            // if we switch to the zero the side, then we need to keep checking the previous bits
            // for e.g. 110101 -> we are going to 0th side, meaning 0, now we have to keep on checking the 11010 route

            if (currentRoot.zero != null){
                currentLocalAns = traverseTreeAndGetAns(currentRoot.zero, num, currentIndex); // if this return non-zero ans, we can stop recurring
                if ( currentLocalAns > 0 ){
                    return currentLocalAns; // this would be definitely maximum xor
                }
            }
            if( currentRoot.one != null){
                // doing xor this bit would not result in any answer, simply proceed
                return query(num, currentIndex-1, currentRoot.one);
            }
        }
        else{
            // if we switch to the zero the side, then we need to keep checking the previous bits
            // for e.g. 0110101 -> we are going to 0th side, meaning 1, now we have to keep on checking the 011010 route
            if ( currentRoot.one != null){
                currentLocalAns = traverseTreeAndGetAns(currentRoot.one, num, currentIndex);
                if ( currentLocalAns > 0 ){
                    return currentLocalAns; // this would be definitely maximum xor
                }
            }

            if( currentRoot.zero != null){
                // doing xor this bit would not result in any answer, simply proceed
                return query(num, currentIndex-1, currentRoot.zero);
            }
        }
        return  currentLocalAns;
    }

    public static int maximumStrongPairXor(int[] nums) {

        int len = nums.length;
        if ( len == 1){
            return 0;
        }

        int mostSignificantBit = 0;
        for ( int i = 0 ; i < len; i++){
            int currentNum = nums[i];
            int j = 0;
            for(; j < 21 && currentNum > 0 ; j++ ){
                currentNum >>= 1;
            }
            mostSignificantBit = Math.max(mostSignificantBit, j-1);
        }

        root = insertInTrie(nums[0], mostSignificantBit, null);

        for(int i = 1 ; i < len; i++){
            ans = Math.max(ans, query(nums[i], mostSignificantBit, root));
            root = insertInTrie(nums[i], mostSignificantBit, root);
        }
        return ans;
    }


    public static void main(String[] args) {
        int ans = maximumStrongPairXor(new int [] {500,520,2500,3000});
        System.out.println(ans);
    }
}
