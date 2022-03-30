/**
 * ou are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

 

Example 1:


Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 10
grid[i][j] is 0, 1, or 2.
 */

//To maintain the matrix indexes
class Point{
    
    int x,y;
    
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}


class Solution {
    //directions    
    int[][] dirs = {{1,0},{-1,0},{0,-1},{0,1}};
    
    public int orangesRotting(int[][] grid) {
        Queue<Point> queue = new LinkedList<>();
        int freshOranges=0, rottenOranges=0, minutes=0;
        
        //count fresh and bad oranges
        //add bad orange index to queue
        for(int i=0;i<grid.length;i++)
            for(int j=0;j<grid[0].length;j++)
                if(grid[i][j]==1){
                    freshOranges++;
                }
                else if(grid[i][j]==2){
                    rottenOranges++;
                    queue.add(new Point(i,j));
                }
        
        //if there are no fresh orange, return 0
        if(freshOranges == 0)
            return 0;
        
        //lets start our BFS technique
        while(!queue.isEmpty() && freshOranges>0){
            
            //get the queue size, here all rotten orange will spoil the fresh one parallely
            int size = queue.size();
            
            //increment minute before exploring
            minutes++;
            
            //this loop is for, at a second, all rotten orange will spoil the good ones.
            for(int i=0;i<size;i++){
                //take the point and calculate the directions it can spoil
                Point rotten = queue.poll();
                for(int[] d: dirs){
                    int x = rotten.x+d[0];
                    int y = rotten.y+d[1];
                    
                    //if there is a fresh orange on the new x,y -> mark as rotten and add to queue
                    //decrement the no. of fresh oranges as well
                    if(x>=0 && y>=0 && x<grid.length && y<grid[0].length && grid[x][y]==1){
                        grid[x][y]=2;
                        queue.add(new Point(x,y));
                        freshOranges--;
                    }
                }
            }
        }
        
        if(freshOranges==0)
            return minutes;
        
        return -1;
    }
}