/**
 * Path with Maximum Gold
Medium

In a gold mine grid of size m x n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.

Return the maximum amount of gold you can collect under the conditions:

Every time you are located in a cell you will collect all the gold in that cell.
From your position, you can walk one step to the left, right, up, or down.
You can't visit the same cell more than once.
Never visit a cell with 0 gold.
You can start and stop collecting gold from any position in the grid that has some gold.
 

Example 1:

Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
Output: 24
Explanation:
[[0,6,0],
 [5,8,7],
 [0,9,0]]
Path to get the maximum gold, 9 -> 8 -> 7.
Example 2:

Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
Output: 28
Explanation:
[[1,0,7],
 [2,0,6],
 [3,4,5],
 [0,3,0],
 [9,0,20]]
Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 15
0 <= grid[i][j] <= 100
There are at most 25 cells containing gold.
 */

class Solution {
    public int getMaximumGold(int[][] grid) {
        
        if(grid == null || grid.length == 0)
            return 0;
        
        int max = 0, m = grid.length, n=grid[0].length;
        
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                if(grid[i][j]>0){
                    int gold = findMaxGold(grid, i, j, m, n, new boolean[m][n]);
                    max = Math.max(max, gold);
                }
        
        return max;
    }
    
    public int findMaxGold(int[][] grid, int row, int col, int m, int n, boolean[][] visited){
        
        if(row<0 || col<0 || row>=m || col>=n || grid[row][col]==0 || visited[row][col])
            return 0;
        
        visited[row][col] = true;
        int gold = grid[row][col];
        int left = findMaxGold(grid, row, col-1, m, n, visited);
        int right = findMaxGold(grid, row, col+1, m, n, visited);
        int top = findMaxGold(grid, row-1, col, m, n, visited);
        int bottom = findMaxGold(grid, row+1, col, m, n, visited);
        
        visited[row][col] = false;
        
        return gold + Math.max(left, Math.max(right, Math.max(top, bottom)));
    }
}