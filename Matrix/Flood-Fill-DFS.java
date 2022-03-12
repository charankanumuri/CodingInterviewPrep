/**
 * 
 * An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.

You are also given three integers sr, sc, and newColor. You should perform a flood fill on the image starting from the pixel image[sr][sc].

To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color), and so on. Replace the color of all of the aforementioned pixels with newColor.

Return the modified image after performing the flood fill.


    1 1 1             2 2 2
    1 1 0    ==>      2 2 0
    1 0 1             2 0 1                       
 

Example 1:


Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, newColor = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.
Example 2:

Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, newColor = 2
Output: [[2,2,2],[2,2,2]]
 

Constraints:

m == image.length
n == image[i].length
1 <= m, n <= 50
0 <= image[i][j], newColor < 216
0 <= sr < m
0 <= sc < n


T(N)=O(N)
S(N)=O(N) -> stack size as we call dfs function recursively
 */

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image.length == 0 || image == null)
            return image;
        
        int oldColor = image[sr][sc];
        
        //Solve only when colors are different
        if(image[sr][sc]!=newColor)
            dfs(image, oldColor, newColor, sr, sc);
        
        return image;
    }
    
    public void dfs(int[][] image, int oldColor, int newColor, int row, int col){
        
        //base case to avoid array out of bounds exception, also technically we cannot go out of grid to fill
        if(row>=image.length || col>=image[0].length || row<0 || col<0)
            return;
        
        if(image[row][col]==oldColor){
            image[row][col] = newColor;
            dfs(image, oldColor, newColor, row+1, col);
            dfs(image, oldColor, newColor, row-1, col);
            dfs(image, oldColor, newColor, row, col+1);
            dfs(image, oldColor, newColor, row, col-1);
        }
    }
}