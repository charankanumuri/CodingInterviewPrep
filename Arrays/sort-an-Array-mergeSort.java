/*
Given an array of integers nums, sort the array in ascending order.

 

Example 1:

Input: nums = [5,2,3,1]
Output: [1,2,3,5]
Example 2:

Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]


T(n) = O(nlogn) -> n elements iterated and recursion stack takes log n steps as it forms a tree
S(n) = O(n) -> for newly created temp array

*/

class Solution {
    public int[] sortArray(int[] nums) {
        if(nums == null || nums.length == 0)
            return nums;
        
        //recursive call starting
        return mergeRecursive(nums, 0, nums.length-1);
        
    }
    
    public int[] mergeRecursive(int[] nums, int start, int end){
        
        //if its the same, then we have only one element left, so return
        if(start==end)
            return nums;
        
        int mid = start+(end-start)/2;
        mergeRecursive(nums, start, mid);
        mergeRecursive(nums, mid+1, end);
        
        return mergeArray(nums, start, mid, end);
    }
    
    public int[] mergeArray(int[] nums, int start, int mid, int end){
        
        if(start>=end)
            return nums;
        
        //creating temp array
        int[] sorted = new int[end-start+1];
        
        //pointers i, j
        int i=start, j=mid+1;
        int k=0; //for new array(sorted) index
        
        while(i<=mid && j<=end){
            if(nums[i]<=nums[j])
                sorted[k++]=nums[i++];
            else
                sorted[k++]=nums[j++];
        }
        
        //left over elements
        while(i<=mid)
            sorted[k++]=nums[i++];
        
        while(j<=end)
            sorted[k++]=nums[j++];
        
        //copy the sorted array to nums arrays
        ////index+start means we have to point to the correct nums index as this method will be called with different start and end values when partitioning the array
         for (int index = 0; index < sorted.length; index++) 
            nums[index + start] = sorted[index]; 
        
        return nums;
    }
}