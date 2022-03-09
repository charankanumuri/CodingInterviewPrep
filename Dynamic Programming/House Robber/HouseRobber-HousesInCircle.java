class Solution {
    public int rob(int[] nums) {
        
        if(nums == null || nums.length == 0)
            return 0;
        
        //Only when one house is there
        if(nums.length == 1)
            return nums[0];
        
        //Memoization array
        int[] maxRobbed = new int[nums.length+1];
        
        //filling -1 because what if amount is zero in each house
        Arrays.fill(maxRobbed, -1);

        //Starting with firstHouse, then skip last house, that's why we are adding -2 on the function call
        int startFirstHouse = recursiveRob(nums, maxRobbed, 0, nums.length - 2);
        
        //refill as values could have changed
        Arrays.fill(maxRobbed, -1);

        //Here, we can visit till last house because, we start with 2nd house
        int skipFirstHouse = recursiveRob(nums, maxRobbed, 1, nums.length - 1);
        
        return Math.max(startFirstHouse, skipFirstHouse);
    }
    
    public int recursiveRob(int[] nums, int[] maxRobbed, int index, int stoppingIndex){
        if(index>stoppingIndex)
            return 0;
        
        if(maxRobbed[index] == -1)
        {
            int pickCurrentHouse = nums[index] + recursiveRob(nums, maxRobbed, index + 2, stoppingIndex);
            int skipCurrentHouse = recursiveRob(nums, maxRobbed, index + 1, stoppingIndex);
            maxRobbed[index] = Math.max(pickCurrentHouse, skipCurrentHouse);
        }
        
        return maxRobbed[index];
    }
}