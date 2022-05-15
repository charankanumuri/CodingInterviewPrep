/**
 * Basic Calculator
Hard

Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

 

Example 1:

Input: s = "1 + 1"
Output: 2
Example 2:

Input: s = " 2-1 + 2 "
Output: 3
Example 3:

Input: s = "(1+(4+5+2)-3)+(6+8)"
Output: 23
 

Constraints:

1 <= s.length <= 3 * 105
s consists of digits, '+', '-', '(', ')', and ' '.
s represents a valid expression.
'+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
'-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
There will be no two consecutive operators in the input.
Every number and running calculation will fit in a signed 32-bit integer.
 */

class Solution {
    
    int index = 0;
    char[] chars;
    public int calculate(String s) {
        
        chars = s.toCharArray();
        return evaluate();
    }
    
    
    public int evaluate(){
        
        int sum = 0;
        int operator = 1;//considering positive number
            
        while(index<chars.length){
            if(chars[index]==')'){
                break;
            }
            //if we see ( symbol, lets recursively call the same function to evaluate an expression
            else if(chars[index]=='('){
                index++;
                sum = sum + evaluate() * operator;
            }
            
            //make operator +ve
            else if(chars[index]=='+'){
                operator=1;
            }
            
            //make operator -ve
            else if(chars[index]=='-'){
                operator=-1;
            }
            
            //for digits, form a number and add it to sum along with operator symbol
            else if(Character.isDigit(chars[index])){
                StringBuilder num = new StringBuilder();
                
                while(index<chars.length && Character.isDigit(chars[index])){
                    num.append(chars[index++]);
                }
                      
                sum = sum + (Integer.parseInt(num.toString()) * operator);
                index--; //this is for the pointer where the above while would have failed
            }
            
            index++;
        }
        return sum;
    }
}