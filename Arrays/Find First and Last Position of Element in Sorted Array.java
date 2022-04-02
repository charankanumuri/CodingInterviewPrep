/**
 * 34. Find First and Last Position of Element in Sorted Array
Medium

9896

281

Add to List

Share
Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.

 

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
Example 3:

Input: nums = [], target = 0
Output: [-1,-1]
 

Constraints:

0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums is a non-decreasing array.
-109 <= target <= 109
 */

class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums.length<1)
            return new int[]{-1,-1};
        
        int index1=binarySearch(nums,target,true);
        int index2=binarySearch(nums,target,false);
        
        return new int[]{index1, index2};
   }
    
    public int binarySearch(int[] nums, int target, boolean firstFind){
        int start=0, end=nums.length-1;
        
        while(start<=end){
            int mid=start+(end-start)/2;
            
            if(nums[mid]==target){
                
                //if it is first find, we keep checking the lower bound
                //but if mid is start or the previous number is not target, we will just return the mid index
                if(firstFind){
                    if(mid==start || nums[mid-1]!=target)
                        return mid;
                    
                    //if there is a duplicate in our range, lets call the same loop by cutting the array by half
                    //we go mid-1 because of lower bound
                    end=mid-1;
                }
                //if it's second index to find, we keep going up
                //but if we reach mid as our end index or the next number is not target, we will just return the mid index
                else
                {
                    if(mid==end || nums[mid+1]!=target)
                        return mid;
                    
                    //if there is duplicate lets cut array by half and we go mid+1 because of upper bound
                    start=mid+1;
                } 
            }
            
            
            else if(target>nums[mid])
                start=mid+1;
            else
                end=mid-1;
        }
        
        //if nothing found return -1
        return -1;
    }
}