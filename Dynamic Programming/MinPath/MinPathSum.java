/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, 
 * which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

 

Example 1:


Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
Example 2:

Input: grid = [[1,2,3],[4,5,6]]
Output: 12
 */

class Solution {
    
    public int minPathSum(int[][] grid) {
        //memory
        int[][] memo = new int[grid.length][grid[0].length];
        
        //fill with -1
        for(int[] m:memo)
            Arrays.fill(m,-1);
        
        //starting with index (0,0)
        return findMinSumPath(grid, 0, 0, memo);
        
    }
    
    public int findMinSumPath(int[][] grid, int row, int col, int[][] memo){
        
        //if we are at last index, just return grid value, we dont have an option to explore further
        if(row==grid.length-1 && col == grid[0].length-1)
            return grid[grid.length-1][grid[0].length-1];
        
        //if we go out of bound, return MAX value, we want to find min actually
        if(row>=grid.length || col>=grid[0].length)
            return Integer.MAX_VALUE;
        
        //we already computed? return from memory
        if(memo[row][col]!=-1)
            return memo[row][col];
        
        int moveRight = findMinSumPath(grid, row, col+1, memo); //move towards right
        int moveDown = findMinSumPath(grid, row+1, col, memo); //move towards bottom
        
        //Include grid value + min(rightExplore, BottomExplore)
        //The reason we are adding grid[row][col] here is because, at one point
        //we will receive Integer.MAX value on out of bound which will mess up the data
        memo[row][col] = grid[row][col]+ Math.min(moveRight, moveDown);
        
        return memo[row][col];
    }
}