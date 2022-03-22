/**
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). 
 * The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. 
 * When the ball stops, it could choose the next direction.

Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and 
destination = [destinationrow, destinationcol], return the shortest distance for the ball to stop at the destination. 
If the ball cannot stop at destination, return -1.

The distance is the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included).

You may assume that the borders of the maze are all walls (see examples).

 

Example 1:


Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
Output: 12
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
The length of the path is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
Example 2:


Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
Output: -1
Explanation: There is no way for the ball to stop at the destination. Notice that you can pass through the destination but you cannot stop there.
Example 3:

Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
Output: -1
 

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
    
    int[][] dirs = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
    
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        
        //calculating distance when ball touches the wall(i.e only when it stops)
        int[][] distance = new int[maze.length][maze[0].length];
        
        //initialize all with max values
        for(int[] dis: distance)
            Arrays.fill(dis,Integer.MAX_VALUE);
        
        //the start is always 0 and then call dfs
        distance[start[0]][start[1]]=0;
        dfs(maze, distance, start, destination);
        
        //check whether destination index is MAX_VALUE, bcz we dont know 
        //whether the ball stopped at destination index or not
        return distance[destination[0]][destination[1]]==Integer.MAX_VALUE? -1: distance[destination[0]][destination[1]];
    }
    
    public void dfs(int[][] maze, int[][] distance, int[] start, int[] destination){
        
        //loop every possible directions
        for(int[] d: dirs){
            int x=start[0]+d[0];
            int y=start[1]+d[1];
            
            //count from 0
            int count=0;
            while(x>=0 && x<maze.length && y>=0 && y<maze[0].length && maze[x][y]==0){
                count++;
                x=x+d[0];
                y=y+d[1];
            }
            
            //compare the distance when ball stops, i.e hits the wall
            //and update if necessary,
            if(distance[start[0]][start[1]]+count<distance[x-d[0]][y-d[1]]){
                distance[x-d[0]][y-d[1]]=distance[start[0]][start[1]]+count;
                
                //call dfs from where the ball stopped
                //we decrement x and y by d[0] and d[1] bcz
                //the while function would have failed bcz of wall(maze[x][y]==1) or out of bound exceptions
                dfs(maze, distance, new int[]{x-d[0],y-d[1]}, destination);
            }
        }
    }
}