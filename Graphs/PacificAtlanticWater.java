/**
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. 
 * The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.

The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] 
represents the height above sea level of the cell at coordinate (r, c).

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, 
and west if the neighboring cell's height is less than or equal to the current cell's height. 
Water can flow from any cell adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) 
to both the Pacific and Atlantic oceans.

 

Example 1:


Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
Example 2:

Input: heights = [[2,1],[1,2]]
Output: [[0,0],[0,1],[1,0],[1,1]]
 */

class Solution {
    
    //directions 
    int[][] dirs = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};
    
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        
        //result set
        List<List<Integer>> pacificAtlantic = new ArrayList<>();
        
        if(heights == null || heights.length == 0)
            return pacificAtlantic;
        
        //we are maintaining two queues for atlantic and pacific areas
        LinkedList<int[]> pacificQueue = new LinkedList<>();
        LinkedList<int[]> atlanticQueue = new LinkedList<>();
        
        //iterate till length of row, and add all possible
        //atlantic and pacific cells as per problem description
        //only corners are addded to their respective queues
        for(int i =0;i<heights.length;i++){
            pacificQueue.add(new int[]{i,0});
            atlanticQueue.add(new int[]{i, heights[0].length-1});
        }
        
        //iterate column wise and do the same
         for(int i =0;i<heights[0].length;i++){
            pacificQueue.add(new int[]{0,i});
            atlanticQueue.add(new int[]{heights.length-1, i});
        }
        
        //we maintain two array to make sure visited is different for both oceans
        boolean[][] pacific = new boolean[heights.length][heights[0].length];
        boolean[][] atlantic = new boolean[heights.length][heights[0].length];
        
        //call BFS for pacific and atlantic
        BFS(pacificQueue, pacific, heights);
        BFS(atlanticQueue, atlantic, heights);
        
        
        //here we are going over both boolean and finding common ones and adding to result
        for(int i=0;i<heights.length;i++){
            for(int j=0;j<heights[0].length;j++){
                if(pacific[i][j] && atlantic[i][j])
                    pacificAtlantic.add(List.of(i,j));
            }
        }
        
        return pacificAtlantic;
    }
    
    public void BFS(LinkedList<int[]> queue, boolean[][] visited, int[][] heights){
        
        //We know, the water from given cell will pass when the 
        //neighbouring cell is equal or less in height
        //We are doing opposite here, bcz we start from corners on both BFS calls
        
        while(!queue.isEmpty()){
            int[] indexs = queue.poll();
            
            //mark it as visited
            visited[indexs[0]][indexs[1]]=true;
            
            for(int[] d: dirs){
                int x = indexs[0]+d[0];
                int y = indexs[1]+d[1];
                
                //if it goes out of bound, continue to next iteration
                if(x<0 || x>=heights.length || y<0 || y>=heights[0].length)
                    continue;
                
                //if already visited, continue to next iteration
                if(visited[x][y])
                    continue;
                
                //as we are starting from corner and exploring
                //we compare if my old cell value is less than or equal to new (x,y) cell
                //if true we add them to queue
                if(heights[indexs[0]][indexs[1]] <= heights[x][y])
                    queue.add(new int[]{x,y});
            }
        }
    }
}