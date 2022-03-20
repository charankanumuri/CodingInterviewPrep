/**
 * You are given an array of non-overlapping intervals intervals where
 *  intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti.
 *  You are also given an interval newInterval = [start, end] that represents the start and end of another interval.

Insert newInterval into intervals such that intervals is still sorted in ascending order by starti
 and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.

 

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 

Constraints:

0 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 105
intervals is sorted by starti in ascending order.
newInterval.length == 2
0 <= start <= end <= 105

T(n) = O(n)
S(n) = O(1)
 */

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals.length==0)
            return new int[][]{newInterval};
        
        LinkedList<int[]> list = new LinkedList<>();
        
        int i=0,n=intervals.length;
        
        //Add all intervals until we find overlap
        while(i<n && intervals[i][1]<newInterval[0]){
            list.add(intervals[i++]);
        }
        
        //This is the place where we found an overlap
        //Here we are updating the newInterval value with min start and max stop values
        //Then, we aren't adding it to the list bcz what if the next interval overlaps with my newly 
        //computed newInterval
        //So check from intervals[i][start] to newInterval[end] and keep merging
        while(i<n && intervals[i][0]<=newInterval[1]){
             newInterval[0] = Math.min(newInterval[0],intervals[i][0]);
             newInterval[1] = Math.max(newInterval[1],intervals[i][1]); 
             i++;
        }
        list.add(newInterval); //adding the final merged interval here
        
        //add remaining intervals if any
        while(i<n)
            list.add(intervals[i++]);
        
        return list.toArray(new int[list.size()][2]);
    }
}