package BitManipulation;

import java.util.ArrayList;
import java.util.List;

public class Subset {
    public static void main(String[] args) {

    }
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        List<List<Integer>> ls = new ArrayList<>();
        subset(0,nums,ans,ls);
        return ls;
    }
    public void subset(int idx,int [] nums,List<Integer>ls ,List<List<Integer>> list){
        if(idx == nums.length){
            list.add(new ArrayList<>(ls));
            return ;
        }
        // take it or skip it
        // take it
        ls.add(nums[idx]);
        subset(idx+1,nums,ls,list);
        ls.removeLast();
        subset(idx+1,nums,ls,list);

    }
    public List<List<Integer>> subsets_1(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        List<List<Integer>> ls = new ArrayList<>();
        return  subset_1(0,nums,ans,ls);
    }
    public List<List<Integer>> subset_1(int idx,int [] nums,List<Integer>ls ,List<List<Integer>> list){
        if(idx == nums.length){
            list.add(new ArrayList(ls));
            return list;
        }
     /*
     Take it or Skip it
      Take it
       Each element has two option either take it or skip it
      */
        ls.add(nums[idx]);
        subset_1(idx+1,nums,ls,list);
        ls.removeLast();
        subset_1(idx+1,nums,ls,list);
        return list;
/*
T.C =O(2^N-1) EULER'S TREE
S.C =O(N) WHERE N IS THE LENGTH OF THE ARRAY
*/
    }
    public List<List<Integer>> subset_4(int [] nums){
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        int total = 1<<n;
        for(int num = 0;num<total;num++){
            List<Integer> list = new ArrayList<>();
            for(int bitIdx =0;bitIdx<n;bitIdx++){
                int mask = (1<<bitIdx);
                if((num&mask)!=0)// means the bit was set now add that element to the arraylist
                    list.add(nums[bitIdx]);
            }
            ans.add(list);
        }
        return ans;
    }
/*
t.c = for each 2^n we are traversing n time so t.c = O((2^N)*N)
S.C = O(1) NO EXTRA SPACE USED 
 */
}

