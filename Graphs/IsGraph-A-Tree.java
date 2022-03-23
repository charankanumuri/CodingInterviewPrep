/**
 * You have a graph of n nodes labeled from 0 to n - 1. 
 * You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates 
 * that there is an undirected edge between nodes ai and bi in the graph.

Return true if the edges of the given graph make up a valid tree, and false otherwise.

 

Example 1:


Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
Output: true
Example 2:


Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
Output: false
 

Constraints:

1 <= n <= 2000
0 <= edges.length <= 5000
edges[i].length == 2
0 <= ai, bi < n
ai != bi
There are no self-loops or repeated edges.
 */

class Solution {
    public boolean validTree(int n, int[][] edges) {
        
        //if there are more/less than n-1 edges, then its not a proper tree
        if(edges.length != n-1)
            return false;
        
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<n;i++)
            graph.add(i, new ArrayList<Integer>());
        
        //preparing adjacent neighbors of graph
        for(int[] edge: edges){
            int v = edge[0];
            int u = edge[1];
            
            graph.get(v).add(u);
            graph.get(u).add(v);
        }
        
        //maintain seen to make sure we dont traverse the same node again
        List<Integer> seen = new ArrayList<>();
        Stack<Integer> stk = new Stack<>();
        
        //push initial node 0 and mark as seen
        stk.push(0);
        seen.add(0);
        
        while(!stk.isEmpty()){
            Integer node = stk.pop();
            
            //explore its neighbor and add if its not seen
            for(Integer neighbor: graph.get(node)){
                if(!seen.contains(neighbor)){
                    seen.add(neighbor);
                    stk.push(neighbor);
                }
            }
        }
        
        //if we have seen all node, then we have a proper tree with no cycles or missing components
        //if there is a cycle or node missing connection, out seen.size() will be less than n
        return seen.size() == n;
    }
}