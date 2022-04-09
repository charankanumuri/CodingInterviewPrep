/**
 * Shortest Path to Get Food
Medium

You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.

You are given an m x n character matrix, grid, of these different types of cells:

'*' is your location. There is exactly one '*' cell.
'#' is a food cell. There may be multiple food cells.
'O' is free space, and you can travel through these cells.
'X' is an obstacle, and you cannot travel through these cells.
You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.

Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.

 

Example 1:


Input: grid = [["X","X","X","X","X","X"],["X","*","O","O","O","X"],["X","O","O","#","O","X"],["X","X","X","X","X","X"]]
Output: 3
Explanation: It takes 3 steps to reach the food.
Example 2:


Input: grid = [["X","X","X","X","X"],["X","*","X","O","X"],["X","O","X","#","X"],["X","X","X","X","X"]]
Output: -1
Explanation: It is not possible to reach the food.
Example 3:


Input: grid = [["X","X","X","X","X","X","X","X"],["X","*","O","X","O","#","O","X"],["X","O","O","X","O","O","X","X"],["X","O","O","O","O","#","O","X"],["X","X","X","X","X","X","X","X"]]
Output: 6
Explanation: There can be multiple food cells. It only takes 6 steps to reach the bottom food.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
grid[row][col] is '*', 'X', 'O', or '#'.
The grid contains exactly one '*'.
 */

//BFS technique is more appropriate here, so we can quickly find the food

class Point{
    int x,y;
    
    public Point(int a, int b){
        this.x = a;
        this.y = b;
    }
}

class Solution {
    
    //4 possible direction we can travel on grid
    int[][] dirs = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    
    public int getFood(char[][] grid) {
        
        int m=grid.length;
        int n=grid[0].length;
        
        //tracking visited, so we don't compute the same cell again
        boolean[][] visited = new boolean[m][n];
        
        //final answer
        int shortestLength=0;
        
        Queue<Point> queue = new LinkedList<>();
        
        
        //checking for the grid with *, as thats my starting point
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                if(grid[i][j]=='*'){
                    visited[i][j]=true; //mark it as visited
                    queue.add(new Point(i,j)); //add to queue and break from this loop
                    break;
                }
        
        while(!queue.isEmpty()){
            
            //find queue size and increase the path length
            int size = queue.size();
            shortestLength++;
            
            //we have 4 possible directions, take every point and apply
            //we are parallely searching for all 4 possible cells here
            for(int i=0;i<size;i++){
                Point p = queue.poll();
                
                // find new x and y and make sure, we are within grid and it is not visited or an obstacle
                for(int[] d: dirs){
                int x=p.x+d[0];
                int y=p.y+d[1];
                
                // if all good, mark as true and add this point to queue 
                if(x>=0 && y>=0 && x<m && y<n && grid[x][y]!='X' && visited[x][y]!=true){
                    visited[x][y]=true;
                    
                    //check quickly if new point computed is the food cell, if yes return
                    if(grid[x][y]=='#'){
                        return shortestLength;
                    }
                    
                    queue.add(new Point(x,y));
                }
            }
            }
            
            
        }
        //if we don't find food, return -1
        return -1;
    }

}