/**
 * Given the root of a binary search tree and a target value, return the value in the BST that is closest to the target.

 

Example 1:


Input: root = [4,2,5,1,3], target = 3.714286
Output: 4
Example 2:

Input: root = [1], target = 4.428571
Output: 1
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
0 <= Node.val <= 109
-109 <= target <= 109
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
    public int closestValue(TreeNode root, double target) {
        int ans=0;
        double closest = Double.MAX_VALUE;
        List<Integer> list = new ArrayList<>();
        
        inorder(root, list);
        
        for(int i=0;i<list.size();i++){
            
            double diff = Math.abs(target-list.get(i));
            if(diff<closest){
                ans = list.get(i);
                closest = diff;
            }
        }
       
        
        return ans;
    }
    
    public static void inorder(TreeNode root, List<Integer> list){
        if(root==null)
            return;
        
        inorder(root.left,list);
        list.add(root.val);
        inorder(root.right, list);
    }
}