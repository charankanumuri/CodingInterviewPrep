package Matrix;

public class WordSearch {

    // leetcode question Number : 79
    // Given an m x n grid of characters board and a string word, return true if
    // word exists in the grid.

    // The word can be constructed from letters of sequentially adjacent cells,
    // where adjacent cells are horizontally or vertically neighboring.
    // The same letter cell may not be used more than once.

    // Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word
    // = "ABCCED"
    // Output: true

    // [A, B, C, E
    // S, F, C, S
    // A, D, E, E]

    public static void main(String[] args) {
        char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
        System.out.println(exist(board, "ABCCED"));
    }

    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // search the matrix from the first character
                // I guess this can be optimized to search from the position of the first char of the string
                if (dfs_search(board, word, i, j, 0))
                    return true;
            }
        }
        return false;
    }

    public static boolean dfs_search(char[][] letters, String word, int i, int j, int k) {
        int rowsLen = letters.length;
        int columnsLen = letters[0].length;

        if (i < 0 || i >= rowsLen || j < 0 || j >= columnsLen || letters[i][j] == '#') {
            return false;
        }

        if (letters[i][j] == word.charAt(k)) {
            char str = letters[i][j];
            // mark as visited
            letters[i][j] = '#';
            if (k == word.length() - 1) {
                return true;
            } else if (dfs_search(letters, word, i - 1, j, k + 1) ||
                    dfs_search(letters, word, i + 1, j, k + 1) ||
                    dfs_search(letters, word, i, j - 1, k + 1) ||
                    dfs_search(letters, word, i, j + 1, k + 1)) {
                return true;
            }
            // keep with the original char for next time search
            letters[i][j] = str;
        }
        return false;
    }

}
