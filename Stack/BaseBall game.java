/**
 * Baseball Game
Easy

You are keeping score for a baseball game with strange rules. The game consists of several rounds, 
where the scores of past rounds may affect future rounds' scores.

At the beginning of the game, you start with an empty record. You are given a list of strings ops, 
where ops[i] is the ith operation you must apply to the record and is one of the following:

An integer x - Record a new score of x.
"+" - Record a new score that is the sum of the previous two scores. It is guaranteed there will always be two previous scores.
"D" - Record a new score that is double the previous score. It is guaranteed there will always be a previous score.
"C" - Invalidate the previous score, removing it from the record. It is guaranteed there will always be a previous score.
Return the sum of all the scores on the record.

 

Example 1:

Input: ops = ["5","2","C","D","+"]
Output: 30
Explanation:
"5" - Add 5 to the record, record is now [5].
"2" - Add 2 to the record, record is now [5, 2].
"C" - Invalidate and remove the previous score, record is now [5].
"D" - Add 2 * 5 = 10 to the record, record is now [5, 10].
"+" - Add 5 + 10 = 15 to the record, record is now [5, 10, 15].
The total sum is 5 + 10 + 15 = 30.
Example 2:

Input: ops = ["5","-2","4","C","D","9","+","+"]
Output: 27
Explanation:
"5" - Add 5 to the record, record is now [5].
"-2" - Add -2 to the record, record is now [5, -2].
"4" - Add 4 to the record, record is now [5, -2, 4].
"C" - Invalidate and remove the previous score, record is now [5, -2].
"D" - Add 2 * -2 = -4 to the record, record is now [5, -2, -4].
"9" - Add 9 to the record, record is now [5, -2, -4, 9].
"+" - Add -4 + 9 = 5 to the record, record is now [5, -2, -4, 9, 5].
"+" - Add 9 + 5 = 14 to the record, record is now [5, -2, -4, 9, 5, 14].
The total sum is 5 + -2 + -4 + 9 + 5 + 14 = 27.
Example 3:

Input: ops = ["1"]
Output: 1
 

Constraints:

1 <= ops.length <= 1000
ops[i] is "C", "D", "+", or a string representing an integer in the range [-3 * 104, 3 * 104].
For operation "+", there will always be at least two previous scores on the record.
For operations "C" and "D", there will always be at least one previous score on the record.
 */

class Solution {
    public int calPoints(String[] ops) {
        
        int sum=0;
        Stack<String> stk = new Stack<>();
        
        //I don't want to go over the stack again to calculate sum
        //so i keep calculating on every operation
        
        for(int i=0;i<ops.length;i++){
            switch(ops[i]){
                case "+":{
                    int num1 = Integer.parseInt(stk.pop());
                    int num2 = Integer.parseInt(stk.pop());
                    int newNum = num1+num2;
                    sum+=newNum;
                    
                    //make sure you do push them in right order -> whatever removed last will go 1st on stk
                    stk.push(String.valueOf(num2));
                    stk.push(String.valueOf(num1));
                    
                    //then the newly computed value
                    stk.push(String.valueOf(newNum));
                    
                    break;
                }
                case "C":{
                    int remove = Integer.parseInt(stk.pop());
                    sum-=remove;
                    break;
                    
                }
                case "D":{
                    int newNum = 2 * Integer.valueOf(stk.peek());
                    stk.push(String.valueOf(newNum));
                    sum+=newNum;
                    break;
                }
                    
                //if its not C, D or +, just insert
                default:{
                    stk.push(ops[i]);
                    sum+=Integer.parseInt(ops[i]);
                }
            }
        }
        
        return sum;
    }
}