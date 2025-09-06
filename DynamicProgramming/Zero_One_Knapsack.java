package DynamicProgramming;

public class Zero_One_Knapsack {
    /*============================================= Recursion ====================================================================================*/
    public static int profit(int idx, int[] wt, int[] price, int capacity, int[][] dp) {
        if (idx == price.length) return 0;
        if (dp[idx][capacity] != -1) return dp[idx][capacity];
      /*
      we will check three things on a current item if the item capacity is less than or equal to the
      capacity if the item capacity is greater  than given capacity of bag then just give it
      we had three option if the item capacity is less than equal to the given capacity then
      i) we had two options either we take it or leave it
      ii) if the item capacity is more than the given capacity we skip it
       */
        int skip = profit(idx + 1, wt, price, capacity, dp);
        if (wt[idx] > capacity) return dp[idx][capacity] = skip;// we will just return skip
        int take = price[idx] + profit(idx + 1, wt, price, capacity - wt[idx], dp);
        return dp[idx][capacity] = Math.max(take, skip);
        /*
        T.C =O(2^N) EACH HAS TWO OPTION TAKE IT OR SKIP IT IF EVERY ITEM WEIGHT<= CAPACITY FOR ITEM WEIGHT>CAPACITY WE JUST RETURN IT
        time complexity is 2^n if we use only recursion solution
        S.C =O(N*C) WHERE N ARE THE ITEMS GIVEN STACK SPACE -------------(N)
                                                                         |
                                                                         |
                                                                         |(C)




        Recursion + Memoization
        T.C =O(N*C) UNIQUE CALLS SIZE OF DP ARRAY
        S.C =O(N*C)
        WE CAN DO THE QUESTION WITH TABULATION  ALSO
         */
    }

    /*========================================== Recursion Ends =========================================================================================*/
    public static void main(String[] args) {
        int[] price = {5, 3, 9, 16};
        int[] wt = {1, 2, 8, 10};
        int c = 8;
        /*
        i = 0 to n-1
        c = c to 0 (capacity) so we will make 2d dp
        To check the question if it is  1D DP OR 2D DP JUST CHECK THE PARAMETERS , HOW MANY PARAMETERS are changing on the basis of that
        we came to know it is 1D DP or 2D DP
         */
        int[][] dp = new int[price.length][c + 1];
        /*
         as  c and zero both are included [0--->c]
         we can also do dp[c+1][price.length]  then return dp[c][i] it will also have to  changed
         */
        for (int i = 0; i < dp.length; i++) for (int j = 0; j < dp[0].length; j++) dp[i][j] = -1;
        System.out.println(profit(0, wt, price, c, dp));
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        tabulation();
    }
    /*
         In recursive code we can go from 0 to n-1 and 0 to C Or we can go from n-1 to 0 and C to 0 for
         but in the recursive code we had went from 0 to n-1 and from 0 to C to convert into tabulation
         first think we are traversing from n-1 to 0 and from   C to 0 okay then just start to convert the recursion logic to
         tabulation in the recursive we are returning two things when
           if (wt[idx] > capacity) return dp[idx][capacity] = skip;// we will just return skip
        int take = price[idx] + profit(idx + 1, wt, price, capacity - wt[idx], dp);
        return dp[idx][capacity] = Math.max(take, skip);
        so we are returning  two times when wt[idx]>capacity  and at last  dp[idx][capacity] = Math.max(take, skip)
        so we convert if(wt[idx]>c) => profit(idx-1,C)
        else {// wt[idx]<c
        profit(val[i]+profit(i-1,c-wt[idx]),profit(i-1,c)
        }
        now convert profit to dp
        okay for logic if dp is travelling from 0 to n-1 and 0 to C but the for loop we will travel from 0 to n-1 and 0 to C+1
        it is also valid also if I think a particular cell answer will depend on cell that are present after

         */
    public static int tabulation() {
        int [] price ={5,3,9,16};
        int [] wt    ={1,2,8,10};
        int Capacity =8;
        int[][] dp = new int[price.length][Capacity+1];
        int skip =-1;
        int pick =-1;
        for(int i =0;i<price.length;i++){
            for(int j =0; j<Capacity+1;j++){// the j represent capacity which is going from 0 to C
                skip = (i>0)?dp[i-1][j]:0;// dp[i-1] as recursive thinking go from n-1 to 0 so for each call we will be going n-1
                if(wt[i]>j) dp[i][j]=skip;
                else{
                    pick = price[i]+((i>0)?dp[i-1][j-wt[i]]:0);
                    // i>0 is to save from going in negative index this will hit when we take it  and j-wt[idx]
                    // will give us an error because of this  if(wt[i]>j) dp[i][j]=skip; we came in else only when
                    // current element weight is less than capacity it will j-wt[i] will give us an +ve value  
                    //
                    dp[i][j] = Math.max(pick,skip);
                }
            }
        }
        for(int i =0; i<dp.length;i++){
            for(int j =0;j<dp[0].length;j++) System.out.print(dp[i][j]+" ");
            System.out.println();
        }
        return dp[dp.length-1][Capacity];// the answer will be the store at the last
    }
}
