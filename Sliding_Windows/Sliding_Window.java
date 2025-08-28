package Sliding_Windows;

import java.util.Scanner;

/**
 * Utility of Sliding Windows :
 * 1) Sub array : continuous pieces of array
 * 2) Substrings
 * 3) Types : K sized / Variable sized
 */
public class Sliding_Window {
    public static void main(String[] args) {
       Sliding_Window obj = new Sliding_Window();
        System.out.println("The maximum is "+obj.max_sub_array_BRUT_force());
        System.out.println();
        System.out.println(obj.method_2());
    }
    /*BASIC ALGO :
    Q) MAXIMUM SUM SUB ARRAY OF SIZE K
    ARR ={10,20,1,3,-40,80,10},K =3

     */
    public int max_sub_array_BRUT_force(){
        // BASIC APPROACH
        /*
        Brut force :
        ALGO : WE TRAVERSE N-K+1 TIMES AND ALSO N-K+1 WINDOWS OF K SIZE WERE THERE
        AND FOR EACH ITERATION WE TRAVERSE K TIMES => T.C =(N-K+1)*K => NK -K^2+K
        IF K =N N^2-N^2+N => O(N) WE JUST TRAVERSE THE INNER LOOP N TIMES AND OUTER LOOP WILL BE 1 TIME
        k range is from [1,arr.length]
         */
        int sum =0;
        int prev_sum =Integer.MIN_VALUE;
         int [] arr ={10,20,1,3,-40,80,10};
         Scanner sc = new Scanner(System.in);
         System.out.println("Enter the k ");
         int k = sc.nextInt();
         for(int i =0; i<arr.length-k+1;i++){// N-K+1 NUMBER OF WINDOWS AND EACH OF K SIZE
             for(int j =i; j<k+i;j++)sum+=arr[j];
             prev_sum = Math.max(prev_sum,sum);
             sum =0;
         }
         return prev_sum;
         /*
         T.C =O((N-K+1)*K)=> NK-K^2+K => K = N => N^2-N^2 +N => O(N)

          IF K =N/2 => N^2 -N^2/4 + N/2 => O(3n^2+N/2) BAD
         S.C =O(1)
          */
    }

    // Another method by me
    public int method_2(){
        int sum =0;
        int prev_sum ;
        int [] arr ={10,20,1,3,-40,80,10};
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the k ");
        int k = sc.nextInt();
        // I HAD FIRST ,CALCULATE THE SUM OF THE FIRST  WINDOW OF SIZE K
        for(int i =0; i<k;i++) sum+=arr[i];
        prev_sum = sum;

        for(int i =1; i<arr.length-k+1;i++){
            sum-=arr[i-1];  // => O(1) OPERATION => HOW  I HAD THINK SEE THE COPY
            sum+=arr[k+i-1];// => O(1) OPERATION
            prev_sum =Math.max(prev_sum,sum);// 0(1) OPERATION

        }
        return prev_sum;
    }
    /*
    T.C =0(K + N-K )
        =O(N) I HAD THOUGHT THESE METHOD BY MYSELF
        IF K =N/2 PREVIOUS METHOD GIVING US N^2 SO THIS APPROACH IS BETTER NO MATTER WHAT IT IS GIVING ME O(N)
        sliding  WINDOW WE STUDIED IN STRING COMPRESSION THEN AGAIN AND ON HASH MAP
     */

// sir's way of doing it
    public int sirs_method(){
        int [] arr ={10,20,1,3,-40,80,10};
        int k =2;
        int n = arr.length;
        int maxSum =0;
        int i =0, j =k+1,sum =0;
        for(int a =0;a<=j;a++)sum+=arr[a];// k times
        i++;
        j++;
        while(j<n){// running n-k times
            sum=sum-arr[i-1]+arr[j];
            maxSum = Math.max(maxSum,sum);
            i++;
            j++;
        }
        return maxSum;
    }
    /*
    t.c =o(n)
    s.c =o(1)
     */
}
