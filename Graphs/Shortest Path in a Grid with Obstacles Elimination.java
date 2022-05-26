/**
 * Shortest Path in a Grid with Obstacles Elimination
Hard

You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). 
You can move up, down, left, or right from and to an empty cell in one step.

Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1) 
given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.

 

Example 1:


Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
Output: 6
Explanation: 
The shortest path without eliminating any obstacle is 10.
The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
Example 2:


Input: grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
Output: -1
Explanation: We need to eliminate at least two obstacles to find such a walk.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 40
1 <= k <= m * n
grid[i][j] is either 0 or 1.
grid[0][0] == grid[m - 1][n - 1] == 0
 */

class Solution {
    
    int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    
    public int shortestPath(int[][] grid, int k) {
        if(grid[0][0]==1)
            return -1;
        
        int m = grid.length, n=grid[0].length;
        int path = 0;
        boolean[][][] visited = new boolean[grid.length][grid[0].length][k+1];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,k});
        
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                int[] curr = q.poll();
                
                int row = curr[0];
                int col = curr[1];
                int obs = curr[2];
                
                if(row == m-1 && col == n-1)
                    return path;
                
                for(int[] d: dirs){
                    
                    int x = row + d[0];
                    int y = col + d[1];
                    
                    if(x>=0 && y>=0 && x<grid.length && y<grid[0].length){
                        
                       if(grid[x][y]==0 && !visited[x][y][obs]){
                           q.add(new int[]{x,y,obs});
                           visited[x][y][obs] = true;
                       }
                       else if(grid[x][y]==1 && obs>0 && !visited[x][y][obs-1]){
                           q.add(new int[]{x,y,obs-1});
                           visited[x][y][obs-1] = true;
                       }
                    }
                }
            }
            path++;
        }
        
        
        return -1;
    }
}