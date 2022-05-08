/**
 * Longest Valid Parentheses
Hard

Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

 

Example 1:

Input: s = "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()".
Example 2:

Input: s = ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()".
Example 3:

Input: s = ""
Output: 0
 

Constraints:

0 <= s.length <= 3 * 104
s[i] is '(', or ')'.
 */

class Solution {
    public int longestValidParentheses(String s) {
        int longest = 0;
        
        int open = 0, close = 0;
        
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='(')
                open++;
            
            else
                close++;
            
            /*if closed is more then its not a valid string, reset the values*/
            if(close>open){
                open = 0;
                close = 0;
            }
            
            if(open == close)
                longest = Math.max(longest, close * 2); //take count of close * 2, as we need to consider open also
        }
        
        open=0; close=0;
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)=='(')
                open++;
            
            else
                close++;
            
            if(open>close){
                open = 0;
                close = 0;
            }
            
            if(open == close)
                longest = Math.max(longest, open * 2); //here we take open because we go from last to front
        }
        
        return longest;
    }
}