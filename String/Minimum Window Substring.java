/**
 * Minimum Window Substring
Hard

Given two strings s and t of lengths m and n respectively, 
return the minimum window substring of s such that every character in t (including duplicates) is included in the window. 
If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

A substring is a contiguous sequence of characters within the string.

 

Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.
 

Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.
 

Follow up: Could you find an algorithm that runs in O(m + n) time?
 */


class Solution {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        
        //add freq for string t
        for(char c: t.toCharArray()){
            map.put(c, map.getOrDefault(c,0)+1);
        }
        
        //set minLenght = string s length+1
        int minSize = s.length()+1;
        int start = 0, minSubStringStart=0;
        int matchedChar = 0;
        
        for(int end=0;end<s.length();end++){
            
            char Rightchar = s.charAt(end);
            
            //if char is on map, lets reduce the freq
            if(map.containsKey(Rightchar)){      
                map.put(Rightchar, map.get(Rightchar)-1);
                
                //we maintain matchedChars to count whether we reached all char freq of t
                //we increment only for >=0 char, we should count if there are multiple char's
                //again and again which has more freq than chars in t
                if(map.get(Rightchar)>=0)
                    matchedChar++;
            }
            
            //if matchedChar count is equal to t length that means we found a substring
            while(matchedChar == t.length()){
                
                //find the minSize with start and end pointers and update them
                //also keep track of minSubString start as well
                if(minSize>end-start+1){
                    minSize=end-start+1;
                    minSubStringStart = start;
                }
                
                //now check from leftChar
                char leftChar = s.charAt(start++);
                
                //if its there on map, and freq is 0, that means thats the end of that char
                if(map.containsKey(leftChar)){
                    
                    //so decrement the matchedChar bcz we are going out of that window now
                    if(map.get(leftChar)==0)
                        matchedChar--;
                    
                    //as we are going out of that window add the freq by 1
                    map.put(leftChar, map.get(leftChar)+1);
                }
            }
        }
        
        //return the substring based on minSize, if minSize is still greater than s length,
        //then we didn't find the min substring
        return minSize>s.length()? "": s.substring(minSubStringStart, minSubStringStart+minSize);
    }
}