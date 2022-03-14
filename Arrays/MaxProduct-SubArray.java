/*
Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.

A subarray is a contiguous subsequence of the array.

 

Example 1:

Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

------------------------------------------------------------------------------------------------------------------

Here subarray means find contiguous subarray with max Product value

We can have negative numbers, which will derail the cummulative productive we have formed,
but the changes are getting another -ve number is possible

So we need to consider the negative number.

So lets, keep track of 2 variable, maxProd and minProd -> both these variables are used to tracking product till
current index

assign the first element of the array to them.


T(n)=O(n)
S(n)=O(1)

*/

class Solution {
    public int maxProduct(int[] nums) {
        
        //if there is only one number, return that
        if(nums.length == 1)
            return nums[0];
        
        int max = nums[0];
        int currentIndex_minProd = nums[0];
        int currentIndex_maxProd = nums[0];
        
        for(int i=1;i<nums.length;i++){
            
            //We maintain two variable for tracking min and maxProd, while iterating the array at that index
            int minProd = nums[i]*currentIndex_minProd;
            int maxProd = nums[i]*currentIndex_maxProd;
            
            //These two steps are finding overall min and maxProd computed so far
            //currentMax prod can be the number at i, or it can be product of current element and previous elements(1 or more)
            currentIndex_maxProd=Math.max(nums[i], Math.max(minProd, maxProd));
            
            //currentMin prod can be the number at i, or it can be product of current element and previous elements(1 or more)
            currentIndex_minProd=Math.min(nums[i], Math.min(minProd, maxProd));
        
            //always update when we find maxProd greater than max
            max = Math.max(max, currentIndex_maxProd);
        }
        
        return max;
    }
}