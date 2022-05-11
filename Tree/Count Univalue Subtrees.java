/**
 * Count Univalue Subtrees
Medium

Given the root of a binary tree, return the number of uni-value subtrees.

A uni-value subtree means all nodes of the subtree have the same value.

 

Example 1:


Input: root = [5,1,5,5,5,null,5]
Output: 4
Example 2:

Input: root = []
Output: 0
Example 3:

Input: root = [5,5,5,5,5,null,5]
Output: 6
 

Constraints:

The number of the node in the tree will be in the range [0, 1000].
-1000 <= Node.val <= 1000
 */

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
    int count = 0;
    public int countUnivalSubtrees(TreeNode root) {
        if(root == null)
            return 0;
        
        findUnivalueSubTrees(root);
        
        return count;
    }
    
    public boolean findUnivalueSubTrees(TreeNode root){
        
        //if we are at the leaf we know it is a valid uniValued tree
        if(root.left == null && root.right == null){
            count++;
            return true;
        }
        
        boolean isUni = true;
        boolean left = true, right = true; 
        
        if(root.left!=null)
             left = findUnivalueSubTrees(root.left) && isUni && (root.left.val == root.val);         

        if(root.right!=null)
            right = findUnivalueSubTrees(root.right) && isUni && (root.right.val == root.val);
        
        //if both are true that means one of our subtree is univalued, add count and return true
        if(left && right){
            count++;
            return true;
        }
        
        //if anyone of it or both are false, then return false 
        return false;
    }
}