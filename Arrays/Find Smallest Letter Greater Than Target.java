/**
 * Find Smallest Letter Greater Than Target
Easy

Given a characters array letters that is sorted in non-decreasing order and a character target, 
return the smallest character in the array that is larger than target.

Note that the letters wrap around.

For example, if target == 'z' and letters == ['a', 'b'], the answer is 'a'.
 

Example 1:

Input: letters = ["c","f","j"], target = "a"
Output: "c"
Example 2:

Input: letters = ["c","f","j"], target = "c"
Output: "f"
Example 3:

Input: letters = ["c","f","j"], target = "d"
Output: "f"
 

Constraints:

2 <= letters.length <= 104
letters[i] is a lowercase English letter.
letters is sorted in non-decreasing order.
letters contains at least two different characters.
target is a lowercase English letter.
 */

class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        
        char answer=letters[0];
        int charTarget = target - 'a';
        
        //if my char was z, then we know we have to find from a,b,...,y
        //so lets set it to -1
        if(charTarget == 25)
            charTarget = -1;
        
        for(char c: letters){
            if(c-'a'>charTarget){
                answer = c;
                break;
            }
        }
        
        return answer;
    }
}