/*

Question: Pow(x, n)

Example:

x->2
n->10

2 power 10 -> can be written as (2 power 5) power 2.    //conditon 2
2 power 5 can be written as (2 power 2) power 2 later multiplied by (2) separately      //conditon 1
2 power 2 can be written as (2 power 1) power 2         //conditon 1
2 power 1 can be written as (2 power 0) later multiplied by (2) separately //conditon 2

We can see n decreases on each recursive call.

Base base is when n->0, we return 1

conditon 1: so when N is even, we return (x pow n) * (x pow n)  --> n here is smaller than the original n from the question
conditon 2: when N is odd, we return (x pow n) * (x pow n) * x  --> n here is smaller than the original n from the question
*/

class Solution {
    public double myPow(double x, int n) {
        
        //base conditions
        if(x==0)
            return 0;
        if(n==0)
            return 1.0;
        
        
        //if n is negative, we know mathematically, x power -n can be represented as (1/x) power n
        if(n<0){
            x = 1/x;
            n = -n;
        }
        
        return calculatePow(x, n);
    }
    
    public double calculatePow(double x, int N){
        
        if(N==0)
            return 1.0;
        
        double value = calculatePow(x,N/2);
        
        //once value is computed and N is even, we dont need an extra x to be multiplied
        if(N%2 == 0)
            return value * value;
        
        //for odd cases, we need extra x to be mutiplied so satisfy when N is odd
        else
            return value * value * x;
    }
}