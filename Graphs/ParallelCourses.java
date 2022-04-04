/**
 * Parallel Courses
Medium

You are given an integer n, which indicates that there are n courses labeled from 1 to n. 
You are also given an array relations where relations[i] = [prevCoursei, nextCoursei], 
representing a prerequisite relationship between course prevCoursei and course nextCoursei: course prevCoursei has to be taken before course nextCoursei.

In one semester, you can take any number of courses as long as you have taken all the prerequisites in the previous semester 
for the courses you are taking.

Return the minimum number of semesters needed to take all courses. If there is no way to take all the courses, return -1.

 

Example 1:


Input: n = 3, relations = [[1,3],[2,3]]
Output: 2
Explanation: The figure above represents the given graph.
In the first semester, you can take courses 1 and 2.
In the second semester, you can take course 3.
Example 2:


Input: n = 3, relations = [[1,2],[2,3],[3,1]]
Output: -1
Explanation: No course can be studied because they are prerequisites of each other.
 

Constraints:

1 <= n <= 5000
1 <= relations.length <= 5000
relations[i].length == 2
1 <= prevCoursei, nextCoursei <= n
prevCoursei != nextCoursei
All the pairs [prevCoursei, nextCoursei] are unique.
 */

class Solution {
    public int minimumSemesters(int n, int[][] relations) {
        
        int[] indegree = new int[n+1];
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int NoOfSemester=0;
        
        //initialize graph upto n, because we have courses from 1 to n
        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }
        
        //add adj list and indgree for each course
        for(int i=0;i<relations.length;i++){
            graph.get(relations[i][0]).add(relations[i][1]);
            indegree[relations[i][1]]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        
        //Add courses to the queue where they can be completed 1st 
        for(int i=1;i<=n;i++){
            if(indegree[i]==0)
                queue.add(i);
        }
        
        int courseCompleted = 0;
        
        //BFS traversal
        while(!queue.isEmpty()){
            
            int size = queue.size();
            
            //maintain count of semester here to count the semester and see 
            //how many courses i can complete in the upcoming for loop
            NoOfSemester++;
            
            //complete the courses and search for other ones if indegree becomes 0 for that course
            //add it to the queue
            for(int i=0;i<size;i++){
                Integer node = queue.poll();
                courseCompleted++;
                for(Integer child: graph.get(node)){
                    indegree[child]--;
                    
                    if(indegree[child]==0)
                        queue.add(child);
                }
            }
        }
        
        return courseCompleted == n? NoOfSemester: -1;
    }
}