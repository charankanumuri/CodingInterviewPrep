/**
 * Valid Triangle Number
Medium

Given an integer array nums, return the number of triplets chosen from the array that can make triangles 
if we take them as side lengths of a triangle.

 

Example 1:

Input: nums = [2,2,3,4]
Output: 3
Explanation: Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Example 2:

Input: nums = [4,2,3,4]
Output: 4
 

Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 1000
 */

class Solution {
    public int triangleNumber(int[] nums) {
        
        int count = 0;
        Arrays.sort(nums);
        
        int n = nums.length-1;\
            
            
        //we know the triangle property is, given 3 sides, sum of two sides always greater than 3rd side
        //so we take last number as c
        for(int i=n;i>=1;i--){
            
            //a is left and b is right
            int left =0, right = i-1;
            
            //we are checking a+b>c
            while(left<right){
                
                //if anything nums[left] + nums[right] is greater than nums[i]
                //that means any number between indices left to right will be greater that nums[i], as array is sorted, so we add (right-left) to count;
                if(nums[i]<nums[left]+nums[right]){
                    count = count + (right-left);
                    right--;
                }
                else
                    left++;
            }
        }
        
        return count;
    }
}