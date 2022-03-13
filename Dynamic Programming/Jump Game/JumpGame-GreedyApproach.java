/*

We are gonna check whether we can reach the final index, moving backward

array-> [2,3,1,1,4]
index-> [0,1,2,3,4]

set lastPosition=4, move back
index =3, can i reach last index from index 3? yes, update lastPosition to 3 and move back
index =2, can i reach index 3 from index 2? If yes, update lastPosition to 2 and move back
.
.
.

finally check whether lastPosition is equal to 0, if not then we are unable to reach final
index, because our starting point is index0 always

T(n)=O(n)
S(n)=O(1)

*/

class Solution {
    public boolean canJump(int[] nums) {
        
        if(nums.length == 1)
            return true;
        
        int lastPosition = nums.length-1;
        
        for(int i=nums.length-2;i>=0;i--){
            if(i+nums[i]>=lastPosition)
                lastPosition = i;
        }
        
        return (lastPosition==0);
    }
}