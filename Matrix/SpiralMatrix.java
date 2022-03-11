package Matrix;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    
    // leetcode question Number : 54
    // Given an m x n matrix, return all elements of the matrix in spiral order.
    
        // [1, 2, 3                        
        //  4, 5, 6            =>          [1, 2, 3, 6, 9, 8, 7, 4, 5] 
        //  7, 8, 9]                        
        //              -> -> -> 
        //              -> ->  |
        //              |      |
        //              <- <- <-  

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(spiralMatrix(matrix));
    }


    public static List<Integer> spiralMatrix(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if(matrix.length == 0 || matrix[0].length == 0) 
            return res;
        
        int top = 0;
        int bottom = matrix.length-1;
        int left = 0;
        int right = matrix[0].length-1;
        
        while(true) {

            // Traverse from left to right keeping the top as constant
            for(int i = left; i <= right; i++) res.add(matrix[top][i]);
            top++;
            if(top > bottom) break;
            
            // Traverse from top to bottom keeping the right as constant
            for(int i = top; i <= bottom; i++) res.add(matrix[i][right]);
            right--;
            if(left > right) break;
            
            // Traverse from right to left keeping the bottom as constant
            for(int i = right; i >= left; i--) res.add(matrix[bottom][i]);
            bottom--;
            if(top > bottom) break;

            // Traverse from bottom to top keeping the left as constant
            for(int i = bottom; i >= top; i--) res.add(matrix[i][left]);
            left++;
            if(left > right) break;
        }
        return res;
    }

    
}
