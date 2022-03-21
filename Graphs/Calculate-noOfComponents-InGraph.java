/**
 * You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi]
 *  indicates that there is an edge between ai and bi in the graph.

Return the number of connected components in the graph.

 

Example 1:


Input: n = 5, edges = [[0,1],[1,2],[3,4]]
Output: 2
Example 2:


Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
Output: 1
 

Constraints:

1 <= n <= 2000
1 <= edges.length <= 5000
edges[i].length == 2
0 <= ai <= bi < n
ai != bi
There are no repeated edges.
 */

class Solution {
    public int countComponents(int n, int[][] edges) {

        //graph -> node -> to list of connected vertices
        List<List<Integer>> graph = new ArrayList<>();
        
        //initialize graph
        for(int i=0;i<n;i++)
            graph.add(i, new ArrayList<Integer>());
        
        //map all respective edges to the graph list
        for(int i=0;i<edges.length;i++){
            int v1 = edges[i][0];
            int v2 = edges[i][1];
            
            //bi-directional, so mark both sides
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }
        
        //maintain visited to no re-visit again
        boolean[] visited = new boolean[n];
        
        int count=0;
        for(int i=0;i<n;i++){
            if(visited[i]!=true){
                count++;    //increment only when not visited
                dfs(graph, visited, i);
            }
        }
    return count;
    }
    
    public void dfs(List<List<Integer>> graph, boolean[] visited, int node){
        if(visited[node]==true)
            return;
        
        // make sure to mark the node as visited whenever we visit a new node
        visited[node]=true;
        
        for(Integer n: graph.get(node)){
            dfs(graph, visited, n);
        }
    }
}