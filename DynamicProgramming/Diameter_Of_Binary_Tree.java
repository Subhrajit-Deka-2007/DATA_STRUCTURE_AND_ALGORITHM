package DynamicProgramming;
/**
 * 543. Diameter of Binary Tree
 * Given the root of a binary tree, return the length of the diameter of the tree.
 *
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 *
 * The length of a path between two nodes is represented by the number of edges between them.

 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 * Example 2:
 *
 * Input: root = [1,2]
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -100 <= Node.val <= 100

 */
import java.util.HashMap;
import java.util.Map;

class TreeNode{
    TreeNode left;
    TreeNode right;
    int val;
    TreeNode(int val){
        this.val = val;
    }
}


public class Diameter_Of_Binary_Tree {
    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);
        TreeNode f = new TreeNode(6);
        TreeNode g = new TreeNode(7);
        TreeNode h = new TreeNode(8);
        TreeNode i = new TreeNode(9);
        TreeNode j = new TreeNode(10);
        TreeNode k = new TreeNode(10);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        e.left = j;
        c.left = f;
        c.right = g;
        d.left = h;
        d.right = i;
        HashMap<TreeNode,Integer > dp = new HashMap<>();
        System.out.println(diameterOfBinaryTree_2(a,dp));

    }




/*===================================== RECURSION OPTIMIZE ===================================================================================*/
// IN UN OPTIMIZE WE JUST CALL THESE LEVEL FUNCTION FOR EACH node
    //It was optimize by me
        private static int level(TreeNode root, int[] maxDia) {
        if (root == null) {
            return 0;
        } else {
            int left = level(root.left, maxDia);
            int right = level(root.right, maxDia);
            maxDia[0] = Math.max(maxDia[0], left + right);
            /* My opinion i can use HashSet also which value i will insert and update only when I get a value less than  that current element
             present in HashSet we just update the value but if we want the node also + diameter i would form a Hashmap of <node,int(diameter)>

            /*
             I had done this in one go  In the answer we are not adding the nodes itself we are just adding the left and right edges
             to get the diameter
             Also not necessary the  maximum path passed through the root only
              */
            return 1 + Math.max(left, right);
            /*
            as when we are putting diameter of the current root we just do right +left no 1 after i put the diameter of that particular node
            after that for the next node which had called it also have to include the calling node edges so i do +1 before returning to that function
            try to understand why 1 is added after putting the diameter
            /*
            T.C =O(N) => WHERE N REPRESENT THE TOTAL NODES WE ARE TRAVERSING EACH NODE TWO TIMES ONE IS CALLING AND OTHER IS RETURNING SO T.C IS O(2N)
            S.C -O(H) WHERE H IS THE LEVEL OF THE TREE LOG N WHERE N IS THE TOTAL  NUMBER OF NODES => IN WORST CASE WE TAKE SCREWED OR SKEWED TREE
             */

        }
    }
/*===============================================RECURSION OPT ENDS =========================================================================================*/







/*===============================If we only believe that maximum path passed through the levels =================================================================*/

/*public int diameterOfBinaryTree(TreeNode root){

    return level(root.left)+level(root.right)
}

 */





/*==================================Recursion Un optimize =================================================================================================*/
public int levels(TreeNode root){
    if(root == null ) return 0;
    int leftlevel = levels(root.left);
    int rightlevel = levels(root.right);
    return 1+Math.max(leftlevel,rightlevel);
}
public int diameterOfBinaryTree(TreeNode root){
    if(root == null) return 0;
    int myDia = levels(root.left)+levels(root.right);
    int leftDia =diameterOfBinaryTree(root.left);
    int rightDia =diameterOfBinaryTree(root.right);
    return Math.max(myDia,Math.max(leftDia,rightDia));
}
/* IN THIS CODE FOR EACH NODE I AM FINDING THE LEVEL SO T.C IS VERY BAD N + N-1+N-2+ ---+1 => IF I CONSIDERED A SKEWED TREE
T.C =O(N*(N-1)/2) => O(N^2-N/2)
S.C =O(H) STACK SPACE EACH CALL WILL HAVE DIFFERENT TREE BUT WE WILL SELECT THE ODE WHICH CONTAINS THE MAXIMUM  LEVELS
AS S.C IS THE MAX SPACE USED BY ALGO IN A PARTICULAR TIME
VERY BAD CODE AS FOR EACH NODE WE ARE FINDING LEVEL
 */





