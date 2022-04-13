/**
 * Game of Life
Medium

According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population.
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.

 

Example 1:


Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
Example 2:


Input: board = [[1,1],[1,0]]
Output: [[1,1],[1,1]]
 

Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 25
board[i][j] is 0 or 1.
 

Follow up:

Could you solve it in-place? Remember that the board needs to be updated simultaneously: You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches upon the border of the array (i.e., live cells reach the border). How would you address these problems?
 */

class Solution {
    
    //8 possible directions
    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1},{-1,-1},{1,1},{-1,1},{1,-1}};
    
    public void gameOfLife(int[][] board) {
        int m=board.length;
        int n=board[0].length;
        
        //going to check for every cell, how many neighbours are alive
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                
                //count of alive neighbours for every cell
                int neighboursAlive = findNeighboursAlive(board, i, j, m, n);
                
                
                //check if current board is alive
                if(board[i][j]==1){
                    
                    //if yes, check the condition where it can fail, set it to -1
                    if(neighboursAlive>3 || neighboursAlive<2){
                        board[i][j]=-board[i][j];
                    }
                }
                
                //if the cell is a dead cell, it can become alive when we have exact 3 neighbours alive
                else{
                    
                    //set it to 2 -> means 2 was dead cell but will become alive in next state
                    if(neighboursAlive==3){
                        board[i][j]=2;
                    }
                    //if not set it to -2, so we know it was dead and still it is dead
                    else
                        board[i][j]=-2;
                }
            }
        }
        
        
        //after that go over the board and change all postivies to 1 and negatives to 0
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                board[i][j] = board[i][j]<1? 0:1;
        
    }
    
    public int findNeighboursAlive(int[][] board, int row, int col, int m, int n){
        int count=0;
        
        for(int[] d: dirs){
            int newX = row+d[0];
            int newY = col+d[1];
            
            //Math.abs() is to know whether the cell is a dead cell or alive cell
            //as we are checking and changing it in-place we must identify dead/alive cells
            //2 means dead turns alive, 1 means alive
            //-2 means dead reamins dead and -1 means alive turns dead in next state
            if(checkBoundary(newX, newY, m, n) && Math.abs(board[newX][newY])==1)
                count++;
        }
        
        return count;
    }
    
    public boolean checkBoundary(int x, int y, int m, int n){
        return (x>=0 && y>=0 && x<m && y<n);
    }
}