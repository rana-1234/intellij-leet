package com.coding.ds;

public class BinaryIndexedTree {

    // BIT is used for querying sum of an array in a range and also update it O(logN) time

    public static class BITree{
        public int [] data; // Space O(logN)
        public int size;

        public BITree(int size){
            this.size = size + 1;
            data = new int[this.size];
        }

        // O(NlogN)
        public BITree(int [] array){
            this.size = array.length + 1;
            data = new int[this.size];
            for (int i = 0 ; i < array.length; i++){
                update(i, array[i]);
            }
        }

        // O(logN)
        public void update(int index, int value){
            index = index+1;// since it starts from 1, 0 index is preserved for 0
            while ( index <= size) {
                data[index] += value;
                index += index & (-index);
            }
        }

        // O(logN)
        public int get(int index){
            index = index+1;
            int result = 0;
            while(index > 0){
                result += data[index];
                index -= index & (-index);
            }
            return result;
        }

    }

    // BITTree for range updates and point query in an array

    public static void rangeUpdateAndPointQuery(){
        // Suppose we have an array [0,0,0,0,0]
        // we say to add val from l to r in all elements i.e. update(l, r, val)
        // we query like give me elements at index i


        /*
            e.g.

            update(1,2,3) : [0,3,3,0,0]
            get(3) = 0,
            get(2) = 3

         */

        /*
            Idea here is to use BIT such that, we add +val at lth index in array and -val at r+1 th index.
            when query we give the prefix sum

            e.g. update(1,2,3) => [0,3,0,-3,0,0] // means +3 is add from 1, to 2, and -3 is added at r+1 th index. So when we take the prefix sum, that cancels out

            get(3) = prefixSum(3) = 0+3+0-3 = 0
            get(2) = prefixSum(2) = 0+3+0 = 3

         */

        int n = 5;
        BITree biTree = new BITree(n);

        //update(1,2,3)
        biTree.update(1, 3); // add +3 to l
        biTree.update(3,-3);  // add -3 to r+1

        int getAt2 = biTree.get(2);
        int getAt3 = biTree.get(3);

        System.out.println("Get 2 : " + getAt2);
        System.out.println("Get 3 : " + getAt3);

    }

    // BITTree for rangeUpdates and rangeQuery
    public static void rangeUpdateAndRangeQuery(){
        /*
            Thi is extended to RangeUpdate and Point query,
            where we need to find the prefix sum from (0, k) for given k

            Idea is to extend the above approach.

            Approach using RangeUpdatePointQuery
                => Do rangeUpdate
                => For query => for(int i = 0 ; i <= k ; i++) ans += pointQuery(i)
                Query taken O(NlogN) time


            Extending the above idea

            update(1,2,3) : [0,3,3,0,0]
            get(3) = 6 (0+3+3+0) => prefix sum till index 3
            get(2) = 6 (0+3+3) => prefix sum till index 2
            get(1) = 3 (0+3) => prefix sum till index 1

            suppose k lies in
            1) k < l : In this case, sum will not be impacted. Prefix sum till k would be the ans
            2) l <= k <= r : In this case, sum would increase by val*k - val*(l-1), since val is added to l and -val is added to r+1.
            3) k > r : In this case, sum would increase by val*r - val*(l-1).

            How to find val ?
            => Finding the val is simple, this is same as PointQuery. We would maintain a BITTree1 for this purpose. This will help in finding the value at kth index. so val*k is calculated.
            => to find the val*(l-1), we would maintain another BITTree2, update val*(l-1) at lth index. so when getSum query is performed it would give val*(l-1)

            // Update would look like
            bitTree1.update(l,val)
            bitTree1.update(r+1, val-1)
            bitTree2.update(l, val*(l-1)) // updating at lth index as val*(l-1)
            bitTree2.update(r+1, -val*r) // updating at r+1 the index as -val*r, when we do the prefix sum here, we would know the contribution of val till index k

            // RangeSum(k)

            bitTree1.query(k)*k - bitTree2.query(k)

            bitTree1.query(k) => val  // since we are updating value in the array [0,0,0,val,0,0,0,-val,0,0,0]
            bitTree1.query(k)*k = val*k;

            bitTree2.query(k) = val*(l-1) // since we are updating the val*(l-1) at l and -val*r to r+1 in the array [0,0,0,val*(l-1),0,0,0,-val*r,0,0,0]

         */

        int n = 5;
        BITree biTree1 = new BITree(n);
        BITree bitTree2 = new BITree(n);

        //update(1,2,3)
        int l = 1, r = 2, val = 3;
        biTree1.update(l, val); // [0,3,0,0,0]
        biTree1.update(r+1, -val); // [0,3,0,-3,0]
        bitTree2.update(l, val*(l-1)); // [0, 0, 0, 0, 0] => 3*(1-1) = 0
        bitTree2.update(r+1, -val*r); // [0,0,0,-6,0] => -3*2 = -6

        // get(3)
        int k = 3;
        int prefixSumTillK = biTree1.get(k)*k - bitTree2.get(k); // 0 - (-6) = 6
        System.out.println("Range sum till " + k + " : " +  prefixSumTillK);

        k = 2;
        prefixSumTillK = biTree1.get(k)*k - bitTree2.get(k); // k = 2, value at first array is 3, 3*2 = 6,
        // k = 2, value at bitTree2 is 0
        System.out.println("Range sum till " + k + " : " +  prefixSumTillK);

        k = 1;
        prefixSumTillK = biTree1.get(k)*k - bitTree2.get(k); // k = 1, value at first array is 3.
        // k = 1, value at bitTree2 is 0, so the sum is 3
        System.out.println("Range sum till " + k + " : " +  prefixSumTillK);


        k = 4;
        prefixSumTillK = biTree1.get(k)*k - bitTree2.get(k); // k = 4, value at first array is 0.
        // k = 4, value at bitTree2 is -6, so the sum is 6
        System.out.println("Range sum till " + k + " : " +  prefixSumTillK);
    }




    public static void main(String[] args) {
        int a[] = {2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9};


        BITree biTree = new BITree(a);

        for ( int i = 0 ; i < a.length; i++){
            System.out.print("Current array element : " + a[i]);
            System.out.println(" => Sum till " + (i+1) + " is : " + biTree.get(i));
        }

        rangeUpdateAndPointQuery();
        rangeUpdateAndRangeQuery();

    }
}
