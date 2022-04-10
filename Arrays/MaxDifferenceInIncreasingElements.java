/**
 * Maximum Difference Between Increasing Elements
Easy

Given a 0-indexed integer array nums of size n, find the maximum difference between nums[i] and nums[j] (i.e., nums[j] - nums[i]), 
such that 0 <= i < j < n and nums[i] < nums[j].

Return the maximum difference. If no such i and j exists, return -1.

 

Example 1:

Input: nums = [7,1,5,4]
Output: 4
Explanation:
The maximum difference occurs with i = 1 and j = 2, nums[j] - nums[i] = 5 - 1 = 4.
Note that with i = 1 and j = 0, the difference nums[j] - nums[i] = 7 - 1 = 6, but i > j, so it is not valid.
Example 2:

Input: nums = [9,4,3,2]
Output: -1
Explanation:
There is no i and j such that i < j and nums[i] < nums[j].
Example 3:

Input: nums = [1,5,2,10]
Output: 9
Explanation:
The maximum difference occurs with i = 0 and j = 3, nums[j] - nums[i] = 10 - 1 = 9.
 

Constraints:

n == nums.length
2 <= n <= 1000
1 <= nums[i] <= 109
 */

class Solution {
    public int maximumDifference(int[] nums) {
        
        //if array is empty, return -1
        if(nums.length == 0)
            return -1;
        
        //have two variables min and max
        int minimum = nums[0];
        int maximum = -1;
        
        //start from 1st index
        for(int i=1;i<nums.length;i++){
            
            //check if my number is greater than prev element, which is minimum here [0] index initially
            if(nums[i]>minimum){
                
                //get the max
                maximum=Math.max(maximum, nums[i]-minimum);
            }
            
            //if not, then my current value will be new minimum and check from there to next indices
            else{
                minimum = nums[i];
            }
                
        }
        
        return maximum;
    }
}

/*

Eg: [1,5,2,10]

minimum = 1
maximum = -1

-------
for loop -> i=1

nums[1]>minimum? -> yes

set maximum = Max(maximum, (5-1)) = 4

----------

for loop -> i=2

nums[2]>minimum? Yes
set maximum = Max(maximum, (2-1)) = 4

------------
for loop -> i=3

nums[3]>minimum? Yes
set maximum = Max(maximum, (10-1)) = 9


*/