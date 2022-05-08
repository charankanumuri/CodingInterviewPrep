/**
 * Find Duplicate Subtrees
Medium

Given the root of a binary tree, return all duplicate subtrees.

For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with the same node values.

 

Example 1:


Input: root = [1,2,3,4,null,2,4,null,null,4]
Output: [[2,4],[4]]
Example 2:


Input: root = [2,1,1]
Output: [[1]]
Example 3:


Input: root = [2,2,2,3,null,3,null]
Output: [[2,3],[3]]
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
    
    List<TreeNode> result;
    Map<String, Integer> map;
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        
        map = new HashMap<>();
        result = new ArrayList<>();
        
        findDuplicate(root);
        
        return result;
    }
    
    public String findDuplicate(TreeNode root){
        
        if(root == null)
            return "null";
        
        String left = findDuplicate(root.left);
        String right = findDuplicate(root.right);
        
        String SubTree = root.val + " " + left + " " + right;
        
        map.put(SubTree, map.getOrDefault(SubTree, 0) + 1);
        
        if(map.get(SubTree) == 2)
            result.add(root);
        
        return SubTree;
    }
}