
/*
Suppose we have an unsorted log file of accesses to web resources. Each log entry consists of an access time, the ID of the user making the access, and the resource ID. 

The access time is represented as seconds since 00:00:00, and all times are assumed to be in the same day.

Example:
logs1 = [
    ["58523", "user_1", "resource_1"],
    ["62314", "user_2", "resource_2"],
    ["54001", "user_1", "resource_3"],
    ["200", "user_6", "resource_5"],    
    ["215", "user_6", "resource_4"],
    ["54060", "user_2", "resource_3"],
    ["53760", "user_3", "resource_3"],
    ["58522", "user_2", "resource_1"],
    ["53651", "user_5", "resource_3"],
    ["2", "user_6", "resource_1"],
    ["100", "user_6", "resource_6"],
    ["400", "user_7", "resource_2"],
    ["100", "user_8", "resource_6"],
    ["54359", "user_1", "resource_3"],
]

Example 2:
logs2 = [
    ["300", "user_1", "resource_3"],
    ["599", "user_1", "resource_3"],
    ["900", "user_1", "resource_3"],
    ["1199", "user_1", "resource_3"],
    ["1200", "user_1", "resource_3"],
    ["1201", "user_1", "resource_3"],
    ["1202", "user_1", "resource_3"]
]

Example 3:
logs3 = [
    ["300", "user_10", "resource_5"]
]

1.Write a function that return userId's earliest time and latest time from the logs

Expected Output:
for logs2-> { user_1: [300,1202]}
for logs3-> {user_10: [300,300]}
for logs1-> {
    user_1: [54001, 58523],
    user_2: [54060, 62314],
    user_3: [53760, 53760],
    user_5: [53651, 53651],
    user_6: [2, 215],
    user_7: [400, 400],
    user_8: [100, 100],
}

2.Write a function that takes the logs and returns the resource with the highest number of accesses in any 5 minute window, together with how many accesses it saw.

Expected Output:
most_requested_resource(logs1) # => ('resource_3', 3)
Reason: resource_3 is accessed at 53760, 54001, and 54060

most_requested_resource(logs2) # => ('resource_3', 4)
Reason: resource_3 is accessed at 1199, 1200, 1201, and 1202

most_requested_resource(logs3) # => ('resource_5', 1)
Reason: resource_5 is accessed at 300

Complexity analysis variables:

n: number of logs in the input
*/


import java.io.*;
import java.util.*;

public class Solution {
  public static void main(String[] argv) {
    String[][] logs1 = new String[][] {
        { "58523", "user_1", "resource_1" },
        { "62314", "user_2", "resource_2" },
        { "54001", "user_1", "resource_3" },
        { "200", "user_6", "resource_5" },
        { "215", "user_6", "resource_4" },
        { "54060", "user_2", "resource_3" },
        { "53760", "user_3", "resource_3" },
        { "58522", "user_22", "resource_1" },
        { "53651", "user_5", "resource_3" },
        { "2", "user_6", "resource_1" },
        { "100", "user_6", "resource_6" },
        { "400", "user_7", "resource_2" },
        { "100", "user_8", "resource_6" },
        { "54359", "user_1", "resource_3"},
    };

    String[][] logs2 = new String[][] {
        {"300", "user_1", "resource_3"},
        {"599", "user_1", "resource_3"},
        {"900", "user_1", "resource_3"},
        {"1199", "user_1", "resource_3"},
        {"1200", "user_1", "resource_3"},
        {"1201", "user_1", "resource_3"},
        {"1202", "user_1", "resource_3"}
    };

    //<300,599,900,1199,1200,1201,1202>
    //attempts=0;
    //maxFrequency = 1
    //i=1 ->599, 0->300 -> attempts=1
    //attempt=0 -> 1-> 2 -> 3 -> 4


    String[][] logs3 = new String[][] {
        {"300", "user_10", "resource_5"}
    };

    System.out.println(userSession(logs1));
    System.out.println(userSession(logs2));
    System.out.println(userSession(logs3));

  }

  public static HashMap<String, List<Integer>> userSession(String[][] logs){
      HashMap<String, List<Integer>> map = new HashMap<>();

      int n=logs.length;

      for(int i=0;i<n;i++){
        String time = logs[i][0];
        String userId = logs[i][1];

        if(!map.containsKey(userId)){

          ArrayList<Integer> timeList = new ArrayList<>();
          timeList.add(Integer.parseInt(time));
          timeList.add(Integer.parseInt(time));

          map.put(userId, timeList);
        }
        else{
          
          List<Integer> timeList = map.get(userId);

          if(Integer.parseInt(time)<timeList.get(0))
          {
            timeList.set(0, Integer.parseInt(time));
          }
          if(Integer.parseInt(time)>timeList.get(1))
          {
            timeList.set(1, Integer.parseInt(time));
          }

          map.put(userId, timeList);
        }
      }

      return map;
  }
}
