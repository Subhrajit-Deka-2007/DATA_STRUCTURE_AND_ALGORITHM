package BitManipulation;

import java.util.Scanner;

public class CheckIfKthBitisSetOrNot {
    public static void main(String[] args) {
        System.out.println("Enter a number ");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        System.out.println(" Enter k ");
        int k = sc.nextInt();
         boolean b = isSet(k,num);
         if(b) System.out.println(" The kth bit is turn on / set ");
         else System.out.println(" The  kth bit is turn off/ not set ");

    }
    public static boolean isSet(int k,int num){
        // Bit masking is 1<<k given k
        int mask =num<<k;
        return!((num&mask)==0);
        /*
         if (a&mask==0) the kth bit is turn off/ kth bit is not set
         if(a&mask!=0) the kth bit is turn on / kth bit is set
         */
    }
}
