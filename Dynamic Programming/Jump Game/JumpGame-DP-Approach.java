/**

Improvement from back tracking to DP memoization concept.

 * You are given an integer array nums. You are initially positioned at the array's first index,
 *  and each element in the array represents your maximum jump length at that position.
 Return true if you can reach the last index, or false otherwise. 


 T(N) = O(N^2)
 S(N) = O(N) -> memo[] array space
 * 
 */

class Solution {
    public boolean canJump(int[] nums) {
        
        if(nums.length == 1)
            return true;
        
        //we will maintain 3 values in this array -> -1,0,1
        // -1 -> means not computed
        // 0 -> means path reachable from this index
        // 1 -> means not reachable from this index
        int[] memo = new int[nums.length+1];
        
        //fill all with -1 initially
        Arrays.fill(memo, -1);
        
        int destinationIndex = nums.length-1;
        
        return canJumpRecursive(0, destinationIndex, nums, memo);
    }
    
    public boolean canJumpRecursive(int index, int destinationIndex, int[] nums, int[] memo){
        
        //if this index was already computed, return its value
        if(memo[index]!=-1)
            return memo[index]==0? true: false;
        
        if(index>destinationIndex)
            return false;
        
        if(index+nums[index] >= destinationIndex)
            return true;
            
        //See how much you can max jump from current index
        int maxJumps = nums[index];

        //try every possible jump in a loop
        for(int i=1;i<=maxJumps;i++){
            
            //if this recursive call returns return, set the memo[] and return true
            if(canJumpRecursive(index+i, destinationIndex, nums, memo)){
                memo[index] = 0;
                return true;
           }
        }
        
        //if we wouldn't have got true from the recursive call,
        //set this index to 1-> means not reachable from here and return false
        memo[index]=1;
        
        return false;
    }
}