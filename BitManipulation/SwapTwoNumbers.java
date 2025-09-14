package BitManipulation;

public class SwapTwoNumbers {
    public static void main(String[] args) {
        /*

        M-1 : Using 3rd variable
        temp = a;
           a = b;
           b = temp;


        M-2 : Without using 3rd Variable
        a = a+b;
        b = a-b;
        a = a-b;

        M-3 : Using Bitwise Operator (XOR)
        XOR IS USED TO TOGGLE THE BITS a^1 then output was flipped(a) a is number
        x^x  = 0
        x^0  = x ---> x is a number not a bit
        and by using associativity property XOR is associative
        x are number by using the properties we are swapping the numbers
         */
        int a =6;
        int b =7;
        a = a^b;
        b =a^b;
        a =a^b;
        System.out.println(" a "+a+" "+" b "+b);
    }
}
