package BitManipulation;

/**
 * 231. Power of Two
 * Easy
 * Topics
 * premium lock icon
 * Companies
 * Given an integer n, return true if it is a power of two. Otherwise, return false.
 *
 * An integer n is a power of two, if there exists an integer x such that n == 2x.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: true
 * Explanation: 20 = 1
 * Example 2:
 *
 * Input: n = 16
 * Output: true
 * Explanation: 24 = 16
 * Example 3:
 *
 * Input: n = 3
 * Output: false
 *
 *
 * Constraints:
 *
 * -231 <= n <= 231 - 1
 *
 *
 * Follow up: Could you solve it without loops/recursion?
 */
public class PowerOfTwo {
    public static void main(String[] args) {
        // The concept is simple if the number is even it is divisible by means it will be power of 2 and if not then it will not a power of 2
    }
    public boolean isPowerOfTwo(int n) {
        return(n>0&&((n&n-1)==0));
      /* Simple n>0 as -ve numbers cannot be the power of 2 it will power of -2 not power of +2
       -8 it is the (-2)^3 not 2^3
       Brut force as 2^power is like this so, we can divide 100000---0 like this and right shift till
       we did not get 1 as last 1 will be after shifting it will be 0000000000001 and 1 is 0th place so answer is 0*/

    }
    public boolean brut_force(int n){
        while(n!=00&&n%2==0) n= n>>1;
        return (n==1);
        /*
        T.C =O(31) OR LOG 2 N
        S.C =O(1)
        */
    }
    public boolean isPower(int n ){
        if(n==1) return true;
        if(n<=0|| n%2!=0) return false;
        return isPower(n/2);
    /* n --> n/2---> n/4---> ----1
    T.C =o(Log n)
    s.c =o(log n)
    */

    }
}
