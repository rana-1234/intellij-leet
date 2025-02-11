package com.coding.ds;

public class MatrixExponentiation {

    /*
        Read here : https://codeforces.com/blog/entry/97627
        Read here : https://www.hackerearth.com/practice/notes/matrix-exponentiation-1/

        We can convert the recurrence relations into matrix and then exponent them in (LogN) time to get the FN term directily

        For e.g.
        F(n) = A0*F(0) + A1*F(1) + A2*F(2) + ...... + A(N-K)*F(N-K)

        then you can represent this in a matrix multiplication format

        [  F(N)  ] = [ A0, A1, A2,......A(N-K)]
        [  F(N-1)]   [ 0,  1
        [  ......]   [
        [  F(N-K)]   [

        5. Generalizing to Other Recurrences

        For any recurrence:
           F(n)=a1F(n−1)+a2F(n−2)+...+akF(n−k)
           F(n)=a1​F(n−1)+a2​F(n−2)+...+ak​F(n−k)

            We construct a k×k transformation matrix:
            M=[a1a2…ak10…001…0⋮⋮⋱⋮00…1]
            M=
            ​a1​10⋮0​a2​01⋮0​………⋱…​ak​00⋮1​
            ​

        Then, compute M^(n-k) using fast exponentiation.

        Fn = Fn + Fn-1
        Fibonacci. M = [(1, 1), (1, 0)]
        Base case, [F1, F0] = [1, 1]
        F[2, 1] = M*F[1, 0]
        F[3, 2] = M^2*F[1,0]
        F[n, n-1] = M^(n-1)*F[1,0]

        1, 1, 2, 3, 5, 8, 13, 21, 34
     */

    long mod = 1_000_000_007;

    long [][] multiply(long [][] a, long [][] b){
        int n = a.length;
        int p = a[0].length;
        int m = b[0].length;

        long [][] res = new long[n][m];
        for ( int i = 0; i < n ; i++ ){
            for ( int j = 0; j < m ; j++){
                for ( int k = 0 ; k < p ; k++ ){
                    res[i][j] = (res[i][j] + a[i][k]*b[k][j])%mod;
                }
            }
        }

        return res;
    }

    long [][] pow(long [][] mat, long b){
        long [][] res = new long [][]{{1,1},{1,1}};
        while ( b > 0 ){
            if ( (b & 1) > 0){
                res = multiply(res, mat);
            }
            mat = multiply(mat, mat);
            b >>= 1;
        }
        return res;
    }

    public long getFib(int n){
        if ( n <= 1){
            return 1;
        }
        long [][] M = new long [][]{{1,1},{1,0}};
        long [][] base = new long [][]{{1}, {1}}; // M*(N-2)*Base = Fn-1, Fn-2
        M = pow(M, n-2);
        base = multiply(M, base);
        return base[0][0];
    }

    public static void main(String[] args) {
        {
            MatrixExponentiation mat = new MatrixExponentiation();
            int n = 2;
            System.out.println("Fib at " + n + " " +  mat.getFib(n));
        }
        {
            MatrixExponentiation mat = new MatrixExponentiation();
            int n = 5;
            System.out.println("Fib at " + n + " " + mat.getFib(n));
        }
        {
            MatrixExponentiation mat = new MatrixExponentiation();
            int n = 6;
            System.out.println("Fib at " + n + " " + mat.getFib(n));
        }
        {
            MatrixExponentiation mat = new MatrixExponentiation();
            int n = 7;
            System.out.println("Fib at " + n + " " + mat.getFib(n));
        }
        {
            MatrixExponentiation mat = new MatrixExponentiation();
            int n = 100;
            System.out.println("Fib at " + n + " " + mat.getFib(n));
        }
        {
            MatrixExponentiation mat = new MatrixExponentiation();
            int n = 5000;
            System.out.println("Fib at " + n + " " + mat.getFib(n));
        }
        {
            MatrixExponentiation mat = new MatrixExponentiation();
            int n = 5000000;
            System.out.println("Fib at " + n + " " + mat.getFib(n));
        }
    }
}
