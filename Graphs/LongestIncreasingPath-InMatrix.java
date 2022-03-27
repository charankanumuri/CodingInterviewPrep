/**
 * 329. Longest Increasing Path in a Matrix
 * 
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.

From each cell, you can either move in four directions: left, right, up, or down.
You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).

 

Example 1:


Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:


Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
Example 3:

Input: matrix = [[1]]
Output: 1
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
0 <= matrix[i][j] <= 231 - 1

T(n)=O(m*n)
S(n)=O(mn)
 */

class Solution {
    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    
    public int longestIncreasingPath(int[][] matrix) {
        
        if(matrix.length == 0 || matrix == null)
            return 0;
        
        //maintain this so no new recomputation is needed
        int[][] maxDistance = new int[matrix.length][matrix[0].length];
        
        int longest = Integer.MIN_VALUE;
        
        for(int i=0;i<matrix.length; i++){
            for(int j=0;j<matrix[0].length;j++){
                //travel the indexes one by one
                longest = Math.max(longest, DFS(i, j, matrix, maxDistance));
            }
        }
        return longest;
    }
    
    public int DFS(int row, int col, int[][] matrix, int[][] memory){
        //if this was already computed, then return the longest of that index
        if(memory[row][col]!=0)
            return memory[row][col];
        
        //go all 4 possible directions
        for(int i=0;i<dirs.length;i++){
            int x = row+dirs[i][0];
            int y = col+dirs[i][1];
            
            //check out of range and whether the next index value is greater than current index value
            //if true, that means we can compute longest value for current index
            //store them using Math.max, with current index memory and next DFS call with new indexes
            if(x>=0 && y>=0 && x<matrix.length && y<matrix[0].length && matrix[x][y]>matrix[row][col])
                memory[row][col] = Math.max(memory[row][col], DFS(x, y, matrix, memory));
        }
        
        //As array when defined has value 0
        //we are just looking for max distance from one index to another
        //so increment the current memory cell
        return ++memory[row][col];
    }
    
}