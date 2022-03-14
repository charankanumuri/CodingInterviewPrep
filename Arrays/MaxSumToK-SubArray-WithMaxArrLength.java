/*
Given an integer array nums and an integer k, return the maximum length of a subarray that sums to k. If there is not one, return 0 instead.

Example 1:

Input: nums = [1,-1,5,-2,3], k = 3
Output: 4
Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
Example 2:

Input: nums = [-2,-1,2,1], k = 1
Output: 2
Explanation: The subarray [-1, 2] sums to 1 and is the longest.

Constraints:

1 <= nums.length <= 2 * 105
-104 <= nums[i] <= 104
-109 <= k <= 109
---------------------------------------------------------------------------------

We form a prefix sum while iterating over the array.
We add it to a map when we find new prefix sum, prefix value and its index

if prefix sum gets to K, we update the maxLen of the subArray to i+1

whenever we calculate prefix sum, we check whether there is a prefix sum at an index = current prefixSum-K, 
then update the longest len of subArray
*/

class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if(nums.length == 0)
            return 0;
        
        //prefixSum, index
        HashMap<Integer, Integer> map = new HashMap<>();
        
        int maxLenSubArray=0;
        int prefixSum=0;
        
        for(int i=0;i<nums.length;i++){
            prefixSum+=nums[i];
            
            if(prefixSum == k)
                maxLenSubArray = i+1;
            
            if(!map.containsKey(prefixSum))
                map.put(prefixSum, i);
            
            if(map.containsKey(prefixSum-k))
                maxLenSubArray=Math.max(maxLenSubArray, i-map.get(prefixSum-k));
        }
        
        return maxLenSubArray;
    }
}