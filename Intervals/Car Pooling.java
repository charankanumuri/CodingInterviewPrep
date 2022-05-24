/**
 * Car Pooling
Medium

There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).

You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] 
indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. 
The locations are given as the number of kilometers due east from the car's initial location.

Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.

 

Example 1:

Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false
Example 2:

Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true
 

Constraints:

1 <= trips.length <= 1000
trips[i].length == 3
1 <= numPassengersi <= 100
0 <= fromi < toi <= 1000
1 <= capacity <= 105
 */

class Solution {
    public boolean carPooling(int[][] trips, int capacity) {

        //maintaining tree map so startTime is sorted automatically
        TreeMap<Integer, Integer> map = new TreeMap<>();
        
        for(int[] t: trips){
            
            //how many passengers i am adding at start time
            int startCount = map.getOrDefault(t[1],0);
            map.put(t[1], startCount+t[0]);
            
            //how many passengers are getting down at end time
            int endCount = map.getOrDefault(t[2],0);
            map.put(t[2], endCount-t[0]);
        }
        
        int passengerOccupied = 0;
        
        for(Integer count: map.values()){
            passengerOccupied = passengerOccupied + count;
            
            if(passengerOccupied>capacity)
                return false;
        }
        
        return true;
    }
}