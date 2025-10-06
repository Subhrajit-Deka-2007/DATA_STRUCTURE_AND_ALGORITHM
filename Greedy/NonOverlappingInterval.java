package Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NonOverlappingInterval {
    public static void main(String[] args) {

    }
        public int eraseOverlapIntervals(int[][] points) {
            List<int[] > ans = new ArrayList<>();
            Arrays.sort(points,(a, b)->Integer.compare(a[0],b[0]));
            for(int [] point :points){
                if(ans.size()==0||ans.get(ans.size()-1)[1]<=point[0])
            /*
            [1, 2] and [2, 3] are non-overlapping.  This time we are considering it different
            so we simply include it in the answer as it will be not considered
            */
                    ans.add(point);
                else{
                    int start = Math.max(ans.get(ans.size()-1)[0],point[0]);
                    int end = Math.min(ans.get(ans.size()-1)[1],point[1]);
                    int [] intervals = {start,end};
                    ans.set(ans.size()-1,intervals);
                }
            }
            return points.length-ans.size();
        }
    }

