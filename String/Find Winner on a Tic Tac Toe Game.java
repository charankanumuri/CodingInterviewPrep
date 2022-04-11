/**
 * Find Winner on a Tic Tac Toe Game
Easy

Tic-tac-toe is played by two players A and B on a 3 x 3 grid. The rules of Tic-Tac-Toe are:

Players take turns placing characters into empty squares ' '.
The first player A always places 'X' characters, while the second player B always places 'O' characters.
'X' and 'O' characters are always placed into empty squares, never on filled ones.
The game ends when there are three of the same (non-empty) character filling any row, column, or diagonal.
The game also ends if all squares are non-empty.
No more moves can be played if the game is over.
Given a 2D integer array moves where moves[i] = [rowi, coli] indicates that the ith move will be played on grid[rowi][coli]. 
return the winner of the game if it exists (A or B). In case the game ends in a draw return "Draw". If there are still movements to play return "Pending".

You can assume that moves is valid (i.e., it follows the rules of Tic-Tac-Toe), the grid is initially empty, and A will play first.

 

Example 1:


Input: moves = [[0,0],[2,0],[1,1],[2,1],[2,2]]
Output: "A"
Explanation: A wins, they always play first.
Example 2:


Input: moves = [[0,0],[1,1],[0,1],[0,2],[1,0],[2,0]]
Output: "B"
Explanation: B wins.
Example 3:


Input: moves = [[0,0],[1,1],[2,0],[1,0],[1,2],[2,1],[0,1],[0,2],[2,2]]
Output: "Draw"
Explanation: The game ends in a draw since there are no moves to make.
 

Constraints:

1 <= moves.length <= 9
moves[i].length == 2
0 <= rowi, coli <= 2
There are no repeated elements on moves.
moves follow the rules of tic tac toe.
 */

class Solution {
    public String tictactoe(int[][] moves) {
        
        if(moves.length<5)
            return "Pending";
        
        //create board and fill with '#'
        char[][] board = new char[3][3];
        for(int i=0;i<board.length;i++)
            Arrays.fill(board[i], '#');
        
        
        //go over moves and fill the board with X and O
        int i=0, steps=0;
        for(int[] b:moves){
            
            int row=b[0];
            int col=b[1];
        
            if(i%2==0){
                board[row][col]='X';
            }
            else{
                board[row][col]='O';
            }
            i++;
            steps++;
        }
        
        //Diagonals check
        if(board[0][0]=='X' && board[1][1]=='X' && board[2][2]=='X')
            return "A";
        
        if(board[0][0]=='O' && board[1][1]=='O' && board[2][2]=='O')
            return "B";
        
         if(board[2][0]=='X' && board[1][1]=='X' && board[0][2]=='X')
            return "A";
        
        if(board[2][0]=='O' && board[1][1]=='O' && board[0][2]=='O')
            return "B";
        
        
        //check row wise and col wise here
        for(int k=0;k<3;k++){
            
            //we should only for X and O, so make sure # is not present
            //chances of # on all rows or on all cols is possible, so to omid that check #
            if(board[0][k]!='#'  && board[0][k] == board[1][k] && board[1][k] == board[2][k])
                return (board[0][k]=='X')? "A":"B";
            
            if(board[k][0]!='#' && board[k][0] == board[k][1] && board[k][1] == board[k][2])
                return (board[k][0]=='X')? "A":"B";
        }
        
        return (steps==9)? "Draw":"Pending";
        
    }
}