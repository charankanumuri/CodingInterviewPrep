class Solution {
    public int climbStairs(int n) {
        
       int[] memo = new int[n+1];
       return CalculateClimbStairs(memo, n);
    }
    
    public int CalculateClimbStairs(int[] memo, int n){
         if(n<=0)
            return 0;
        //if we are 1step away we have 1 way to go
        if(n==1)
            return 1;
        //if there are 2steps we have 2 ways to go
        if(n==2)
            return 2;
        
        //Fibonacci pattern
        if(memo[n]==0){
            memo[n] = CalculateClimbStairs(memo, n-1) + CalculateClimbStairs(memo, n-2);
        }
        
        return memo[n];
    }
}