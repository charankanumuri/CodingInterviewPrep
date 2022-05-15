/**
 * Asteroid Collision
Medium

We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

 

Example 1:

Input: asteroids = [5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
Example 2:

Input: asteroids = [8,-8]
Output: []
Explanation: The 8 and -8 collide exploding each other.
Example 3:

Input: asteroids = [10,2,-5]
Output: [10]
Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 

Constraints:

2 <= asteroids.length <= 104
-1000 <= asteroids[i] <= 1000
asteroids[i] != 0
 */

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stk = new Stack<>();
        int size = asteroids.length;
        
        for(int i=0;i<asteroids.length;i++){
            
            int asteroid = asteroids[i];
            
            //if positive insert it directly
            if(asteroid>0){
                stk.push(asteroid);
            }
            /*
                We have 3 cases here:
                1. if stack is not empty and top of stack is +ve, where abs(current asteroid) > top of stack
                    then pop the top of the stack and run it in while loop until condition becomes false
                2. if stack is empty, just insert it or if top of the stack has -ve number, just push to stack
                3. if the top of stack is equal to absolute value of the current asteroid, pop the top of stack
            */
            else{
                while(!stk.isEmpty() && stk.peek()>0 && stk.peek()<-asteroid)
                    stk.pop();
                
                if(stk.isEmpty() || stk.peek()<0)
                    stk.push(asteroid);
                
                else if(stk.peek() == -asteroid)
                    stk.pop();
            }
        }
        
        //create an array of stack size and store elements in reverse order
        int[] result = new int[stk.size()];
        for(int i=stk.size()-1;i>=0;i--){
            result[i] = stk.pop();
        }
        
        return result;
    }
}