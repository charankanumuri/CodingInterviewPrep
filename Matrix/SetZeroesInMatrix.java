package Matrix;

public class SetZeroesInMatrix {
    
    // leetcode question Number : 73
    // Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
    // You must do it in place.
    
        // [1, 1, 1                        [1, 0, 1
        //  1, 0, 1            =>           0, 0, 0
        //  1, 1, 1]                        1, 0, 1]

    // Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
    // Output: [[1,0,1],[0,0,0],[1,0,1]]

    // Youtube video for better understanding: https://www.youtube.com/watch?v=M65xBewcqcI
    public static void main(String[] args) {
        int[][] matrix = {{1,1,1},{1,0,1},{1,1,1}};
        setZeroes(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
    
    public static void setZeroes(int[][] matrix) {
        // zeroRow used for determining first row to be set with Zeroes or not
        boolean zeroRow = false;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix.length; j++) {
                if(matrix[i][j] == 0) {
                    // If zero is found in the cell then modify the first cell of that column to Zero
                    matrix[i][0] = 0;
                    // check the row before making it to zero
                    if(i > 0)
                    // If any row other than first row, then directly set the value to zero
                        matrix[0][j] = 0;
                    else
                    // In case if there is any zero found in first row do not modify direclty but use ZeroRow
                        zeroRow = true;
                }
            }
        }

        for(int i = 1; i < matrix.length; i++) {
            for(int j = 1; j < matrix[0].length; j++) {
                // All cells other than first row & first column can be made Zeroes if first col or row has 0
                if(matrix[i][0] == 0 || matrix[0][j] == 0)  
                    matrix[i][j] = 0;
            }   
        }


        // Set the first cell of all the rows to Zero when matrix[0][0] == 0
        if(matrix[0][0] == 0) {
            for(int i = 0; i < matrix.length; i++)
                matrix[i][0] = 0;
        }

        // set all the cells of first row to zero.
        if(zeroRow) {
            for(int j = 0; j < matrix[0].length; j++)
                matrix[0][j] = 0;
        }
    }


}
