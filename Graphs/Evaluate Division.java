/**
 * Evaluate Division
Medium

You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and 
values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

 

Example 1:

Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation: 
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
Example 2:

Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]
Example 3:

Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]
 

Constraints:

1 <= equations.length <= 20
equations[i].length == 2
1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */


class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        
        //divident -> map(divisor, value)
        //we have can same divident with multiple divisors
        Map<String, Map<String, Double>> map = new HashMap<>();
        
        //lets build the graph
        for(int i=0;i<equations.size();i++){
            List<String> eq = equations.get(i);
            String divident = eq.get(0);
            String divisor = eq.get(1);
            
            double value = values[i];
            
            
            //when we have a/b = x, we know b/a=1/x, so lets add that as well
            if(!map.containsKey(divident))
                map.put(divident, new HashMap<String, Double>());
            if(!map.containsKey(divisor))
                map.put(divisor, new HashMap<String, Double>());
            
            map.get(divident).put(divisor, value);
            map.get(divisor).put(divident, 1/value);
        }
        
        
        double[] result = new double[queries.size()];
        
        //lets go one query at a time
        for(int i=0;i<queries.size();i++){
            List<String> query = queries.get(i);
            String divident = query.get(0);
            String divisor = query.get(1);
            
            //base case
            if(!map.containsKey(divident) || !map.containsKey(divisor))
                result[i]=-1;
            
            //if equal then its always going to be 1
            else if(divident == divisor)
                result[i]=1;
            
            //if not lets see whether we can reach divident -> divisor from the graph built
            else{
                
                //we maintain this set to make sure we don't visit the same divident or divisor again
                HashSet<String> set = new HashSet<>();
                result[i] = evaluateQuery(map, divident, divisor, 1.0, set);
            }
        }
        
        return result;
    }
    
    
    public double evaluateQuery(Map<String, Map<String, Double>> map, String source, String destination, double value, Set<String> visited){
        
        //add the source string to visited
        visited.add(source);
        
        //result can be -1, so lets start from here
        double res = -1;
        
        //get all neighbors avaialble
        Map<String, Double> neighbor = map.get(source);
        
        //see if we can find the target string here, if yes, lets calculate result
        if(neighbor.containsKey(destination)){
            res = value * neighbor.get(destination);
        }
        
        //if we don't find, lets find neighbors of neighbor and see whether we can find
        else{
            for(Map.Entry<String, Double> entry: neighbor.entrySet()){
                String node = entry.getKey();
                
                //we go only when it is not visited
                if(!visited.contains(node)){
                    res = evaluateQuery(map, node, destination, value*entry.getValue(), visited);
                    
                    //if the res is not -1, that means we found the path from source to destination
                    //so break and stop traversing
                    if(res!=-1)
                        break;
                }
            }
        }
        
        //return the result
        return res;
    }
}