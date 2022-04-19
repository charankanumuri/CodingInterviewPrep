/**
 * Binary Tree Longest Consecutive Sequence
Medium

Given the root of a binary tree, return the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
The longest consecutive path needs to be from parent to child (cannot be the reverse).

 

Example 1:


Input: root = [1,null,3,2,4,null,null,null,5]
Output: 3
Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
Example 2:


Input: root = [2,null,3,2,null,1]
Output: 2
Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
 

Constraints:

The number of nodes in the tree is in the range [1, 3 * 104].
-3 * 104 <= Node.val <= 3 * 104
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
    int longest = 0;
    public int longestConsecutive(TreeNode root) {
        if(root == null)
            return 0;
        
        //parent and length are initially null and 0
        findLongestConsecutive(root, null, 0);
        
        return longest;
    }
    
    public void findLongestConsecutive(TreeNode node, TreeNode parent, int length){
        
        //base case
        if(node==null)
            return;
        
        //if there is a parent, then check if current node is a consecutive element from parent by 1, if yes, increase the length
        if(parent!=null && parent.val + 1 == node.val){
            length = length+1;
        }
        //else we are starting again from 1
        else
            length = 1;
        
        //compare the longest everytime
        longest = Math.max(longest, length);
        

        //explore left and right trees with its new parent
        findLongestConsecutive(node.left, node, length);
        findLongestConsecutive(node.right, node, length);
        
    }
}