/*

Given an array of integers nums and an integer k,
return the number of contiguous subarrays where the product of all the elements
in the subarray is strictly less than k.

Example 1:

Input: nums = [10,5,2,6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are:
[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
Example 2:

Input: nums = [1,2,3], k = 0
Output: 0


Constraints:

1 <= nums.length <= 3 * 104
1 <= nums[i] <= 1000
0 <= k <= 106

-------------------------------------------------------------------------------------------------------------------------------------------

Going to follow sliding window technique

T(n)=O(n)
S(n)=O(1)

*/

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {

        //if nums is empty return 0
        if(nums.length == 0)
            return 0;
        
        //if k is one or less than that, return 0.. because we need less than K
        if(k<=1)
            return 0;
        
        //counter to know
        int noOfSubArrays=0;
        
        //start window and end window index
        int startW=0, endW=0;

        //product variable to keep track
        int product=1;
        
        for(endW=0;endW<nums.length;endW++){
            product = product*nums[endW];
            
            //when product is equal to K or greater, start removing element from the beginning
            //here is when startW index comes into play
            //We are using while loop because, what if our last endW index had a very big integer value, so we keep checking until product is < K
            while(product>=k && startW<nums.length)
                product = product/nums[startW++];
            
            //count sunArrays found, note here (endW-startW+1) means we found a subArray less than K,
            // but it also means, each element in that range is also a subArray
            noOfSubArrays= noOfSubArrays + (endW-startW+ 1);
        }
        
        return noOfSubArrays;
    }
}