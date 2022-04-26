/**
 * First Missing Positive
Hard

Given an unsorted integer array nums, return the smallest missing positive integer.

You must implement an algorithm that runs in O(n) time and uses constant extra space.

 

Example 1:

Input: nums = [1,2,0]
Output: 3
Example 2:

Input: nums = [3,4,-1,1]
Output: 2
Example 3:

Input: nums = [7,8,9,11,12]
Output: 1
 

Constraints:

1 <= nums.length <= 5 * 105
-231 <= nums[i] <= 231 - 1
 */

class Solution {
    public int firstMissingPositive(int[] nums) {
        int n=nums.length;
        
        //to check whether we have one
        boolean containsOne = false;
        
        for(int i=0;i<n;i++){
            if(nums[i]==1)
                containsOne = true;
            else{
                
                //we also convert 0's, negatives and numbers more than n to 1
                if(nums[i]<=0 || nums[i]>n)
                    nums[i] = 1;
            }
        }
        
        //if we dont have just return that number
        if(!containsOne)
            return 1;
        
        
        //here we select every number(take Math.abs) as it can be -ve number and subtract -1
        //to get the index
        for(int i=0;i<n;i++){
            int index = Math.abs(nums[i])-1;
            
            //make it negative
            if(nums[index]>0)
                nums[index] = nums[index]*-1;
        }
        
        //check for number which is positive that's our missing positive integer
        for(int i=0;i<n;i++){
            if(nums[i]>0)
                return i+1;
        }        
        
        //otherwise return n+1
        return n+1;
    }
}