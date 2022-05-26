/**
 * Race Car
Hard

Your car starts at position 0 and speed +1 on an infinite number line. Your car can go into negative positions. 
Your car drives automatically according to a sequence of instructions 'A' (accelerate) and 'R' (reverse):

When you get an instruction 'A', your car does the following:
position += speed
speed *= 2
When you get an instruction 'R', your car does the following:
If your speed is positive then speed = -1
otherwise speed = 1
Your position stays the same.
For example, after commands "AAR", your car goes to positions 0 --> 1 --> 3 --> 3, and your speed goes to 1 --> 2 --> 4 --> -1.

Given a target position target, return the length of the shortest sequence of instructions to get there.

 

Example 1:

Input: target = 3
Output: 2
Explanation: 
The shortest instruction sequence is "AA".
Your position goes from 0 --> 1 --> 3.
Example 2:

Input: target = 6
Output: 5
Explanation: 
The shortest instruction sequence is "AAARA".
Your position goes from 0 --> 1 --> 3 --> 7 --> 7 --> 6.
 */


//Positions we generate are basically node and speed is like an edge connection
//from one position to another

class Solution {
    public int racecar(int target) {
        
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        HashSet<Pair<Integer, Integer>> visited = new HashSet<>();
        
        //pos, speed
        queue.add(new Pair(0,1));
        visited.add(queue.peek());
        int instructions = 0;
        
        while(!queue.isEmpty()){
            int size = queue.size();
            
            for(int i=0;i<size;i++){
                Pair<Integer, Integer> node = queue.poll();
                int position = node.getKey();
                int speed = node.getValue();
                
                //once we hit target, return instructions count
                if(position == target)
                    return instructions;
                
                //we have 2 possibilities
                //accelerate
                helper(queue, target, visited, speed*2, position+speed);

                //reverse
                helper(queue, target, visited, speed>0? -1: 1, position);
            }
            
            //increase instruction
            instructions++;
        }
        
        return -1;
    }
    
    public void helper(Queue<Pair<Integer, Integer>> queue, int target, 
                       HashSet<Pair<Integer, Integer>> set, int speed, int position){
        
        Pair<Integer, Integer> node = new Pair<>(position, speed);
        
        //we aren't supposed to go reverse from position 0, if we do, it will
        //only increase instructions, so we make sure we are >= 0 position
        
        //we might go beyond target, but we can reverse and come back to target
        if(position>=0 && position<=2*target && !set.contains(node)){
            set.add(node);
            queue.add(node);
        }
    }
}