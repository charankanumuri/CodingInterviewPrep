/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring.
 The same letter cell may not be used more than once.

 

Example 1:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
Example 2:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true
Example 3:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false
 

Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.
 

Follow up: Could you use search pruning to make your solution faster with a larger board?

T(N) = O(m*n * 4^S)
m and n are matrix size
4^S -> we have 4 recursive calls inside the dfs function of String length S

 * 
 */

class Solution {
    public boolean exist(char[][] board, String word) {
        
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                //Once i find the 1st letter, lets invoke dfs
                if(word.charAt(0) == board[i][j] && dfs(board, word, 0, i, j))
                    return true;
            }
        }
        
        return false;
    }
    
    public boolean dfs(char[][] board, String word, int index, int row, int col){
        
        //if the index is equal to word length, that means we found the word
        if(index==word.length())
            return true;
        
        //base case to avoid out of bound index, also we compare the board character and word character at index
        if(row>=board.length || row<0 || col<0 || col>=board[0].length || word.charAt(index)!=board[row][col])
            return false;
        
        //store this in temp variable as we need it if we dont find the character at that index
        char temp = board[row][col];
        board[row][col]='#';
        
        //go and search at all 4 direction and store the result as boolean
        boolean found = dfs(board, word, index+1, row+1, col) || dfs(board, word, index+1, row-1, col) || dfs(board, word, index+1, row, col+1) ||dfs(board, word, index+1, row, col-1);
        
        // replace the board with original character
        board[row][col]=temp;
        
        return found;
    }
}