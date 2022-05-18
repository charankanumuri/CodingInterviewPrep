/**
 * Minimum Time Difference
Medium

Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any two time-points in the list.
 

Example 1:

Input: timePoints = ["23:59","00:00"]
Output: 1
Example 2:

Input: timePoints = ["00:00","23:59","00:00"]
Output: 0
 

Constraints:

2 <= timePoints.length <= 2 * 104
timePoints[i] is in the format "HH:MM".
 */

class Solution {
    public int findMinDifference(List<String> timePoints) {

        //we have 1440 mins
        boolean[] times = new boolean[1440];
        
        for(String time: timePoints){
            
            String[] t = time.split(":");
            
            int hours = Integer.parseInt(t[0]);
            int mins = Integer.parseInt(t[1]);
            
            int totalMins = hours * 60 + mins;
            
            if(times[totalMins])
                return 0;
            
            times[totalMins] = true;
        }
        
        int currentTime = -1;
        int prevTime = -1;
        int minDiff = Integer.MAX_VALUE;
        
        for(int i=0;i<1440;i++){
            if(times[i]){
                if(prevTime == -1){
                    currentTime = i;
                    prevTime = i;
                    continue;
                }
                
                minDiff = Math.min(minDiff, i - prevTime);
                prevTime = i;
            }
        }
        
        //prevTime and currTime can be very close to each other, so lets take that combination also into consideration
        return Math.min(minDiff, 1440-prevTime+currentTime);
    }
}