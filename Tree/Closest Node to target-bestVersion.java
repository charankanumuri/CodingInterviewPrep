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
    public int closestValue(TreeNode root, double target) {
        int ans=0;
        double closest = Double.MAX_VALUE;
        
        while(root!=null){
            
            double diff = Math.abs(target-root.val);
            if(diff<closest){
                ans = root.val;
                closest = diff;
            }
            
            //cutting one half of the tree based on target value and root value
            root = (target<root.val)? root.left: root.right;
        }
       
        return ans;
    }
    
   
}