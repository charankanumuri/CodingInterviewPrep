/**
 *  Guess the Word
Hard

This is an interactive problem.

You are given an array of unique strings wordlist where wordlist[i] is 6 letters long, and one word in this list is chosen as secret.

You may call Master.guess(word) to guess a word. The guessed word should have type string and must be from the original list with 6 lowercase letters.

This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word. 
Also, if your guess is not in the given wordlist, it will return -1 instead.

For each test case, you have exactly 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or fewer calls to Master.
guess and at least one of these guesses was secret, then you pass the test case.

 

Example 1:

Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"], numguesses = 10
Output: You guessed the secret word correctly.
Explanation:
master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
master.guess("abcczz") returns 4, because "abcczz" has 4 matches.
We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
Example 2:

Input: secret = "hamada", wordlist = ["hamada","khaled"], numguesses = 10
Output: You guessed the secret word correctly.
 

Constraints:

1 <= wordlist.length <= 100
wordlist[i].length == 6
wordlist[i] consist of lowercase English letters.
All the strings of wordlist are unique.
secret exists in wordlist.
numguesses == 10
 */

/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 */
class Solution {
    public void findSecretWord(String[] wordlist, Master master) {
        Random r = new Random();
        List<String> words = new ArrayList<>(Arrays.asList(wordlist));
        
        for(int i=0;i<10;i++){
            int index = r.nextInt(words.size());
            String wordGuessed = words.get(index);
            int guessValue = master.guess(wordGuessed);
            
            //lets stop if we have found it
            if(guessValue == 6)
                return;
            
            //we are going to find words with same matched char count
            List<String> wordsMatched = new ArrayList<>();
            
            //basically we are going to omit words that have value less than guessValue
            //we know what's the guessed word, so lets use that and find all words with same guess value
            //and omit the remaining, so probability of finding the secret is high
            for(String w: words){
                if(findMatches(w,wordGuessed) == guessValue)
                    wordsMatched.add(w);
            }
            
            //lets shrink the words list with wordsMatched list
            words = new ArrayList<>(wordsMatched);
        }
    }
    
    public int findMatches(String s1, String s2){
        
        int match = 0;
        for(int i=0;i<6;i++){
            if(s1.charAt(i)==s2.charAt(i))
                match++;
        }
        
        return match;
    }
}