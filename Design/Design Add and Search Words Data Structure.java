/**
 * Design Add and Search Words Data Structure
Medium

Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 

Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True
 

Constraints:

1 <= word.length <= 25
word in addWord consists of lowercase English letters.
word in search consist of '.' or lowercase English letters.
There will be at most 3 dots in word for search queries.
At most 104 calls will be made to addWord and search.
 */

class Node{
    HashMap<Character, Node> map;
    boolean isEnd;
    
    public Node(){
        map = new HashMap<>();
        isEnd = false;
    }
}

class WordDictionary {
    
    Node root;
    public WordDictionary() {
        root = new Node();
    }
    
    public void addWord(String word) {
        Node current = root;
        
        for(char c: word.toCharArray()){
            if(!current.map.containsKey(c)){
                current.map.put(c, new Node());
            }
            
            current = current.map.get(c);
        }
        current.isEnd=true;
    }
    
    public boolean search(String word) {
        return searchWord(word, 0, root);
    }
    
    public boolean searchWord(String word, int index, Node root) {
        if(index>=word.length())
            return root.isEnd;
        
        char ch=word.charAt(index);
        
        if(ch=='.'){
            
            //check for all keys, starting from index+1
            for(char c: root.map.keySet()){
                if(searchWord(word,index+1,root.map.get(c)))
                    return true;
            }
            
            //if we dont find it, return false
            return false;
        }
        
        //if the given character is not present, return false
        else if(root.map.get(ch)==null)
            return false;
        else
            return searchWord(word, index+1, root.map.get(ch)); //start with next index & next child node
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */