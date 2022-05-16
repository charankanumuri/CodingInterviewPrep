/**
 * Daily Temperatures
Medium

Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

 

Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
Example 2:

Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
Example 3:

Input: temperatures = [30,60,90]
Output: [1,1,0]
 

Constraints:

1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100
 */

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Integer> stk = new Stack<>();
        
        //push initial element
        stk.push(0);
        
        for(int i=1;i<temperatures.length;i++){
            
            int currentTemp = temperatures[i];
            
            //if current temp is greater than previous temperatures, update the no.of days
            while(!stk.isEmpty() && currentTemp>temperatures[stk.peek()]){
                int index = stk.pop();
                
                //no of days is taken through indices difference
                result[index] = i - index;
            }
            
            //now push the current index on to stack
            stk.push(i);
        }
        
        return result;
    }
}