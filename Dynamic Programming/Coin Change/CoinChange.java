class Solution {
    public int coinChange(int[] coins, int amount) {
        Integer[][] memo = new Integer[coins.length][amount+1];
        int mininumCoins = findMinCoins(coins, amount, memo, 0);
        
        return (mininumCoins == Integer.MAX_VALUE) ? -1: mininumCoins;
    }
    
    public int findMinCoins(int[] coins, int amount, Integer[][] memo, int index){
        
        if(amount == 0)
            return 0;
        
        //If we are at the end, return MAX_VALUE bcz the question is asking for MIN coin change, returning zero won't work here as we are using Math.min in our solution
        if(index>=coins.length)
            return Integer.MAX_VALUE;
        
        //If we haven't calculated before
        if(memo[index][amount]==null){
            
            //take this variable with max value to track the outcome of our 1st recursive call -> result variable
            int coin1 = Integer.MAX_VALUE;
            if(coins[index]<=amount){
                int result = findMinCoins(coins, amount-coins[index], memo, index);
                
                //result will give 0 at one some point
                if(result<coin1)
                    coin1 = result+1; // plus one is to calculate the no.of coins we are adding one by one on every recursive call
            }
            
            
            //recursive call if current coin is not picked
            int coin2 = findMinCoins(coins, amount, memo, index+1);
            
            
            memo[index][amount] = Math.min(coin1, coin2);
        }
        
        return memo[index][amount];
    }
}