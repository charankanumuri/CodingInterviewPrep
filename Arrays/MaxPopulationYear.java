/**
 * Maximum Population Year
Easy

You are given a 2D integer array logs where each logs[i] = [birthi, deathi] indicates the birth and death years of the ith person.

The population of some year x is the number of people alive during that year. 
The ith person is counted in year x's population if x is in the inclusive range [birthi, deathi - 1]. 
Note that the person is not counted in the year that they die.

Return the earliest year with the maximum population.

 

Example 1:

Input: logs = [[1993,1999],[2000,2010]]
Output: 1993
Explanation: The maximum population is 1, and 1993 is the earliest year with this population.
Example 2:

Input: logs = [[1950,1961],[1960,1971],[1970,1981]]
Output: 1960
Explanation: 
The maximum population is 2, and it had happened in years 1960 and 1970.
The earlier year between them is 1960.
 

Constraints:

1 <= logs.length <= 100
1950 <= birthi < deathi <= 2050
 */

class Solution {
    public int maximumPopulation(int[][] logs) {
        
        //we know the input is from 1950 to 2050
        //index 0 is 1950, 1 is 1951 and so on..
        int[] populationAtYear = new int[101];
        
        
        //max year population setting it to 1950, as thats the starting year as per question
        int maxYearPopulation=1950;
        
        //maintaining no. of people born and dead at a particular year
        //if its positive we have people alive, if negative we have more death in that year
        for(int i=0;i<logs.length;i++){
            
            int birthYear = logs[i][0];
            int deathYear = logs[i][1];
            
            populationAtYear[birthYear-1950]++;
            populationAtYear[deathYear-1950]--;
        }
        
        //Set maxPopulation to 1st value
        int maxPopulation=populationAtYear[0];
        
        //lets start from index 1 and iterate over the array we formed which has total people alive/dead at a year
        for(int i=1;i<populationAtYear.length;i++){
            
            //accumulate the sum at each year and see if maxPopulation is less than newly commuted population 
            populationAtYear[i]=populationAtYear[i]+populationAtYear[i-1];
            
            
            //keep tracking year and population
            if(maxPopulation<populationAtYear[i]){
                maxPopulation=populationAtYear[i];
                maxYearPopulation=1950+i;
            }
        }
        
        return maxYearPopulation;
    }
}