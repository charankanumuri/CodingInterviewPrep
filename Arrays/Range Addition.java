/**
 * Range Addition
Medium

You are given an integer length and an array updates where updates[i] = [startIdxi, endIdxi, inci].

You have an array arr of length length with all zeros, and you have some operation to apply on arr. 
In the ith operation, you should increment all the elements arr[startIdxi], arr[startIdxi + 1], ..., arr[endIdxi] by inci.

Return arr after applying all the updates.

 

Example 1:


Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
Output: [-2,0,3,5,3]
Example 2:

Input: length = 10, updates = [[2,4,6],[5,6,8],[1,9,-4]]
Output: [0,-4,2,2,2,4,4,-4,-4,-4]
 

Constraints:

1 <= length <= 105
0 <= updates.length <= 104
0 <= startIdxi <= endIdxi < length
-1000 <= inci <= 1000
 */

class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] answer = new int[length];
        
        for(int i=0;i<updates.length;i++){
            
            int first = updates[i][0];
            int second = updates[i][1];
            int value = updates[i][2];
            
            //sum up the value to first index
            answer[first]+=value;
            
            //check whether we have a place after second index
            //if yes, mark it with sumation of negative value
            if(second+1<length)
                answer[second+1]-=value;
        }
        
        //loop all elements and perform prefix sum operation
        for(int i=1;i<length;i++){
            answer[i] = answer[i]+answer[i-1];
        }
        
        return answer;
    }
}