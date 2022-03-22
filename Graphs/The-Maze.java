/**
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1).
 *  The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall.
 *  When the ball stops, it could choose the next direction.

Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol]
 and destination = [destinationrow, destinationcol], return true if the ball can stop at the destination, otherwise return false.

You may assume that the borders of the maze are all walls (see examples).

 

Example 1:


Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
Example 2:


Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
Output: false
Explanation: There is no way for the ball to stop at the destination. Notice that you can pass through the destination but you cannot stop there.
Example 3:

Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
Output: false
 

Constraints:

m == maze.length
n == maze[i].length
1 <= m, n <= 100
maze[i][j] is 0 or 1.
start.length == 2
destination.length == 2
0 <= startrow, destinationrow <= m
0 <= startcol, destinationcol <= n
Both the ball and the destination exist in an empty space, and they will not be in the same position initially.
The maze contains at least 2 empty spaces.
 */

class Solution {
    
    //4 possible directions the ball can move
    int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        
        //maintaining visited 
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        
        return dfs(maze, visited, start, destination);
    }
    
    public boolean dfs(int[][] maze, boolean[][] visited, int[] start, int[] destination){
        
        //if already visited, return false
        if(visited[start[0]][start[1]])
            return false;
        
        //if the source and destination are same, return true
        if(start[0]==destination[0] && start[1]==destination[1])
            return true;
        
        //mark the current point as true, because are gonna explore other options now
        visited[start[0]][start[1]]=true;
        
        boolean reached = false;
        for(int[] d: dirs){
            int x=start[0]+d[0];
            int y=start[1]+d[1];
            
            //loop this until we find a wall(1) or out of bound index check
            while(x>=0 && x<maze.length && y>=0 && y<maze[0].length && maze[x][y]==0)
            {
                x=x+d[0];
                y=y+d[1];
            }
            
            //call dfs of the newly computed start
            //here we do minus d[0] and d[1] because, thats where our condition
            //would have failed on the while loop
            reached = reached || dfs(maze, visited, new int[]{x-d[0], y-d[1]}, destination);
        }
        
        return reached;
    }
}