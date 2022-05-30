/**
 * All Nodes Distance K in Binary Tree
Medium

Given the root of a binary tree, the value of a target node target, and an integer k, 
return an array of the values of all nodes that have a distance k from the target node.

You can return the answer in any order.

 

Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
Output: [7,4,1]
Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
Example 2:

Input: root = [1], target = 1, k = 3
Output: []
 

Constraints:

The number of nodes in the tree is in the range [1, 500].
0 <= Node.val <= 500
All the values Node.val are unique.
target is the value of one of the nodes in the tree.
0 <= k <= 1000
 */

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
    
    HashMap<TreeNode, List<TreeNode>> graph = new HashMap<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        
        List<Integer> result = new ArrayList<>();
        
        buildGraph(root, null);
        
        HashSet<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> q = new LinkedList<>();
        
        q.add(target);
        visited.add(target);
        
        while(!q.isEmpty()){
            int size = q.size();
            
            for(int i=0;i<size;i++){
                TreeNode node = q.poll();
                
                if(k==0)
                    result.add(node.val);
                
                List<TreeNode> nodes = graph.get(node);
                
                for(int j=0;j<nodes.size();j++){
                    if(!visited.contains(nodes.get(j))){
                        visited.add(nodes.get(j));
                        q.add(nodes.get(j));
                    }
                }
            }
            
            if(k==0)
                return result;
            
            k--;
        }
    
        return result;
    
    }
    
    public void buildGraph(TreeNode node, TreeNode parent){
        if(node == null)
            return;
        
        if(!graph.containsKey(node))
            graph.put(node, new ArrayList<>());
        
        if(parent!=null){
            graph.get(parent).add(node);
            graph.get(node).add(parent);
        }
        
        buildGraph(node.left, node);
        buildGraph(node.right, node);
    }
}