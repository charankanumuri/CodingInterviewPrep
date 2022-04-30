/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    long second=Long.MAX_VALUE;
    int first;
    public int findSecondMinimumValue(TreeNode root) {
       
        first=root.val;
        
        dfs(root);
        
        return (second<Long.MAX_VALUE)? (int)second: -1;
    }
    
    public void dfs(TreeNode root){
        if(root==null)
            return;
        
        if(first<root.val && root.val<second){
            second = root.val;
        }
        else if(root.val == first){
            dfs(root.left);
            dfs(root.right);
        }
    }
}