/**
 * Rearrange Spaces Between Words
Easy

You are given a string text of words that are placed among some number of spaces. 
Each word consists of one or more lowercase English letters and are separated by at least one space. 
It's guaranteed that text contains at least one word.

Rearrange the spaces so that there is an equal number of spaces between every pair of adjacent words and that number is maximized. 
If you cannot redistribute all the spaces equally, place the extra spaces at the end, meaning the returned string should be the same length as text.

Return the string after rearranging the spaces.

 

Example 1:

Input: text = "  this   is  a sentence "
Output: "this   is   a   sentence"
Explanation: There are a total of 9 spaces and 4 words. We can evenly divide the 9 spaces between the words: 9 / (4-1) = 3 spaces.
Example 2:

Input: text = " practice   makes   perfect"
Output: "practice   makes   perfect "
Explanation: There are a total of 7 spaces and 3 words. 7 / (3-1) = 3 spaces plus 1 extra space. We place this extra space at the end of the string.
 

Constraints:

1 <= text.length <= 100
text consists of lowercase English letters and ' '.
text contains at least one word.
 * 
 */

class Solution {
    public String reorderSpaces(String text) {
        int spaces = 0;
      
        //count the spacex
        for(char c: text.toCharArray()){
            if(c==' ')
                spaces++;
        }
        
        //form word array
        String[] words = text.trim().split("\\s+");
        int nWords = words.length;
        
        StringBuilder sb = new StringBuilder();
        int spacesToApply=0,extraSpaces=0;
        
        //if there is only 1 word, then all spaces will be at the end
        if(nWords == 1){
            extraSpaces=spaces;
        }
        
        //if there are multiple words, find the spaces to apply between words and also any extra space
        else{
            spacesToApply = spaces / (nWords-1);
            extraSpaces = spaces % (nWords-1);
        }
        
        //append every word and then apply spaces
        for(int i=0;i<words.length-1;i++){
            sb.append(words[i]);
            
            for(int j=0;j<spacesToApply;j++)
                sb.append(" ");
        }
        
        //now append last word separately, bcz we dont want to apply spaces after last word
        sb.append(words[nWords-1]);
        
        //if there are any extra spaces that cannot be distributed among words, add them here
        for(int j=0;j<extraSpaces;j++)
                sb.append(" ");
        
        return sb.toString();
    }
}