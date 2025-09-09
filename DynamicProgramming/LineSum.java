package DynamicProgramming;

public class LineSum {
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
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        e.left = j;
        c.left = f;
        c.right = g;
        d.left = h;
        d.right = i;

    }
    public static int lineSum(TreeNode root){
    /*
    In Line Sum we just take  maximum root to leaf path
     ALGO : We just take the current root
    value then add with the maximum  root to leaf from LST or RST we will only add rst or lst depending on who is giving maximum root
    to leaf path
     */
        if(root == null) return 0;
        int leftSum = lineSum(root.left);
        int rightSum = lineSum(root.right);
        return root.val+Math.max(leftSum,rightSum);
    }
}
