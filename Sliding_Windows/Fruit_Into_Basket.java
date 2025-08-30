package Sliding_Windows;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

/**
 * 904. Fruit Into Baskets
 * You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
 *
 * You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
 *
 * You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
 * Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
 * Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
 * Given the integer array fruits, return the maximum number of fruits you can pick.
 *
 *
 *
 * Example 1:
 *
 * Input: fruits = [1,2,1]
 * Output: 3
 * Explanation: We can pick from all 3 trees.
 * Example 2:
 *
 * Input: fruits = [0,1,2,2]
 * Output: 3
 * Explanation: We can pick from trees [1,2,2].
 * If we had started at the first tree, we would only pick from trees [0,1].
 * Example 3:
 *
 * Input: fruits = [1,2,3,2,2]
 * Output: 4
 * Explanation: We can pick from trees [2,3,2,2].
 * If we had started at the first tree, we would only pick from trees [1,2].
 *
 *
 * Constraints:
 *
 * 1 <= fruits.length <= 105
 * 0 <= fruits[i] < fruits.lengt
 */
public class Fruit_Into_Basket {
    public static void main(String[] args) {
        int[] fruits ={0,1,6,6,4,4,6};
        System.out.println(totalFruits(fruits));
        // Similar question pick toys
    }

    public  static int totalFruits(int[] fruits) {
        // My algo is wrong still it passed 71 test case out of 92 good job
        int i = 0, j = 0, n = fruits.length;
        int type = 0;
        int type_1 = -1;
        int type_2 = -1;
        int len = 0;
        int maxLen = Integer.MIN_VALUE;
//        while (j < n && type < 2) {
//            if (fruits[j] == type_1 || fruits[j] == type_2) j++;
//            else {
//
//                if (type < 2) {
//                    type++;
//                    type_2 = fruits[j];
//                    type_1 = fruits[i];
//                    j++;
//                } else {
//                    len = j - i;
//                    maxLen = Math.max(maxLen, len);
//                    while (fruits[i] == type_1) i++;
//                    type--;
//                    break;
//                }
//            }


            while (j < n) {
                if (fruits[j] == type_1 || fruits[j] == type_2){
                  //  System.out.println("hit j value "+j);
                    j++;
                }
                else {
                    if (type < 2) {
                        type++;
                        type_2 = fruits[j];
                        type_1 = fruits[i];
                        j++;
                        System.out.println("type_2 "+ type_2+" type_1 "+ type_1);
                    }
                else{
                    len = j - i;
                  //  System.out.println("length "+len);;
                    maxLen = Math.max(maxLen, len);
                    type--;
                    while (i!=j-1){
                        i++;
                    }

                        System.out.println("i "+ i);
                       // System.out.println(" i "+i+" j "+j);
                }

            }

        }
            len = j -i ;
            maxLen = Math.max(maxLen,len);

            return maxLen;
    }



    public int totalFruit(int [] arr){
        int n = arr.length,i =0, j =0, maxLen =0;
        Map<Integer,Integer> map = new HashMap<>();
        while(j<n){
            if(map.containsKey(arr[j]))map.put(arr[j], map.get(arr[j])+1);
            else map.put(arr[j],1);

            /*
             if map.size()>2 then we starting moving the i  and with that we decrease that current i element frequencies also
             and , we remove the element whose frequency get zero as we want only two variety elements in the map
             */
            while(map.size()>2){
                int freq = map.get(arr[i]);
                // frequency of that current ith element is 1 just remove it only
                if(freq ==1)map.remove(arr[i]);
                else map.put(arr[i],freq-1);
                i++;
            }

            // now after doing the while loop map size has become 2
            int len = j-i+1;
            maxLen = Math.max(len,maxLen);
            j++;

        }
        return maxLen;
    }
    /*
    T.C =O(N+N)
    S.C =O(1)
     */
}



