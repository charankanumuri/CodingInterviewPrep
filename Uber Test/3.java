/*
You've created a new programming language, and now you've decided to add hashmap support to it. Actually you are quite disappointed that in common programming languages it's impossible to add a number to all hashmap keys, or all its values. So you've decided to take matters into your own hands and implement your own hashmap in your new language that has the following operations:

insert x y - insert an object with key x and value y.
get x - return the value of an object with key x.
addToKey x - add x to all keys in map.
addToValue y - add y to all values in map.
To test out your new hashmap, you have a list of queries in the form of two arrays: queryTypes contains the names of the methods to be called (eg: insert, get, etc), and queries contains the arguments for those methods (the x and y values).

Your task is to implement this hashmap, apply the given queries, and to find the sum of all the results for get operations.

Example

For queryType = ["insert", "insert", "addToValue", "addToKey", "get"] and query = [[1, 2], [2, 3], [2], [1], [3]], the output should be solution(queryType, query) = 5.

The hashmap looks like this after each query:

1 query: {1: 2}
2 query: {1: 2, 2: 3}
3 query: {1: 4, 2: 5}
4 query: {2: 4, 3: 5}
5 query: answer is 5
The result of the last get query for 3 is 5 in the resulting hashmap.
*/

long solution(String[] queryType, int[][] query) {
    long sum = 0;
    
    TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
    
    for(int i=0;i<queryType.length;i++){
        String action = queryType[i];
        
        
        switch(action){
            case "insert":{
                int key= query[i][0];
                int value = query[i][1];
                map.put(key, value);
                sum = sum+value;
            }
            
            case "get":{
                return sum;
            }
            
            case "addToKey":{
                int key= query[i][0];
                for(Map.Entry<Integer, Integer> entry: map.entrySet()){
                    int k = entry.getKey();
                    int v = entry.getValue();
                    
                    map.remove(k);
                    map.put(k+key,v);
                }
            }
            
            case "addToValue":{
                int value = query[i][0];
                for(Map.Entry<Integer, Integer> entry: map.entrySet()){
                    int k = entry.getKey();
                    int v = entry.getValue();
                    sum = sum - v;
                    map.remove(k);
                    map.put(k,v+value);
                    sum = sum - v + value;
                }
            }
        }
    }
    
    
    
    
    return sum;
}