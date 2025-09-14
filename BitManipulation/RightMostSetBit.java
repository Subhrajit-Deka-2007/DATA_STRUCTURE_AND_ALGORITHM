package BitManipulation;

import java.util.Scanner;

/**
 * We have to look from the right side and find out that first right most bit that is turn on and we have to turn it off we are not given k
 */
public class RightMostSetBit {
    public static void main(String[] args) {
        /**
         * M-1 :
         * ALGO : First we will make a copy of given number and, we know if the number is even the rightmost bit will be zero so we will right
         * shift it by 1 and we will be , continuously doing it till the number become odd means the rightmost bit will be 1 and we want the first
         * rightmost bit which is 1  we are not given k
          */
        System.out.println(" Enter a number ");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        System.out.println(" After turning the rightmost turn on bit the number is "+method_1(num));
    }
    public static int method_1(int num){
        int b = num;
        int k =0;
        while(b%2==0){
            b =b>>1;
            k++;
        }
        // And we know to turn of the kth bit by making a bit mass
        int mass =1<<k;
        /*
        it will be 000000010000000000 but we want 1111111101111111 for that we will use
         */
        mass =~mass;// toggling the bit
        /*
        Now we will be taking & with the mass and the number
         */
        return (num&mass);
    }
}
