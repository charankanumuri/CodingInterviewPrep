/**
 * Step-By-Step Directions From a Binary Tree Node to Another
Medium

You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. 
You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing 
the value of the destination node t.

Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only 
the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:

'L' means to go from a node to its left child node.
'R' means to go from a node to its right child node.
'U' means to go from a node to its parent node.
Return the step-by-step directions of the shortest path from node s to node t.

 

Example 1:


Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
Output: "UURL"
Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.
Example 2:


Input: root = [2,1], startValue = 2, destValue = 1
Output: "L"
Explanation: The shortest path is: 2 → 1.
 

Constraints:

The number of nodes in the tree is n.
2 <= n <= 105
1 <= Node.val <= n
All the values in the tree are unique.
1 <= startValue, destValue <= n
startValue != destValue
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
    public String getDirections(TreeNode root, int startValue, int destValue) {
        if(root == null)
            return "";
        
        StringBuilder direction = new StringBuilder();
        
        //find common parent for both nodes
        TreeNode lca= findLCA(root, startValue, destValue);
        
        //we will have two paths
        //root-> start node
        //root-> end node
        List<String> paths = new ArrayList<>();
        
        //find both paths individually
        find(lca, startValue, new StringBuilder(), paths);
        find(lca, destValue, new StringBuilder(), paths);
        
        //now lets create directions, the 1st path was root->start node, so all direction from 
        //start to root will be Up, lets add it to directions
        for(int i=0;i<paths.get(0).length();i++)
            direction.append("U");
        
        //this one will remain the same bcz after reaching start -> root, we will go
        //root -> dest node, so those directions will be the same
        direction.append(paths.get(1));
        
        return direction.toString();
    }
    
    public TreeNode findLCA(TreeNode root, int start, int end){
        if(root==null)
            return null;
        
        //if anyone of it is equal then that will be my LCA
        if(root.val == start || end == root.val)
            return root;
        
        TreeNode left = findLCA(root.left, start, end);
        TreeNode right = findLCA(root.right, start, end);
        
        //after we find the lca for left & right subtrees, if both aren't equal, then the ans is root
        if(left!=null && right!=null)
            return root;
        
        //if anyone of is null, then lets return the one which is not null
        return left!=null? left: right;
    }
    
    public void find(TreeNode root, int value, StringBuilder path, List<String> paths){
        
        //if we found, lets add it to paths and return
        if(root.val == value){
            paths.add(new String(path));
            return;
        }
        
        //we call only when left is not null, so we don't append 'L' unnecessarily
        if(root.left!=null)
            find(root.left, value, path.append("L"), paths);
        
        if(root.right!=null)
            find(root.right, value, path.append("R"), paths);
        
        //if we reach here that means we didn't find the path yet, lets remove the last direction added.
        path.deleteCharAt(path.length()-1);
        return;
    }
}