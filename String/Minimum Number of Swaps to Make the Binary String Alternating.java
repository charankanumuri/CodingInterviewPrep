/**
 * Minimum Number of Swaps to Make the Binary String Alternating
Medium

Given a binary string s, return the minimum number of character swaps to make it alternating, or -1 if it is impossible.

The string is called alternating if no two adjacent characters are equal. For example, the strings "010" and "1010" are alternating, while the string "0100" is not.

Any two characters may be swapped, even if they are not adjacent.

 

Example 1:

Input: s = "111000"
Output: 1
Explanation: Swap positions 1 and 4: "111000" -> "101010"
The string is now alternating.
Example 2:

Input: s = "010"
Output: 0
Explanation: The string is already alternating, no swaps are needed.
Example 3:

Input: s = "1110"
Output: -1
 

Constraints:

1 <= s.length <= 1000
s[i] is either '0' or '1'.
 */

class Solution {
    public int minSwaps(String s) {
        int ones = 0;
        int zeros = 0;
        
        //count no. of zeros and ones
        for(char c: s.toCharArray()){
            if(c=='0')
                zeros++;
            else
                ones++;
        }
        
        //base case we cannot make it alternative if this is the case
        if(Math.abs(ones-zeros)>1)
            return -1;
        
        //if ones are more, the string should start with 1
        if(ones>zeros)
            return findMinSwaps(s, '1');
        
        //string should start with 0
        if(zeros>ones)
            return findMinSwaps(s, '0');
        
        //if both are equal, lets check both possibilities
        return Math.min(findMinSwaps(s, '1'),findMinSwaps(s, '0'));
    }
    
    
    //we are counting the no. of changes we are doing, but we are basically swapping two indexes,
    //so return /2
    public int findMinSwaps(String s, char ch){
        int minSwaps = 0;
        
        for(char c: s.toCharArray()){
            if(c!=ch)
            {
                minSwaps++;
            }
            ch = (ch == '1')? '0':'1';
        }
        
        return minSwaps/2;
    }
}