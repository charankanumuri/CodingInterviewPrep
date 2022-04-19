/**
 * Delete Node in a BST
Medium

Given a root node reference of a BST and a key, delete the node with the given key in the BST. 
Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
 

Example 1:


Input: root = [5,3,6,2,4,null,7], key = 3
Output: [5,4,6,2,null,null,7]
Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.

Example 2:

Input: root = [5,3,6,2,4,null,7], key = 0
Output: [5,3,6,2,4,null,7]
Explanation: The tree does not contain a node with value = 0.
Example 3:

Input: root = [], key = 0
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 104].
-105 <= Node.val <= 105
Each node has a unique value.
root is a valid binary search tree.
-105 <= key <= 105
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
    TreeNode answer;
    public TreeNode deleteNode(TreeNode root, int key) {
        
        if(root==null)
            return null;
        
        
         if(key<root.val){

            //search on the left
            root.left = deleteNode(root.left, key);
             return root;
         }
        
        else if(key>root.val){

            //search on the right
            root.right = deleteNode(root.right, key);
            return root;
        }
        
        //found the node to delete
        else{
            
            //we are checking if any one of the children is null, if so, just return the other subtree
            if(root.right==null)
                return root.left;
            
            else if(root.left==null)
                return root.right;
            
            //if both children are present, then we need to replace the
            //node to delete with some value
            
            //when we delete a node here, its the root value at some recursion step
            //so when we delete root value, we should select a value that will be greater than
            //left subtree values and less than right subtree values
            
            //so we pick the smallest from right subtree
            else{
                
                //set to the right tree
                TreeNode minimum = root.right;
                
                
                //keep traversing to the left until we reach the end
                //bcz the left most node on right subtree will have the smallest element
                while(minimum.left!=null){
                    minimum = minimum.left;
                }
                
                //swap with root value, now we delete the root value
                root.val = minimum.val;
                
                //we need to remove the element we replaced
                //so my new right subtree shouldn't have the minimum value anymore bcz that is my root value after swapping
                root.right = deleteNode(root.right, minimum.val);
                
                return root;
            }
        }
    }
}