/*=============================================== REC + MEM(USING HASHMAP)======================================================================*/
public static void Diameter_Of_BT(TreeNode root){// my method
    /* Memoize using hashmap as we will store node and that node levels
    We are using HashMap for memoization as nodes have different value so, it cannot be
    index understanding what I am saying that is why
    we are using HashMap as in Tree there is no idea of index so, we are using a HashMap for memo
    */
    Map<TreeNode,Integer > map = new HashMap<>();
    level_1(root,map);
    for(TreeNode x :map.keySet()) System.out.println("Node "+x.val+" MaxLevelOfthatNode  "+map.get(x)+" ");
}
    private static int level_1(TreeNode root, Map<TreeNode,Integer> map) {
        if (root == null) return 0;
        int left_level = level_1(root.left,map);

        int right_level = level_1(root.right,map);
        map.put(root,left_level+right_level);
        return 1+  Math.max(left_level,right_level);
    }






/*=========================================  Sir approach of Memo ===============================================================================*/
public static int levels_2(TreeNode root,HashMap<TreeNode ,Integer>dp){
    // Sirs method need memoization as we are calling the function many times for many nodes but in my method no memo is needed as I had
   // done it in one go means in my method there is no redundant calls my method calls each node only one time
    if(root == null ) return 0;
    if(dp.containsKey(root))return dp.get(root);
    int leftlevel = levels_2(root.left,dp);
    int rightlevel = levels_2(root.right,dp);
    dp.put(root,1+Math.max(leftlevel,rightlevel));
    return dp.get(root);
}
    public static int diameterOfBinaryTree_2(TreeNode root,HashMap<TreeNode,Integer>map){
        if(root == null) return 0;
        int myDia = levels_2(root.left,map)+levels_2(root.right,map);
        int leftDia =diameterOfBinaryTree_2(root.left,map);
        int rightDia =diameterOfBinaryTree_2(root.right,map);
        return Math.max(myDia,Math.max(leftDia,rightDia));
    }
/*
T.C  = O(N) => WHERE N IS THE NUMBER OF NODES
S.C  = O(H) H =>HEIGHT OF THE TREE STACK SPACE + HASHMAP SPACE =>O(N)
T.C =O(N)
S.C =O(LOG N + N)
 */
/*=========================================== Sir's approach ends ================================================================================*/





/*================================================= Without memoization DP =================================================================================*/
    /*
    My method only using x[0] one size array for storing the maxDia  that method only we cannot use wrapper class as it is not
    passed by reference it is passed by value ----> the method is  little different
    */
public class Int{
    int val;
    Int(int val){
        this.val = val;
    }
}
public int levels_3(TreeNode root,Int dia){
    if(root == null) return 0;
    int left_level = levels_3(root.left,dia);
    int right_level =levels_3(root.right,dia);
    int path = left_level+right_level;
    dia.val = Math.max(dia.val,path);
    return 1+ Math.max(left_level,right_level);
}
 public int diameterOFBinaryTree(TreeNode root){
    Int dia = new Int(0);
    levels_3(root,dia);
    return dia.val;
/*
H/W : BALANCED BINARY TREE USING RECURSION , REC+MEMO(USING HASHMAP) NOW DON' DO THE TABULATION FOR THIS
FOR BALANCED BINARY TREE  FOR EACH NODE =>|LEVEL(LST)-LEVEL(RST)|<=1
 */
 }
}


