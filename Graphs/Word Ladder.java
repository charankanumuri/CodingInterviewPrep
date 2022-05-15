/**
 * Word Ladder
Hard

A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words
 beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from
beginWord to endWord, or 0 if no such sequence exists.

 

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 

Constraints:

1 <= beginWord.length <= 10
endWord.length == beginWord.length
1 <= wordList.length <= 5000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
 */

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        
        //add all words in set, so access can be O(1)
        HashSet<String> set = new HashSet<>(wordList);
        
        //if endWord is not there, there is no possibility to find the word
        if(!set.contains(endWord))
            return 0;
        
        HashSet<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        
        //add beginWord to queue
        queue.add(beginWord);
        int length = 1;
        
        while(!queue.isEmpty()){
            
            int size = queue.size();
            
            for(int i=0;i<size;i++){
                String word = queue.poll();
                
                //check if the word is endWord, if yes, return the ladder len
                if(word.equals(endWord))
                    return length;
                
                //now we have to check for each char in the word
                for(int j=0;j<word.length();j++){
                    
                    //take the word and convert it to char array
                    char[] chars = word.toCharArray();
                    
                    //we can place to a-z chars
                    //place them at all possible indices
                    for(int k='a';k<='z';k++){
                        chars[j] = (char) k;
                        
                        //form the word
                        String str = new String(chars);
                        
                        //check if they are there in wordList set and see if its not visited yet
                        if(set.contains(str) && !visited.contains(str)){
                            visited.add(str);
                            queue.add(str);
                        }
                    }
                }
            }
            length++;
        }
        
        return 0;
    }
}