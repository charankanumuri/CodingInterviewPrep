/**
 * One Edit Distance
Medium

Given two strings s and t, return true if they are both one edit distance apart, otherwise return false.

A string s is said to be one distance apart from a string t if you can:

Insert exactly one character into s to get t.
Delete exactly one character from s to get t.
Replace exactly one character of s with a different character to get t.
 

Example 1:

Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.
Example 2:

Input: s = "", t = ""
Output: false
Explanation: We cannot get t from s by only one step.
 

Constraints:

0 <= s.length, t.length <= 104
s and t consist of lowercase letters, uppercase letters, and digits.
 */

class Solution {
    public boolean isOneEditDistance(String s, String t) {
        
        //if both string lengths differ by size >1, we need two edit minimum
        if(Math.abs(s.length()-t.length())>1)
            return false;
        
        //if both are equal, lets see whether we need any modifications
        if(s.length() == t.length()){
            return checkStrings(s,t);
        }
        //if they differ by one, lets me how many edits we can make
        else{
            return checkStringsWithOneEdit(s,t);
        }
    }
    
    public boolean checkStrings(String s1, String s2){
        int difference=0;
        
        int i=0;
        
        while(i<s1.length()){
            
            //increment the difference on the strings
            if(s1.charAt(i)!=s2.charAt(i))
                difference++;
            
            i++;
        }
        
        //if we have more than 1 difference, retrun false
        return difference == 1;
    }
    
    public boolean checkStringsWithOneEdit(String s1, String s2){
        
        //take longest and shortest strings by finding the length
        String bigger = (s1.length()>s2.length())? s1: s2;
        String smaller = (s1.length()<s2.length())? s1: s2;
        
        int i=0, j=0;
        
        //go over the index one by one, atmost we can have one edit
        while(i<smaller.length() && j<bigger.length()){
            
            //when they differ, see if both are pointing to same index
            if(smaller.charAt(i)!=bigger.charAt(j)){
                
                //if this becomes true, that means its our second edit
                if(i!=j)
                    return false;
                
                //first time, if both are different we consider and increment the longer string index
                else{
                    j++;
                }
            }
            
            //if they are equal, lets move on to the next indices
            else{
                i++;
                j++;
            }
        }
        
        return true;
    }
}