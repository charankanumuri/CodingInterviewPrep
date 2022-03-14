/**
 * longest common subsequence max length
 * 
 * Memoization technique
 */

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        Integer[][] dp = new Integer[text1.length()][text2.length()];
        return findLongestCommonSubString(text1, text2, 0, 0, dp);
    }
    
    public int findLongestCommonSubString(String s1, String s2, int index1, int index2, Integer[][] dp){
        if(index1 == s1.length() || index2 == s2.length())
            return 0;
        
        if(dp[index1][index2]!=null)
            return dp[index1][index2];
        
        if(s1.charAt(index1) == s2.charAt(index2))
            return 1 + findLongestCommonSubString(s1, s2, index1+1, index2+1, dp);
        
        int count1 = findLongestCommonSubString(s1, s2, index1, index2+1, dp);
        int count2 = findLongestCommonSubString(s1, s2, index1+1, index2, dp);
        
        dp[index1][index2] = Math.max(count1, count2);
        
        return dp[index1][index2];
    }
}