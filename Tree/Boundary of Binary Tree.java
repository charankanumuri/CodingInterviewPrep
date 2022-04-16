/**
 * Boundary of Binary Tree
Medium

The boundary of a binary tree is the concatenation of the root, the left boundary, the leaves ordered from left-to-right, 
and the reverse order of the right boundary.

The left boundary is the set of nodes defined by the following:

The root node's left child is in the left boundary. If the root does not have a left child, then the left boundary is empty.
If a node in the left boundary and has a left child, then the left child is in the left boundary.
If a node is in the left boundary, has no left child, but has a right child, then the right child is in the left boundary.
The leftmost leaf is not in the left boundary.
The right boundary is similar to the left boundary, except it is the right side of the root's right subtree. 
Again, the leaf is not part of the right boundary, and the right boundary is empty if the root does not have a right child.

The leaves are nodes that do not have any children. For this problem, the root is not a leaf.

Given the root of a binary tree, return the values of its boundary.
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
    
    List<Integer> answer = new ArrayList<>();
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        
        if(root == null)
            return answer;
        
        //root node
        answer.add(root.val);
        
        leftBoundary(root.left);
        leaves(root.left);
        leaves(root.right);
        rightBoundary(root.right);
        
        return answer;
    }
    
    public void leftBoundary(TreeNode root){
        if(root ==null || (root.left==null && root.right==null))
            return;
        
        answer.add(root.val);
        if(root.left==null)
            leftBoundary(root.right);
        else
            leftBoundary(root.left);
    }
    
    public void rightBoundary(TreeNode root){
        if(root ==null || (root.left==null && root.right==null))
            return;
        
        if(root.right==null)
            rightBoundary(root.left);
        else
            rightBoundary(root.right);
        
        answer.add(root.val);
    }
    
    public void leaves(TreeNode root){
        
        if(root==null)
            return;
        
        if(root.left==null && root.right==null){
            answer.add(root.val);
            return;
        }
        
        leaves(root.left);
        leaves(root.right);
    }
}