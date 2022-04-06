/**
 * Valid Parenthesis String
Medium

Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.

The following rules define a valid string:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
 

Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "(*)"
Output: true
Example 3:

Input: s = "(*))"
Output: true
 */
class Solution {
    public boolean checkValidString(String s) {
        int open=0, close=0;
        int len=s.length();
        
        for(int i=0;i<len;i++){
            
            //considering * as Open bracket
            if(s.charAt(i)=='(' || s.charAt(i)=='*')
                open++;
            else
                open--;
            
            // if this goes under 1, that means string is not balanced
            if(open<0)
            return false;
        }
        
        //means the string is already balanced
        if(open==0)
            return true;
        
        for(int i=len-1;i>=0;i--){
            
            //considering * as Closed bracket
            if(s.charAt(i)==')' || s.charAt(i)=='*')
                close++;
            else
                close--;
            
            //means string is not balanced
            if(close<0)
            return false;
        }
        
       return true; 
    }
}