package DynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;
/**
 Knapsack problem only
  Q) Subset Sum  */
public class SubSetSum {
    public static void main(String[] args) {
        int[] arr ={8,-1,2,4};
        System.out.println(" Enter the target ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println( subset_sum(0,0,arr,n));
        int [][] dp = new int[arr.length][n+1];
        Arrays.fill(dp,-1);
        System.out.println(memoization(0,arr,n,dp));

    }
    /*============================================== Recursion : TRY OUT ALL POSSIBLE SUBSETS USING RECURSION =================================================*/
// WE CAN DO TWO THINGS EITHER WE COULD SEND THE SUM OR WE CAN REDUCE THE TARGET TO K -arr[i] and if condition becomes less then k
// if(target-arr[i]<0) we will not pick only we will skip but this is true for only +ve numbers
    public static boolean  subset_sum(int idx,int sum ,int[] arr, int k) {
        if(idx==arr.length) return(sum== k);
        boolean skip = subset_sum(idx+1,sum,arr,k);
        if(sum+arr[idx]>k) return skip;
        /* don't write this case for -ve as
         just remember the logic at the first sum is greater, than k but if there are negative numbers then
         there is probability that it may give us an answer so don't use this case in some case it may give correct answer
         which is by luck so read the question carefully what is given in the question
         before calling pick checking if sum + current element if we pick if it is greater than why to call pick
         just return skip this thing is only valid for
         +ve numbers but if there are -ve numbers depending on the case it may hit or may not hit the case means
         if the -ve 's magnitude is small or depending on the case it will hit
         but sir told not to write the case when there is negative numbers


         Note in this question 0/1 knapsack question we had two options either we take it or skip it
         0 means skip and one means completely pick up and answer will be not the combination of both either
         skip will give the required answer or take will give the required answer .
         */
        boolean pick = subset_sum(idx+1,sum+arr[idx],arr,k);

        return pick || skip; // either pick will give answer or skip will give answer both cannot give
    }
    /*
    T.C =O(2^N) because of we are trying all possible combination
    S.C =O(N) STACK SPACE LEVEL  = LENGTH OF THE ARRAY
    HERE WE CAN SEE TWO VARIABLES IDX AND SUM IS CHANGING SO 2D DP OF DP[IDX][SUM] IS FORMING sum[0 to target]
     */
    public static boolean memoization(int idx,int[] arr,int target,int[][]dp ){
        // since in the recursive code we can see two variables index and sum are changing so, we make dp of dp[idx][sum] target =[0 to target]
        // idx =0 to n-1 don't make  boolean dp
        if(idx==arr.length) return(target == 0);
        if(dp[idx][target]!=-1) return (dp[idx][target]==1);
        boolean ans = false;
        boolean skip =  memoization(idx+1, arr, target,dp );
        if(target-arr[idx]<0)ans = skip;
        else{
            boolean pick = memoization(idx+1,arr,target-arr[idx],dp);
            ans = pick||skip;
        }
         if(ans) dp[idx][target] =1;// or dp[idx][target] =ans?1:0;
         else dp[idx][target] =0;
        return ans;
    }
/*
T.C =O(N*(T+1))
S.C =O(N*(T+1))
 */
/*=============================Changing idx movements from 0 to n-1 ============================================================================*/
public static boolean memoization_1(int idx,int[] arr,int target,int[][]dp ) {
    // since in the recursive code we can see two variables index and sum are changing so, we make dp of dp[idx][sum] target =[0 to target]
    // idx =0 to n-1 don't make  boolean dp
    if (idx == -1) return (target == 0);
    if (dp[idx][target] != -1) return (dp[idx][target] == 1);
    boolean ans = false;
    boolean skip = memoization(idx - 1, arr, target, dp);
    if (target - arr[idx] < 0) ans = skip;
    else {
        boolean pick = memoization(idx - 1, arr, target - arr[idx], dp);
        ans = pick || skip;
    }
    if (ans) dp[idx][target] = 1;// or dp[idx][target] =ans?1:0;
    else dp[idx][target] = 0;
    return ans;
}
/*======================================= Result is same =======================================================================================*/


}

