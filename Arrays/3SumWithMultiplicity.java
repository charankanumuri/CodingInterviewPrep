/**
 * 3Sum With Multiplicity
Medium

Given an integer array arr, and an integer target, return the number of tuples i, j, k such that i < j < k and arr[i] + arr[j] + arr[k] == target.

As the answer can be very large, return it modulo 109 + 7.

 

Example 1:

Input: arr = [1,1,2,2,3,3,4,4,5,5], target = 8
Output: 20
Explanation: 
Enumerating by the values (arr[i], arr[j], arr[k]):
(1, 2, 5) occurs 8 times;
(1, 3, 4) occurs 8 times;
(2, 2, 4) occurs 2 times;
(2, 3, 3) occurs 2 times.
Example 2:

Input: arr = [1,1,2,2,2,2], target = 5
Output: 12
Explanation: 
arr[i] = 1, arr[j] = arr[k] = 2 occurs 12 times:
We choose one 1 from [1,1] in 2 ways,
and two 2s from [2,2,2,2] in 6 ways.
 

Constraints:

3 <= arr.length <= 3000
0 <= arr[i] <= 100
0 <= target <= 300
 */

class Solution {
    public int threeSumMulti(int[] arr, int target) {
        
        //answer is long bcz we may cross beyond int value range limit
        long answer = 0;
        
        //mod is used here as per question to avoid answer overflow
        long mod = 1000000007;
        Arrays.sort(arr);
        
        //We are using 3 pointer technique here, take 1st element subtract with target and
        //do a two pointer approach to find the triplet with new target to find (target-arr[i]) 
        
        for(int i=0;i<arr.length;i++){
            
            //value to find after subtracting
            int findValue = target - arr[i];
            
            //we started with i, so our range should start from i+1
            int j = i+1;
            int k = arr.length-1;
            
            while(j<k){
                
                //move pointers based on sum value
                if(arr[j]+arr[k]<findValue)
                    j++;
                
                else if(arr[j]+arr[k]>findValue)
                    k--;
                
                //if sum equals to target, check whether both values are same or not
                else if(arr[j]!=arr[k]){
                    
                    //if not, lets start counting whether there are duplicates of a[j] && a[k]
                    int leftNumCount = 1, rightNumCount=1;
                    
                    
                    //count leftNum a[j]
                    while(j+1<k && arr[j]==arr[j+1]){
                        leftNumCount++;
                        j++;
                    }
                    
                    ////count rightNum a[k]
                    while(k-1>j && arr[k]==arr[k-1]){
                        rightNumCount++;
                        k--;
                    }
                    
                    //calculate the answer and find mod of it as per question
                    answer = answer + (rightNumCount * leftNumCount);
                    answer = answer%mod;
                    
                    //move both pointers
                    j++;
                    k--;
                }
                
                //if both values are same, available combination should be
                // n*(n+1)/2
                // here n is (k-j)
                else
                {
                    answer = answer + (k-j)*(k-j+1)/2;
                    answer = answer%mod;
                    break;
                }
            }
        }
        
        return (int) answer;
    }
}