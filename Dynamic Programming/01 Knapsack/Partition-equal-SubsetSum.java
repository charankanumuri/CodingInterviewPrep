/**
 * Given a non-empty array nums containing only positive integers,
 *  find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
 

Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 100
 * 
 */

class Solution {
    public boolean canPartition(int[] nums) {
        if(nums.length == 0)
            return true;
        
        int target =0;
        
        for(int n: nums){
            target+=n;
        }
        
        //if its not even, then return false
        if(target%2!=0)
            return false;
        
        // memoization technique -> always have 2D array for problems involving 0/1 knapsack
        Boolean[][] dp = new Boolean[nums.length+1][target / 2 + 1];
        
        return findPartition(nums, target/2, 0, dp);
    }
    
    public boolean findPartition(int[] nums, int sum, int index, Boolean[][] dp){
        
        if(dp[index][sum]!=null)
            return dp[index][sum];
        
        if(sum == 0)
            return true;
       
        if(index>=nums.length)
            return false;
        
        if(nums[index]<=sum){
            if(findPartition(nums, sum-nums[index], index+1, dp)){
               dp[index][sum]=true;
                  return true;
             } 
         }
        
        
        if(findPartition(nums, sum, index+1, dp))
            dp[index][sum] = true;
        else
            dp[index][sum]=false;
        
        return dp[index][sum];
        
    }
}