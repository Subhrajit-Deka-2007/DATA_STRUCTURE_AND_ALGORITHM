package BitManipulation;

import java.util.Scanner;

public class ToggleKthBit {
    public static void main(String[] args) {
            System.out.println("Enter the number ");
            Scanner sc = new Scanner(System.in);
            int n =sc.nextInt();
            System.out.println(" Enter the the bit that you want to toggle ");
            int t = sc.nextInt();
            System.out.println(" After toggling the number is "+toggleKth(n,t));
        }

    private static int  toggleKth(int n, int k) {
        return ((1<<k)^n);
    }
}

