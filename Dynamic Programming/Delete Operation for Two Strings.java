/**
 * Delete Operation for Two Strings
Medium

Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.

In one step, you can delete exactly one character in either string.

 

Example 1:

Input: word1 = "sea", word2 = "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Example 2:

Input: word1 = "leetcode", word2 = "etco"
Output: 4
 

Constraints:

1 <= word1.length, word2.length <= 500
word1 and word2 consist of only lowercase English letters.
 */

class Solution {
    public int minDistance(String word1, String word2) {
        
        Integer[][] memo = new Integer[word1.length()+1][word2.length()+1];
//         int longestCommonSubSeq = lcs(word1, word2, word1.length(), word2.length(), memo);
        
//         int deletions = word1.length()-longestCommonSubSeq;
//         int insertions = word2.length()-longestCommonSubSeq;
        
        return word1.length() + word2.length() - 2 * findLongestCommonSubString(word1, word2, 0,0, memo);
        
    }
    
    public int findLongestCommonSubString(String s1, String s2, int index1, int index2, Integer[][] dp){
        if(index1 == s1.length() || index2 == s2.length())
            return 0;
        
        if(dp[index1][index2]!=null)
            return dp[index1][index2];
        
        //if they are equal move both pointers
        if(s1.charAt(index1) == s2.charAt(index2))
            return 1 + findLongestCommonSubString(s1, s2, index1+1, index2+1, dp);
        
        //if not, try moving one pointer of every string
        int count1 = findLongestCommonSubString(s1, s2, index1, index2+1, dp);
        int count2 = findLongestCommonSubString(s1, s2, index1+1, index2, dp);
        
        //take the max of it and return
        dp[index1][index2] = Math.max(count1, count2);
        
        return dp[index1][index2];
    }
}