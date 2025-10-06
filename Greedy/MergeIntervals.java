package Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/** Merge Intervals :
 * Given an array of intervals where intervals[i] = [start,end] merge all overlapping intervals , and return an array of the non
 * - overlapping intervals that cover all the intervals in the input .
 * Example 1 :
 * Input : intervals =[[1,3],[2,6],[8,10],[15,18]]
 * Output : [[1,6],[8,10][15,18]]
 * Explanation : Since intervals [1,3] and [2,6] overlap , merge them into [1,6]
 *
 * Example 2 :
 * Input : intervals =[[1,4],[4,5]]
 * Output : [[1,5]]
 * Explanation : Intervals [1,4] and [4,5] are considered overlapping
 * Constraints :
 * 1<= intervals.length <=10^4
 * intervals[i].length==2
 * 0<=start<=end<=10^4
 */
public class MergeIntervals {
    public static void main(String[] args) {

    }
    public int [][] merge(int [][] intervals){
        Arrays.sort(intervals,new Comparator<int[]>(){
            @Override
            public int compare(int[]o1,int[]o2){// as 2d array is 1d array itself
                return o1[0]-o2[0];// we are sorting it on the basis of the starting point in ascending order
            }
        });
        List<int[]> ans = new ArrayList<>();//{[---],[----],[---]-----} it is like this
        for(int [] interval : intervals){
            if(ans.isEmpty()|| (ans.get(ans.size()-1)[1]<interval[0])){
             ans.add(interval);
            }else{
               int [] newInterval = new int[2];
               newInterval[0] = ans.get(ans.size()-1)[0];
               newInterval[1] = Math.min(ans.get(ans.size()-1)[1],interval[1]);
               ans.set(ans.size()-1,newInterval);
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }
   /** H/w do on the basis of end time */
}
