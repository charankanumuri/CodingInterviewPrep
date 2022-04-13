/**
 * Sort Colors
Medium

Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.

 

Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Example 2:

Input: nums = [2,0,1]
Output: [0,1,2]
 

Constraints:

n == nums.length
1 <= n <= 300
nums[i] is either 0, 1, or 2.
 */

class Solution {
    public void sortColors(int[] nums) {
        int i=0, j=0, k=nums.length-1;
        
		//i-> 0's pointer, j-> 1's pointer, k->2's pointer
        while(j<=k){
            
			//if j is 0, lets swap with i'th index
            if(nums[j]==0)
            {
                swapColors(nums,i,j);
                i++;
                j++;
            }
			// if we see 1, lets just move forward
            else if(nums[j]==1)
                j++;
				
			//if we see a 2, lets swap with k'th index and not moving j bcz we don't know what value j holds after swap
            else{
                swapColors(nums,j,k);
                k--;
            }
        }
    }
    
    public void swapColors(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}