package DynamicProgramming;

import java.util.Scanner;

/**
 * Friends Pairing Problem
 * Difficulty: MediumAccuracy: 25.04%Submissions: 129K+Points: 4
 * Given n friends, each one can remain single or can be paired up with some other friend. Each friend can be paired only once. Find out the total number of ways in which friends can remain single or can be paired up.
 *
 * Examples :
 *
 * Input: n = 3
 * Output: 4
 * Explanation:
 * {1}, {2}, {3} : All single
 * {1}, {2,3} : 2 and 3 paired but 1 is single.
 * {1,2}, {3} : 1 and 2 are paired but 3 is single.
 * {1,3}, {2} : 1 and 3 are paired but 2 is single.
 * Note that {1,2} and {2,1} are considered same.
 *
 *
 * Input: n = 2
 * Output: 2
 * Explanation:
 * {1} , {2} : All single.
 * {1,2} : 1 and 2 are paired.
 *
 * Input: n = 1
 * Output: 1
 *
 *
 *
 * Constraints:
 * 1 ≤ n ≤ 18
 */
public class FriendsPairingProblem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of friends ");
        int n = sc.nextInt();
        int[] dp = new int[n + 1];
        /*
        as we want the answer exactly at n and 0 INDEX IS TREATED AS 0 FRIEND ITSELF AND SO WE WANT AN NTH
        TO TREAT IT AS NTH INDEX
         */
        dp[0] = 1;
        dp[1] = 1;// ny assigning these we can handle without base case also
        pairing_2(n, dp);
        System.out.println(dp[n]);// answer will be store at the nth index why see the explanation i had written below
        for (int i = 0; i < dp.length; i++) System.out.print(dp[i] + " ");
        System.out.println();
        System.out.println(pairing_3(dp));
        System.out.println();
        System.out.println(pairing_4(n));
    }

    /*=============================================Recursive Solution ================================================================================*/
    private static int pairing(int n) {

        if (n == 0 || n == 1) return 1;
      /*
      0 as we are reducing 2 times so, it can reach  o for n = even and n=1 for n = odd number of friends
      for n-1 it is moving one forward so, we can stop it n=1 as it is moving by
      only one step
       */
        return pairing(n - 1) + (n - 1) * pairing(n - 2);
    }
    /*=================================================Recursive Solution Ends ========================================================================*/

    /*=================================================Recursion+ Memoization===========================================================================*/
    private static int pairing_2(int n, int[] dp) {

        // if(n==0||n==1) return 1;
      /*
      0 as we are reducing 2 times so, it can reach  o for n = even and n=1 for n = odd number of friends
      for n-1 it is moving one forward so, we can stop it n=1 as it is moving by
      only one step
       */
        if (dp[n] != 0) return dp[n];// no base case needed as prefilled it

        else return dp[n] = pairing_2(n - 1, dp) + (n - 1) * pairing_2(n - 2, dp);

        /* answer will be store at the nth index how?<------------we are calling like this so
                                                                 ^ so it will end here only which is the nth index
         */


    }

    /*
    T.C =O(N)
    S. C =O( RECURSIVE SPACE O(N)+ DP ARRAY (N+1))
     */
    /*============================================Tabulation : Bottom Up Dp ===========================================================*/
    private static int pairing_3( int[] dp) {

        for (int i = 2; i < dp.length; i++) dp[i] = dp[i - 1] + (i - 1) * dp[i - 2];
        return dp[dp.length-1];
        /*
        T.C =O(N-2)
        S.C =O(N+1)
         */
    }



    private static int pairing_4( int n) {
        int s1 =1;
        int s2 =1;
        int x =-1;
        for (int i = 2; i <=n; i++){
            x= s2;
            s2 = s2 + (i-1) * s1;
            s1 = x;
        }
        return s2;
        /*
        T.C =O(N-2)
        S.C =O(1)
         */
    }
/*============================================Tabulation : Bottom Up approach END ===============================================================*/
}
/*
 Here we cannot go from right to left as we don't have the right value if we have value of both left and right then
 we can go from both left and right from both methods


 And to do with tabulation I have to know recursion --> then recursion + memoization--->if I can visualize(recursion + memo)
 then I can do tabulation also for recursion+memo  how it works for this I had to know how recursion is working and its calls
 so main is recursion then I can shift to tabulation    



 */

