package BitManipulation;

public class Exponent {
    public static void main(String[] args) {
        /*
         To find power of 2 if we use 2^n/2 *2^n/2 when n is even and, we use 2^n/2 * 2^n/2* 2 we use it when n is odd
        But its T.C is O (log n) but if we use 2^n = 1<<n we will get the answer in O(1)
         */
        System.out.println(1<<2);// 2^n is 1<<n
    }
}
