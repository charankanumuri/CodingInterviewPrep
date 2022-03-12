package Tree;

//   Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class MaxDepthOfBT {

    // leetcode question Number : 104
    // Given the root of a binary tree, return its maximum depth.

    // A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

    // Input: root = [3,9,20,null,null,15,7]
    // Output: 3
    public static int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int lh = maxDepth(root.left);
        int rh = maxDepth(root.right);
        return Math.max(lh, rh) + 1;
    }
}
