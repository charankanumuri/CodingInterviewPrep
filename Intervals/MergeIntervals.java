/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 *  and return an array of the non-overlapping intervals that cover all the intervals in the input.

 

Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 

Constraints:

1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104

T(n)=O(nLogn)
S(n)=(n)
 * 
 */

class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals.length<=1)
            return intervals;
        
        //Sort the array so we have to iterate only once 
        Arrays.sort(intervals, (a,b) -> (a[0]-b[0])); //here we sort by comparing start time, index 0->start, index 1->end
        
        //We need a DS to maintain the merged interval, we aren't sure what will be the size of output, so we use list
        LinkedList<int[]> list = new LinkedList<>();
        
        //every 2d array is basically a 1D array, if we pick them individually
        //[0][0] and [0][1] is basically can be stored in 1D array [0] and [1]
        for(int[] interval: intervals){
            
            //If list is empty, we directly insert or we compare the previous end time and current interval start time, if they dont overlap, add the interval to the list
            if(list.isEmpty() || list.getLast()[1]<interval[0]){
                list.add(interval);
            }
            
            //If they overlap, then we need the max end time to merge the intervals.
            //compare previous end time and current interval end time and take the largest
            else{
                list.getLast()[1] = Math.max(interval[1], list.getLast()[1]);
            }
        }
        
        return list.toArray(new int[list.size()][]);
    }
}