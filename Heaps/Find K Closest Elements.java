/**
 * Find K Closest Elements
Medium

Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. 
The result should also be sorted in ascending order.

An integer a is closer to x than an integer b if:

|a - x| < |b - x|, or
|a - x| == |b - x| and a < b
 

Example 1:

Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]
Example 2:

Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]
 

Constraints:

1 <= k <= arr.length
1 <= arr.length <= 104
arr is sorted in ascending order.
-104 <= arr[i], x <= 104
 */

class Pair{
    int value;
    int diff;
    
    public Pair(int val, int d){
       this.value = val;
       this.diff = d;
    }
}

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        
        List<Integer> result = new ArrayList<>();
        
        //Going to solve this using min heaps
        //heap is based on x value from the parameter
        /*
            The comparator compares two pairs and checks if differences are same or not
            if it is same we take 1st pair as our top otherwise, we take the one with
            less difference at the top
        */
        PriorityQueue<Pair> minHeap= new PriorityQueue<Pair>(new Comparator<Pair>(){
                public int compare(Pair p1, Pair p2){
                    if(p1.diff!=p2.diff)
                        return p1.diff-p2.diff;
                    else
                        return p1.value-p2.value;
                }
            });
        
        //adding all values and its difference from x
        //ordering is automatically taken care by the comparator
        for(int i=0;i<arr.length;i++){
            minHeap.add(new Pair(arr[i], Math.abs(x-arr[i])));
        }
        
        //we remove pairs from the top and add it to result list upto k
        for(int i=0; i<k;i++)
            result.add(minHeap.poll().value);
        
        //we need to sort this bcz, our top element from heap was with min difference,
        //so it would be behind on the list, so sort it
        Collections.sort(result);
        
        return result;
    }
}