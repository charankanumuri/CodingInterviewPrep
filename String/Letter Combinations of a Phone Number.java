/**
 * Letter Combinations of a Phone Number
 *
 * Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:

Input: digits = ""
Output: []
Example 3:

Input: digits = "2"
Output: ["a","b","c"]
 * 
 */

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        
        if(digits.length() == 0 || digits == null)
            return result;
        
        //index 0,1 are set to just numbers, because they wont be used but we need to create an array with 0
        //pulling mapping based on index will be lot easier so we start with 0 and 1 numbers and then letters
        String[] mapping = new String[]{
            "0","1","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"
        };
        
        findLetterCombination(mapping, digits, result, 0, "");
            
        return result;
    }
    
    public void findLetterCombination(String[] mapping, String digits, List<String> result, int index, String combination){
        
        //base case, we get a combination of digits length
        if(combination.length() == digits.length()){
            result.add(combination);
            return;
        }
        
        //get all possible character from the mapping
        String possibleLetters = mapping[digits.charAt(index)-'0'];
        
        for(char c: possibleLetters.toCharArray()){
            
            //Add the character
            combination+=c;
            
            //go to next number
            findLetterCombination(mapping, digits, result, index+1, combination);
            //returns when combination is found
            
            
            //we need to remove the last character to go with next characters
            combination = combination.substring(0,combination.length()-1);
        }
    }
}