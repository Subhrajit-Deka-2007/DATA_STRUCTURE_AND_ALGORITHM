package BitManipulation;

/**
 * 342. Power of Four
 * Given an integer n, return true if it is a power of four. Otherwise, return false.
 *
 * An integer n is a power of four, if there exists an integer x such that n == 4x.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 16
 * Output: true
 * Example 2:
 *
 * Input: n = 5
 * Output: false
 * Example 3:
 *
 * Input: n = 1
 * Output: true
 *
 *
 * Constraints:
 *
 * -231 <= n <= 231 - 1
 *
 *
 * Follow up: Could you solve it without loops/recursion?
 */
public class PowerOfFour {
    public static void main(String[] args) {

    }

    public boolean isPowerOfFour(int n) {
        if (n == 1) return true;
        if (n <= 0 || n % 4 != 0) return false;
        return isPowerOfFour(n / 4);
        /*
        T.C = N--> N/4-->N/16-----    1
        r =1/4
       T.C => Number of terms = log 4 N
        as on each time we are not doing n works or n/4 works we are just calling where number of call is log 4 N and work
        done in each call is C
        S.C = O(1)
         */
    }

    /*
    M-2 : FIRST WE WILL CHECK THE NUMBER IS POWER OF + 2 THEN WE WILL CHECK IF THE NUMBER IS PERFECT SQUARE OR
    NOT THEN WE CAN SAY NUMBER IF ALL ARE YES THEN WE CAN SAY NUMBER IS POWER OF K
     */
    public boolean isPowerOfFour_1(int n) {
        return (n > 0 && ((n & n - 1) == 0) && (Math.sqrt(n) * Math.sqrt(n) == n));
        /*
        T.C =O(1)
        S.C =O(1)
         */
    }

    /*
    m-3 : First check the number is power of 2 and then one more new condition if nay power of 4 is divided by 3 leaves the remainder 1
     */
    public boolean isPowerOfFour_2(int n) {
        return (n > 0 && ((n & n - 1) == 0) && (n % 3 == 1));
    }
}
