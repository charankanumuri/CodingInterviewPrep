/*
Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.

 

Example 1:


Input: grid = [[0,1],[1,0]]
Output: 2
Example 2:


Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4
Example 3:

Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1
 

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1
*/

class Solution {
    
    int[][] dirs={{1,0},{-1,0},{0,1},{0,-1},{-1,-1},{-1,1},{1,-1},{1,1}};
    
    public int shortestPathBinaryMatrix(int[][] grid) {
        
        int n=grid.length;
        
        //if starting cell or ending cell is not free, then we will never reach
        if(grid[0][0]!=0 || grid[n-1][n-1]!=0)
            return -1;
        
        //if there is only one cell, we will check and return 1 if the cell is reachable
        if(grid.length==1 && grid[0][0] == 0)
            return 1;
        
        //tracking visited cells
        boolean[][] visited = new boolean[n][n];
        
        //queue for BFS traversal
        LinkedList<Point> queue = new LinkedList<>();
        
        //Add (0,0) starting point to queue and mark it as visited
        //we are tracking the distance of it on the same cell, starting with 1
        queue.add(new Point(0,0));
        grid[0][0]=1;
        visited[0][0]=true;
        
        while(!queue.isEmpty()){
            Point p = queue.remove();
            
            //keep distance separate and use them on all possible direction + 1
            //to keep updating the distance of each cell
            int distance = grid[p.x][p.y];
            
            for(int[] d: dirs){
                int x = p.x+d[0];
                int y = p.y+d[1];
                
                //if my new (x,y) is the last cell and if it is 0, return distance+1
                if(x==n-1 && y==n-1 && grid[x][y]==0)
                    return distance+1;
                
                //make sure, new (x,y) doesn't go beyond the grid && it is not visited
                if(x>=0 && x<n && y>=0 && y<n && grid[x][y]!=1 && visited[x][y]==false){
                    queue.add(new Point(x,y));
                    grid[x][y]=1+distance;
                    visited[x][y]=true;
                }
            }
        }
        
        //if we don't reach then return -1
        return -1;
    }
}

class Point{
    int x,y;
    Point(int a, int b){
        this.x = a;
        this.y = b;
    }
}