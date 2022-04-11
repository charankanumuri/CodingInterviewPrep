/*

Shift 2D Grid
Easy

Given a 2D grid of size m x n and an integer k. You need to shift the grid k times.

In one shift operation:

Element at grid[i][j] moves to grid[i][j + 1].
Element at grid[i][n - 1] moves to grid[i + 1][0].
Element at grid[m - 1][n - 1] moves to grid[0][0].
Return the 2D grid after applying shift operation k times.

 

Example 1:


Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
Output: [[9,1,2],[3,4,5],[6,7,8]]
Example 2:


Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
Example 3:

Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
Output: [[1,2,3],[4,5,6],[7,8,9]]
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m <= 50
1 <= n <= 50
-1000 <= grid[i][j] <= 1000
0 <= k <= 100

-----------------------------------------------------------------------------------------------------------------------------

Using mod approach - to quickly place the element at the right cell
Instead of iterating k times to find the outcome.

Here the trick is, we need to find newCol and newRow value of the grid to place the element

Lets create List<List<Integer>> -> 1st list is the row number and 2nd nested list is the col values

when we iterate, we calculate newCol by (col index + k) % colSize -> which is similar to rotating an array
To calculate the newRow, we must know whether my newCol is staying in the same row or not,
we can find that using (col index + k) / colSize, because this gives us the quotient which tells how much should i move from current row.

Imagine, row as floor and col as rooms, once we complete visiting all rooms, we move the next floor.

If we don't cover all rooms in the floor, we wont move to next floor. Similarly, if we don't go beyond the colSize on the newCol computation, we will remain in the same row

*/

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<List<Integer>> result = new ArrayList<>();
        
		//initializing list with zero's
        for(int i=0;i<grid.length;i++){
            List<Integer> list = new ArrayList<>();
            for(int j=0;j<grid[0].length;j++){
                list.add(0);
            }
            
            result.add(list);
        }
        
        int rows=grid.length;
        int cols=grid[0].length;
        
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                
                //finding newCol is easy because we move to the right by k steps
                //if we go beyond col length, we take mod of cols and get appropriate col index
                int newCol = (j+k)%cols;
                
                
                int findRowMovement = (j+k)/cols;
                int newRow = (i+findRowMovement)%rows;
                
                //get the list by row number and set the value at (col index, value)
                result.get(newRow).set(newCol, grid[i][j]);
            }
        }
        return result;
    }
}