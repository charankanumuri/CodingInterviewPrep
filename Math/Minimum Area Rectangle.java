/**
 * Minimum Area Rectangle
Medium

You are given an array of points in the X-Y plane points where points[i] = [xi, yi].

Return the minimum area of a rectangle formed from these points, with sides parallel to the X and Y axes. 
If there is not any such rectangle, return 0.

 

Example 1:


Input: points = [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4
Example 2:


Input: points = [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2
 */

class Solution {
    public int minAreaRect(int[][] points) {

        //store all combination of points(x,y) x -> y's
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        
        for(int[] p: points){
            if(!map.containsKey(p[0]))
                map.put(p[0], new HashSet<>());
            
            map.get(p[0]).add(p[1]);
        }
        
        
        int area = Integer.MAX_VALUE;
        
        //we know the are of rectangle is length * breadth
        for(int i=0;i<points.length;i++){
            for(int j=i+1;j<points.length;j++){
                int[] p1 = points[i];
                int[] p2 = points[j];
                
                //between two points (x1,y1) (x2,y2), if any one of x1=x2 or y1=y2, lets move to another point
                if(p1[0] == p2[0] || p1[1] == p2[1])
                    continue;
                
                //now check x1 has y2 and x2 has y1 mapping
                if(map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])){
                    area = Math.min(area, Math.abs(p1[0]-p2[0]) * Math.abs(p2[1]-p1[1]));
                }
            }
        }
        
        return area == Integer.MAX_VALUE? 0: area;
    }
}