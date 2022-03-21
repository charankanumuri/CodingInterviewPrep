/**
 * There are n cities. Some of them are connected, while some are not.
 *  If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1
 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.

 

Example 1:


Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2
Example 2:


Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3
 

Constraints:

1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] is 1 or 0.
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]

T(n)=O(n^2) ->main function with a for loop and dfs has a for loop upto N
S(n)=O(n) ->visited array

 */

class Solution {
    public int findCircleNum(int[][] isConnected) {
        
        //base case
        if(isConnected.length==0)
            return 0;
        
        //keep track of visited
        boolean[] visited = new boolean[isConnected.length];
        
        int noOfprovinces=0;


        for(int i=0;i<isConnected.length;i++){

            //if place is not visited, its a start of the province
            if(visited[i]==false){
                noOfprovinces++; //increment the province
                dfs(isConnected, visited,i);
            }
        }
        
        return noOfprovinces;
    }
    
    public void dfs(int[][] isConnected, boolean[] visited, int node){
        
        //here we are going to traverse in 1D fashion, 
        //given node is the row value and we are gonna scan the columns
        //if we find 1, then row and column are connected(cities in this case), we consider them only if they are not visited
        for(int i=0;i<isConnected.length;i++){
            if(isConnected[node][i]==1 && visited[i]==false)
            {
                visited[i]=true; //mark as visited for the column
                dfs(isConnected, visited, i); //pass the column on the dfs function
            }
        }
    }
}