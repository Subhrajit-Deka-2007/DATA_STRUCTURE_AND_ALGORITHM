package BitManipulation;

import java.util.Scanner;

/**
 * We are given two numbers which represent the range and, we have to do the XOR of all number between the range
 */
public class XOROfNumberInARange {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" Enter the first range ");
        int left = sc.nextInt();
        System.out.println(" Enter the second range ");
        int right = sc.nextInt();
        System.out.println(" The xor of the number in the range "+left+" to "+right+" is "+ brut_force(left,right));
        System.out.println(" The xor of the number in the range "+left+" to "+right+" is "+ optimize(left,right));
    }

    private static int brut_force(int left, int right) {
        // If we are given [2,6] => then 2^3^4^5^6
        int xor = 0;
        for(int i = left;i<=right;i++) xor^=i;
        return xor;
    }
    /*
    T.C =O(N) OR O(right -left + 1)
    S.C =O(1)
     */
   private static int optimize (int left, int right){
/*
For optimize approach we have to know to find the xor of 1 to n
to find xor from 1 to n observation
xor(int n){
     if(n%4==1) return 1;
else if(n%4==2) return n+1;
else if(n%4==3) return 0;
else (n%4 == 0) return n ;
but it is xor from 1 to n
for finding xor from a to b of a given range we can do is
(1^2^---^a-1)^(1^2^3^--------)=> xor(1,a-1) ^ xor(1,b) b>a and a belongs to b range

 */
       return xor(left-1)^xor(right);
    }
    private static int xor(int n){
        if(n%4==1) return 1;
        else if(n%4==2) return n+1;
        else if(n%4==3) return 0;
        else //(n%4 == 0)
        return n ;
        /*
        T.C =O(1)
        S.C =O(1)
         */
    }

}
