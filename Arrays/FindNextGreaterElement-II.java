/**
 * Next Greater Element II
Medium


Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), 
return the next greater number for every element in nums.

The next greater number of a number x is the first greater number to its traversing-order next in the array, 
which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.

 

Example 1:

Input: nums = [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number. 
The second 1's next greater number needs to search circularly, which is also 2.
Example 2:

Input: nums = [1,2,3,4,3]
Output: [2,3,4,-1,4]
 

Constraints:

1 <= nums.length <= 104
-109 <= nums[i] <= 109
 */

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        
        int n=nums.length;
        int[] output = new int[n];

        //fill values with -1 initially, 
        Arrays.fill(output, -1);
        
        //holds index
        Stack<Integer> stk = new Stack<>();
        
        //as this is a circular array, we are pushing the array index twice
        for(int i=0;i<2*n;i++){
            
            //check whether my prev element is less than current element index, i
            while(!stk.isEmpty() && nums[stk.peek()]<nums[i%n])
                output[stk.pop()]=nums[i%n];    //if yes, update it on output array
            
            if(i<n)
                stk.push(i);
        }
        return output;
    }
}