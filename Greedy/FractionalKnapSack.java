package Greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Q) The weight and profits of N items , in the form of (profit, weight) put these, in a knapsack of capacity W, to get the maximum
 * total profit int the ack. In fractional Knapsack , we can break items for maximizing the total of the  knapsack .
 *
 * arr[] ={{60,10},{100,20},{120,30}}, W = 50
 * profit = 240
 * Explanation : By taking items of weight 10 and 20 kg and 2/3 fraction of 30 kg
 * Total price will be 60+100+2/3*120 = 240
 * arr[] ={{500,30}},W = 10
 * profit =166.667

 */
public class FractionalKnapSack {

    // Function to get maximum value
    public static double getMaxValue(ItemValue[] arr, int capacity){
        // Sorting items by profit/weight ratio
        Arrays.sort(arr,new Comparator<ItemValue>(){
            @Override
            public int compare(ItemValue item1, ItemValue item2){
                // Sorting in decreasing order in the ratio profit/weight
                double cpr1 = (double)item1.profit/(double)item1.weight;
                double cpr2 =(double)item2.profit/(double)item2.weight;
                if(cpr1<cpr2)return 1;
                else return -1;
            }

        });
        double totalValue =0d;
        for(ItemValue i : arr){
            int curWt = (int)i.weight;
            int curVal =(int)i.profit;
            if(capacity-curWt>=0){
                // The weight can be picked
                capacity = capacity-curWt;
                totalValue+=curVal;
            }else{
                // Whole item cannot be picked up
                double fraction =((double)capacity/(double)curWt);
                totalValue+=(curVal*fraction);
                capacity = (int)(capacity -(curWt*fraction));
                break;
            }
            System.out.println("totalVal "+totalValue);
        }
        return totalValue;
        }
        // Item value class
    static class ItemValue{
        int profit,weight;
        //Item Value function
        public ItemValue(int val,int wt){
            this.weight = wt;
            this.profit = val;
        }

    }
    // Driver code
    public static void main(String[] args) {
        ItemValue [] arr = {new ItemValue(60,50),new ItemValue(100,20),new ItemValue(120,30)};
        int capacity = 50;
        double maxVal = getMaxValue(arr,capacity);
        // Function call
        System.out.println(maxVal);
    }
}
