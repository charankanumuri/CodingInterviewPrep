/**
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi],
 *  return the minimum number of conference rooms required.

 

Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: 1
 

Constraints:

1 <= intervals.length <= 104
0 <= starti < endi <= 106
 */

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        
        //sort by start time
        Arrays.sort(intervals, (a,b)->a[0]-b[0]);
        
        //keep two arrays, start times and end times
       int[] start = new int[intervals.length];
       int[] end = new int[intervals.length];
        
        //initilaize all start and end arrays
        for(int i=0;i<intervals.length;i++){
            start[i]=intervals[i][0];
            end[i]=intervals[i][1];
        }
        
        //sort all of start and end times
        Arrays.sort(start);
        Arrays.sort(end);
        
        int roomRequired=0;
        int startPointer=0;
        int endPointer=0;
        
        
        while(startPointer<intervals.length){
            
            //we check if my start time is >= to end time, that means we don't need a new room
            //we will use the same room so we decrease and increase
            if(start[startPointer]>=end[endPointer]){
                roomRequired--;
                endPointer++;
            }

                roomRequired++;
                startPointer++;

        }
        return roomRequired;
    }
}