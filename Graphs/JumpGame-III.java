/**
 * Jump Game III
 * 
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array.
 *  When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.

Notice that you can not jump outside of the array at any time.

 

Example 1:

Input: arr = [4,2,3,0,3,1,2], start = 5
Output: true
Explanation: 
All possible ways to reach at index 3 with value 0 are: 
index 5 -> index 4 -> index 1 -> index 3 
index 5 -> index 6 -> index 4 -> index 1 -> index 3 
Example 2:

Input: arr = [4,2,3,0,3,1,2], start = 0
Output: true 
Explanation: 
One possible way to reach at index 3 with value 0 is: 
index 0 -> index 4 -> index 1 -> index 3
Example 3:

Input: arr = [3,0,2,1,2], start = 2
Output: false
Explanation: There is no way to reach at index 1 with value 0.
 

Constraints:

1 <= arr.length <= 5 * 104
0 <= arr[i] < arr.length
0 <= start < arr.length


T(n) = O(n)
S(n) = O(n)


We are following graph technique here, as every index in the array can jump +/- of  (index, arr[index])
We can maintain a relationship in the form of graph.

Followed BFS technique to solve the below
 * 
 */

class Solution {
    public boolean canReach(int[] arr, int start) {
        
        //if the given start is pointing to zero, return true
        if(arr[start]==0)
            return true;
        
        //keep track of visited
        boolean[] visited = new boolean[arr.length];
        
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(start);
        
        while(!queue.isEmpty()){
            
            if(arr[queue.peek()]==0)
                return true;
            
            Integer indexValue = queue.poll();
            
            //mark as visited
            visited[indexValue]=true;
            
            //make sure we dont cross the index of the array && we haven't visited this index
            if(indexValue+arr[indexValue]<arr.length && visited[indexValue+arr[indexValue]]==false)
                queue.add(indexValue+arr[indexValue]);
            
            if(indexValue-arr[indexValue]>=0 && visited[indexValue-arr[indexValue]]==false)
                queue.add(indexValue-arr[indexValue]);
        }
        
     return false;   
    }  
}