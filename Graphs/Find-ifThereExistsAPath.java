/**
 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive).
 *  The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge
 *  between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.

You want to determine if there is a valid path that exists from vertex source to vertex destination.

Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.

 

Example 1:


Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
Output: true
Explanation: There are two paths from vertex 0 to vertex 2:
- 0 → 1 → 2
- 0 → 2
Example 2:


Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
Output: false
Explanation: There is no path from vertex 0 to vertex 5.
 

Constraints:

1 <= n <= 2 * 105
0 <= edges.length <= 2 * 105
edges[i].length == 2
0 <= ui, vi <= n - 1
ui != vi
0 <= source, destination <= n - 1
There are no duplicate edges.
There are no self edges.

T(N)=O(V+E)
S(N)=O(V+E)
 */

class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
      
        List<List<Integer>> graph = new ArrayList<>();
        
        //create base graph, with empty connections
        for(int i=0;i<n;i++){
            graph.add(i,new ArrayList<Integer>());
        }
        
        //go over edges and add connections to graph
        //if there is a edge, its bi-directional
        for(int i=0;i<edges.length;i++){
            int v1 = edges[i][0];
            int v2 = edges[i][1];
            
            //bi-directional here
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }
        
        boolean[] visited = new boolean[n];
        
        return dfs(graph, visited, source, destination);
    }
    
    public boolean dfs(List<List<Integer>> graph, boolean[] visited, int source, int destination){
        if(visited[source] == true)
            return false;
        
        if(source == destination)
            return true;
        
        visited[source] = true;
        for(Integer n: graph.get(source)){
            if(dfs(graph, visited, n, destination))
                return true;
        }
        
        return false;
    }
}