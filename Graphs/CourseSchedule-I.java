/*
Course Schedule
Medium

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 

Constraints:

1 <= numCourses <= 105
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.

Strategy:
Planning to use Topological sort here.

I will have graph setup in a form of adj list
I will a list which will be my final order list
I will maintain an indegree setup for every vertex

I will have a queue, where i will add vertex to the queue if the indegree of that vertex is 0.
Then i will remove that node, add it to my order list and explore its child, for every child, i will decrement ingree of that vertex
and if the updated indegree is 0 after decrementing, i will add it to my queue

After queue becomes empty, i will check whether my order list is equal to N -> courses here
*/

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        List<Integer> orderList = new ArrayList<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree =new HashMap<>();
        
        Queue<Integer> queue = new LinkedList<>();
        
        //initializing graph and indegree for every node
        for(int i=0;i<numCourses;i++){
            graph.put(i,new ArrayList<Integer>());
            indegree.put(i,0);
        }
        
        //creating graph and setting indegree for every node(course in this scenario)
        for(int i=0;i<prerequisites.length;i++){
            Integer parent = prerequisites[i][0];
            Integer child = prerequisites[i][1];
            
            //child depends on parent, (i.e) to complete child course, i need to complete parent course first
            graph.get(parent).add(child);
            indegree.put(child, indegree.get(child)+1);
        }
        
        //for vertex where indegree is 0, thats going to be my source, add it to my queue
        for(int i=0;i<numCourses;i++){
            if(indegree.get(i)==0)
                queue.add(i);
        }
        
        
        //Explore possible options
        while(!queue.isEmpty()){
            Integer node = queue.poll();
            
            //add courses that should be taken in order
            orderList.add(node);
            
            //as we completed one course, now decrement indegree of its children
            for(Integer n: graph.get(node)){
                indegree.put(n, indegree.get(n)-1);
                
                //if any child indegree becomes zero that means we can take that course
                //add it to queue
                if(indegree.get(n)==0)
                    queue.add(n);
            }   
        }
        
        //after completion of the queue traversal, see whether we completed all courses
        return (orderList.size() == numCourses);
    }
}