package BitManipulation;

import java.util.Scanner;

public class TurnOnSetThekthBit {
    public static void main(String[] args) {
        System.out.println("Enter the number ");
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        System.out.println(" Enter the the bit that you want to toggle ");
        int t = sc.nextInt();
        System.out.println(" Number after turning on that "+t+" th bit is "+turnOnKth(n,t));
    }
    public static int turnOnKth(int num,int k){
        return num|(1<<k);
    }
}
