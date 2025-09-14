package BitManipulation;

import java.util.Scanner;

public class TurnOff_ClearTheKthBit {
    public static void main(String[] args) {
        System.out.println("Enter the number ");
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        System.out.println(" Enter the the bit that you want to toggle ");
        int t = sc.nextInt();
        System.out.println(" The "+t+" th bit is already turn off "+turnOffKth(n,t));
    }
    public static boolean  turnOffKth(int num,int k){
        int mask =1<<k;
        mask =~mask;
        return (((num&mask)==num));
    }
}
