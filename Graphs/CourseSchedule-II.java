/**
 * Course Schedule II
Medium

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. 
If it is impossible to finish all courses, return an empty array.

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. 
Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
Example 3:

Input: numCourses = 1, prerequisites = []
Output: [0]
 

Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= numCourses * (numCourses - 1)
prerequisites[i].length == 2
0 <= ai, bi < numCourses
ai != bi
All the pairs [ai, bi] are distinct.
 */

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        
        //output order array
        int[] courseOrder = new int[numCourses];
        
        //if there are no courses, return empty array
        if(numCourses == 0)
            return new int[]{};
        
        //if there are no pre reqs, return array with courses
        if(prerequisites.length==0 || prerequisites==null)
        {
            for(int i=0;i<numCourses;i++)
                courseOrder[i]=i;
            
            return courseOrder;
        }
        
        //indegree array of all courses
        int[] indegree = new int[numCourses];
        
        //the 1st value here represents the pre req
        for(int[] pre: prerequisites){
            indegree[pre[0]]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        
        //find all indegrees with 0, add it to queue
        for(int i=0;i<numCourses;i++){
            if(indegree[i]==0)
                queue.add(i);
        }
        
        //if there are no indegrees of 0, then queue will be empty and we cannot do any course
        if(queue.isEmpty())
            return new int[]{};
        
        //have this index for ordering the courses in the output array
        int index=0;


        while(!queue.isEmpty()){
            Integer node = queue.poll();
            courseOrder[index]=node;    //add to output array and increase the index for next order 
            index++;
            
            //check if this course was a pre req for other courses and decrease their indegree
            for(int[] pre: prerequisites){
                if(pre[1]==node){
                    indegree[pre[0]]--;
                    
                    //if the new indegree is 0 for other course, add it to queue
                    if(indegree[pre[0]]==0)
                        queue.add(pre[0]);
                }
            }
        }
        

        //after going over the queue, we must be left with 0 indegree for all vertices/courses
        //if not return empty array, means we didn't complete all courses
        for(int i=0;i<numCourses;i++){
            if(indegree[i]!=0)
                return new int[]{};
        }
        
        return courseOrder;
    }
}