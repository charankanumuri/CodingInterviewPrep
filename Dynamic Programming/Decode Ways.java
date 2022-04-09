/**
 * Decode Ways
Medium

A message containing letters from A-Z can be encoded into numbers using the following mapping:

'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above 
(there may be multiple ways). For example, "11106" can be mapped into:

"AAJF" with the grouping (1 1 10 6)
"KJF" with the grouping (11 10 6)
Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".

Given a string s containing only digits, return the number of ways to decode it.

The test cases are generated so that the answer fits in a 32-bit integer.

 

Example 1:

Input: s = "12"
Output: 2
Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: s = "226"
Output: 3
Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
Example 3:

Input: s = "06"
Output: 0
Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
 

Constraints:

1 <= s.length <= 100
s contains only digits and may contain leading zero(s).
 */

class Solution {
    public int numDecodings(String s) {
        
        //map to store index, no.of ways from here
        HashMap<Integer, Integer> memo = new HashMap<>();
        
        return findNoOfDecodings(s, memo, 0);
    }
    
    public int findNoOfDecodings(String s, Map<Integer,Integer> memo, int index){
        
        //if we are beyond the string length that means 
        //we moved from one character to this index
        if(index==s.length())
            return 1;
        
        //if it is zero, return 0
        if(s.charAt(index)=='0')
            return 0;
        
        //if we are at end of the string, return 1 bcz they is only 1 way
         if(index==s.length()-1)
            return 1;
        
        //if already computed, return it from map
        if(memo.containsKey(index))
            return memo.get(index);
        
        //lets recursively find no.of ways moving index one by one
        int ways = findNoOfDecodings(s, memo, index+1);
        
        //we also know we can move at 2 places and see if its a valid number to decode
        //26 characters here, check whether the 2 index place is less than 26 (a-z)
        if(Integer.parseInt(s.substring(index, index+2))<=26){
            
            //if yes, add the previously computed ways + call the function recursively with index+2
            //we are finding all possible ways, so we are adding ways that is calculated from previous step
            ways = ways + findNoOfDecodings(s, memo, index+2);
        }
        
        //add to map after computation
        memo.put(index, ways);
        
        return ways;
    }
}