/**
 * Given an array of intervals intervals where intervals[i] = [starti, endi],
 *  return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

 

Example 1:

Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
Example 2:

Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
Example 3:

Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 

Constraints:

1 <= intervals.length <= 105
intervals[i].length == 2
-5 * 104 <= starti < endi <= 5 * 104
 * 
 * 
 * 
 * 
 * T(n)=O(nLogn)
 * S(n)=O(1)
 */

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length<=1)
            return 0;
        
        //sorting based on start time
        Arrays.sort(intervals, (a,b)->a[0]-b[0]);
        
        //We are going to start from 1st interval, so have previousInterval set to 0
        int prevInterval = 0, intervalsErased = 0;
        
        for(int i=1;i<intervals.length;i++){
            
            //if my previous interval end time > current interval start time
            if(intervals[prevInterval][1]>intervals[i][0]){
                
                //we have a overlap, we need to remove an inteval, so increment
                intervalsErased++;
                
                //we need to choose which interval to go next with, compare their end times
                //take the lowest one, here i'th interval end time is less than previous end time
                //We choose lowest one if we take highest ones, the changes of future interval getting overlapped is more
                //The question is asking for minimum no. of intervals to be removed
                if(intervals[prevInterval][1]>intervals[i][1]){
                    prevInterval = i;
                }
            }
            
            //If we do not see an overlap, we just iterate to the next, with previous being updated to i'th interval
            else{
                 prevInterval = i;
             }
        }
        
        return intervalsErased;
    }
}