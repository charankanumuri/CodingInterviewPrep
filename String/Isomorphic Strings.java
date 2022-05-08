/**
 * Isomorphic Strings
Easy

Given two strings s and t, determine if they are isomorphic.

Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. 
No two characters may map to the same character, but a character may map to itself.

 

Example 1:

Input: s = "egg", t = "add"
Output: true
Example 2:

Input: s = "foo", t = "bar"
Output: false
Example 3:

Input: s = "paper", t = "title"
Output: true
 

Constraints:

1 <= s.length <= 5 * 104
t.length == s.length
s and t consist of any valid ascii character.
 */


class Solution {
    HashMap<Character, Character> mapST = new HashMap<>();
    HashMap<Character, Character> mapTS = new HashMap<>();
    public boolean isIsomorphic(String s, String t) {
        return find(s,t);
    }
    
    public boolean find(String s, String t){
         int i=0, len = s.length();
        
        while(i<len){
            Character c1 = s.charAt(i);
            Character c2 = t.charAt(i);
            if(!mapST.containsKey(c1) && !mapTS.containsKey(c2)){
                    mapST.put(c1, c2);
                    mapTS.put(c2, c1);
            }
            else{
                if(mapST.get(c1) != c2 || mapTS.get(c2)!=c1)
                    return false;
            }
            i++;
        }
        
        return true;
    }
}