/**
 * Given an integer numRows, return the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

Example 1:

Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
Example 2:

Input: numRows = 1
Output: [[1]]
 

Constraints:

1 <= numRows <= 30
 */

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascal = new ArrayList<>();
        List<Integer> row1 = new ArrayList<>();
        row1.add(1);
        
        //initial 1st row
        pascal.add(row1);
        
        for(int i=1;i<numRows;i++){
            List<Integer> newRow = new ArrayList<>();
            List<Integer> prevRow = pascal.get(i-1);
            
            //first row value is always 1
            newRow.add(1);
            
            for(int j=1;j<i;j++){

                //col values are calculated between 2 col values(current and previous cols) of the previous row
                newRow.add(prevRow.get(j-1)+prevRow.get(j));
            }
            
            //last row value is always one
            newRow.add(1);
            
            pascal.add(newRow);
        }
        
        return pascal;
    }
}