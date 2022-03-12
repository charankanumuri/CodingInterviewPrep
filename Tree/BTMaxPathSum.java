package Tree;


public class BTMaxPathSum {
    
    // leetcode question Number : 124
    // A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. 
    // A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

    // The path sum of a path is the sum of the node's values in the path.

    // Given the root of a binary tree, return the maximum path sum of any non-empty path.

    int max = Integer.MIN_VALUE;
    
    // TC : O(N) as we touch every node during the postOrder traversal.
    // SC : O(H) as H-height of the tree & recursion happens H times. 
    public int maxPathSum(TreeNode root) {
        postOrder(root);
        return max;
    }
    
    public int postOrder(TreeNode root) {
        if(root == null)
            return 0;
        // If the post order gives negitive value
        // waste of considering the sum, so bound it to Zero.
        int left = Math.max(postOrder(root.left), 0);
        int right = Math.max(postOrder(root.right), 0);
        
        max = Math.max(max, left + right + root.val);
        return Math.max(left, right) + root.val;
    }
}
