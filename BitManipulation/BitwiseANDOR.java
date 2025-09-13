package BitManipulation;

public class BitwiseANDOR {
    public static void main(String[] args) {
              /** Bitwise OR  */
        System.out.println(5|9);// first convert 5 to binary then 9 to binary then do the operation
        System.out.println(-5|-4);// first we will convert -5 and -4 to binary then we will use the Bitwise OR operator
        /*
      NOTE:If we do ORing of number with itself then output is result itself 1011 | 1011 => output => 1011(It is the number itself)
      SIMILARLY IT IS TRUE FOR BITWISE AND OPERATOR ALSO
      System.out.println(5|5);=> Output => 5
      System.out.println(5&5);=> Output => 5 Not true for all type of operators
      x|0 =x
      x&0 =0
      x|1 =1 x is a single bit here the above two x are numbers not a single bit
         */
        /** Bitwise AND */
        System.out.println(5&7);
        System.out.println(45&6);
        // byte x = 257 ; gives error not possible
        System.out.println(Short.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
        System.out.println(Byte.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE);
       /**                               Bitwise  XOR(^)                                                                            */
       /*
       If both are same it will be zero 1^1 =0, 0^0 =0 and if both are different then  1
       When we have even number of ones answer is 0 and odd number of ones then answer is 1
       Any bitwise operator the order does not matter answer same a|b|c|d or a|c|b|d answer same true for any bitwise operator
       Bitwise , operator are associative

       In interview main question asks from XOR, Left Shift and Right Shift Operator
        */;
        System.out.println(1^1);

        /**                              1's Complement , 2's Complement                                                           */
        /*
        1's complement we can use tilde(~) or backtick(`)
        Formula for ~x is =-x-1(It is 1's complement)
        2's complement of x is -x or we can do is ~x+1
        2's complement is (1's complement(x))+1(the addition 1 is binary number)
         */
        System.out.println(~6);//1's complement--->~x
        System.out.println(~6+1);// 2's complement answer we can find manually it is -x
        /**     Right Shift Operator (>>) the left side we put the number by how many bit we will shift the number      */
        System.out.println(91>>2);// the 92's binary number  get shift by 2 bits on right side
        /**              Left Shift Operator (<<)                                                               */
        System.out.println(91<<2); // Left Shift is more important than right shift
        // the  number a<<k increases generally

    }
}
