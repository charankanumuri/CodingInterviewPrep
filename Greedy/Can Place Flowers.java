/**
 * Can Place Flowers
Easy

You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.

Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, 
return if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule.

 

Example 1:

Input: flowerbed = [1,0,0,0,1], n = 1
Output: true
Example 2:

Input: flowerbed = [1,0,0,0,1], n = 2
Output: false
 */

class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(n==0)
            return true;
        
        int index=0, length = flowerbed.length;
        
        while(index<length && n>0){
            
            //i have a spot to place flower
            if(flowerbed[index]==0){
                
                boolean checkprev = ((index==0) || flowerbed[index-1]==0);
                boolean checknext = ((index==length-1) || flowerbed[index+1]==0);
                  
                //check prev & next adjacent spots to satisfy the condition
                if(checkprev && checknext){
                    n--;
                    index+=2;
                }
                
                //if they don't satisfy the condition, lets check on next index
                else
                    index++;
            }
            
            //if i have a 1, lets move forward by 2 positions
            else{
                index+=2;
            }
        }
        
        return n==0;
    }
}