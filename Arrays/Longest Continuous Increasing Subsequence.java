/*
Intuition behind: we need to check whether current element is 
greater than previous element, increment a counter on every comparison this until condition breaks.

when condition fails, copy the counter value to a Maximum variable to keep track of the largest increasing sequence.

When the condition fails, we need to rest the counter to 1 again, to see whether we can find
any other long continuos subsequence. And keep iterating till the end of array


T(N) = O(N)
S(N) = O(1)



Input: nums = [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5] with length 3.
Even though [1,3,5,7] is an increasing subsequence, it is not continuous as elements 5 and 7 are separated by element
4.


Input: nums = [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2] with length 1. Note that it must be strictly
increasing.
*/

class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length == 0 || nums==null)
            return 0;
        
        int MaxSubSequenceLength = 1;
        int counter = 1;
        
        for(int i=1;i<nums.length;i++){
            
            //increment counter if its increasing sequence, else set it to 1
            counter = nums[i]>nums[i-1]? counter+1 : 1;
            
            //Always compare MaxLength and its counter length, to find the max of it
            MaxSubSequenceLength = Math.max(MaxSubSequenceLength, counter);
        }
        
        return MaxSubSequenceLength;
    }
}