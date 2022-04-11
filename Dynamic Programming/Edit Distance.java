/**
 * Edit Distance
Hard

Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character
 

Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
 

Constraints:

0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
 */

class Solution {
    public int minDistance(String word1, String word2) {
        Integer[][] memo = new Integer[word1.length()+1][word2.length()+1];
        return findMinOperations(word1, word2, 0, 0, memo);
    }
    
    public int findMinOperations(String s1, String s2, int index1, int index2, Integer[][] memo){
        
        if(memo[index1][index2]==null){
            
            //if one goes out of bound, return remaining characters of s2
            if(index1==s1.length())
                memo[index1][index2] = s2.length()-index2;

            //if s2 goes out of bound, return remaining characters of s1
            else if(index2==s2.length())
                memo[index1][index2] = s1.length()-index1;

            //if both are equal call, no operations required, call next two indexes
            else if(s1.charAt(index1)==s2.charAt(index2)){
               memo[index1][index2] = findMinOperations(s1,s2,index1+1,index2+1, memo);
            }
            
            else{
                int choice1 = findMinOperations(s1,s2,index1, index2+1, memo); //insert
                int choice2 = findMinOperations(s1,s2,index1+1, index2, memo); //delete
                int choice3 = findMinOperations(s1,s2,index1+1, index2+1, memo); //replace

                memo[index1][index2] = 1 + Math.min(choice1, Math.min(choice2, choice3));    
            }
        }  

        return memo[index1][index2];
    }
}