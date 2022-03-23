/*
Given an array of non-negative integers nums, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

You can assume that you can always reach the last index.

 

Example 1:

Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [2,3,0,1,4]
Output: 2
 

Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 1000
*/
class Solution {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];

        //fill arrays with Max VALUE
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        //starting index - jumps is 0, so we set it
        dp[0]=0;

        //Finding all possible jumps from every index and taking min
        for(int i=0;i<nums.length;i++){
            int maxJumps = nums[i]; //how many jumps i can do?
            
            //lets jump one by one and calculate min jumps at each index
            for(int j=1;j<=maxJumps;j++){
                if(i+j<nums.length){
                    //here we do min bcz, what if i+j was already computed from previous iterations of i
                    dp[i+j]= Math.min(dp[i+j], dp[i]+1);
                }
            }
        }
        
        //return the last index, which will have min jumps
        return dp[nums.length-1];
    }
}