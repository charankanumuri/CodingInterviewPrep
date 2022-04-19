/*

Recover Binary Search Tree
Medium

You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake. 
Recover the tree without changing its structure.

 

Example 1:


Input: root = [1,3,null,null,2]
Output: [3,1,null,null,2]
Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
Example 2:


Input: root = [3,1,4,null,null,2]
Output: [2,1,4,null,null,3]
Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.
 

Constraints:

The number of nodes in the tree is in the range [2, 1000].
-231 <= Node.val <= 231 - 1
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
    
    //we maintain 3 variables to have the prev value, first & second node to swap and recover the BST
    TreeNode prev=null,first=null, second=null;
    public void recoverTree(TreeNode root) {
        
        if(root == null)
            return;
        
        // we know in order traversal will give non decreasing order of the BST
        inorder(root);
        int temp = first.val;
        first.val=second.val;
        second.val=temp;
    }
    
    public void inorder(TreeNode node){
        
        if(node == null)
            return;
        
        inorder(node.left);
        
        //if the prev value is greater than the current node value that means ordering is wrong
        //but 1st we need to have previous value atleast
        if(prev!=null && prev.val>node.val){

            //if we are finding the 1st value that fails the non-decreasing order, assign it to first value
            if(first == null)
                first = prev;
            
            //update the second value to current node
            second=node;
        }
        
        //always keep track of previous node
        prev = node;
        
        
        inorder(node.right);
    }
}