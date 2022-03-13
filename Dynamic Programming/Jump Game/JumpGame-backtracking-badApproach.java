/**
 * You are given an integer array nums. You are initially positioned at the array's first index,
 *  and each element in the array represents your maximum jump length at that position.
 Return true if you can reach the last index, or false otherwise. 

 * The below approach is 2^n time complexity and it times out for very large inputs.
 * Works only for smaller i/p's
 * 
 * Take it code for understanding purpose only
 */

class Solution {
    public boolean canJump(int[] nums) {
        
        if(nums.length == 1)
            return true;
        
        int destinationIndex = nums.length-1;
        
        return canJumpRecursive(0, destinationIndex, nums);
    }
    
    public boolean canJumpRecursive(int index, int destinationIndex, int[] nums){
        
        if(index>destinationIndex)
            return false;
        
        if(index+nums[index] >= destinationIndex)
            return true;
        
        //See how much you can max jump from current index
        int maxJumps = nums[index];
        

        //try every possible jump in a loop
        for(int i=1;i<=maxJumps;i++){
            
            if(canJumpRecursive(index+i, destinationIndex, nums) == true)
                return true;
        }
        
        return false;
    }
}