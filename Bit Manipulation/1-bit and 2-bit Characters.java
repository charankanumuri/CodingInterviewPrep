/**
 * 1-bit and 2-bit Characters
Easy

We have two special characters:

The first character can be represented by one bit 0.
The second character can be represented by two bits (10 or 11).
Given a binary array bits that ends with 0, return true if the last character must be a one-bit character.

 

Example 1:

Input: bits = [1,0,0]
Output: true
Explanation: The only way to decode it is two-bit character and one-bit character.
So the last character is one-bit character.
Example 2:

Input: bits = [1,1,1,0]
Output: false
Explanation: The only way to decode it is two-bit character and two-bit character.
So the last character is not one-bit character.
 

Constraints:

1 <= bits.length <= 1000
bits[i] is either 0 or 1.
 */

class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int index=0;
        
        //we are checking one place before the last number bcz question says it will end with 0
        //we just have to make sure, we reach there 
        while(index<bits.length-1){
            
            //if our current index is pointing to 1, that means we will 10 or 11 from here
            //so lets jump by 2 places
            if(bits[index]==1)
                index=index+2;
            
            //if it is not 1, we just have 0, lets jump by 1 spot
            else
                index=index+1;
        }
        
        // if we reach the end, return true
        return index==bits.length-1;
    }
}