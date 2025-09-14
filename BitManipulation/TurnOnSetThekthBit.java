package BitManipulation;

import java.util.Scanner;

public class TurnOnSetThekthBit {
    public static void main(String[] args) {
        System.out.println("Enter the number ");
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        System.out.println(" Enter the the bit that you want to toggle ");
        int t = sc.nextInt();
        System.out.println(" The  "+t+" th bit is  turn ON "+turnOnKth(n,t));
        System.out.println(" The  "+t+" th bit is  turn ON "+turnOnKth_2(n,t));
    }
    public static boolean  turnOnKth(int num,int k){
        return (num|(1<<k))==num;
    }
    public static boolean turnOnKth_2(int num,int k){
//        num =num>>k;
//        return !(num%2==0);
        return !((num>>k)%2==0);
    }
}
