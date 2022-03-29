/**
 * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

 

Example 1:

Input: nums = [1,2,2,3,1]
Output: 2
Explanation: 
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.
Example 2:

Input: nums = [1,2,2,3,1,4,2]
Output: 6
Explanation: 
The degree is 3 because the element 2 is repeated 3 times.
So [2,2,3,1,4,2] is the shortest subarray, therefore returning 6.
 */

class Solution {
    public int findShortestSubArray(int[] nums) {
        /*
        Maintain left for left index values, right for right index values
        Count is used to store the frequency of a numbers from nums array
        */
        HashMap<Integer, Integer> left = new HashMap<>();
        HashMap<Integer, Integer> right = new HashMap<>();
        HashMap<Integer, Integer> count = new HashMap<>();
        
        for(int i=0;i<nums.length;i++){
            
            //if we encounter a number that hasn't added before
            //add it to left map
            if(left.get(nums[i])==null)
                left.put(nums[i],i);
            
            //add it to right map as well, as it can be the only number in the array
            right.put(nums[i],i);
            
            //add the frequency of the number
            count.put(nums[i], count.getOrDefault(nums[i],0)+1);
        }
        
        //lets find the max of frequency on the map 
        int degree = Collections.max(count.values());
        int answer = nums.length;
        
        for(int x: count.keySet()){
            if(count.get(x)==degree){
                answer = Math.min(answer, right.get(x)-left.get(x)+1);
            }
        }
        
        return answer;
    }
}