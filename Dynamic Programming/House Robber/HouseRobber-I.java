class Solution {
    public int rob(int[] nums) {
        int[] maxRobbed = new int[nums.length+1];
        Arrays.fill(maxRobbed, -1);
        return robHouses(0, nums, maxRobbed);
    }
    
    public int robHouses(int index, int[] nums, int[] maxRobbed){
        
        if(index>=nums.length)
            return 0;
        
        if(maxRobbed[index]==-1){
            
            //If we select current house, skip one house and go to next
            int stealCurrentHouse = nums[index] + robHouses(index+2, nums, maxRobbed);

            //If we are skipping current house, go to very next index
            int skipCurrentHouse = robHouses(index+1, nums, maxRobbed);

            maxRobbed[index] = Math.max(stealCurrentHouse, skipCurrentHouse);
        }
        
        return maxRobbed[index];
    }
}