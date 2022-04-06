/**
 * 3Sum
Medium

Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] 
such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

 

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Example 2:

Input: nums = []
Output: []
Example 3:

Input: nums = [0]
Output: []
 

Constraints:

0 <= nums.length <= 3000
-105 <= nums[i] <= 105
 */

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
     
        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort(nums);
        
        for(int i=0;i<nums.length;i++){
            if(i==0 || nums[i-1]!=nums[i])
                findTwoSum(nums, answer, -nums[i], i);
        }
        return answer;
    }
    
    public void findTwoSum(int[] nums, List<List<Integer>> answer, int target, int index){
        
        int start = index+1;
        int end = nums.length-1;
        
        while(start<end){
            int sum = nums[start]+nums[end];
            
            if(sum<target)
                start++;
                
            else if(sum>target)
                end--;
            
            else{
                answer.add(Arrays.asList(nums[index], nums[start], nums[end]));   
                start++;
                end--;
                
                //we need to check whether the same number is present on the next start
                //if so, that a duplicate we shouldn't consider, keep moving front
                while(start<end && nums[start]==nums[start-1])
                    start++;
            }
        }
    }
}