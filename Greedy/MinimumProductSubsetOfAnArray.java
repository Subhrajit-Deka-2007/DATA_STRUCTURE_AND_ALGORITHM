package Greedy;

/**
 * Given an array , we have to find the minimum product possible with the subset of elements present in the array
 * . The minimum product can be a single element also .
 * Example 1:
 * Input : a[] ={-1,-1,-2,4,3}
 * Output : -24
 * Explanation : Minimum product will be (-2*-1*-1*4*3)=-24
 *
 * Example 2:
 * Input : a[] = {-1,0}
 * Output : -1
 * Explanation : -1(single element ) is minimum product possible
 *
 * Example 3:
 * Input : a[] = { 0,0,0}
 * Output = 0
 *
 *
 *
 * Facts :
 * 1) If there are even number of negative numbers and no zeros , the result is product of all except
 * the largest valued negative number {because if we multiply with the that it become +ve big number now it has odd number of - ve numbers
 * so it will be a smaller number only in -ve big number means -5>-989 okay.}


 * 2)If there are an odd number of negative numbers and zeros , the result is simply the product of all .
 * 3) If there are zeros and positive , no negative , the result is 0. The exceptional case is when there is no negative numbers
 * and all other elements positive then our result should be the first minimum positive number
 */
public class MinimumProductSubsetOfAnArray {
    public static void main(String[] args) {

    }
    public int minProductSubset(int [] a){
        if(a.length==1)return a[0];
        int negmax =Integer.MIN_VALUE;
        int posmin = Integer.MAX_VALUE;
        int count_neg =0,count_zero =0;
        int product =1;
        for(int i =0;i<a.length;i++){
            if(a[i]==0){
                count_zero++;
                /*

                 we don't want to multiply with product as if there are -ve numbers +zero in given number then answer will
                 product of -ve numbers so answer will be less than zero so, we are not including in the answer .

                 */
                continue;
            }
            if(a[i]<0){
                count_neg++;
                negmax = Math.max(negmax,a[i]);
            }
            if(a[i]>0&&a[i]<posmin)posmin=a[i];

            product*=a[i];// we are adding the last two if's a[i] element  in the product
        }
        if(count_zero==a.length||(count_neg==0&&count_zero>0))return 0;
        if(count_neg==0)return posmin;
        if(count_neg%2==0&&count_neg!=0){
            product =product/negmax;
        }
        return (-1)*product;// this is for that if condition
    }

}
