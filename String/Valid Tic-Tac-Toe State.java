/**
 * Valid Tic-Tac-Toe State
Medium

Given a Tic-Tac-Toe board as a string array board, return true if and only if it is possible to reach this board position 
during the course of a valid tic-tac-toe game.

The board is a 3 x 3 array that consists of characters ' ', 'X', and 'O'. The ' ' character represents an empty square.

Here are the rules of Tic-Tac-Toe:

Players take turns placing characters into empty squares ' '.
The first player always places 'X' characters, while the second player always places 'O' characters.
'X' and 'O' characters are always placed into empty squares, never filled ones.
The game ends when there are three of the same (non-empty) character filling any row, column, or diagonal.
The game also ends if all squares are non-empty.
No more moves can be played if the game is over.
 

Example 1:


Input: board = ["O  ","   ","   "]
Output: false
Explanation: The first player always plays "X".
Example 2:


Input: board = ["XOX"," X ","   "]
Output: false
Explanation: Players take turns making moves.
Example 3:


Input: board = ["XOX","O O","XOX"]
Output: true
 

Constraints:

board.length == 3
board[i].length == 3
board[i][j] is either 'X', 'O', or ' '.

--------------------------------------------------------------

So, basically the question is asking, we are given a tic tac toe board at some state
we should check and tell whether its a valid state or not

Thought process:
1. We know X's always starts first, so count of X will be more by just 1 if X wins, otherwise invalid
2. As X starts first, we cannot have count of O's more on the board, if thats the case, then its invalid
3. When O wins the game, board must have equal no of O's and X's, otherwise the board is invalid
4. The overall state of the board countX-countO should be 0 or 1.. Anything beyond is an invalid board
*/

class Solution {
    public boolean validTicTacToe(String[] board) {
        int countX=0;
        int countO=0;
        
        //count the no. of X and O on the board
        for(String s: board){
            
            for(char c:s.toCharArray()){
                
                if(c=='X')
                    countX++;
                
                if(c=='O')
                    countO++;
            }
        }
        
        //if O's count is more than X - its invalid bcz X always starts first
        //if X's count is more than O's by 1+, then its a invalid board
        if(countO>countX || countX-countO>1)
            return false;
        
        
        //variables to see whether we find 3'X or 3'O - row wise, col wise or diagonal
        boolean filledWithAllX=false;
        boolean filledWithAllO=false;
        
        //checking row wise
        for(String s:board){
            
            if(s.equals("XXX"))
                filledWithAllX=true;
            
            if(s.equals("OOO"))
                filledWithAllO=true;
        }
        
        //-----------------------
        //column wise check for X's
        if(board[0].charAt(0)=='X' && board[1].charAt(0)=='X' && board[2].charAt(0)=='X')
            filledWithAllX=true;
        
        if(board[0].charAt(1)=='X' && board[1].charAt(1)=='X' && board[2].charAt(1)=='X')
            filledWithAllX=true;
        
        if(board[0].charAt(2)=='X' && board[1].charAt(2)=='X' && board[2].charAt(2)=='X')
            filledWithAllX=true;
       
        //-----------------------
        //column wise check for O's       
        if(board[0].charAt(0)=='O' && board[1].charAt(0)=='O' && board[2].charAt(0)=='O')
            filledWithAllO=true;
        
        if(board[0].charAt(1)=='O' && board[1].charAt(1)=='O' && board[2].charAt(1)=='O')
            filledWithAllO=true;
        
        if(board[0].charAt(2)=='O' && board[1].charAt(2)=='O' && board[2].charAt(2)=='O')
            filledWithAllO=true;
        
        //--------------------------     
        //check for diagonals - we have 2 diagonals on 3x3 board
        if(board[0].charAt(0)=='O' && board[1].charAt(1)=='O' && board[2].charAt(2)=='O')
            filledWithAllO=true;
        
        if(board[0].charAt(0)=='X' && board[1].charAt(1)=='X' && board[2].charAt(2)=='X')
            filledWithAllX=true;
        
        if(board[0].charAt(2)=='O' && board[1].charAt(1)=='O' && board[2].charAt(0)=='O')
            filledWithAllO=true;
        
        if(board[0].charAt(2)=='X' && board[1].charAt(1)=='X' && board[2].charAt(0)=='X')
            filledWithAllX=true;
        
        //if both are true, then its invalid board, bcz only one player can win
        if(filledWithAllX && filledWithAllO)
            return false;
        
        //when X wins, X's count should be more than O, as X starts first
        if(filledWithAllX && (countX<=countO))
            return false;
        
        //when O wins, O will be equal to X's count if not its invalid
        if(filledWithAllO && (countO!=countX))
            return false;
        
        return true;
        
    }
}