/**
 * Given a string s, find the length of the longest substring without repeating characters.

 

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 

Constraints:

0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.
 * 
 */

class Solution {
    public int lengthOfLongestSubstring(String s) {
       if(s.length()<=1)
           return s.length();
        
        //character, index
        Map<Character, Integer> map = new HashMap<>();
        
        
        //Sliding window with start and end pointers
        int start=0;
        int longest=0;
        int localMax=0;
        
        for(int end=0;end<s.length();end++){
            Character ch = s.charAt(end);
            
            if(!map.containsKey(ch)){
                
                //load on to the map, character & its index
                map.put(ch, end);
                
                //keep counting the string length while traversing
                //we add +1 because the string index starts with 0
                localMax=end-start+1;
            }
            
            else{
                //As we found dupicate, we need to remove chars upto certain index on the map
                //Get the index of the duplicate character encountered
                int deleteUpTo = map.get(ch);
                
                //Find the substring length
                //we dont add +1 because, end index here is duplicate, so end-start will give
                //correct string length
                localMax=end-start;
                
                //Lets remove all characters from the map 
                //Until the duplicate index, i.e, deleteUpTo
                while(start<s.length() && start<=deleteUpTo)
                    map.remove(s.charAt(start++));
                
                //update the start window
                start=deleteUpTo+1;
                
                //add the new character and its index
                map.put(ch,end);
            }
            
            //compare with localMax and longest
            longest = Math.max(longest, localMax);
        }
        
        return longest;
    }
}
