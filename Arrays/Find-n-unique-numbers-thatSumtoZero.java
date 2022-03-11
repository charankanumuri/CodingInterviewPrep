// Given an integer n, return any array containing n unique integers such that they add up to 0.
/*
Input: n = 5
Output: [-7,-1,1,3,4]
Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].

n will be atleast 2
*/

class Solution {
    public int[] sumZero(int n) {
        
        int[] result = new int[n];
        int sum=0;
        
        for(int i =0;i<n-1;i++){
            result[i] = i+1; //add numbers from 1 to n-1
            sum = sum + result[i]; //keep track of this sum for every number assignment
        }
        
        //for the last element of the array, add negative sum value, when we add all numbers of the array it will add up to zero.
        result[n-1] = -sum;
        
        return result;
    }
}