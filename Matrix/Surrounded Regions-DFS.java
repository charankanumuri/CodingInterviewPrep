/**
 * Surrounded Regions
Medium

Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

 

Example 1:


Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
Explanation: Surrounded regions should not be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
Example 2:

Input: board = [["X"]]
Output: [["X"]]
 */

class Solution {
    
    int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    
    public void solve(char[][] board) {
        int m = board.length, n=board[0].length;
        
        for(int i=0;i<m;i++){
            markBorders(board, m, n, i, 0); //left corner
            markBorders(board, m, n, i, n-1);   //right corner
        }
        
        for(int i=0;i<n;i++){
            markBorders(board, m, n, 0, i); //top corner
            markBorders(board, m, n, m-1, i);   //bottom corner
        }
        
        flipRegions(board, m ,n);
    }
    
    public void markBorders(char[][] board, int m, int n, int i, int j){
        
        if(i<0 || j<0 || i>=m || j>=n || board[i][j]!='O')
            return;
        
        //mark this O as some char as we cannot flip it as its at the border
        board[i][j]='#';
        
        //see all 4-possible directions from here to mark the border
        for(int[] d: dirs){
            int x = i + d[0];
            int y = j + d[1];
            
            markBorders(board, m, n, x, y);
        }
    }
    
    //now flip all values to X if we still see O
    //or flip # value to O's again as they were part of border O or its a border O
    public void flipRegions(char[][] board, int m ,int n){
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=='#'){
                    board[i][j] = 'O';
                }
                else if(board[i][j]=='O'){
                    board[i][j]='X';
                }
            }
        }
    }
}