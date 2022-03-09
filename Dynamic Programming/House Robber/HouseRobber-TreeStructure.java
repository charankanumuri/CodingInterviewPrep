/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int rob(TreeNode root) {
        if(root == null)
            return 0;
        
        //maintain this to avoid repetitive subproblem calculations
        HashMap<TreeNode, Integer> memo = new HashMap<>();
        return robRecursive(root, memo);
    }
    
    public int robRecursive(TreeNode node, HashMap<TreeNode, Integer> memo){
        if(node == null)
            return 0;
        
        //If already calculated, return the result for that node
        if(memo.containsKey(node))
            return memo.get(node);
        
        // Selecting current house and skipping it's children if children is not NULL, call its grand children instead, if children is NULL, take 0
        int robCurrentHouse = node.val +
            (node.left == null? 0: robRecursive(node.left.left, memo) + robRecursive(node.left.right,memo)) +
            (node.right == null? 0: robRecursive(node.right.left, memo) + robRecursive(node.right.right,memo));
         
        //If we are skipping current house, lets consider it's children
        int skipCurrentHouse = robRecursive(node.left, memo) + robRecursive(node.right, memo);
        
        //Add the newly calculated amount to the respective node on the map
        memo.put(node, Math.max(robCurrentHouse, skipCurrentHouse));
        
        return Math.max(robCurrentHouse, skipCurrentHouse);
    }
}