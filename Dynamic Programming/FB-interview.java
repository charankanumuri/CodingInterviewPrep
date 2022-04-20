// #Q1 Determine if any 3 integers in an array sum to 0.
// Examples: 
// [3, 5, 8, 10, -9, -11, 16, 2] => true // [3, 8, -11]
// [3, 5, 4, 9, -16, -10] => false
// [3, -3, 4, 8, -13] => false

/*
sort the array, so its sorted
[3, 5, 8, 10, -9, -11, 16, 2] -> after sorting -11 will be the 1st number.. so i have to find two numbers
that sums up to 11.. if we find a pair that sums to 11 from index-1 to index-n. if we find we return true

if not, we go for the next.

sorting - O(NLogN) + O(N^2) = O(N^2) time complexity

start pointer & end pointer.. sum[start+end values] and we can check whether its my target.. 


sum<target -> move my start pointer toward end
sum>target -> move my end pointer towards start
*/

/*
[3, 5, 8, 10, -9, -11, 16, 2]

sorted -> [-11,-9,2,3,5,8,10,16]

findTarget(11,1,nums)

start=1
end=7

sum=7
*/

class Solution{

    public boolean findTargetToZero(int[] nums){
  
        int length = nums.length;
        Arrays.sort(nums);
        for(int i=0; i<length; i++){
            if(findTarget(-nums[i],i+1, nums))
              return true;
        }
      return false;
    }
  
    public boolean findTarget(int target, int index, int[] nums){
        int start=index;
        int end = nums.length-1;
  
        while(start<end && start<nums.length){
          int sum = nums[start]+nums[end];
  
          if(sum + target == 0)
            return true;
  
          else if(sum<target)
            start++;
  
          else
            end--;
        }
    }
  }
  
  
  
  // #Q2
  // Input:
  // List of words: ["face", "book", "good", "bug"]
  // Board: 
  // [
  // "bkdu", 
  // "goob", 
  // "face"
  // ]
  
  // output: ["face", "book", "good"]
  
  /*
  
  Space Complexity: O(m*n) -> marking visited for a cell + recursive stack - O(m*n)
  Time Complexity: O(L)-> list of words * O(m*n) 
  */
  
  class Solution{
  
    List<String> wordsFound = new ArrayList<>();
    int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
  
    public List<String> findWordsOnGrid(char[][] grid, List<String> words){
  
        for(String word: words){
  
            String str = word;
            boolean[][] visitedCells= new boolean[grid.length][grid[0].length];
  
            for(int i=0;i<grid.length;i++){
                for(int j=0;j<grid[i].length;j++){
  
                  if(grid[i][j] == str.charAt(0)){
                      dfs(grid, i, j, visitedCells, str, 0);
                  }
                }
  
            }
  
        }
    }
  
    public void dfs(char[][] grid, int row, int col, boolean[][] visited, String word, int wordIndex){
      
      if(wordIndex == word.length){
        wordsFound.add(word);
        return;
      }
  
      visited[row][col]=true;
  
      for(int[] d: dirs){
        int x=row+d[0];
        int y=col+d[1];
  
        if(x>=0 && y>=0 && x<grid.length && y<grid[0].length && word.charAt(wordIndex+1 == grid[x][y]) && visited[x][y]==false)
        {
            dfs(grid, x,y,visited,word,wordIndex+1);
        }
      }
    }
  }  