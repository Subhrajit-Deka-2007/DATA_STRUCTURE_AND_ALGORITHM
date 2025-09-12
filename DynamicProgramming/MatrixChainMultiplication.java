package DynamicProgramming;

public class MatrixChainMultiplication {
    public static void main(String[] args) {
        int [][] arr ={{1,2},{2,3},{3,4},{4,2}};
        System.out.println(mcm(0,arr.length-1,arr));
        int[] array ={1,2,3,4,2};
        System.out.println(mcm_2(0,array.length-2,array));
        System.out.println(mcm_3(1,array.length-1,array));
    }

    private static int  mcm(int i, int j, int[][] arr) {
        if(i==j) return 0;// means only one matrix
        int minCost =Integer.MAX_VALUE;
        for(int k =i;k<j;k++){
            int x = arr[i][0]*arr[k][1]*arr[j][1];// or we can write arr[k+1][0]
            int totalCost = mcm(i,k,arr)+mcm(k+1,j,arr)+x;// this type of call we call it partition DP
            minCost =Math.min(minCost,totalCost);// we can find maximum costing also just change min to max
        }
        return minCost;
    }
    //coder for 2nd type input format
    /**
     * [1][2][3][4][2]
     * i         j
     * x = arr[i]*arr[k+1]*arr[j+1] and if we consider first as [1] as 1x2
     *
     */
    private static int  mcm_2(int i, int j, int[]arr) {
        if(i==j) return 0;// means only one matrix
        int minCost =Integer.MAX_VALUE;
        for(int k =i;k<j;k++){
            int x = arr[i]*arr[k+1]*arr[j+1];// or we can write arr[k+1][0]
            int totalCost = mcm_2(i,k,arr)+mcm_2(k+1,j,arr)+x;// this type of call we call it partition DP
            minCost =Math.min(minCost,totalCost);// we can find maximum costing also just change min to max
        }
        return minCost;
    }



    private static int  mcm_3(int i, int j, int[]arr) {
        if(i==j) return 0;// means only one matrix
        int minCost =Integer.MAX_VALUE;
        for(int k =i;k<j;k++){
            int x = arr[i-1]*arr[k]*arr[j];// or we can write arr[k+1][0]
            int totalCost = mcm_3(i,k,arr)+mcm_3(k+1,j,arr)+x;// this type of call we call it partition DP
            minCost =Math.min(minCost,totalCost);// we can find maximum costing also just change min to max
        }
        return minCost;
    }
    /*
    RECURSIVE T.C = EXPONENTIAL
     */
/** Now lets use DP  The 2D array input format is also 1D only
 * FOR DP  I AND J ARE CHANGING SO WE WILL FORM 2D ARRAY SIZE OF THE 2D ARRAY ROW DEPEND ON WHERE IS GOING FROM WHERE TO WHERE
 * AND FOR COLUMN ALSO
 * i, STARTING FROM 0 ANd can go for that see mcm_2(k+1,j,arr) see k+1 at max i can go k<j so k+1 max value is j and k+1 will not exceed j
 * see the function as base case (i==j) it would not let i>j it will stop before this condition j maximum value we had sent is n-2  i will not exceed it
 * so dp[n-1] index we have 0 to n-2 and j is starting from 0 and can go till 0 means n-2 to 0 at worst case so
 * dp[n-1][n-1] columns = 0 to n-2 at 0 it will hit base case fir both row and column just draw the diagram and see
 * how i and j are changing i can go from 0 to n-2 at n-2 hit base case so we form dp[n-1][] as n-1 size means index from 0 to n-2
 * */
/*
               DP : Dynamic Programming
 */
    public void dp_memo(int[] arr){
        int[][] dp = new int[arr.length-1][arr.length-1];
        for(int i =0;i<dp.length;i++)for(int j =0;j<dp[0].length;j++)dp[i][j]=-1;
        System.out.println(mcm_4(0,arr.length-2,arr,dp));
    }
    private static int  mcm_4(int i, int j, int[]arr,int[][] dp) {
        if(i==j) return 0;// means only one matrix
        if(dp[i][j]!=-1) return dp[i][j];
        int minCost =Integer.MAX_VALUE;
        for(int k =i;k<j;k++){
            int x = arr[i]*arr[k+1]*arr[j+1];// or we can write arr[k+1][0]
            int totalCost = mcm_4(i,k,arr,dp)+mcm_4(k+1,j,arr,dp)+x;// this type of call we call it partition DP
            minCost =Math.min(minCost,totalCost);// we can find maximum costing also just change min to max
        }
        return dp[i][j]= minCost;
    }
    /**
     * T.C =O((N-1)*(N-1)) => NUMBER OF UNIQUE CALLS
     * S.C =O((N-1)*(N-1)+ STACK SPACE)
     */
}
