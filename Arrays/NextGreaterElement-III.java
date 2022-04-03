/*
Next Greater Element III
Medium

2127

338

Add to List

Share
Given a positive integer n, find the smallest integer which has exactly the same digits existing in the integer n and is greater in value than n. 
If no such positive integer exists, return -1.

Note that the returned integer should fit in 32-bit integer, if there is a valid answer but it does not fit in 32-bit integer, return -1.

 

Example 1:

Input: n = 12
Output: 21
Example 2:

Input: n = 21
Output: -1
 

Constraints:

1 <= n <= 231 - 1

Follow this blog for the algorithm: https://www.nayuki.io/page/next-lexicographical-permutation-algorithm
*/

class Solution {
    public int nextGreaterElement(int n) {

        String nextElement="";
        
        String number = String.valueOf(n);
        char[] nums = number.toCharArray();
        
        int i=nums.length-2;
        
        while(i>=0 && nums[i]>=nums[i+1]){
            i--;
        }
        
        if(i<0)
            return -1;
        
        if(i>=0){
            int j = nums.length-1;
            while(nums[i]>=nums[j])
                j--;
            
            swap(nums,i,j);
        }
        
        reverse(nums, i+1, nums.length-1);
        nextElement = new String(nums);
        int newNo;
        try{
            newNo = Integer.parseInt(nextElement);
            return newNo;
        }
        catch(Exception e){
            return -1;
        }
        
    }
    
    public void swap(char[] nums, int i, int j){
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public void reverse(char[] nums, int start, int end){
        while(start<end)
            swap(nums, start++, end--);
    }
}