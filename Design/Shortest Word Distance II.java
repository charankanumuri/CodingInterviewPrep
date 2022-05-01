/*
Design a data structure that will be initialized with a string array, and then it should answer queries of the shortest distance 
between two different strings from the array.

Implement the WordDistance class:

WordDistance(String[] wordsDict) initializes the object with the strings array wordsDict.
int shortest(String word1, String word2) returns the shortest distance between word1 and word2 in the array wordsDict.
 

Example 1:

Input
["WordDistance", "shortest", "shortest"]
[[["practice", "makes", "perfect", "coding", "makes"]], ["coding", "practice"], ["makes", "coding"]]
Output
[null, 3, 1]

Explanation
WordDistance wordDistance = new WordDistance(["practice", "makes", "perfect", "coding", "makes"]);
wordDistance.shortest("coding", "practice"); // return 3
wordDistance.shortest("makes", "coding");    // return 1
 

Constraints:

1 <= wordsDict.length <= 3 * 104
1 <= wordsDict[i].length <= 10
wordsDict[i] consists of lowercase English letters.
word1 and word2 are in wordsDict.
word1 != word2
At most 5000 calls will be made to shortest
*/


class WordDistance {
    
    //we use this map because same words can be repeated multiple times and going over entire array again and again is not feasible
    //lets track the list of indices for every word
    HashMap<String, List<Integer>> map;
    public WordDistance(String[] wordsDict) {
        map = new HashMap<>();
        
        for(int i=0;i<wordsDict.length;i++){
            if(map.containsKey(wordsDict[i]))
                map.get(wordsDict[i]).add(i);
            else{ 
                List<Integer> indices = new ArrayList<>();
                indices.add(i);
                
                map.put(wordsDict[i], indices);
            }
        }
    }
    
    public int shortest(String word1, String word2) {
        
        //take only the indices of that repective words, so we don't traverse entire array
        List<Integer> indices1 = map.get(word1);
        List<Integer> indices2 = map.get(word2);
        int minDistance = Integer.MAX_VALUE;
        
        int i=0,j=0;
        
        while(i<indices1.size() && j<indices2.size()){
            int index1 = indices1.get(i);
            int index2 = indices2.get(j);
            
            if(index1<index2){
                minDistance = Math.min(minDistance, index2-index1);
                i++; //if index1 is less, we should increment i, so we go close to index2 and we can find better minDistance
            }
            else{
                minDistance = Math.min(minDistance, index1-index2);
                j++; //otherway around as mentioned on the previous i++
            }
        }
      
        return minDistance;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(wordsDict);
 * int param_1 = obj.shortest(word1,word2);
 */