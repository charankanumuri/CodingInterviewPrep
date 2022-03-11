package Matrix;

public class RotateImageMatrix {

    // leetcode question Number : 48
    // You are given an n x n 2D matrix representing an image, rotate the image by
    // 90 degrees (clockwise).

    // You have to rotate the image in-place, which means you have to modify the
    // input 2D matrix directly.
    // DO NOT allocate another 2D matrix and do the rotation.

    //      [1, 2, 3                     [7, 4, 1
    //       4, 5, 6        =>           8, 5, 2
    //      7, 8, 9]                     9, 6, 3]

    // Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
    // Output: [[7,4,1],[8,5,2],[9,6,3]]

    // APPROACH: First find transpose and then Swap the first half of colums with
    // last half

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void rotate(int[][] matrix) {

        // 1 2 3
        // 4 5 6
        // 7 8 9

        // To find transpose matrix[i][j] = matrix[j][i]
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[0].length; j++) {
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // After Transpose
        // 1 4 7
        // 2 5 8
        // 3 6 9

        // To flip horizontally
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length / 2; j++) {
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = temp;
            }
        }

        // After Horizontal Flip
        // 7 4 1
        // 8 5 2
        // 9 6 3

    }
}
